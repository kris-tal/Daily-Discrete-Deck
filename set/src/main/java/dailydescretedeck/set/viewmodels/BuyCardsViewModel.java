package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.models.Cart;
import dailydescretedeck.set.models.Player;
import dailydescretedeck.set.models.Product;
import dailydescretedeck.set.views.carddesignes.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.List;

public class BuyCardsViewModel {
    private Player player;
    private ListProperty<Product> products;
    private Cart cart;
    public static ListProperty<Product> cartItems = new SimpleListProperty<>(FXCollections.observableArrayList());
    private IntegerProperty totalCost;
    private IntegerProperty selectedProductsCount;
    private List<Product> purchasedProducts;

    public BuyCardsViewModel(Player player) {
        this.player = player;
        this.cart = new Cart();
        this.products = new SimpleListProperty<>(FXCollections.observableArrayList());
        this.totalCost = new SimpleIntegerProperty(cart.getTotalPrice());
        this.selectedProductsCount = new SimpleIntegerProperty(cartItems.size());
        this.purchasedProducts = new ArrayList<>();

        // example products
        products.add(new Product("Blue", 10, new BlueCardDesign()));
        products.add(new Product("Emo", 20, new EmoCardDesign()));
        products.add(new Product("JH", 35, new JHCardDesign()));
        products.add(new Product("Red", 15, new RedCardDesign()));
        products.add(new Product("Galaxy", 15, new GalaxyCardDesign()));
        products.add(new Product("Adventure", 500, new AdventureCardDesign()));
        products.add(new Product("Fantasy", 100, new FantasyCardDesign()));
        products.add(new Product("The Witcher", 100, new WitcherCardDesign()));
        products.add(new Product("Orange", 30, new OrangeCardDesign()));
        products.add(new Product("Beach", 99, new BeachCardDesign()));
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

    public int checkout() {
        long playerMoney = player.getMoney();
        int cost = totalCost.get();

        if (cost == 0) return 0;
        if (playerMoney >= cost) {
            player.spendMoney(cost);
            player.addDesigns(cartItems);
            purchasedProducts.addAll(cartItems);
            products.removeAll(cartItems);
            cartItems.clear();
            totalCost.set(0);
            selectedProductsCount.set(0);
            return 2;
        } else {
            return 1;
        }
    }

    public boolean hasItemsInCart() {
        return !cartItems.isEmpty();
    }

    public List<Product> getPurchasedProducts() {
        return purchasedProducts;
    }

    public void removeAllFromCart() {
        cartItems.clear();
        totalCost.set(0);
        selectedProductsCount.set(0);
    }
}
