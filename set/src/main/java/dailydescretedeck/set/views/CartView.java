package dailydescretedeck.set.views;

import dailydescretedeck.set.models.Product;
import dailydescretedeck.set.viewmodels.Scenes;
import dailydescretedeck.set.viewmodels.StoreViewModel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CartView extends Pane {
    private StoreViewModel viewModel;
    private Scenes scenes;
    private Runnable backToStore;

    public CartView(StoreViewModel viewModel) {
        this.viewModel = viewModel;
        this.backToStore = backToStore;
        this.scenes = new Scenes();
        setPrefSize(400, 600);
        initialize();
    }

    private void initialize() {
        getChildren().clear();

        Label titleLabel = new Label("Cart");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        ListView<Product> cartListView = new ListView<>(viewModel.getCartItems());
        cartListView.setCellFactory(param -> new ProductCell(viewModel));

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> scenes.showStoreView());

        VBox vbox = new VBox(10, titleLabel, cartListView, backButton);
        vbox.setPadding(new Insets(10));
        vbox.setPrefSize(400, 600);
        getChildren().add(vbox);
    }

    private static class ProductCell extends ListCell<Product> {
        private HBox content;
        private Label nameLabel;
        private Label priceLabel;
        private Button removeButton;

        public ProductCell(StoreViewModel viewModel) {
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
