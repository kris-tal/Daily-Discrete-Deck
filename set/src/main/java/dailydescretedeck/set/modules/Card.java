package dailydescretedeck.set.modules;

import dailydescretedeck.set.views.carddesignes.DefaultCardDesign;

import java.util.ArrayList;

public class Card {
    private final ArrayList<Integer> fields;
    private final CardDesign design;

    public Card(ArrayList<Integer> existingFields) {
        this.fields = existingFields;
        this.design = new DefaultCardDesign();
    }

    public ArrayList<Integer> getFields(int field) {
        return this.fields;
    }

//    public Color getColor(int idx) {
//        return design.getColor(idx);
//    }
//
//    public Shape getShape(int idx) {
//        return design.getShape(idx);
//    }

}
