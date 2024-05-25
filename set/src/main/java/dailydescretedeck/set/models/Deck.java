package dailydescretedeck.set.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> cards;

    public void fill(List<List<Dots>> list, List<Dots> temp, List<Dots> colors, int start) {
        if(!temp.isEmpty()) list.add(new ArrayList<>(temp));
        for (int i = start; i < colors.size(); i++) {
            temp.add(colors.get(i));
            fill(list, temp, colors, i + 1);
            temp.removeLast();
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
        //cards.remove(new Card(new ArrayList<>()));

        //System.out.println(cards.contains(new Card(new ArrayList<>())));
        shuffle();
    }

    public List<Card> getRemainingCards() {
        return cards;
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
            return card;
        } else {
            return null;
        }
    }
}
