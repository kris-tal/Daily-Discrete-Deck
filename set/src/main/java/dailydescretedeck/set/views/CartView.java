package dailydescretedeck.set.views;

import dailydescretedeck.set.models.Dots;
import dailydescretedeck.set.models.Product;
import dailydescretedeck.set.viewmodels.BuyCardsViewModel;
import dailydescretedeck.set.viewmodels.CardDesign;
import dailydescretedeck.set.viewmodels.Scenes;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

import static javafx.scene.paint.Color.THISTLE;

public class CartView extends Pane {
    private BuyCardsViewModel buyCardsViewModel;
    private Scenes scenes;
    private VBox cardDesignBox;

    public CartView(BuyCardsViewModel viewModel) {
        this.buyCardsViewModel = viewModel;
        this.scenes = new Scenes();
        setBackground(Background.fill(THISTLE));
        setPrefSize(600, 600); 
        initialize();
    }

    private void initialize() {
        getChildren().clear();

        Label titleLabel = new Label("Cart");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        ListView<Product> cartListView = new ListView<>(buyCardsViewModel.getCartItems());
        cartListView.setCellFactory(parameter -> new ProductCell(buyCardsViewModel, this));
        cartListView.setStyle("-fx-background-color: THISTLE;");

        Button backButton = new MyButton("Back");
        backButton.setOnAction(event -> scenes.showBuyCardsView());

        Button finaliseButton = new MyButton("Finalise Purchase");
        finaliseButton.setOnAction(event -> finalisePurchase());

        cardDesignBox = new VBox();
        cardDesignBox.setPrefWidth(200);
        cardDesignBox.setBackground(Background.fill(THISTLE));
        cardDesignBox.setPadding(new Insets(10));

        HBox mainBox = new HBox(10, cartListView, cardDesignBox);
        VBox vbox = new VBox(15, titleLabel, mainBox, backButton, finaliseButton);
        vbox.setPadding(new Insets(10));
        vbox.setPrefSize(600, 600);
        getChildren().add(vbox);
    }

    private void finalisePurchase() {
        int purchaseState = buyCardsViewModel.checkout();
        if (purchaseState == 0) {
            AestheticAlert.showAlert("Cart Empty", "Your cart is empty. Please add items to your cart before finalising the purchase.");
        } else if (purchaseState == 1) {
            AestheticAlert.showAlert("Purchase Failed", "You do not have enough money to complete this purchase.");
        } else {
            scenes.showBuyCardsView();
        }
    }

    public void displayCardDesign(Product product) {
        cardDesignBox.getChildren().clear();

        if (product != null) {
            CardDesign design = product.getDesign();
            List<Dots> existingFields = new ArrayList<>();
            existingFields.add(Dots.A1);
            existingFields.add(Dots.A2);
            existingFields.add(Dots.B1);
            existingFields.add(Dots.B2);
            existingFields.add(Dots.C1);
            existingFields.add(Dots.C2);
            CardView cardView = new CardView(existingFields, design, 0, 0, 2);
            cardDesignBox.getChildren().add(cardView);
        }
    }

    private static class ProductCell extends ListCell<Product> {
        private HBox content;
        private Label nameLabel;
        private Label priceLabel;
        private Button removeButton;
        private CartView parentView;

        public ProductCell(BuyCardsViewModel viewModel, CartView parentView) {
            super();
            this.parentView = parentView;
            nameLabel = new Label();
            priceLabel = new Label();
            removeButton = new MyButton("Remove");
            removeButton.setStyle("-fx-background-color: THISTLE; -fx-text-fill: #746174; -fx-background-radius: 20;");
            removeButton.setFont(Font.font("System", 12));
            removeButton.setOnAction(event -> viewModel.removeFromCart(getItem()));
            content = new HBox(10, nameLabel, priceLabel, removeButton);
            content.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 10px; -fx-border-color: #cccccc; -fx-border-width: 1px; -fx-border-radius: 5px;");

            setOnMouseClicked(event -> {
                if (!isEmpty()) {
                    System.out.println("Clicked on: " + getItem().getName());
                    parentView.displayCardDesign(getItem());
                }
            });
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
            setStyle("-fx-border-color: transparent; -fx-background-color: #E6D4E6;");
        }
    }
}
