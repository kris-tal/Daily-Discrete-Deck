package dailydescretedeck.set.models;


import java.util.ArrayList;
import java.util.List;

public class Card {
    private List<Dots> fields;
    private CardDesigns design;

    public Card(List<Dots> existingFields) {
        this.fields = (ArrayList<Dots>) existingFields;
        this.design = Player.getCardDesignInUse();
    }

    public Card() {
        this.fields = new ArrayList<>(List.of(Dots.A1, Dots.A2, Dots.B1, Dots.B2, Dots.C1, Dots.C2));
        this.design = Player.getCardDesignInUse();
    }

    public Card(List<Dots> existingFields, CardDesigns design) {
        this.fields = existingFields;
        this.design = design;
    }

    public List<Dots> getFields() {
        return this.fields;
    }
    public CardDesigns getDesign() {
        return this.design;
    }
}
