package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.models.Player;
import dailydescretedeck.set.models.Product;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

public class StoreViewModel {
    private Player player;
    private ListProperty<Product> products;
    public static ListProperty<Product> cartItems = new SimpleListProperty<>(FXCollections.observableArrayList());

    public StoreViewModel(Player player) {
        this.player = player;
        this.products = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    public ListProperty<Product> getProducts() {
        return products;
    }

    public IntegerProperty getPlayerMoney() {
        return new SimpleIntegerProperty(player.getPoints());
    }

}
