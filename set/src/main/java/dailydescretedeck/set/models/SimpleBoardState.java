package dailydescretedeck.set.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleBoardState  implements BoardState{
    private Deck deck;
    private Board board;

    public SimpleBoardState(Deck deck, Board board) {
        this.deck = deck;
        this.board = board;
        deck.shuffle();
    }

    @Override
    public void addCards(List<Card> card) {
        board.drawCardFromDeck(deck);
    }

    @Override
    public void removeCards(List<Card> card) {
        for(Card c : card) {
            board.removeCard(c);
        }
        board.drawCardFromDeck(deck);
    }

    @Override
    public Iterable<CardState> getCards() {
        return board.getCardsOnBoard();
    }

    @Override
    public void clear() {
        this.deck = new Deck();
        this.board = new Board();
        deck.shuffle();
    }

    @Override
    public void refresh() {
        board.drawCardFromDeck(deck);
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public Card Xor(List<Card> cards)
    {
        Map<Dots, Integer> map = preparationToCount(cards);
        List<Dots> numbers = new ArrayList<>();
        for(Map.Entry<Dots, Integer> entry : map.entrySet())
        {
            if(entry.getValue() % 2 != 0)
            {
                numbers.add(entry.getKey());
            }
        }
        return new Card(numbers);
    }

    @Override
    public boolean isSetOk(List<Card> cards) {
        Map<Dots, Integer> map = preparationToCount(cards);

        for (Integer value : map.values()) {
            if (value % 2 != 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public  Map<Dots, Integer> preparationToCount(List<Card> cards)
    {
        Map<Dots, Integer> map = new HashMap<>();
        for (Card card : cards) {
            for (int i = 0; i < card.getFields().size(); i++) {
                if (map.containsKey(card.getFields().get(i))) {
                    map.put(card.getFields().get(i), map.get(card.getFields().get(i)) + 1);
                } else {
                    map.put(card.getFields().get(i), 1);
                }
            }
        }
        return map;
    }
    
}
