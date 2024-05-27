package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.models.Product;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

public class BuyCardsViewModel {
    private ListProperty<Product> products;

    public BuyCardsViewModel() {
        this.products = new SimpleListProperty<>(FXCollections.observableArrayList());

        //example
        products.add(new Product("Red Dots", 10));
        products.add(new Product("Blue Dots", 15));
        products.add(new Product("Green Dots", 20));
        products.add(new Product("Gold Dots", 35));
        products.add(new Product("Yellow Dots", 100));
        products.add(new Product("Orange Dots", 15));
        products.add(new Product("Olive Dots", 20));
        products.add(new Product("Brown Dots", 35));
    }

    public ListProperty<Product> getProducts() {
        return products;
    }

    public void addToCart(Product product) {
        StoreViewModel.cartItems.add(product);
    }

    public void removeFromCart(Product product) {
        StoreViewModel.cartItems.remove(product);
    }

    public javafx.collections.ObservableList<Product> getCartItems() {
        return StoreViewModel.cartItems;
    }
}
