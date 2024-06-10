package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.models.Player;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OwnedCardsViewModel {
    private Player player;
    private ListProperty<CardDesign> ownedDesigns;

    public OwnedCardsViewModel(Player player) {
        this.player = player;
        this.ownedDesigns = new SimpleListProperty<>(FXCollections.observableArrayList(player.getOwnedDesigns()));
    }

    public ObservableList<CardDesign> getProducts() {
        return ownedDesigns.get();
    }

    public int getDesignsSize() {
        return ownedDesigns.size();
    }

    public void setCurrentDesign(CardDesign selectedDesign) {
        player.setDesign(selectedDesign);
    }
}
