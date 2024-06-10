package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.models.Card;
import dailydescretedeck.set.models.Dots;

import java.util.List;

public class CardViewModel {
    Card card;
    CardDesign design;
    CardViewModel(CardDesign design) {
        this.design = design;
    }
    public CardDesign getDesign(){
        return design;
    }

    public List<Dots> getFields() {
        return card.getFields();
    }
}
