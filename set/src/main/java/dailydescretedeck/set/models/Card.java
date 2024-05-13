package dailydescretedeck.set.models;

import dailydescretedeck.set.views.carddesignes.DefaultCardDesign;

import java.util.ArrayList;
import java.util.List;

public class Card {
    private final ArrayList<Integer> fields;
    private final CardDesign design;

    public Card(ArrayList<Integer> list) {
        this.fields = list;
        this.design = new DefaultCardDesign();
    }

    public ArrayList<Integer> getFields() {
        return this.fields;
    }
    public CardDesign getDesign() {
        return this.design;
    }
}
