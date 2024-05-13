package dailydescretedeck.view;


import dailydescretedeck.set.models.Board;
import dailydescretedeck.set.models.Card;
import dailydescretedeck.set.views.CardView;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.input.MouseEvent;

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

        gap = square/5;

        double cardWidth = square * 2;
        double cardHeight = square * 3;

        double startX = bigRectX + gap;
        double startY = bigRectY + gap / 2;

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
        double buttonHeight = bigRectHeight/12;

        button1.setLayoutX(10);
        button1.setLayoutY(0);
        button1.setPrefWidth(buttonWidth);
        button1.setPrefHeight(buttonHeight);
        button1.setStyle("-fx-background-color: #EBE3EB; -fx-text-fill: #746174; -fx-font-weight: bold;");

        button2.setLayoutX(20 + buttonWidth);
        button2.setLayoutY(0);
        button2.setPrefWidth(buttonWidth);
        button2.setPrefHeight(buttonHeight);
        button2.setStyle("-fx-background-color: #EBE3EB; -fx-text-fill: BLACK; -fx-font-weight: bold;");

        button3.setLayoutX(30 + 2 * buttonWidth);
        button3.setLayoutY(0);
        button3.setPrefWidth(buttonWidth);
        button3.setPrefHeight(buttonHeight);
        button3.setStyle("-fx-background-color: #EBE3EB; -fx-text-fill: #746174; -fx-font-weight: bold; -fx-background-radius: 40;");

        button4.setLayoutX(paneWidth - buttonWidth / 2 - gap);
        button4.setLayoutY(gap);
        button4.setPrefWidth(buttonWidth / 2);
        button4.setPrefHeight(buttonHeight);
        button4.setStyle("-fx-background-color: #b06bff; -fx-text-fill: white; -fx-font-weight: bold;");

        button1.setOnAction(event -> {
            System.out.println("Kliknięto w przycisk Surrender");
            /*BoardView newBoardView = new BoardView(board);
            StackPane parent = (StackPane) getParent();
            parent.getChildren().remove(this);
            parent.getChildren().add(newBoardView);*/
            selectedCards.clear();
            selectedCards = board.getSet();

            for(Card card : selectedCards) {
                CardView cardView = cardViews.get(card);
                cardView.select();
            }
            CardView.disableCards();
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
