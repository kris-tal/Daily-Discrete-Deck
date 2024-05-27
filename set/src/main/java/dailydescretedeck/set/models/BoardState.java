package dailydescretedeck.set.models;


public interface BoardState {

    void reset();
    void update();
    void addCard(Card card);
    void removeCard(Card card);

    Board getBoard();
}
