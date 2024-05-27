package dailydescretedeck.set.models;

public class Product {
    private String name;
    private int price;
    private int category;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getCategory() {
        return category;
    }
}
