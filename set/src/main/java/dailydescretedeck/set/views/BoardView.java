package dailydescretedeck.set.views;

import dailydescretedeck.set.models.Board;
import dailydescretedeck.set.models.Card;
import dailydescretedeck.set.models.Deck;
import dailydescretedeck.set.views.CardView;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;

import static java.lang.Double.min;

public class BoardView extends Pane {
    private Board board;
    private double gap;
    private List<Card> selectedCards = new ArrayList<>();
    private boolean confirm = false;
    private Map<Card, CardView> cardViews = new HashMap<>();


    public BoardView(Board board) {
        this.board = board;
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

        int numberCards = board.getDeck().size() + board.getCards().size();
        Font font = new Font("Comic Sans MS", gap * 2);

        Label label1 = new Label("Cards left: " + numberCards + "/63");
        label1.setStyle("-fx-strikethrough: true; -fx-text-fill: #746174;");
        label1.setFont(font);
        label1.setLayoutX(gap);
        label1.setLayoutY(gap);
        getChildren().add(label1);


        Label label2 = new Label("Collected SETs: " + board.getnNumberSets());
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

        Button button1 = new Button("surrender");
        Button button2 = new Button("confirm");
        Button button3 = new Button("cancel");
        Button button4 = new Button("XOR");

        Pane buttonsPane = new Pane();
        buttonsPane.getChildren().addAll(button1, button2, button3);
        buttonsPane.setPrefWidth(bigRectWidth);
        buttonsPane.setLayoutX(bigRectX);
        buttonsPane.setLayoutY(bigRectY + bigRectHeight + gap);

        double buttonWidth = (bigRectWidth - 40) / 3;
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

        button4.setLayoutX(paneWidth - buttonWidth / 2 - gap);
        button4.setLayoutY(gap);
        button4.setPrefWidth(buttonWidth / 2);
        button4.setPrefHeight(buttonHeight);
        button4.setFont(Font.font("System", gap * 1.6));
        button4.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");

        button1.setOnAction(event -> {
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

        button2.setOnAction(event -> {
            System.out.println("Kliknięto w przycisk Confirm");
            if(board.isSetOk(selectedCards))
            {
                board.removeCards(selectedCards);
                System.out.println("Znaleziono SET");
                BoardView newBoardView = new BoardView(board);
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

        button3.setOnAction(event -> {
            System.out.println("Kliknięto w przycisk Cancel");
            for(Card card : board.getCards()) {
                CardView cardView = cardViews.get(card);
                cardView.unclick();
            }
            CardView.enableCards();
            selectedCards.clear();
        });

        button4.setOnAction(event -> {
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

        getChildren().add(buttonsPane);
        getChildren().add(button4);
    }

}
