package dailydescretedeck.set.views;

import dailydescretedeck.set.models.Card;
import dailydescretedeck.set.models.CardDesign;
import dailydescretedeck.set.models.Player;
import dailydescretedeck.set.models.Product;
import dailydescretedeck.set.viewmodels.StoreViewModel;
import dailydescretedeck.set.views.carddesignes.DefaultCardDesign;
import dailydescretedeck.set.views.carddesignes.EmoCardDesign;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;

import java.util.List;

import static java.lang.Double.min;

public class StoreView extends VBox {
    private StoreViewModel storeViewModel;
    private int currentProduct = 0;
    private Player player;
    private Stage stage;
    private ArrayList<? extends CardDesign> products;
    ;

    public StoreView(Stage stage, Player player) {
        products = (ArrayList<? extends CardDesign>)List.of(new DefaultCardDesign(), new EmoCardDesign());
        this.storeViewModel = new StoreViewModel(player);
        this.player = player;
        this.stage = stage;
    }

    public StoreView(Stage stage, StoreViewModel svm, Player player) {
        this.storeViewModel = svm;
        this.player = player;
        this.stage = stage;
    }

    public void display() {
        Scene scene = new Scene(this, stage.getWidth(),stage.getHeight());
        scene.getRoot().setStyle("-fx-background-color: thistle;");
        stage.setScene(scene);
        stage.setTitle("Set");
        stage.show();

        HBox cardsHBox = new HBox();
        double width = getWidth();
        double height = getHeight();

        double square = min(width, height);

        Font font = new Font("System",  2);

        Label titleLabel = new Label("Store");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button previousButton = new Button("Previous");
        previousButton.setPrefWidth(100);
        previousButton.setPrefHeight(40);
        previousButton.setFont(Font.font("System", 18));
        previousButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");
        previousButton.setOnAction(event -> {
            if (currentProduct == 0) currentProduct = products.size() - 1;          //powtorzenie trzeba zmienic
            currentProduct--;
            System.out.println("Previous");
            cardsHBox.getChildren().clear();
            if(currentProduct == 0) {
                cardsHBox.getChildren().add(new CardView(new Card(products.getLast()), 0, 0, 0.6));
            }
            else {
                cardsHBox.getChildren().add(new CardView(new Card(products.get(currentProduct - 1)), 0, 0, 0.6));
            }
            cardsHBox.getChildren().add(new CardView(new Card(products.get(currentProduct)), 0, 0, 1));
            if(currentProduct == products.size() - 1) {
                cardsHBox.getChildren().add(new CardView(new Card(products.getFirst()), 0, 0, 0.6));
            }
            else {
                cardsHBox.getChildren().add(new CardView(new Card(products.get(currentProduct + 1)), 0, 0, 0.6));
            }
        });

        Button nextButton = new Button("Next");
        nextButton.setPrefWidth(100);
        nextButton.setPrefHeight(40);
        nextButton.setFont(Font.font("System", 18));
        nextButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");
        nextButton.setOnAction(event -> {
            System.out.println("Previous");
            currentProduct++;
            if (currentProduct == products.size() - 1) currentProduct = 0;          //powtorzenie trzeba zmienic

            cardsHBox.getChildren().clear();
            if(currentProduct == 0) {
                cardsHBox.getChildren().add(new CardView(new Card(products.getLast()), 0, 0, 0.6));
            }
            else {
                cardsHBox.getChildren().add(new CardView(new Card(products.get(currentProduct - 1)), 0, 0, 0.6));
            }
            cardsHBox.getChildren().add(new CardView(new Card(products.get(currentProduct)), 0, 0, 1));
            if(currentProduct == products.size() - 1) {
                cardsHBox.getChildren().add(new CardView(new Card(products.getFirst()), 0, 0, 0.6));
            }
            else {
                cardsHBox.getChildren().add(new CardView(new Card(products.get(currentProduct + 1)), 0, 0, 0.6));
            }
        });

        getChildren().addAll(titleLabel, cardsHBox);
    }

    private static class ProductView extends StackPane {
        private Circle circle;
        private Label nameLabel;
        private Color originalColor;
        private boolean isSelected = false;

        public ProductView(Product product, double x, double y, double scale) {
            originalColor = Color.valueOf(product.getName().split(" ")[0].toUpperCase());
            circle = new Circle(5 * scale);
            circle.setFill(originalColor);
            circle.setLayoutX(x);
            circle.setLayoutY(y);

            nameLabel = new Label(product.getName());
            nameLabel.setFont(Font.font("Comic Sans MS", 10 * scale));

            getChildren().addAll(circle);

            setOnMouseClicked(event -> toggleSelect());
        }

        private void toggleSelect() {
            if (isSelected) {
                unselect();
            } else {
                select();
            }
        }

        private void select() {
            circle.setFill(originalColor.darker());
            isSelected = true;
        }

        private void unselect() {
            circle.setFill(originalColor);
            isSelected = false;
        }
    }
}
