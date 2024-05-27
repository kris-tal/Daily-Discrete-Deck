package dailydescretedeck.set.views;

import dailydescretedeck.set.models.Board;
import dailydescretedeck.set.models.Calendar;
import dailydescretedeck.set.models.Card;
import dailydescretedeck.set.services.End;
import dailydescretedeck.set.services.SetCollector;
import dailydescretedeck.set.viewmodels.MenuViewModel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static java.lang.Double.min;
import java.time.LocalDate;
import javafx.util.Duration;

public class BoardView extends Pane {
    private Board board;
    private double gap;
    private List<Card> selectedCards = new ArrayList<>();
    private boolean confirm = false;
    private Map<Card, CardView> cardViews = new HashMap<>();
    private SetCollector setCollector;
    private static  long startTime = System.currentTimeMillis();
    private static Timeline timeline;
    private Stage stage;
    private static boolean bylo = false;

    public BoardView(Board board, Stage stage) {
        this.board = board;
        this.stage = stage;
        if (setCollector == null) { 
            setCollector = SetCollector.getInstance();
        }
        redrawBoard();

        widthProperty().addListener((observable, oldValue, newValue) -> redrawBoard());
        heightProperty().addListener((observable, oldValue, newValue) -> redrawBoard());
    }

    public void redrawBoard() {
        getChildren().clear();
        if(bylo)
        {
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

        double bigRectX = (paneWidth - bigRectWidth) / 2;
        double bigRectY = (paneHeight - bigRectHeight) / 2;

        Rectangle bigRect = new Rectangle(bigRectX, bigRectY, bigRectWidth, bigRectHeight);
        bigRect.setFill(Color.THISTLE);
        getChildren().add(bigRect);

        gap = square / 5;

        double cardWidth = square * 2;
        double cardHeight = square * 3;

        double startX = bigRectX + gap;
        double startY = bigRectY + gap / 2;

        int numberCards = board.getDeck().size() + board.getCards().size();
        Font font = new Font("System", gap * 1.8);

        Label timeLabel = new Label();
        timeLabel.setFont(font);
        timeLabel.setLayoutX(gap);
        timeLabel.setLayoutY(gap * 7); 
        getChildren().add(timeLabel);

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

        Label cardsLeftLabel = new Label("cards left: " + numberCards + "/63");
        cardsLeftLabel.setStyle("-fx-strikethrough: true; -fx-text-fill: #746174;");
        cardsLeftLabel.setFont(font);
        cardsLeftLabel.setLayoutX(gap);
        cardsLeftLabel.setLayoutY(gap);
        getChildren().add(cardsLeftLabel);



        Label collectedSetsLabel = new Label("collected SETs: " + board.getNumberSets());
        collectedSetsLabel.setStyle("-fx-strikethrough: true; -fx-text-fill: #746174;");
        collectedSetsLabel.setFont(font);
        collectedSetsLabel.setLayoutX(gap);
        collectedSetsLabel.setLayoutY(gap * 4);
        getChildren().add(collectedSetsLabel);

        int cardIndex = 0;

        for (int row = 0; row < 2; row++) {
            double rowY = startY + row * (cardHeight + gap);
            startX += row * square;

            for (int col = 0; col < 4; col++) {
                if (cardIndex >= board.getCards().size()) {
                    break;
                }

                Card card = board.getCards().get(cardIndex++);

                CardView cardView = new CardView(card, 0, 0, square / 60);
                cardViews.put(card, cardView);
                Card Card = (Card) card;
                cardView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    if (selectedCards.contains(Card)) {
                        selectedCards.remove(Card);
                    } else {
                        selectedCards.add(Card);
                    }
                });
                StackPane cardPane = new StackPane();
                cardPane.getChildren().add(cardView);

                cardPane.setLayoutX(startX + col * (cardWidth + gap));
                cardPane.setLayoutY(rowY);

                getChildren().add(cardPane);
            }
        }

        Button surrenderButton = new Button("surrender");
        Button confirmButton = new Button("confirm");
        Button cancelButton = new Button("cancel");
        Button xorButton = new Button("XOR");
        Button menuButton = new Button("Menu");

        Pane buttonsPane = new Pane();
        buttonsPane.getChildren().addAll(surrenderButton, confirmButton, cancelButton);
        buttonsPane.setPrefWidth(bigRectWidth);
        buttonsPane.setLayoutX(bigRectX);
        buttonsPane.setLayoutY(bigRectY + bigRectHeight + gap);

        double buttonWidth = (bigRectWidth - 40) / 3;
        double buttonHeight = bigRectHeight / 10;

        surrenderButton.setLayoutX(10);
        surrenderButton.setLayoutY(0);
        surrenderButton.setPrefWidth(buttonWidth);
        surrenderButton.setPrefHeight(buttonHeight);
        surrenderButton.setFont(Font.font("System", gap * 1.8));
        surrenderButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");

