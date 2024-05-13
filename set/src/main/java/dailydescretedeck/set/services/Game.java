package dailydescretedeck.set.services;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dailydescretedeck.set.models.Board;
import dailydescretedeck.set.models.BoardState;
import dailydescretedeck.set.models.Deck;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import dailydescretedeck.set.models.Card;
import dailydescretedeck.set.models.Dots;
import dailydescretedeck.set.models.SimpleBoardState;

public class Game {
    private BoardState boardState;
    private List<Card> selectedCards;

    public Game() {
        boardState = new SimpleBoardState(new Deck(), new Board());
        selectedCards = new ArrayList<>();
    }
    
    public void startGame() {
<<<<<<< HEAD
        deck = new Deck();
        board = new Board();
        board.drawCardFromDeck(deck);
=======
        boardState.clear();
    }
    
    public boolean checkWinConditions(List<Card> cards) {
        return boardState.isSetOk(cards);
>>>>>>> d8f95d3 (the big merge)
    }

    public void connectCardToGui(Card card, ImageView cardImageView) {
        cardImageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedCards.add(card);
            }
        });
    }
    public void Confirm(Button confirmButton) {
        confirmButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Move();
            }
        });
    }
}
