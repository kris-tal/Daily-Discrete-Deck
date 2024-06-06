package dailydescretedeck.set.views;

import dailydescretedeck.set.models.Calendar;
import dailydescretedeck.set.models.Card;
import dailydescretedeck.set.services.End;
import dailydescretedeck.set.services.SetCollector;
import dailydescretedeck.set.viewmodels.BoardViewModel;
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

public class BoardView extends Pane {
    private BoardViewModel viewModel;
    private double gap;
    private ObservableList<Card> selectedCards = FXCollections.observableArrayList();
    private Map<Card, CardView> cardViews = new HashMap<>();
    private Runnable onBackToMenu;
    private static  long startTime = System.currentTimeMillis();
    private static Timeline timeline;
    private static boolean bylo = false;

    public BoardView(BoardViewModel viewModel, Runnable onBackToMenu) {
        this.viewModel = viewModel;
        this.onBackToMenu = onBackToMenu;
        redrawBoard();

        widthProperty().addListener((observable, oldValue, newValue) -> redrawBoard());
        heightProperty().addListener((observable, oldValue, newValue) -> redrawBoard());
    }

    private void redrawBoard() {
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

        int numberCards = viewModel.leftCards();
        Font font = new Font("Comic Sans MS", gap * 2);

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

        Label label1 = new Label("Cards left: " + numberCards + "/63");
        label1.setStyle("-fx-strikethrough: true; -fx-text-fill: #746174;");
        label1.setFont(font);
        label1.setLayoutX(gap);
        label1.setLayoutY(gap);
        getChildren().add(label1);


        Label label2 = new Label("Collected SETs: " + viewModel.getBoard().getNumberSets());
        label2.setStyle("-fx-strikethrough: true; -fx-text-fill: #746174;");
        label2.setFont(font);
        label2.setLayoutX(gap);
        label2.setLayoutY(gap * 4);
        getChildren().add(label2);

        int cardIndex = 0;

        for (int row = 0; row < 2; row++) {
            double rowY = startY + row * (cardHeight + gap);
            startX += row * square;

            for (int col = 0; col < 4; col++) {
                if (cardIndex >= viewModel.cardsProperty().size()) {
                    break;
                }

                Card card = viewModel.cardsProperty().get(cardIndex++);

                CardView cardView = new CardView(card, 0, 0, square / 60);
                cardViews.put(card, cardView);
                cardView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    if (selectedCards.contains(card)) {
                        selectedCards.remove(card);
                    } else {
                        selectedCards.add(card);
                    }
                });
                StackPane cardPane = new StackPane();
                cardPane.getChildren().add(cardView);

                cardPane.setLayoutX(startX + col * (cardWidth + gap));
                cardPane.setLayoutY(rowY);

                getChildren().add(cardPane);
            }
        }

        Button surrenderButton = new Button("Surrender");
        Button confirmButton = new Button("Confirm");
        Button cancelButton = new Button("Cancel");
        Button xorButton = new Button("XOR");
        Button backButton = new Button("Back to Menu");

        Pane buttonsPane = new Pane();
        buttonsPane.getChildren().addAll(surrenderButton, confirmButton, cancelButton, xorButton, backButton);
        buttonsPane.setPrefWidth(bigRectWidth);
        buttonsPane.setLayoutX(bigRectX);
        buttonsPane.setLayoutY(bigRectY + bigRectHeight + gap);

        double buttonWidth = (bigRectWidth - 50) / 5;
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

        xorButton.setLayoutX(paneWidth - buttonWidth / 2 - gap);
        xorButton.setLayoutY(gap);
        xorButton.setPrefWidth(buttonWidth / 2);
        xorButton.setPrefHeight(buttonHeight);
        xorButton.setFont(Font.font("System", gap * 1.6));
        xorButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");

        backButton.setLayoutX(paneWidth - buttonWidth - gap); 
        backButton.setLayoutY(paneHeight - buttonHeight - gap); 
        backButton.setPrefWidth(buttonWidth);
        backButton.setPrefHeight(buttonHeight);
        backButton.setFont(Font.font("System", gap * 1.8));
        backButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");
        backButton.setOnAction(event ->{
            if (timeline != null) {
                timeline.stop();
            }
            bylo = true;
            onBackToMenu.run();
        } );

        surrenderButton.setOnAction(event -> {
            if (timeline != null) {
                timeline.stop();
            }

            selectedCards.clear();
            selectedCards.addAll(viewModel.getNotSet());

            for (Card card : selectedCards) {
                CardView cardView = cardViews.get(card);
                cardView.selectNotSelected();
            }
            CardView.disableCards();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText(null);
            alert.setContentText("You lost!");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
        });

        confirmButton.setOnAction(event -> {
            if (viewModel.isSetOk(selectedCards)) {
                boolean ok = viewModel.removeCards(selectedCards);
                SetCollector setCollector = SetCollector.getInstance();
                setCollector.addSets(1);
                System.out.println("Zapisano ilość zebranych SETów: " + setCollector.getSets());

                Map<LocalDate, Integer> setsMap = Calendar.getSetsMap();
                setsMap.put(LocalDate.now(), setCollector.getSets());
                Calendar.setSetsMap(setsMap);
        
                System.out.println("Znaleziono SET");
                if(!ok){
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

                BoardView newBoardView = new BoardView(viewModel, onBackToMenu);
                StackPane parent = (StackPane) getParent();
                parent.getChildren().remove(this);
                parent.getChildren().add(newBoardView);
                CardView.enableCards();
                selectedCards.clear();
            } else {
                System.out.println("Nie znaleziono SET");
                for(Card card : viewModel.cardsProperty()) {
                    CardView cardView = cardViews.get(card);
                    cardView.unclick();
                }
                CardView.enableCards();
                selectedCards.clear();
            }
        });

        cancelButton.setOnAction(event -> {
            for (Card card : viewModel.cardsProperty()) {
                CardView cardView = cardViews.get(card);
                cardView.unclick();
            }
            CardView.enableCards();
            selectedCards.clear();
        });

        xorButton.setOnAction(event -> {
            Card card = viewModel.getXor(selectedCards);
            CardView cardView = new CardView(card, 0, 0, square/(2 * 60));
            cardView.disableThisCard();
            StackPane cardPane = new StackPane();
            cardPane.getChildren().add(cardView);
            cardPane.setLayoutX(paneWidth - buttonWidth / 2 - gap);
            cardPane.setLayoutY(gap + buttonHeight + gap);
            getChildren().add(cardPane);
        });

        getChildren().add(buttonsPane);
        getChildren().add(xorButton);
        getChildren().add(backButton);
    }
}
