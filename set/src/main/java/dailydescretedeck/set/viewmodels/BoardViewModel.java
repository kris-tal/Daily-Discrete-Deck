package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.models.Board;
import dailydescretedeck.set.models.Card;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BoardViewModel {
    private Board board;
    private ListProperty<CardViewModel> cards;

    public BoardViewModel(Board board) {
        this.board = board;
        this.cards = new SimpleListProperty<>(FXCollections.observableArrayList(
                board.getCards().stream().map(CardViewModel::new).collect(Collectors.toList())
        ));
    }

    public ListProperty<CardViewModel> cardsProperty() {
        return cards;
    }

    public int leftCards() {
        return board.getDeck().size() + cards.size();
    }

    public void addCard(Card card) {
        board.addCard(card);
        cards.add(new CardViewModel(card));
    }

    public void removeCard(Card card) {
        board.removeCard(card);
        cards.removeIf(cardViewModel -> cardViewModel.getCard().equals(card));
    }

    public boolean isSetOk(List<CardViewModel> selectedCardViewModels, boolean set) {
        List<Card> selectedCards = selectedCardViewModels.stream().map(CardViewModel::getCard).collect(Collectors.toList());
        return board.isSetOk(selectedCards, set);
    }

    public boolean removeCards(List<CardViewModel> selectedCardViewModels) {
        List<Card> selectedCards = selectedCardViewModels.stream().map(CardViewModel::getCard).collect(Collectors.toList());
        boolean ok = board.removeCards(selectedCards);
        cards.removeAll(selectedCardViewModels);
        return ok;
    }

    public void reset() {
        board.reset();
        cards.set(FXCollections.observableArrayList(
                board.getCards().stream().map(CardViewModel::new).collect(Collectors.toList())
        ));
    }

    public void update() {
        board.update();
        cards.set(FXCollections.observableArrayList(
                board.getCards().stream().map(CardViewModel::new).collect(Collectors.toList())
        ));
    }

    public List<CardViewModel> getNotSet() {
        return board.getNotSet().stream().map(CardViewModel::new).collect(Collectors.toList());
    }

    public CardViewModel getXor(List<CardViewModel> selectedCardViewModels) {
        List<Card> selectedCards = selectedCardViewModels.stream().map(CardViewModel::getCard).collect(Collectors.toList());
        return new CardViewModel(board.getXor(selectedCards));
    }

    public List<CardViewModel> getSet() {
        return board.getSet().stream().map(CardViewModel::new).collect(Collectors.toList());
    }

    public Board getBoard() {
        return board;
    }

    public int getNumberSets() {
        return board.getNumberSets();
    }
}
