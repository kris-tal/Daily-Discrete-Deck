package dailydescretedeck.set.views;

import dailydescretedeck.set.models.Product;
import dailydescretedeck.set.viewmodels.BuyCardsViewModel;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Double.min;

public class BuyCardsView extends Pane {
    private BuyCardsViewModel viewModel;
    private StoreViewModel storeViewModel;
    private Stage stage;
    private Runnable backToStore;
    private double gap;
    private Map<Product, ProductView> productViews = new HashMap<>();
    private ObservableList<Product> selectedProducts = FXCollections.observableArrayList();
    private int currentPage = 0;
    private final int itemsPerPage = 6;

    public BuyCardsView(BuyCardsViewModel viewModel, StoreViewModel storeViewModel, Stage stage, Runnable backToStore) {
        this.viewModel = viewModel;
        this.storeViewModel = storeViewModel;
        this.stage = stage;
        this.backToStore = backToStore;
        setPrefSize(1000, 800);
        redrawView();

        widthProperty().addListener((observable, oldValue, newValue) -> redrawView());
        heightProperty().addListener((observable, oldValue, newValue) -> redrawView());
    }

    private void redrawView() {
        getChildren().clear();

        double paneWidth = getWidth();
        double paneHeight = getHeight();

        if (paneWidth == 0 || paneHeight == 0) {
            return;
        }

        double square = min(paneWidth, paneHeight) * 0.05;
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

        Label titleLabel = new Label("Buy Cards");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        titleLabel.setLayoutX(gap);
        titleLabel.setLayoutY(gap);
        getChildren().add(titleLabel);

        List<Product> products = viewModel.getProducts().subList(currentPage * itemsPerPage,
                Math.min((currentPage + 1) * itemsPerPage, viewModel.getProducts().size()));

        int productIndex = 0;

        for (int row = 0; row < 2; row++) {
            double rowY = startY + row * (square * 2 + gap);

            for (int col = 0; col < 3; col++) {
                if (productIndex >= products.size()) {
                    break;
                }

                Product product = products.get(productIndex++);
                ProductView productView = new ProductView(product, 0, 0, square);
                productViews.put(product, productView);

                productView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    if (!selectedProducts.contains(product)) {
                        selectedProducts.add(product);
                        productView.select();
                        storeViewModel.addToCart(product);
                    }
                });

                StackPane productPane = new StackPane();
                productPane.getChildren().add(productView);

                productPane.setLayoutX(startX + col * (square * 2 + gap));
                productPane.setLayoutY(rowY);

                getChildren().add(productPane);

                Label priceLabel = new Label(String.valueOf(product.getPrice()));
                priceLabel.setFont(Font.font("Comic Sans MS", gap * 1.5));
                priceLabel.setLayoutX(startX + col * (square * 2 + gap));
                priceLabel.setLayoutY(rowY + square + gap / 2);
                getChildren().add(priceLabel);
            }
        }

        Button backButton = new Button("Back to Store");
        backButton.setLayoutX(gap);
        backButton.setLayoutY(bigRectY + bigRectHeight + gap);
        backButton.setPrefWidth(bigRectWidth / 3 - gap);
        backButton.setPrefHeight(40);
        backButton.setFont(Font.font("System", 18));
        backButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");
        backButton.setOnAction(event -> backToStore.run());

        Button cartButton = new Button("View Cart");
        cartButton.setLayoutX(gap + bigRectWidth / 3 + gap);
        cartButton.setLayoutY(bigRectY + bigRectHeight + gap);
        cartButton.setPrefWidth(bigRectWidth / 3 - gap);
        cartButton.setPrefHeight(40);
        cartButton.setFont(Font.font("System", 18));
        cartButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");
        cartButton.setOnAction(event -> openCartView());

        Button previousButton = new Button("Previous");
        previousButton.setLayoutX(bigRectX + bigRectWidth - gap - 200);
        previousButton.setLayoutY(bigRectY + bigRectHeight + gap);
        previousButton.setPrefWidth(100);
        previousButton.setPrefHeight(40);
        previousButton.setFont(Font.font("System", 18));
        previousButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");
        previousButton.setOnAction(event -> {
            if (currentPage > 0) {
                currentPage--;
                redrawView();
            }
        });

        Button nextButton = new Button("Next");
        nextButton.setLayoutX(bigRectX + bigRectWidth - gap - 100);
        nextButton.setLayoutY(bigRectY + bigRectHeight + gap);
        nextButton.setPrefWidth(100);
        nextButton.setPrefHeight(40);
        nextButton.setFont(Font.font("System", 18));
        nextButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");
        nextButton.setOnAction(event -> {
            if ((currentPage + 1) * itemsPerPage < viewModel.getProducts().size()) {
                currentPage++;
                redrawView();
            }
        });

        getChildren().addAll(backButton, cartButton, previousButton, nextButton);
    }

    private void openCartView() {
        CartView cartView = new CartView(storeViewModel, stage, this::goBackToBuyCards);
        Scene scene = new Scene(cartView, 400, 600);
        stage.setScene(scene);
    }

    private void goBackToBuyCards() {
        Scene scene = new Scene(new BuyCardsView(viewModel, storeViewModel, stage, backToStore), getWidth(), getHeight());
        stage.setScene(scene);
    }

    private static class ProductView extends StackPane {
        private Circle circle;
        private Color originalColor;
        private boolean isSelected = false;

        public ProductView(Product product, double x, double y, double scale) {
            originalColor = Color.valueOf(product.getName().split(" ")[0].toUpperCase());
            circle = new Circle(10 * scale);
            circle.setFill(originalColor);
            circle.setLayoutX(x);
            circle.setLayoutY(y);

            getChildren().addAll(circle);

            setOnMouseEntered(event -> circle.setFill(originalColor.darker()));
            setOnMouseExited(event -> {
                if (!isSelected) {
                    circle.setFill(originalColor);
                }
            });
            setOnMouseClicked(event -> select());
        }

        private void select() {
            if (!isSelected) {
                circle.setFill(originalColor.darker().darker());
                isSelected = true;
                new Thread(() -> {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    javafx.application.Platform.runLater(() -> circle.setFill(originalColor));
                }).start();
            }
        }
    }
}
