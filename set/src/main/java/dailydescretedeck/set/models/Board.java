package dailydescretedeck.set.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Card> cardsOnBoard;

    public Board() {
        cardsOnBoard = new ArrayList<>();
    }

    public void drawCardFromDeck(Deck deck) {
        while(cardsOnBoard.size() < 6 && deck.size() > 0){
            Card card = deck.drawCard();
            cardsOnBoard.add(card);
        } 
    }

    public void removeCard(Card card) {

        cardsOnBoard.remove(card);
    }

    public List<Card> getCardsOnBoard() {

        return cardsOnBoard;
    }
}
