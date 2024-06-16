package dailydescretedeck.set.views;

import dailydescretedeck.set.FileManagement.End;
import dailydescretedeck.set.FileManagement.Money;
import dailydescretedeck.set.FileManagement.SavingService;
import dailydescretedeck.set.FileManagement.SetCollector;
import dailydescretedeck.set.FileManagement.TheBestTime;
import dailydescretedeck.set.models.Calendar;
import dailydescretedeck.set.models.Card;
import dailydescretedeck.set.viewmodels.BoardViewModel;
import dailydescretedeck.set.viewmodels.Scenes;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Modality;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.lang.Double.min;
import java.time.LocalDate;
import javafx.util.Duration;

public class BoardView extends VBox {
    private BoardViewModel boardViewModel;
    private double gap;
    private ObservableList<Card> selectedCards = FXCollections.observableArrayList();
    private Map<Card, CardView> cardViews = new HashMap<>();
    private static long startTime = System.currentTimeMillis();
    private static Timeline timeline;
    private static boolean bylo = false;
    private Scenes scenes;
    private Money money;
    private boolean showSet;
    private HBox xorCardBox;
    private Label timeLabel;
    private Label label1;
    private Label label2;
    private HBox buttonsBox;

    public BoardView(BoardViewModel boardViewModel) {
        this.boardViewModel = boardViewModel;
        this.scenes = new Scenes();
        this.money = new Money();
        this.showSet = false;

        initializeComponents();
        display();

        widthProperty().addListener((observable, oldValue, newValue) -> display());
        heightProperty().addListener((observable, oldValue, newValue) -> display());
    }

    private void initializeComponents() {
        setStyle("-fx-background-color: thistle;");

        if (bylo) {
            startTime = System.currentTimeMillis();
            bylo = false;
        }

        gap = 20;

        timeLabel = new Label();
        timeLabel.setFont(new Font("System", gap * 2));

        Runnable updateTime = () -> {
            long time = System.currentTimeMillis() - startTime;
            String timeString = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(time),
                    TimeUnit.MILLISECONDS.toSeconds(time) % 60);
            timeLabel.setText("time: " + timeString);
        };

        updateTime.run();

