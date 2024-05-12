package dailydescretedeck.set.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    private void wypelnij(List<List<Integer>> list, List<Integer> tempList, List<Integer> numbers, int start) {
        if (!tempList.isEmpty()) {
            list.add(new ArrayList<>(tempList));
        }
        for (int i = start; i < numbers.size(); i++) {
            tempList.add(numbers.get(i));
            wypelnij(list, tempList, numbers, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    public Deck() {
        cards = new ArrayList<>();
        List<List<Integer>> everything = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            numbers.add(i);
        }
        wypelnij(everything, new ArrayList<>(), numbers, 0);
        for (List<Integer> list : everything) {
            cards.add(new Card());
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
