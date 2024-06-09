package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.views.CardView;
import dailydescretedeck.set.models.Card;
import dailydescretedeck.set.models.Dots;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

public class CardViewModel {
    private Card card;
    private CardView cardView;

    public CardViewModel(Card card) {
        this.card = card;
        this.cardView = new CardView(this, 0, 0, 2);
    }

    public CardViewModel(List<Dots> existingFields, CardDesign design) {
        this.card = new Card(existingFields, design);
        this.cardView = new CardView(this, 0, 0, 2);
    }

    public List<Dots> getFields() {
        return card.getFields();
    }

    public CardDesign getDesign() {
        return card.getDesign();
    }

    public CardView getView() {
        return cardView;
    }

    public void setViewScale(double sq) {
        cardView.updateScale(sq);
    }

    public void click() {
        cardView.clicked();
    }

    public void unclick() {
        cardView.unclick();
    }

    public void selectNotSelected() {
        cardView.selectNotSelected();
    }

    public void disableThisCard() {
        cardView.disableThisCard();
    }

    public Card getCard() {
        return card;
    }



    public List<Dots> dotsProperty() {
        return card.getFields();
    }

    public void setCardView(CardView cardView) {
        this.cardView = cardView;
    }
}
