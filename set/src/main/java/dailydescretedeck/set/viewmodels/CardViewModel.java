package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.models.Card;
import dailydescretedeck.set.models.Dots;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.util.List;

public class CardViewModel {
    private Card card;
    private ListProperty<Dots> dots;

    public CardViewModel(Card card) {
        this.card = card;
        this.dots = new SimpleListProperty<>(FXCollections.observableArrayList(card.getFields()));
    }

    public CardViewModel(List<Dots> dots, CardDesign design) {
        this.card = new Card(dots, design);
        this.dots = new SimpleListProperty<>(FXCollections.observableArrayList(dots));
    }

    public CardDesign getDesign() {
        return card.getDesign();
    }

    public Card getCard() {
        return card;
    }

    public ListProperty<Dots> dotsProperty() {
        return dots;
    }
}
