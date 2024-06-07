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

import static java.lang.Double.min;

public class StoreView extends Pane {
    private StoreViewModel storeViewModel;
    private Stage stage;
    private double gap;
    private ObservableList<Product> selectedProducts = FXCollections.observableArrayList();
    private Map<Product, ProductCell> productCells = new HashMap<>();
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

        Label storeLabel = new Label("Store");
        storeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        storeLabel.setLayoutX(gap);
        storeLabel.setLayoutY(gap);
        getChildren().add(storeLabel);

        Label totalCostLabel = new Label();
        totalCostLabel.textProperty().bind(storeViewModel.getTotalCost().asString("Total Cost: %d"));
        totalCostLabel.setFont(font);
        totalCostLabel.setLayoutX(gap);
        totalCostLabel.setLayoutY(gap * 4);
        getChildren().add(totalCostLabel);

        Label playerMoneyLabel = new Label();
        playerMoneyLabel.textProperty().bind(storeViewModel.getPlayerMoney().asString("Money: %d"));
        playerMoneyLabel.setFont(font);
        playerMoneyLabel.setLayoutX(gap);
        playerMoneyLabel.setLayoutY(gap * 6);
        getChildren().add(playerMoneyLabel);

        Label selectedProductsLabel = new Label();
        selectedProductsLabel.textProperty().bind(storeViewModel.getSelectedProductsCount().asString("Selected Products: %d"));
        selectedProductsLabel.setFont(font);
        selectedProductsLabel.setLayoutX(gap);
        selectedProductsLabel.setLayoutY(gap * 8);
        getChildren().add(selectedProductsLabel);

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

        for (int i = 0; i < 6; i++) {
            Button categoryButton = new Button("Category " + (i + 1));
            categoryButton.setLayoutX(startX + (i % 3) * (bigRectWidth / 3));
            categoryButton.setLayoutY(startY + (i / 3) * (bigRectHeight / 2));
            categoryButton.setPrefSize(bigRectWidth / 3 - gap, bigRectHeight / 2 - gap);
            categoryButton.setFont(Font.font("System", 18));
            categoryButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 10;");
            final int categoryIndex = i;
            if (categoryIndex == 0) {
                categoryButton.setOnAction(event -> scenes.showBuyCardsView(storeViewModel));   //dodac playera
            }
            getChildren().add(categoryButton);
        }
    }

//    private void openCartView() {
//        CartView cartView = new CartView(storeViewModel);
//        Scene scene = new Scene(cartView, 400, 600);
//        stage.setScene(scene);
//    }

    private void openBuyCardsView() {
        BuyCardsViewModel buyCardsViewModel = new BuyCardsViewModel();
        BuyCardsView buyCardsView = new BuyCardsView(buyCardsViewModel, storeViewModel);
        Scene scene = new Scene(buyCardsView, getWidth(), getHeight());
        stage.setScene(scene);
    }

    private void goBackToStore() {
        Scene scene = new Scene(new StoreView(storeViewModel), getWidth(), getHeight());
        stage.setScene(scene);
    }

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
}
