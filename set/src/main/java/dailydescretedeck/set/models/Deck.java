package dailydescretedeck.set.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    private void wypelnij(List<List<Color>> list, List<Color> temp, List<Color> colors, int start) {
        if (!temp.isEmpty()) {
            list.add(new ArrayList<>(temp));
        }
        for (int i = start; i < colors.size(); i++) {
            temp.add(colors.get(i));
            wypelnij(list, temp, colors, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    public Deck() {
        cards = new ArrayList<>();
        List<List<Color>> everything = new ArrayList<>();
        List<Color> colors = new ArrayList<>();
        for (Color color : Color.values()) {
            colors.add(color);
        }
        wypelnij(everything, new ArrayList<>(), colors, 0);
        for (List<Color> list : everything) {
            cards.add(new Card(list));
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int size() {
        return cards.size();
    }

    public Card drawCard() {
        if (!cards.isEmpty()) {
            return cards.remove(cards.size() - 1);
        } else {
            return null;
        }
    }
}