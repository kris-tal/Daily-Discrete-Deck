package dailydescretedeck.set.views;

import dailydescretedeck.set.viewmodels.Scenes;
import dailydescretedeck.set.viewmodels.StoreViewModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class StoreView extends VBox {
    private StoreViewModel storeViewModel;
    private Scenes scenes;

    public StoreView(StoreViewModel storeViewModel) {
        this.storeViewModel = storeViewModel;
        this.scenes = new Scenes();
        initializeComponents();
    }

    private void initializeComponents() {
        setStyle("-fx-background-color: thistle;");
        setPadding(new Insets(10));
        setSpacing(20);
        setAlignment(Pos.CENTER);

        VBox topButtons = new VBox();
        topButtons.setAlignment(Pos.CENTER);
        topButtons.setSpacing(20);

        Button backButton = new Button("Back to Menu");
        backButton.setFont(Font.font("System", 18));
        backButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");
        backButton.setOnAction(event -> scenes.showMenuView());

        backButton.prefWidthProperty().bind(widthProperty().multiply(0.3));
        backButton.prefHeightProperty().bind(heightProperty().multiply(0.05));

        topButtons.getChildren().add(backButton);

        HBox mainButtons = new HBox();
        mainButtons.setAlignment(Pos.CENTER);
        mainButtons.setSpacing(20);

        Button miniStoreButton = new Button("Buy Cards");
        miniStoreButton.setFont(Font.font("System", 18));
        miniStoreButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 10;");
        miniStoreButton.setOnAction(event -> scenes.showBuyCardsView());

        Button ownedButton = new Button("Owned");
        ownedButton.setFont(Font.font("System", 18));
        ownedButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 10;");
        ownedButton.setOnAction(event -> scenes.showOwnedCardsView());

        miniStoreButton.prefWidthProperty().bind(widthProperty().multiply(0.4));
        miniStoreButton.prefHeightProperty().bind(heightProperty().multiply(0.3));

        ownedButton.prefWidthProperty().bind(widthProperty().multiply(0.4));
        ownedButton.prefHeightProperty().bind(heightProperty().multiply(0.3));

        mainButtons.getChildren().addAll(miniStoreButton, ownedButton);

        getChildren().addAll(mainButtons, topButtons);
    }
}
