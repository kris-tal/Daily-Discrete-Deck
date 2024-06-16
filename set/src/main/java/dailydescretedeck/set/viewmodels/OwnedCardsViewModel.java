package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.models.CardDesigns;
import dailydescretedeck.set.models.Player;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.stream.Collectors;

public class OwnedCardsViewModel {
    private Player player;
    private ListProperty<CardDesigns> ownedDesigns;

    public OwnedCardsViewModel(Player player) {
        this.player = player;
        this.ownedDesigns = new SimpleListProperty<>(FXCollections.observableArrayList(player.getOwnedDesigns()));
    }

    public ObservableList<CardDesign> getProducts() {
        return ownedDesigns.get().stream()
                .map(CardDesignMap::getInstance)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public int getDesignsSize() {
        return ownedDesigns.size();
    }

    public void setCurrentDesign(CardDesigns selectedDesign) {
        player.setDesign(selectedDesign);
    }
}
