package dailydescretedeck.set.views;

import dailydescretedeck.set.models.Card;
import dailydescretedeck.set.viewmodels.OwnedCardsViewModel;
import dailydescretedeck.set.viewmodels.Scenes;
import javafx.beans.Observable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import dailydescretedeck.set.viewmodels.BuyCardsViewModel;
import dailydescretedeck.set.viewmodels.Scenes;
import dailydescretedeck.set.viewmodels.StoreViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class OwnedCardsView extends Pane {
    private Scenes scenes;
    private OwnedCardsViewModel ownedCardsViewModel;
    private int currentDesign = 0;

    public OwnedCardsView(OwnedCardsViewModel ownedCardsViewModel) {
        this.ownedCardsViewModel = ownedCardsViewModel;
        this.scenes = new Scenes();
        display();
    }

    public void display() {
        /*HBox cardsHBox = new HBox();
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
            if (currentDesign == 0) currentDesign = ownedCardsViewModel.getDesignsSize() - 1;          //powtorzenie trzeba zmienic
            else currentDesign--;
            System.out.println("Previous");
            cardsHBox.getChildren().clear();
            cardsHBox.getChildren().add(previousButton);
            if (currentDesign == 0) {
                cardsHBox.getChildren().add(new CardView(new Card(products.get(ownedCardsViewModel.getDesignsSize() - 1)), 0, sideCardOffset, sideCardSize));
            } else {
                cardsHBox.getChildren().add(new CardView(new Card(products.get(currentDesign - 1)), 0, sideCardOffset, sideCardSize));
            }
            cardsHBox.getChildren().add(new CardView(new Card(ownedCardsViewModel.getFullDots()), 0, 0, mainCardSize));
            if (currentDesign == ownedCardsViewModel.getDesignsSize() - 1) {
                cardsHBox.getChildren().add(new CardView(new Card(products.get(0)), 0, sideCardOffset, sideCardSize));
            } else {
                cardsHBox.getChildren().add(new CardView(new Card(products.get(currentDesign + 1)), 0, sideCardOffset, sideCardSize));
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
            if (currentDesign == ownedCardsViewModel.getDesignsSize() - 1) currentDesign = 0;
            else currentDesign++;                                       //powtorzenie trzeba zmienic
            if (currentDesign == 0) {
                cardsHBox.getChildren().add(new CardView(new Card(products.get(ownedCardsViewModel.getDesignsSize() - 1)), 0, sideCardOffset, sideCardSize));
            } else {
                cardsHBox.getChildren().add(new CardView(new Card(products.get(currentDesign - 1)), 0, sideCardOffset, sideCardSize));
            }
            cardsHBox.getChildren().add(new CardView(new Card(products.get(currentDesign)), 0, 0, mainCardSize));
            if (currentDesign == ownedCardsViewModel.getDesignsSize() - 1) {
                cardsHBox.getChildren().add(new CardView(new Card(products.get(0)), 0, sideCardOffset, sideCardSize));
            } else {
                cardsHBox.getChildren().add(new CardView(new Card(products.get(currentDesign + 1)), 0, sideCardOffset, sideCardSize));
            }
            cardsHBox.getChildren().add(nextButton);
        });

        cardsHBox.getChildren().addAll(previousButton, new CardView(new Card(products.get(ownedCardsViewModel.getDesignsSize() - 1)), 0, sideCardOffset, sideCardSize),
                new CardView(new Card(products.get(0)), 0, 0, mainCardSize),
                new CardView(new Card(products.get(1)), 0, sideCardOffset, sideCardSize),
                nextButton);

        getChildren().addAll(titleLabel, cardsHBox);
        Scene scene = new Scene(this, getWidth(), getHeight());
        scene.getRoot().setStyle("-fx-background-color: thistle;");*/
    }
}
