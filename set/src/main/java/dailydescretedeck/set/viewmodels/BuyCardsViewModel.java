package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.models.Cart;
import dailydescretedeck.set.models.Player;
import dailydescretedeck.set.models.Product;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

public class BuyCardsViewModel {
    private Player player;
    private ListProperty<Product> products;
    private Cart cart;
    public static ListProperty<Product> cartItems = new SimpleListProperty<>(FXCollections.observableArrayList());
    private IntegerProperty totalCost;
    private IntegerProperty selectedProductsCount;

    public BuyCardsViewModel(Player player) {
        this.player = player;
        this.cart = new Cart();
        this.products = new SimpleListProperty<>(FXCollections.observableArrayList());
        this.totalCost = new SimpleIntegerProperty(cart.getTotalPrice());
        this.selectedProductsCount = new SimpleIntegerProperty(cartItems.size());

        // example products
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

    public ListProperty<Product> getCartItems() {
        return cartItems;
    }

    public IntegerProperty getTotalCost() {
        return totalCost;
    }

    public IntegerProperty getPlayerMoney() {
        return new SimpleIntegerProperty(player.getPoints());
    }

    public IntegerProperty getSelectedProductsCount() {
        return selectedProductsCount;
    }

    public void addToCart(Product product) {
        if (product != null && !cartItems.contains(product)) {
            cartItems.add(product);
            cart.addProduct(product);
            totalCost.set(totalCost.get() + product.getPrice());
            selectedProductsCount.set(selectedProductsCount.get() + 1);
        }
    }

    public void removeFromCart(Product product) {
        if (product != null && cartItems.contains(product)) {
            cartItems.remove(product);
            cart.getItems().remove(product);
            totalCost.set(totalCost.get() - product.getPrice());
            selectedProductsCount.set(selectedProductsCount.get() - 1);
        }
    }

    public void checkout() {
        int playerMoney = player.getPoints();
        int cost = totalCost.get();

        if (playerMoney >= cost) {
            player.updatePoints(playerMoney - cost);
            cartItems.clear();
            totalCost.set(0);
            selectedProductsCount.set(0);
        } else {
            // alert
        }
    }
}
