package dailydescretedeck.set.models;

import dailydescretedeck.set.views.carddesignes.DefaultCardDesign;

import java.util.ArrayList;

public class Card {
    private final ArrayList<Integer> fields;
    private final CardDesign design;

    public Card(ArrayList<Integer> existingFields) {
        this.fields = existingFields;
        this.design = new DefaultCardDesign();
    }

    public ArrayList<Integer> getFields() {
        return this.fields;
    }
    public CardDesign getDesign() {
        return this.design;
    }
}
