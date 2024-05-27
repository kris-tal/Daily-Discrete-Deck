package dailydescretedeck.set.models;

import java.util.List;
import java.util.Map;

public interface BoardState {

    void reset();
    void update();
    void addCard(Card card);
    void removeCard(Card card);

    Board getBoard();
}
