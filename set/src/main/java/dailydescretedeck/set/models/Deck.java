package dailydescretedeck.set.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> cards;

    public void fill(List<List<Dots>> list, List<Dots> temp, List<Dots> colors, int start) {
        list.add(new ArrayList<>(temp));
        for (int i = start; i < colors.size(); i++) {
            temp.add(colors.get(i));
            fill(list, temp, colors, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    public Deck() {
        cards = new ArrayList<>();
        List<List<Dots>> everything = new ArrayList<>();
        List<Dots> colors = Arrays.asList(Dots.values());
        fill(everything, new ArrayList<>(), colors, 0);
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
            return cards.removeLast();
        } else {
            return null;
        }
    }
}
