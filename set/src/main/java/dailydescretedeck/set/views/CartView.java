package dailydescretedeck.set.views;

import dailydescretedeck.set.models.Product;
import dailydescretedeck.set.viewmodels.BuyCardsViewModel;
import dailydescretedeck.set.viewmodels.Scenes;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CartView extends Pane {
    private BuyCardsViewModel buyCardsViewModel;
    private Scenes scenes;

    public CartView(BuyCardsViewModel viewModel) {
        this.buyCardsViewModel = viewModel;
        this.scenes = new Scenes();
        setPrefSize(400, 600);
        initialize();
    }

    private void initialize() {
        getChildren().clear();

        Label titleLabel = new Label("Cart");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        ListView<Product> cartListView = new ListView<>(buyCardsViewModel.getCartItems());
        cartListView.setCellFactory(parameter -> new ProductCell(buyCardsViewModel));

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> scenes.showBuyCardsView());

        Button finaliseButton = new Button("Finalise Purchase");
        finaliseButton.setOnAction(event -> finalisePurchase());

        VBox vbox = new VBox(10, titleLabel, cartListView, backButton, finaliseButton);
        vbox.setPadding(new Insets(10));
        vbox.setPrefSize(400, 600);
        getChildren().add(vbox);
    }

    private void finalisePurchase() {
        int purchaseState = buyCardsViewModel.checkout();
        if (purchaseState == 0) {
            AestheticAlert.showAlert("Cart Empty", "Your cart is empty. Please add items to your cart before finalising the purchase.");
        } else if (purchaseState == 1) {
            AestheticAlert.showAlert("Purchase Failed", "You do not have enough money to complete this purchase.");
        } else {
            scenes.showStoreView();
        }
    }

    private static class ProductCell extends ListCell<Product> {
        private HBox content;
        private Label nameLabel;
        private Label priceLabel;
        private Button removeButton;

        public ProductCell(BuyCardsViewModel viewModel) {
            super();
            nameLabel = new Label();
            priceLabel = new Label();
            removeButton = new Button("Remove");
            removeButton.setOnAction(event -> viewModel.removeFromCart(getItem()));
            content = new HBox(10, nameLabel, priceLabel, removeButton);
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
