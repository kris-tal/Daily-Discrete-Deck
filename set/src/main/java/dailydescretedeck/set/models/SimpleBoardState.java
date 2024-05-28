package dailydescretedeck.set.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleBoardState implements BoardState {
    private Board board;
    int n;
    public SimpleBoardState(int n) {
        this.board = new Board(n);
        this.n = n;
    }

    @Override
    public void reset() {
        this.board = new Board(n);
    }

    @Override
    public void update() {
        board.drawCardFromDeck();
    }
    private List<Card> cards = new ArrayList<>();

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public void removeCard(Card card) {
        cards.remove(card);
    }


    @Override
    public Board getBoard() {
        return board;
    }

}