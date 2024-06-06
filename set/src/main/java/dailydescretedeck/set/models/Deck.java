package dailydescretedeck.set.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import dailydescretedeck.set.services.Feature;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        List<List<Dots>> everything = new ArrayList<>();
        List<Dots> colors = Arrays.asList(Dots.values());
        Feature.fill(everything, new ArrayList<>(), colors, 0);
        for (List<Dots> list : everything) {
            cards.add(new Card((ArrayList<Dots>)list));
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards, new Random(System.currentTimeMillis()));
    }

    public int size() {
        return cards.size();
    }

    public Card drawCard() {
        if (!cards.isEmpty()) {
            return cards.remove(cards.size() - 1);
        }
        return null;
    }

    public List<Card> getRemainingCards() {
        return new ArrayList<>(cards);
    }
}
