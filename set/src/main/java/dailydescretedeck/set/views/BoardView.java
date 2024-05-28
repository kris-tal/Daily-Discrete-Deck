package dailydescretedeck.set.views;

import dailydescretedeck.set.models.Card;
import dailydescretedeck.set.viewmodels.BoardViewModel;
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
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Double.min;

public class BoardView extends Pane {
    private BoardViewModel viewModel;
    private double gap;
    private ObservableList<Card> selectedCards = FXCollections.observableArrayList();
    private Map<Card, CardView> cardViews = new HashMap<>();
    private Runnable onBackToMenu;

    public BoardView(BoardViewModel viewModel, Runnable onBackToMenu) {
        this.viewModel = viewModel;
        this.onBackToMenu = onBackToMenu;
        redrawBoard();

        widthProperty().addListener((observable, oldValue, newValue) -> redrawBoard());
        heightProperty().addListener((observable, oldValue, newValue) -> redrawBoard());
    }

    private void redrawBoard() {
        getChildren().clear();

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

        int numberCards = viewModel.cardsProperty().size();
        Font font = new Font("Comic Sans MS", gap * 2);

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

        Button button1 = new Button("Surrender");
        Button button2 = new Button("Confirm");
        Button button3 = new Button("Cancel");
        Button button4 = new Button("XOR");
        Button backButton = new Button("Back to Menu");

        Pane buttonsPane = new Pane();
        buttonsPane.getChildren().addAll(button1, button2, button3, button4, backButton);
        buttonsPane.setPrefWidth(bigRectWidth);
        buttonsPane.setLayoutX(bigRectX);
        buttonsPane.setLayoutY(bigRectY + bigRectHeight + gap);

        double buttonWidth = (bigRectWidth - 50) / 5;
        double buttonHeight = bigRectHeight / 10;

        button1.setLayoutX(10);
        button1.setLayoutY(0);
        button1.setPrefWidth(buttonWidth);
        button1.setPrefHeight(buttonHeight);
        button1.setFont(Font.font("System", gap * 1.8));
        button1.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");

        button2.setLayoutX(20 + buttonWidth);
        button2.setLayoutY(0);
        button2.setPrefWidth(buttonWidth);
        button2.setPrefHeight(buttonHeight);
        button2.setFont(Font.font("System", gap * 1.8));
        button2.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");

        button3.setLayoutX(30 + 2 * buttonWidth);
        button3.setLayoutY(0);
        button3.setPrefWidth(buttonWidth);
        button3.setPrefHeight(buttonHeight);
        button3.setFont(Font.font("System", gap * 1.8));
        button3.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");

        button4.setLayoutX(40 + 3 * buttonWidth);
        button4.setLayoutY(0);
        button4.setPrefWidth(buttonWidth);
        button4.setPrefHeight(buttonHeight);
        button4.setFont(Font.font("System", gap * 1.8));
        button4.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");

        backButton.setLayoutX(50 + 4 * buttonWidth);
        backButton.setLayoutY(0);
        backButton.setPrefWidth(buttonWidth);
        backButton.setPrefHeight(buttonHeight);
        backButton.setFont(Font.font("System", gap * 1.8));
        backButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");
        backButton.setOnAction(event -> onBackToMenu.run());

        button1.setOnAction(event -> {
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

        button2.setOnAction(event -> {
            if (viewModel.isSetOk(selectedCards)) {
                boolean ok = viewModel.removeCards(selectedCards);
                if(!ok){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Koniec gry");
                    alert.setHeaderText(null);
                    alert.setContentText("Wygrałeś!");
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.showAndWait();
                }

                redrawBoard();
                CardView.enableCards();
                selectedCards.clear();
            } else {
            }
        });

        button3.setOnAction(event -> {
            for (Card card : viewModel.cardsProperty()) {
                CardView cardView = cardViews.get(card);
                cardView.unclick();
            }
            CardView.enableCards();
            selectedCards.clear();
        });

        button4.setOnAction(event -> {
            Card card = viewModel.getXor(selectedCards);
            CardView cardView = new CardView(card, 0, 0, square / 60);
            cardView.disableThisCard();
            StackPane cardPane = new StackPane();
            cardPane.getChildren().add(cardView);
            cardPane.setLayoutX(bigRectX + bigRectWidth + gap);
            cardPane.setLayoutY(gap + buttonHeight + gap);
            getChildren().add(cardPane);
        });

        getChildren().add(buttonsPane);
    }
}
