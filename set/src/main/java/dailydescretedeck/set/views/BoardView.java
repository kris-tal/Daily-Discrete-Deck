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

    public BoardView(BoardViewModel boardViewModel) {
        this.boardViewModel = boardViewModel;
        this.scenes = new Scenes();
        this.money = new Money();
        this.showSet = false;
        display();

        widthProperty().addListener((observable, oldValue, newValue) -> display());
        heightProperty().addListener((observable, oldValue, newValue) -> display());
    }

    private void display() {
        getChildren().clear();

        setStyle("-fx-background-color: thistle;");

        if (bylo) {
            startTime = System.currentTimeMillis();
            bylo = false;
        }

        double paneWidth = getWidth();
        double paneHeight = getHeight();

        if (paneWidth == 0 || paneHeight == 0) {
            return;
        }

        double square = min(paneWidth, paneHeight) * 0.09;
        double bigRectWidth = square * 9;
        double bigRectHeight = square * 7;

        gap = square / 5;

        double cardWidth = square * 2;
        double cardHeight = square * 3;

        VBox boardContainer = new VBox();
        boardContainer.setSpacing(gap);
        boardContainer.setPrefWidth(paneWidth);
        boardContainer.setPrefHeight(bigRectHeight);
        boardContainer.setStyle("-fx-background-color: thistle;");
        boardContainer.setAlignment(javafx.geometry.Pos.CENTER);

        Label timeLabel = new Label();
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

        Label label1 = new Label("Cards left: " + boardViewModel.leftCards() + "/63");
        label1.setStyle("-fx-strikethrough: true; -fx-text-fill: #746174;");
        label1.setFont(new Font("System", gap * 2));

        Label label2 = new Label("Collected SETs: " + boardViewModel.getBoard().getNumberSets());
        label2.setStyle("-fx-strikethrough: true; -fx-text-fill: #746174;");
        label2.setFont(new Font("System", gap * 2));

        HBox labelsBox = new HBox(gap);
        labelsBox.getChildren().addAll(label1, label2, timeLabel);
        labelsBox.setAlignment(javafx.geometry.Pos.CENTER);

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
                cardView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    if (selectedCards.contains(card)) {
                        selectedCards.remove(card);
                    }
                    else {
                        selectedCards.add(card);
                    }
                });
                rowBox.getChildren().add(cardView);
            }
            cardsBox.getChildren().add(rowBox);
        }

        boardContainer.getChildren().addAll(labelsBox, cardsBox);

        Button surrenderButton = new MyButton("Surrender");
        Button confirmButton = new MyButton("Confirm");
        Button cancelButton = new MyButton("Cancel");
        Button xorButton = new MyButton("XOR");
        Button backButton = new MyButton("Exit");
        Button shuffleButton = new MyButton("Shuffle");
        Button showButton = new MyButton("Show SET");

        HBox buttonsBox = new HBox(gap);
        buttonsBox.setAlignment(javafx.geometry.Pos.CENTER);
        buttonsBox.getChildren().addAll(surrenderButton, confirmButton, cancelButton, shuffleButton, showButton, xorButton, backButton);

        VBox mainContainer = new VBox(gap);
        mainContainer.setAlignment(javafx.geometry.Pos.CENTER);
        mainContainer.getChildren().addAll(boardContainer, buttonsBox);

        getChildren().add(mainContainer);

        shuffleButton.setOnAction(event -> {
            boardViewModel.shuffleCards();
            BoardView newBoardView = new BoardView(boardViewModel);
            StackPane parent = (StackPane) getParent();
            parent.getChildren().add(newBoardView);
        });

        showButton.setOnAction(event -> {
            selectedCards.clear();
            selectedCards.addAll(boardViewModel.getSet());
            showSet = true;
            for (Card card : selectedCards) {
                CardView cardView = cardViews.get(card);
                cardView.clicked();
                cardView.selectNotSelected();
            }
        });

        backButton.setOnAction(event -> {
            if (timeline != null) {
                timeline.stop();
            }
            CardView.enableCards();
            bylo = true;
            scenes.showMenuView();
        });

        surrenderButton.setOnAction(event -> {
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
            confirmButton.setDisable(true);
            showButton.setDisable(true);
            surrenderButton.setDisable(true);
            cancelButton.setDisable(true);
            xorButton.setDisable(true);
            shuffleButton.setDisable(true);

            AestheticAlert.showAlert("Game Over", "You lost!");
        });

        confirmButton.setOnAction(event -> {
            if (boardViewModel.isSetOk(selectedCards, true)) {
                SetCollector setCollector = SetCollector.getInstance();
                End end = End.getInstance();
                TheBestTime theBestTime = TheBestTime.getInstance();
                if (SavingService.loadDateFromFile("saves/Date.txt") == null) {
                    SavingService.saveDateToFile("saves/Date.txt", LocalDate.now());
                    setCollector.resetsSets();
                    end.resetEnds();
                    theBestTime.resetTime();
                }
                else if (!SavingService.loadDateFromFile("saves/Date.txt").equals(LocalDate.now())) {
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
                }
                else {
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

                    confirmButton.setDisable(true);
                    showButton.setDisable(true);
                    surrenderButton.setDisable(true);
                    cancelButton.setDisable(true);
                    xorButton.setDisable(true);
                    shuffleButton.setDisable(true);

                    AestheticAlert.showAlert("Game over", "You won!");
                    return;
                }

                BoardView newBoardView = new BoardView(boardViewModel);
                StackPane parent = (StackPane) getParent();
                parent.getChildren().remove(this);
                parent.getChildren().add(newBoardView);
                CardView.enableCards();
                selectedCards.clear();
            }
            else {
                for (Card card : boardViewModel.cardsProperty()) {
                    CardView cardView = cardViews.get(card);
                    cardView.unclick();
                }
                CardView.enableCards();
                selectedCards.clear();
            }
        });

        cancelButton.setOnAction(event -> {
            for (Card card : boardViewModel.cardsProperty()) {
                CardView cardView = cardViews.get(card);
                cardView.unclick();
            }
            CardView.enableCards();
            selectedCards.clear();
        });

        xorButton.setOnAction(event -> {
            Card card = boardViewModel.getXor(selectedCards);
            CardView cardView = new CardView(card, 0, 0, square / (2 * 60));
            cardView.disableThisCard();
            StackPane cardPane = new StackPane();
            cardPane.getChildren().add(cardView);
            cardPane.setLayoutX(paneWidth - square / 2 - gap);
            cardPane.setLayoutY(gap + square / 2 + gap);
            getChildren().add(cardPane);
        });
    }
}
