package dailydescretedeck.set.models;

import java.util.ArrayList;
import java.util.List;

import static dailydescretedeck.set.models.Dots.*;

public class Card {
    private ArrayList<Dots> fields;
    private CardDesign design;

    public Card(List<Dots> existingFields) {
        this.fields = (ArrayList<Dots>) existingFields;
        this.design = Player.getCardDesignInUse();
    }

    public Card(CardDesign design) {
        this.fields = new ArrayList<>(List.of(A1, A2, B1, B2, C1, C2));
        this.design = design;
    }

    public List<Dots> getFields() {
        return this.fields;
    }
    public CardDesign getDesign() {
        return this.design;
    }
}
