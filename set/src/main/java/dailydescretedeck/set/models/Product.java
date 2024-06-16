package dailydescretedeck.set.models;

import dailydescretedeck.set.viewmodels.CardDesign;

import java.util.List;

public class Product {
    private String name;
    private int price;
    private CardDesigns design;

    public Product(String name, int price, CardDesigns design) {
        this.name = name;
        this.price = price;
        this.design = design;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public CardDesigns getDesign() {
        return design;
    }
}
