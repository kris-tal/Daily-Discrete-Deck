package dailydescretedeck.set.services;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dailydescretedeck.set.models.Board;
import dailydescretedeck.set.models.Deck;
import dailydescretedeck.set.models.Card;

public class Game {
    private Deck deck;
    private Board board;

    public Game() {
        deck = new Deck();
        board = new Board();
    }
    public void startGame() {
        deck = new Deck();
        board = new Board();
        board.drawCardFromDeck(deck);
    }

    public  Map<Integer, Integer> preparationToCount(List<Card> cards)
    {
        Map<Integer, Integer> map = new HashMap<>();
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
    public boolean isSetOk(List<Card> cards) {
        Map<Integer, Integer> map = preparationToCount(cards);

        for (Integer value : map.values()) {
            if (value % 2 != 0) {
                return false;
            }
        }
        return true;
    }
    public Card Xor(List<Card> cards)
    {
        Map<Integer, Integer> map = preparationToCount(cards);
        List<Integer> numbers = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : map.entrySet())
        {
            if(entry.getValue() % 2 != 0)
            {
                numbers.add(entry.getKey());
            }
        }
        return new Card(numbers);
    }

    public void FoundSet(List<Card> cards)
    {
        for(Card card : cards)
        {
            board.removeCard(card);
        }
        board.drawCardFromDeck(deck);
    }
}
