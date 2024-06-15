package dailydescretedeck.set.views;

import dailydescretedeck.set.models.Product;
import dailydescretedeck.set.viewmodels.BuyCardsViewModel;
import dailydescretedeck.set.viewmodels.Scenes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dailydescretedeck.set.viewmodels.Scenes.player;
import static javafx.scene.paint.Color.THISTLE;

public class BuyCardsView extends VBox {
    private BuyCardsViewModel buyCardsViewModel;
    private Scenes scenes;
    private double gap;
    private Map<Product, ProductView> productViews = new HashMap<>();
    private ObservableList<Product> selectedProducts = FXCollections.observableArrayList();
    private int currentPage = 0;
    private final int itemsPerPage = 6;

    public BuyCardsView(BuyCardsViewModel viewModel) {
        this.buyCardsViewModel = viewModel;
        this.scenes = new Scenes();
        setBackground(new Background(new BackgroundFill(THISTLE, CornerRadii.EMPTY, Insets.EMPTY)));
        setPrefSize(1000, 800);
        setPadding(new Insets(10));
        setSpacing(20);
        setAlignment(Pos.CENTER);
        initializeComponents();
    }

    private void initializeComponents() {
        gap = 20;

        Font font = new Font("Comic Sans MS", 20);

        VBox labelsBox = new VBox(gap);
        labelsBox.setAlignment(Pos.TOP_LEFT);

        Label totalCostLabel = new Label();
        totalCostLabel.textProperty().bind(buyCardsViewModel.getTotalCost().asString("Total Cost: %d"));
        totalCostLabel.setFont(font);
        labelsBox.getChildren().add(totalCostLabel);

        Label playerMoneyLabel = new Label();
        playerMoneyLabel.setText("Money: " + player.getMoney());
        playerMoneyLabel.setFont(font);
        labelsBox.getChildren().add(playerMoneyLabel);

        Label selectedProductsLabel = new Label();
        selectedProductsLabel.textProperty().bind(buyCardsViewModel.getSelectedProductsCount().asString("Selected Products: %d"));
        selectedProductsLabel.setFont(font);
        labelsBox.getChildren().add(selectedProductsLabel);

        VBox productsContainer = new VBox(gap);
        productsContainer.setAlignment(Pos.CENTER);

        updateProducts(productsContainer, font);

        HBox navigationButtonsBox = new HBox(gap);
        navigationButtonsBox.setAlignment(Pos.BOTTOM_CENTER);

        Button backButton = createButton("Back to Store", font);
        backButton.setOnAction(event -> {
            if (buyCardsViewModel.hasItemsInCart()) {
                AestheticAlert.showAlert("Error", "Please finalize your purchase or empty your cart before leaving.");
            }
            else {
                scenes.showStoreView();
            }
        });

        Button cartButton = createButton("View Cart", font);
        cartButton.setOnAction(event -> scenes.showCartView());

        Button previousButton = createButton("Previous", font);
        previousButton.setOnAction(event -> {
            if (currentPage > 0) {
                currentPage--;
                updateProducts(productsContainer, font);
            }
        });

        Button nextButton = createButton("Next", font);
        nextButton.setOnAction(event -> {
            if ((currentPage + 1) * itemsPerPage < buyCardsViewModel.getProducts().size()) {
                currentPage++;
                updateProducts(productsContainer, font);
            }
        });

        navigationButtonsBox.getChildren().addAll(backButton, cartButton, previousButton, nextButton);

        getChildren().addAll(labelsBox, productsContainer, navigationButtonsBox);
    }

    private void updateProducts(VBox productsContainer, Font font) {
        productsContainer.getChildren().clear();

        List<Product> products = buyCardsViewModel.getProducts().subList(currentPage * itemsPerPage,
                Math.min((currentPage + 1) * itemsPerPage, buyCardsViewModel.getProducts().size()));

        int productIndex = 0;
        for (int row = 0; row < (itemsPerPage / 2); row++) {
            HBox rowBox = new HBox(gap);
            rowBox.setAlignment(Pos.CENTER);

            for (int col = 0; col < 2; col++) {
                if (productIndex >= products.size()) {
                    break;
                }

                Product product = products.get(productIndex++);
                ProductView productView = new ProductView(product);
                productViews.put(product, productView);
                productView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    if (!selectedProducts.contains(product)) {
                        selectedProducts.add(product);
                        productView.select();
                        buyCardsViewModel.addToCart(product);
                    }
                });

                VBox productContainer = new VBox(gap / 2);
                productContainer.setAlignment(Pos.CENTER);
                productContainer.prefWidthProperty().bind(rowBox.widthProperty().multiply(0.5).subtract(gap / 2));
                productContainer.prefHeightProperty().bind(rowBox.heightProperty());

                productView.prefWidthProperty().bind(productContainer.widthProperty());
                productView.prefHeightProperty().bind(productContainer.heightProperty().multiply(0.7));
                productView.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

                productContainer.getChildren().addAll(
                        productView,
                        createLabel(String.valueOf(product.getName()), font),
                        createLabel(String.valueOf(product.getPrice()), font)
                );

                rowBox.getChildren().add(productContainer);
            }

            productsContainer.getChildren().add(rowBox);
        }
    }

    private Label createLabel(String text, Font font) {
        Label label = new Label(text);
        label.setFont(font);
        return label;
    }

    private Button createButton(String text, Font font) {
        Button button = new Button(text);
        button.setFont(font);
        button.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");
        button.prefWidthProperty().bind(widthProperty().multiply(0.2));
        button.prefHeightProperty().bind(heightProperty().multiply(0.05));
        return button;
    }
}
