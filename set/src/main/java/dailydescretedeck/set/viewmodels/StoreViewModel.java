package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.models.Player;
import dailydescretedeck.set.models.Product;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StoreViewModel {
    private Player player;
    private ListProperty<Product> products;
    public static ListProperty<Product> cartItems = new SimpleListProperty<>(FXCollections.observableArrayList());
    private IntegerProperty totalCost;
    private IntegerProperty selectedProductsCount;

    public StoreViewModel(Player player) {
        this.player = player;
        this.products = new SimpleListProperty<>(FXCollections.observableArrayList());
        this.totalCost = new SimpleIntegerProperty(0);
        this.selectedProductsCount = new SimpleIntegerProperty(0);
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
            totalCost.set(totalCost.get() + product.getPrice());
            selectedProductsCount.set(selectedProductsCount.get() + 1);
        }
    }

    public void removeFromCart(Product product) {
        if (product != null && cartItems.contains(product)) {
            cartItems.remove(product);
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
        }
        else {
            // alert
        }
    }
}
