package dailydescretedeck.set.models;

import java.util.List;
import java.util.Map;

public interface BoardState {
    void addCards(List<Card> card);
    void removeCards(List<Card> card);
    Iterable<CardState> getCards();
    void clear();
    void refresh();
    Board getBoard();
    public Card Xor(List<Card> cards);
    public boolean isSetOk(List<Card> cards);
    public Map<Dots, Integer> preparationToCount(List<Card> cards);
}
