package dailydescretedeck.set.models;

import java.util.*;

import dailydescretedeck.set.services.Feature;

public class CardManager {
    private Board board;

    public CardManager(Board board) {
        this.board = board;
    }

    public Map<Dots, Integer> prepareCount(List<Card> cards) {
        Map<Dots, Integer> map = new HashMap<>();
        for (Card card : cards) {
            for (Dots dot : card.getFields()) {
                map.put(dot, map.getOrDefault(dot, 0) + 1);
            }
        }
        return map;
    }

    public Card xor(List<Card> cards) {
        Map<Dots, Integer> map = prepareCount(cards);
        List<Dots> numbers = new ArrayList<>();
        for (Map.Entry<Dots, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 != 0) {
                numbers.add(entry.getKey());
            }
        }
        return new Card(new ArrayList<>(numbers));
    }

    public boolean isSetOk(List<Card> cards) {
        Map<Dots, Integer> map = prepareCount(cards);
        boolean notZero = false;
        for (Integer value : map.values()) {
            if (value.intValue() % 2 != 0) {
                return false;
            }
            if(value.intValue() != 0) notZero = true;
        }
        if(!notZero) return false;
        board.incrementSets();
        return true;
    }

    public List<Card> getSet() {
        List<List<Card>> combinations = new ArrayList<>();
        Feature.fill(combinations, new ArrayList<>(), board.getCards(), 0);
        for (List<Card> combination : combinations) {
            if (isSetOk(combination) && combination.size() == 3) {
                return combination;
            }
        }
        return null;
    }

    public List<Card> getNotSet() {
        List<Card> set = getSet();
        List<Card> notSet = new ArrayList<>(board.getCards());
        if (set != null) {
            notSet.removeAll(set);
        }
        return notSet;
    }

    public boolean removeCards(List<Card> selectedCards) {
        for (Card c : selectedCards) {
            int index = board.getCards().indexOf(c);
            if (index != -1) {
                board.getCards().remove(c);
                Card newCard = board.getDeck().drawCard();
                if (newCard != null) {
                    if (newCard.getFields().size() != 0) board.getCards().add(index, newCard);
                    else {
                        newCard = board.getDeck().drawCard();
                        board.getCards().add(index, newCard);
                    }
                }
            }
        }
        if (board.getCards().isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }
}