        Platform.runLater(() -> {
            timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateTime.run()));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        });

        label1 = new Label();
        label1.setStyle("-fx-strikethrough: true; -fx-text-fill: #746174;");
        label1.setFont(new Font("System", gap * 2));

        label2 = new Label();
        label2.setStyle("-fx-strikethrough: true; -fx-text-fill: #746174;");
        label2.setFont(new Font("System", gap * 2));

        HBox labelsBox = new HBox(gap);
        labelsBox.getChildren().addAll(label1, label2, timeLabel);
        labelsBox.setAlignment(javafx.geometry.Pos.CENTER);

        Button surrenderButton = new MyButton("Surrender");
        Button confirmButton = new MyButton("Confirm");
        Button cancelButton = new MyButton("Cancel");
        Button xorButton = new MyButton("XOR");
        Button backButton = new MyButton("Exit");
        Button shuffleButton = new MyButton("Shuffle");
        Button showButton = new MyButton("Show SET");

        buttonsBox = new HBox(gap);
        buttonsBox.setAlignment(javafx.geometry.Pos.CENTER);
        buttonsBox.getChildren().addAll(surrenderButton, confirmButton, cancelButton, shuffleButton, showButton, xorButton, backButton);

        VBox mainContainer = new VBox(gap);
        mainContainer.setAlignment(javafx.geometry.Pos.CENTER);

        xorCardBox = new HBox();
        xorCardBox.setPrefSize(0, 0);
        xorCardBox.setStyle("-fx-border-color: THISTLE; -fx-border-width: 2; -fx-background-color: THISTLE;");
        xorCardBox.setAlignment(javafx.geometry.Pos.CENTER);

        mainContainer.getChildren().addAll(labelsBox, buttonsBox, xorCardBox);
        getChildren().add(mainContainer);

        surrenderButton.setOnAction(event -> handleSurrender());
        confirmButton.setOnAction(event -> handleConfirm());
        cancelButton.setOnAction(event -> handleCancel());
        xorButton.setOnAction(event -> handleXor());
        backButton.setOnAction(event -> handleBack());
        shuffleButton.setOnAction(event -> handleShuffle());
        showButton.setOnAction(event -> handleShow());
    }

    private void display() {
        label1.setText("Cards left: " + boardViewModel.leftCards() + "/63");
        label2.setText("Collected SETs: " + boardViewModel.getBoard().getNumberSets());

        VBox boardContainer = new VBox();
        boardContainer.setSpacing(gap);
        boardContainer.setAlignment(javafx.geometry.Pos.CENTER);

        double paneWidth = getWidth();
        double paneHeight = getHeight();

        if (paneWidth == 0 || paneHeight == 0) {
            return;
        }

        double square = min(paneWidth, paneHeight) * 0.09;
        double cardWidth = square * 2;
        double cardHeight = square * 3;

        VBox cardsBox = new VBox(gap);
        cardsBox.setAlignment(javafx.geometry.Pos.CENTER);

        int cardIndex = 0;
        for (int row = 0; row < 2; row++) {
            HBox rowBox = new HBox(gap);
            rowBox.setAlignment(javafx.geometry.Pos.CENTER);
            for (int col = 0; col < 4; col++) {
                if (cardIndex >= boardViewModel.cardsProperty().size()) {
                    break;
                }
                Card card = boardViewModel.cardsProperty().get(cardIndex++);
                CardView cardView = new CardView(card, 0, 0, square / 60);
                cardViews.put(card, cardView);
                cardView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> handleCardClick(card));
                rowBox.getChildren().add(cardView);
            }
            cardsBox.getChildren().add(rowBox);
        }

        boardContainer.getChildren().add(cardsBox);

        if (getChildren().size() > 1) {
            getChildren().remove(1);
        }
        getChildren().add(1, boardContainer);
    }

    private void handleCardClick(Card card) {
        if (selectedCards.contains(card)) {
            selectedCards.remove(card);
        } else {
            selectedCards.add(card);
        }
    }

    private void handleShuffle() {
        boardViewModel.shuffleCards();
        display();
    }

    private void handleShow() {
        selectedCards.clear();
        selectedCards.addAll(boardViewModel.getSet());
        showSet = true;
        for (Card card : selectedCards) {
            CardView cardView = cardViews.get(card);
            cardView.clicked();
            cardView.selectNotSelected();
        }
    }

    private void handleBack() {
        if (timeline != null) {
            timeline.stop();
        }
        CardView.enableCards();
        bylo = true;
        scenes.showMenuView();
    }

    private void handleSurrender() {
        if (timeline != null) {
            timeline.stop();
        }

        selectedCards.clear();
        selectedCards.addAll(boardViewModel.getNotSet());

        for (Card card : selectedCards) {
            CardView cardView = cardViews.get(card);
            cardView.selectNotSelected();
        }
        CardView.disableCards();
        disableButtons(true);

        AestheticAlert.showAlert("Game Over", "You lost!");
    }

    private void handleConfirm() {
        if (boardViewModel.isSetOk(selectedCards, true)) {
            SetCollector setCollector = SetCollector.getInstance();
            End end = End.getInstance();
            TheBestTime theBestTime = TheBestTime.getInstance();
            if (SavingService.loadDateFromFile("saves/Date.txt") == null) {
                SavingService.saveDateToFile("saves/Date.txt", LocalDate.now());
                setCollector.resetsSets();
                end.resetEnds();
                theBestTime.resetTime();
            } else if (!SavingService.loadDateFromFile("saves/Date.txt").equals(LocalDate.now())) {
                SavingService.saveDateToFile("saves/Date.txt", LocalDate.now());
                setCollector.resetsSets();
                end.resetEnds();
                theBestTime.resetTime();
                money.addMoney(5);
            }

            boolean ok = boardViewModel.removeCards(selectedCards);
            if (!showSet) {
                money.addMoney(1);
                setCollector.addSets(1);
            } else {
                showSet = false;
            }

            Map<LocalDate, Long> setsMap = SavingService.loadMapFromFile("saves/setsMap.txt");
            setsMap.put(LocalDate.now(), setCollector.getSets());
            SavingService.saveMapToFile("saves/setsMap.txt", setsMap);

            if (!ok) {
                if (timeline != null) {
                    timeline.stop();
                }
                long t = SavingService.loadNumberFromFile("saves/theBestTime.txt");
                long timeNow = System.currentTimeMillis() - startTime;
                if (t == 0 || t > timeNow) {
                    theBestTime.newTime(timeNow);
                    SavingService.saveNumberToFile("saves/theBestTime.txt", timeNow);
                    money.addMoney(10);
                }
                end.addEnds(1);
                money.addMoney(end.getEnds());

                disableButtons(true);

                AestheticAlert.showAlert("Game over", "You won!");
                return;
            }

            BoardView newBoardView = new BoardView(boardViewModel);
            StackPane parent = (StackPane) getParent();
            parent.getChildren().remove(this);
            parent.getChildren().add(newBoardView);
            CardView.enableCards();
            selectedCards.clear();
        } else {
            for (Card card : boardViewModel.cardsProperty()) {
                CardView cardView = cardViews.get(card);
                cardView.unclick();
            }
            CardView.enableCards();
            selectedCards.clear();
        }
    }

    private void handleCancel() {
        for (Card card : boardViewModel.cardsProperty()) {
            CardView cardView = cardViews.get(card);
            cardView.unclick();
        }
        CardView.enableCards();
        selectedCards.clear();
    }

    private void handleXor() {
        xorCardBox.getChildren().clear();
        Card card = boardViewModel.getXor(selectedCards);
        CardView cardView = new CardView(card, 0, 0, 0.5);
        cardView.disableThisCard();
        xorCardBox.getChildren().add(cardView);
    }

    private void disableButtons(boolean disable) {
        for (Node node : buttonsBox.getChildren()) {
            if (node instanceof Button && !"Exit".equals(((Button) node).getText())) {
                ((Button) node).setDisable(disable);
            }
        }
    }
}
