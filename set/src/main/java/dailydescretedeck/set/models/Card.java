package dailydescretedeck.set.models;

import dailydescretedeck.set.views.carddesignes.DefaultCardDesign;

import java.util.ArrayList;
import java.util.List;

public class Card {
    private final ArrayList<Integer> fields;
    private final CardDesign design;

    public Card(List<Integer> list) {
        this.fields = list;
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
