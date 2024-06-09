package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.models.Board;
import dailydescretedeck.set.models.Card;
import dailydescretedeck.set.models.Dots;

import java.util.List;

public class CardViewModel {
    private Card card;

    CardViewModel(Card card) {
        this.card = card;
    }

    public CardDesign getDesign(){
        return card.getDesign();
    }

    public CardViewModel(List<Dots> dots, CardDesign design){
        this.card = new Card(dots, design);
    }
}
