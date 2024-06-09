package dailydescretedeck.set.models;

import dailydescretedeck.set.viewmodels.CardDesign;

import java.util.ArrayList;
import java.util.List;

public class Card {
    private ArrayList<Dots> fields;
    private CardDesign design;

    public Card(List<Dots> existingFields) {
        this.fields = (ArrayList<Dots>) existingFields;
        this.design = Player.getCardDesignInUse();
    }

    public List<Dots> getFields() {
        return this.fields;
    }
    public CardDesign getDesign() {
        return this.design;
    }
}
