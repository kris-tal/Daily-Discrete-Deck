package dailydescretedeck.set.views;

import dailydescretedeck.set.models.Product;
import dailydescretedeck.set.viewmodels.BuyCardsViewModel;
import dailydescretedeck.set.viewmodels.Scenes;
import dailydescretedeck.set.viewmodels.StoreViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.HashMap;
import java.util.Map;

import static dailydescretedeck.set.viewmodels.Scenes.*;
import static java.lang.Double.min;

public class StoreView extends Pane {
    private StoreViewModel storeViewModel;
    private Stage stage;
    private double gap;
    //private ObservableList<Product> selectedProducts = FXCollections.observableArrayList();
    //private Map<Product, ProductCell> productCells = new HashMap<>();
    private Scenes scenes;


    public StoreView(StoreViewModel storeViewModel) {
        this.storeViewModel = storeViewModel;
        this.stage = stage;
        scenes = new Scenes();
        setPrefSize(1000, 800);
        redrawStore();

        widthProperty().addListener((observable, oldValue, newValue) -> redrawStore());
        heightProperty().addListener((observable, oldValue, newValue) -> redrawStore());
    }

    private void redrawStore() {
        getChildren().clear();

        setStyle("-fx-background-color: thistle;");

        double paneWidth = getWidth();
        double paneHeight = getHeight();

        if (paneWidth == 0 || paneHeight == 0) {
            return;
        }

        double square = min(paneWidth, paneHeight) * 0.09;
        double bigRectWidth = square * 9;
        double bigRectHeight = square * 7;

        double bigRectX = (paneWidth - bigRectWidth) / 2;
        double bigRectY = (paneHeight - bigRectHeight) / 2;

        Rectangle bigRect = new Rectangle(bigRectX, bigRectY, bigRectWidth, bigRectHeight);
        bigRect.setFill(Color.THISTLE);
        getChildren().add(bigRect);

        gap = square / 5;

        double startX = bigRectX + gap;
        double startY = bigRectY + gap / 2;

        Font font = new Font("Comic Sans MS", gap * 2);

        /*
        Label storeLabel = new Label("Store");
        storeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        storeLabel.setLayoutX(gap);
        storeLabel.setLayoutY(gap);
        getChildren().add(storeLabel);

         */



        Button backButton = new Button("Back to Menu");
        backButton.setLayoutX(startX);
        backButton.setLayoutY(startY + bigRectHeight + gap);
        backButton.setPrefWidth(bigRectWidth / 3 - gap);
        backButton.setPrefHeight(40);
        backButton.setFont(Font.font("System", 18));
        backButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");
        backButton.setOnAction(event -> scenes.showMenuView());

        /*
        Button cartButton = new Button("View Cart");
        cartButton.setLayoutX(startX + bigRectWidth / 3 + gap);
        cartButton.setLayoutY(startY + bigRectHeight + gap);
        cartButton.setPrefWidth(bigRectWidth / 3 - gap);
        cartButton.setPrefHeight(40);
        cartButton.setFont(Font.font("System", 18));
        cartButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");
        cartButton.setOnAction(event -> scenes.showCartView(storeViewModel));   //jak gabi doda playera to powinno dzialac

         */

        getChildren().addAll(backButton);


        Button miniStoreButton = new Button("Buy Cards");
        miniStoreButton.setLayoutX(startX );
        miniStoreButton.setLayoutY(startY + (bigRectHeight / 6)  );
        miniStoreButton.setPrefSize(1.4 * bigRectWidth / 3 - gap, 1.4 * bigRectHeight / 2 - gap);
        miniStoreButton.setFont(Font.font("System", 18));
        miniStoreButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 10;");
        miniStoreButton.setOnAction(event -> scenes.showBuyCardsView(getPlayer()));   //dodac playera
        getChildren().add(miniStoreButton);

        Button ownedButton = new Button("Owned");
        ownedButton.setLayoutX(startX + 1.5 * (bigRectWidth / 3));
        ownedButton.setLayoutY(startY + (bigRectHeight / 6) );
        ownedButton.setPrefSize(1.4 * bigRectWidth / 3 - gap, 1.4 * bigRectHeight / 2 - gap);
        ownedButton.setFont(Font.font("System", 18));
        ownedButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 10;");
        ownedButton.setOnAction(event -> scenes.showOwnedCardsView());   //dodac playera
        getChildren().add(ownedButton);

    }

//    private void openCartView() {
//        CartView cartView = new CartView(storeViewModel);
//        Scene scene = new Scene(cartView, 400, 600);
//        stage.setScene(scene);
//    }


    private void openBuyCardsView() {
        BuyCardsViewModel buyCardsViewModel = new BuyCardsViewModel(storeViewModel.getPlayer());
        BuyCardsView buyCardsView = new BuyCardsView(buyCardsViewModel);
        Scene scene = new Scene(buyCardsView, getWidth(), getHeight());
        stage.setScene(scene);
    }

    private void goBackToStore() {
        Scene scene = new Scene(new StoreView(storeViewModel), getWidth(), getHeight());
        stage.setScene(scene);
    }

    /*
    private static class ProductCell extends ListCell<Product> {
        private HBox content;
        private Label nameLabel;
        private Label priceLabel;
        private Button addButton;

        public ProductCell(StoreViewModel storeViewModel) {
            super();
            nameLabel = new Label();
            priceLabel = new Label();
            addButton = new Button("Add to Cart");
            addButton.setOnAction(event -> storeViewModel.addToCart(getItem()));
            content = new HBox(nameLabel, priceLabel, addButton);
            content.setSpacing(10);
        }

        @Override
        protected void updateItem(Product product, boolean empty) {
            super.updateItem(product, empty);
            if (product != null && !empty) {
                nameLabel.setText(product.getName());
                priceLabel.setText(String.valueOf(product.getPrice()));
                setGraphic(content);
            } else {
                setGraphic(null);
            }
        }
    }

     */
}
