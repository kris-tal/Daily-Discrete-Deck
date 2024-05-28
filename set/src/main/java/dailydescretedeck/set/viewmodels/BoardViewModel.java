package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.models.Board;
import dailydescretedeck.set.models.Card;
import dailydescretedeck.set.models.CardManager;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class BoardViewModel {
    private Board board;
    private CardManager cardManager;
    private ListProperty<Card> cards;

    public BoardViewModel(Board board) {
        this.board = board;
        this.cardManager = new CardManager(board);
        this.cards = new SimpleListProperty<>(FXCollections.observableArrayList(board.getCards()));
    }

    public ListProperty<Card> cardsProperty() {
        return cards;
    }

    public void addCard(Card card) {
        board.addCard(card);
        cards.set(FXCollections.observableArrayList(board.getCards()));
    }

    public void removeCard(Card card) {
        board.removeCard(card);
        cards.set(FXCollections.observableArrayList(board.getCards()));
    }

    public boolean isSetOk(List<Card> selectedCards) {
        return cardManager.isSetOk(selectedCards);
    }

    public boolean removeCards(List<Card> selectedCards) {
        boolean ok = cardManager.removeCards(selectedCards);
        cards.set(FXCollections.observableArrayList(board.getCards()));
        return ok;
    }

    public void reset() {
        board.reset();
        cards.set(FXCollections.observableArrayList(board.getCards()));
    }

    public void update() {
        board.update();
        cards.set(FXCollections.observableArrayList(board.getCards()));
    }

    public List<Card> getNotSet() {
        return cardManager.getNotSet();
    }

    public Card getXor(List<Card> selectedCards) {
        return cardManager.xor(selectedCards);
    }

    public Board getBoard() {
        return board;
    }
}
