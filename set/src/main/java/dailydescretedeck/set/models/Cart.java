package dailydescretedeck.set.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Cart {
    static ObservableList<Product> items = FXCollections.observableArrayList();

    public void addProduct(Product product) {
        items.add(product);
    }

    public ObservableList<Product> getItems() {
        return items;
    }

    public int getTotalPrice() {
        return items.stream().mapToInt(Product::getPrice).sum();
    }
}
