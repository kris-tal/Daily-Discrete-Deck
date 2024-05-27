package dailydescretedeck.set.views;

import dailydescretedeck.set.models.Card;
import dailydescretedeck.set.models.CardDesign;
import dailydescretedeck.set.models.Player;
import dailydescretedeck.set.viewmodels.StoreViewModel;
import dailydescretedeck.set.views.carddesignes.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;

import java.util.List;

public class StoreView extends VBox {
    private StoreViewModel storeViewModel;
    private int currentProduct = 0;
    private Player player;
    private Stage stage;
    private ArrayList<CardDesign> products;


    public StoreView(Stage stage, Player player) {
        products = new ArrayList<>(List.of(new DefaultCardDesign(),
                new JHCardDesign(),
                new RedCardDesign(),
                new BlueCardDesign()));
        this.storeViewModel = new StoreViewModel(player);
        this.player = player;
        this.stage = stage;
    }

    public StoreView(Stage stage, StoreViewModel svm, Player player) {
        products = new ArrayList<>(List.of(new DefaultCardDesign(),
                new JHCardDesign(),
                new RedCardDesign(),
                new BlueCardDesign()));
        this.storeViewModel = svm;
        this.player = player;
        this.stage = stage;
    }

    public void display() {
        HBox cardsHBox = new HBox();
        cardsHBox.setSpacing(60);
        cardsHBox.setAlignment(Pos.CENTER);

        double sideCardSize = 1.5;
        double mainCardSize = 2.5;
        double sideCardOffset = 180 * (mainCardSize / 2 - sideCardSize / 2);

        Font font = new Font("System", 2);

        Label titleLabel = new Label("Store");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        cardsHBox.setPadding(new javafx.geometry.Insets(20));
        titleLabel.setPadding(new javafx.geometry.Insets(20));

        Button nextButton = new Button(">");

        Button previousButton = new Button("<");
        previousButton.setPrefWidth(100);
        previousButton.setPrefHeight(40);
        previousButton.setFont(Font.font(18));
        previousButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");
        previousButton.setOnAction(event -> {
            if (currentProduct == 0) currentProduct = products.size() - 1;          //powtorzenie trzeba zmienic
            else currentProduct--;
            cardsHBox.getChildren().clear();
            cardsHBox.getChildren().add(previousButton);
            if (currentProduct == 0) {
                cardsHBox.getChildren().add(new CardView(new Card(products.get(products.size() - 1)), 0, sideCardOffset, sideCardSize));
            } else {
                cardsHBox.getChildren().add(new CardView(new Card(products.get(currentProduct - 1)), 0, sideCardOffset, sideCardSize));
            }
            cardsHBox.getChildren().add(new CardView(new Card(products.get(currentProduct)), 0, 0, mainCardSize));
            if (currentProduct == products.size() - 1) {
                cardsHBox.getChildren().add(new CardView(new Card(products.get(0)), 0, sideCardOffset, sideCardSize));
            } else {
                cardsHBox.getChildren().add(new CardView(new Card(products.get(currentProduct + 1)), 0, sideCardOffset, sideCardSize));
            }
            cardsHBox.getChildren().add(nextButton);
        });


        nextButton.setPrefWidth(100);
        nextButton.setPrefHeight(40);
        nextButton.setFont(Font.font( 18));
        nextButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");
        nextButton.setOnAction(event -> {
            System.out.println("Next");
            cardsHBox.getChildren().clear();
            cardsHBox.getChildren().add(previousButton);
            if (currentProduct == products.size() - 1) currentProduct = 0;
            else currentProduct++;                                       //powtorzenie trzeba zmienic
            if (currentProduct == 0) {
                cardsHBox.getChildren().add(new CardView(new Card(products.get(products.size() - 1)), 0, sideCardOffset, sideCardSize));
            } else {
                cardsHBox.getChildren().add(new CardView(new Card(products.get(currentProduct - 1)), 0, sideCardOffset, sideCardSize));
            }
            cardsHBox.getChildren().add(new CardView(new Card(products.get(currentProduct)), 0, 0, mainCardSize));
            if (currentProduct == products.size() - 1) {
                cardsHBox.getChildren().add(new CardView(new Card(products.get(0)), 0, sideCardOffset, sideCardSize));
            } else {
                cardsHBox.getChildren().add(new CardView(new Card(products.get(currentProduct + 1)), 0, sideCardOffset, sideCardSize));
            }
            cardsHBox.getChildren().add(nextButton);
        });

        cardsHBox.getChildren().addAll(previousButton, new CardView(new Card(products.get(products.size() - 1)), 0, sideCardOffset, sideCardSize),
                new CardView(new Card(products.get(0)), 0, 0, mainCardSize),
                new CardView(new Card(products.get(1)), 0, sideCardOffset, sideCardSize),
                nextButton);

        getChildren().addAll(titleLabel, cardsHBox);
        Scene scene = new Scene(this, stage.getWidth(), stage.getHeight());
        scene.getRoot().setStyle("-fx-background-color: thistle;");
        stage.setScene(scene);
        stage.setTitle("Set");
        stage.show();
    }

}