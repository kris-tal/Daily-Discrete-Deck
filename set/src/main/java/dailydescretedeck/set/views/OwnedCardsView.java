package dailydescretedeck.set.views;

import dailydescretedeck.set.viewmodels.OwnedCardsViewModel;
import dailydescretedeck.set.viewmodels.Scenes;
import javafx.beans.Observable;
import javafx.scene.Parent;
import dailydescretedeck.set.viewmodels.BuyCardsViewModel;
import dailydescretedeck.set.viewmodels.Scenes;
import dailydescretedeck.set.viewmodels.StoreViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class OwnedCardsView extends Pane {
    private Scenes scenes;
    OwnedCardsViewModel ownedCardsViewModel;
    public OwnedCardsView(OwnedCardsViewModel ownedCardsViewModel) {
        this.ownedCardsViewModel = ownedCardsViewModel;
        this.scenes = new Scenes();
        //setPrefSize(1000, 800);
        redrawView();

        //widthProperty().addListener((observable, oldValue, newValue) -> redrawView());
        //heightProperty().addListener((observable, oldValue, newValue) -> redrawView());
    }

    private void redrawView() {
    }
}