        confirmButton.setLayoutX(20 + buttonWidth);
        confirmButton.setLayoutY(0);
        confirmButton.setPrefWidth(buttonWidth);
        confirmButton.setPrefHeight(buttonHeight);
        confirmButton.setFont(Font.font("System", gap * 1.8));
        confirmButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");

        cancelButton.setLayoutX(30 + 2 * buttonWidth);
        cancelButton.setLayoutY(0);
        cancelButton.setPrefWidth(buttonWidth);
        cancelButton.setPrefHeight(buttonHeight);
        cancelButton.setFont(Font.font("System", gap * 1.8));
        cancelButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");

        xorButton.setLayoutX(paneWidth -( buttonWidth / 2 - gap) /4);
        xorButton.setLayoutY(gap);
        xorButton.setPrefWidth(buttonWidth / 2);
        xorButton.setPrefHeight(buttonHeight);
        xorButton.setFont(Font.font("System", gap * 1.6));
        xorButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");

        menuButton.setLayoutX(getWidth() - buttonWidth + 50);
        menuButton.setLayoutY(getHeight() - buttonHeight);
        menuButton.setPrefWidth(buttonWidth * 0.5);
        menuButton.setPrefHeight(buttonHeight* 0.5);
        menuButton.setFont(Font.font("System", gap * 1));
        menuButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");
        surrenderButton.setOnAction(event -> {
            if (timeline != null) {
                timeline.stop();
            }

            System.out.println("Kliknięto w przycisk Surrender");
            selectedCards.clear();
            selectedCards = board.getNotSet();

            for(Card card : selectedCards) {
                CardView cardView = cardViews.get(card);
                cardView.selectNotSelected();
            }
            CardView.disableCards();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Koniec gry");
            alert.setHeaderText(null);
            alert.setContentText("Przegrałeś!");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
        });

        confirmButton.setOnAction(event -> {
            System.out.println("Kliknięto w przycisk Confirm");
            System.out.println("Wybrane karty: " + setCollector.getSets());  
            if(board.isSetOk(selectedCards))
            {
                SetCollector setCollector = SetCollector.getInstance();
                setCollector.addSets(1);
                System.out.println("Zapisano ilość zebranych SETów: " + setCollector.getSets());
        
                Map<LocalDate, Integer> setsMap = Calendar.getSetsMap();
                setsMap.put(LocalDate.now(), setCollector.getSets());
                Calendar.setSetsMap(setsMap);
        
                board.removeCards(selectedCards);
                System.out.println("Znaleziono SET");
                if(board.getCards().isEmpty()){
                    if (timeline != null) {
                        timeline.stop();
                    }
                    End.getInstance().addEnds(1);
                    Map<LocalDate, Integer> endsMap = Calendar.getEndsMap();
                    endsMap.put(LocalDate.now(), End.getInstance().getEnds());
                     Calendar.setEndsMap(endsMap);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Koniec gry");
                    alert.setHeaderText(null);
                    alert.setContentText("Wygrałeś!");
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.showAndWait();
                }
                BoardView newBoardView = new BoardView(board, stage);
                StackPane parent = (StackPane) getParent();
                parent.getChildren().remove(this);
                parent.getChildren().add(newBoardView);
                CardView.enableCards();
                selectedCards.clear();
            }
            else {
                System.out.println("Nie znaleziono SET");
                for(Card card : board.getCards()) {
                    CardView cardView = cardViews.get(card);
                    cardView.unclick();
                }
                CardView.enableCards();
                selectedCards.clear();
            }
        });

        cancelButton.setOnAction(event -> {
            System.out.println("Kliknięto w przycisk Cancel");
            for(Card card : board.getCards()) {
                CardView cardView = cardViews.get(card);
                cardView.unclick();
            }
            CardView.enableCards();
            selectedCards.clear();
        });

        xorButton.setOnAction(event -> {
            System.out.println("Kliknięto w przycisk XOR");
            Card card = board.Xor((ArrayList<Card>) selectedCards);
            CardView cardView = new CardView(card, 0, 0, square/(2 * 60));
            cardView.disableThisCard();
            StackPane cardPane = new StackPane();
            cardPane.getChildren().add(cardView);
            cardPane.setLayoutX(paneWidth - buttonWidth / 2 - gap);
            cardPane.setLayoutY(gap + buttonHeight + gap);
            getChildren().add(cardPane);
        });
        menuButton.setOnAction(event -> {
            if (timeline != null) {
                timeline.stop();
            }
            bylo = true;
            System.out.println("Kliknięto w przycisk Menu");
            MenuViewModel menuViewModel = new MenuViewModel();
        MenuView menuView = new MenuView(menuViewModel, stage);
        Scene scene = new Scene(menuView, 1000, 800);
        scene.getRoot().setStyle("-fx-background-color: thistle;");
        stage.setScene(scene);
        stage.setTitle("Set");
        stage.show();

        menuView.display();
        });

        getChildren().add(buttonsPane);
        getChildren().add(xorButton);
        getChildren().add(menuButton);
    }

}
