package dailydescretedeck.set.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        List<List<Dots>> combinations = generateCombinations();
        for (List<Dots> combination : combinations) {
            cards.add(new Card(new ArrayList<>(combination)));
        }
        shuffle();
    }

    private List<List<Dots>> generateCombinations() {
        List<List<Dots>> combinations = new ArrayList<>();
        List<Dots> dots = Arrays.asList(Dots.values());
        fillCombinations(combinations, new ArrayList<>(), dots, 0);
        return combinations;
    }

    private void fillCombinations(List<List<Dots>> combinations, List<Dots> current, List<Dots> dots, int start) {
        if (!current.isEmpty()) combinations.add(new ArrayList<>(current));
        for (int i = start; i < dots.size(); i++) {
            current.add(dots.get(i));
            fillCombinations(combinations, current, dots, i + 1);
            current.remove(current.size() - 1);
        }
    }

    public void shuffle() {
        Collections.shuffle(cards, new Random(System.currentTimeMillis()));
    }

    public int size() {
        return cards.size();
    }

    public Card drawCard() {
        if (!cards.isEmpty()) {
            Card card = cards.remove(cards.size() - 1);

            if(card.getFields().size() != 0) return card;
            else return drawCard();
        } else {
            return null;
        }
        return null;
    }

    public List<Card> getRemainingCards() {
        return new ArrayList<>(cards);
    }
}