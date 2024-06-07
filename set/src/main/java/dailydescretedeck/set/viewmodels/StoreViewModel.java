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

    public StoreViewModel(Player player) {
        this.player = player;
    }


    public Player getPlayer() {
        return player;
    }
}
