package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.views.CardView;
import dailydescretedeck.set.models.Card;
import dailydescretedeck.set.models.Dots;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

public class CardViewModel {
    private Card card;
    private ObjectProperty<CardView> cardView;

    public CardViewModel(Card card) {
        this.card = card;
        this.cardView = new SimpleObjectProperty<>(new CardView(this, 0, 0, 1));
    }

    public CardViewModel(List<Dots> existingFields, CardDesign design) {
        this.card = new Card(existingFields, design);
        this.cardView = new SimpleObjectProperty<>(new CardView(this, 0, 0, 1));
    }

    public List<Dots> getFields() {
        return card.getFields();
    }

    public CardDesign getDesign() {
        return card.getDesign();
    }

    public CardView getView() {
        return cardView.get();
    }

    public void setViewScale(double sq) {
        cardView.get().updateScale(sq);
    }

    public void click() {
        cardView.get().clicked();
    }

    public void unclick() {
        cardView.get().unclick();
    }

    public void selectNotSelected() {
        cardView.get().selectNotSelected();
    }

    public void disableThisCard() {
        cardView.get().disableThisCard();
    }

    public Card getCard() {
        return card;
    }

    public List<Dots> dotsProperty() {
        return card.getFields();
    }
}
