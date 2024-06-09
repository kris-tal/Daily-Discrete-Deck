package dailydescretedeck.set.views;

import dailydescretedeck.set.models.Product;
import dailydescretedeck.set.viewmodels.BuyCardsViewModel;
import dailydescretedeck.set.viewmodels.CardDesign;
import dailydescretedeck.set.viewmodels.Scenes;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dailydescretedeck.set.viewmodels.Scenes.player;
import static java.lang.Double.min;
import static javafx.scene.paint.Color.THISTLE;
import static javafx.scene.paint.Color.WHITE;

public class BuyCardsView extends Pane {
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
        setBackground(Background.fill(THISTLE));
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

        double square = min(paneWidth, paneHeight) * 0.09;
        double bigRectWidth = square * 9;
        double bigRectHeight = square * 7;

        double bigRectX = (paneWidth - bigRectWidth) / 2;
        double bigRectY = (paneHeight - bigRectHeight) / 2;

        Rectangle bigRect = new Rectangle(bigRectX, bigRectY, bigRectWidth, bigRectHeight);
        bigRect.setFill(THISTLE);
        getChildren().add(bigRect);

        gap = square / 5;

        double startX = bigRectX + gap;
        double startY = bigRectY + gap / 2;

        Font font = new Font("Comic Sans MS", gap * 2);

        Label totalCostLabel = new Label();
        totalCostLabel.textProperty().bind(buyCardsViewModel.getTotalCost().asString("Total Cost: %d"));
        totalCostLabel.setFont(font);
        totalCostLabel.setLayoutX(gap);
        totalCostLabel.setLayoutY(gap);
        getChildren().add(totalCostLabel);

        /*
        Label playerMoneyLabel = new Label();
        playerMoneyLabel.textProperty().bind(buyCardsViewModel.getPlayerMoney().asString("Money: %d"));
        playerMoneyLabel.setFont(font);
        playerMoneyLabel.setLayoutX(gap);
        playerMoneyLabel.setLayoutY(gap * 3.5);
        getChildren().add(playerMoneyLabel);

         */

        Label playerMoneyLabel = new Label();
        playerMoneyLabel.setText("Money: " + player.getMoney());
        playerMoneyLabel.setFont(font);
        playerMoneyLabel.setLayoutX(gap);
        playerMoneyLabel.setLayoutY(gap * 3.5);
        getChildren().add(playerMoneyLabel);

        Label selectedProductsLabel = new Label();
        selectedProductsLabel.textProperty().bind(buyCardsViewModel.getSelectedProductsCount().asString("Selected Products: %d"));
        selectedProductsLabel.setFont(font);
        selectedProductsLabel.setLayoutX(gap);
        selectedProductsLabel.setLayoutY(gap * 6);
        getChildren().add(selectedProductsLabel);

        List<Product> products = buyCardsViewModel.getProducts().subList(currentPage * itemsPerPage,
                Math.min((currentPage + 1) * itemsPerPage, buyCardsViewModel.getProducts().size()));

        int productIndex = 0;

        for (int row = 0; row < 3; row++) {
            double rowY = startY + row * (square * 2.4 + gap);

            for (int col = 0; col < 2; col++) {
                if (productIndex >= products.size()) {
                    break;
                }

                Product product = products.get(productIndex++);
                ProductView productView = new ProductView(product, buyCardsViewModel, square / 8.5);
                productViews.put(product, productView);
                productView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    if (!selectedProducts.contains(product)) {
                        selectedProducts.add(product);
                        productView.select();
                    }
                });

                StackPane productPane = new StackPane();
                productPane.getChildren().add(productView);

                productPane.setLayoutX(startX + col * (square * 4.5 + gap));
                productPane.setLayoutY(rowY);

                getChildren().add(productPane);

                Label priceLabel = new Label(String.valueOf(product.getPrice()));
                priceLabel.setFont(Font.font("Comic Sans MS", gap * 2.0));
                priceLabel.setLayoutX(startX + col * (square * 4.5 + gap));
                priceLabel.setLayoutY(rowY + square + gap / 2);
                getChildren().add(priceLabel);

                Label nameLabel = new Label(String.valueOf(product.getName()));
                nameLabel.setFont(Font.font("Comic Sans MS", gap * 2.0));
                nameLabel.setLayoutX(startX + col * (square * 4.5 + gap));
                nameLabel.setLayoutY(rowY + 0.5 * square + gap / 2);
                getChildren().add(nameLabel);
            }
        }

        Button backButton = new Button("Back to Store");
        backButton.setLayoutX(gap);
        backButton.setLayoutY(bigRectY + bigRectHeight + gap);
        backButton.setPrefWidth(bigRectWidth / 3 - gap);
        backButton.setPrefHeight(40);
        backButton.setFont(Font.font("System", 18));
        backButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");
        backButton.setOnAction(event -> {
            if (buyCardsViewModel.hasItemsInCart()) {
                AestheticAlert.showAlert("Error", "Please finalize your purchase or empty your cart before leaving.");
            } else {
                scenes.showStoreView();
            }
        });

        Button cartButton = new Button("View Cart");
        cartButton.setLayoutX(gap + bigRectWidth / 3 + gap);
        cartButton.setLayoutY(bigRectY + bigRectHeight + gap);
        cartButton.setPrefWidth(bigRectWidth / 3 - gap);
        cartButton.setPrefHeight(40);
        cartButton.setFont(Font.font("System", 18));
        cartButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");
        cartButton.setOnAction(event -> scenes.showCartView());

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
            if ((currentPage + 1) * itemsPerPage < buyCardsViewModel.getProducts().size()) {
                currentPage++;
                redrawView();
            }
        });

        getChildren().addAll(backButton, cartButton, previousButton, nextButton);
    }


    private static class ProductView extends StackPane {
        private Circle[] circles;
        private Color[] originalColors;
        private boolean isSelected = false;

        public ProductView(Product product, BuyCardsViewModel bcvm, double scale) {
            circles = new Circle[6];
            originalColors = new Color[6];
            CardDesign design = product.getDesign();
            HBox hbox = new HBox(5); // spacing between circles

            for (int i = 0; i < circles.length; i++) {
                circles[i] = new Circle(2.5 * scale);
                originalColors[i] = design.getColor(i + 1);
                circles[i].setFill(originalColors[i]);
                hbox.getChildren().add(circles[i]);
            }

            getChildren().add(hbox);

            setOnMouseEntered(event -> setCirclesColor(0.1));
            setOnMouseExited(event -> {
                if (!isSelected) {
                    resetCirclesColor();
                }
            });

            setOnMouseClicked(event -> {
                if (isSelected) {
                    bcvm.addToCart(product);
                    selectPermanently();
                } else {
                    bcvm.addToCart(product);
                    select();
                }
            });
        }

        private void setCirclesColor(double factor) {
            for (int i = 0; i < circles.length; i++) {
                circles[i].setFill(darkenColor(originalColors[i], factor));
            }
        }

        private void resetCirclesColor() {
            for (int i = 0; i < circles.length; i++) {
                circles[i].setFill(originalColors[i]);
            }
        }

        private void select() {
            if (!isSelected) {
                setCirclesColor(0.3);
                isSelected = true;
            }
        }

        private void selectPermanently() {
            setCirclesColor(0.3);
        }

        private Color darkenColor(Color color, double factor) {
            return color.deriveColor(0, 1, 1 - factor, 1);
        }
    }

}
