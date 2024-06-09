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

    public Card() {
        this.fields = new ArrayList<>(List.of(Dots.A1, Dots.A2, Dots.B1, Dots.B2, Dots.C1, Dots.C2));
        this.design = Player.getCardDesignInUse();
    }

    public Card(List<Dots> existingFields, CardDesign design) {
        this.fields = (ArrayList<Dots>) existingFields;
        this.design = design;
    }

    public List<Dots> getFields() {
        return this.fields;
    }
    public CardDesign getDesign() {
        return this.design;
    }
}
