package dailydescretedeck.set.views;

import dailydescretedeck.set.viewmodels.CardDesign;
import dailydescretedeck.set.viewmodels.OwnedCardsViewModel;
import dailydescretedeck.set.viewmodels.Scenes;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.List;

import static javafx.scene.paint.Color.THISTLE;

public class OwnedCardsView extends VBox {
    private Scenes scenes;
    private OwnedCardsViewModel ownedCardsViewModel;
    private int currentDesign = 0;
    private HBox cardsHBox;
    private List<CardDesign> products;
    private Button nextButton;
    private Button previousButton;
    private Button backButton;
    private Button selectButton;

    public OwnedCardsView(OwnedCardsViewModel ownedCardsViewModel) {
        this.ownedCardsViewModel = ownedCardsViewModel;
        this.scenes = new Scenes();
        this.products = ownedCardsViewModel.getProducts();
        setBackground(new Background(new BackgroundFill(THISTLE, CornerRadii.EMPTY, Insets.EMPTY)));
        initialize();
    }

    private void initialize() {
        setSpacing(20);
        setAlignment(Pos.CENTER);
        setPadding(new javafx.geometry.Insets(20));

        Label titleLabel = new Label("Owned Cards");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        cardsHBox = new HBox();
        cardsHBox.setSpacing(60);
        cardsHBox.setAlignment(Pos.CENTER);

        Font font = new Font("System", 2);

        nextButton = new Button(">");
        previousButton = new Button("<");

        styleButton(previousButton);
        styleButton(nextButton);

        previousButton.setOnAction(event -> navigateCards(-1));
        nextButton.setOnAction(event -> navigateCards(1));

        cardsHBox.getChildren().addAll(previousButton, createCardView(currentDesign - 1, 1.5), createCardView(currentDesign, 2.5), createCardView(currentDesign + 1, 1.5), nextButton);

        backButton = new Button("Back");
        selectButton = new Button("Select");

        styleButton(backButton);
        styleButton(selectButton);

        backButton.setOnAction(event -> scenes.showStoreView());
        selectButton.setOnAction(event -> selectCurrentDesign());

        HBox buttonBox = new HBox(10, backButton, selectButton);
        buttonBox.setAlignment(Pos.CENTER);

        getChildren().addAll(titleLabel, cardsHBox, buttonBox);
    }

    private void styleButton(Button button) {
        button.setPrefWidth(100);
        button.setPrefHeight(40);
        button.setFont(Font.font(18));
        button.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");
    }

    private CardView createCardView(int index, double size) {
        int actualIndex = (index + products.size()) % products.size();
        CardDesign cardDes = products.get(actualIndex);
        return new CardView(cardDes, 0, 0, size);
    }

    private void navigateCards(int direction) {
        currentDesign = (currentDesign + direction + products.size()) % products.size();
        cardsHBox.getChildren().setAll(previousButton, createCardView(currentDesign - 1, 1.5), createCardView(currentDesign, 2.5), createCardView(currentDesign + 1, 1.5), nextButton);
    }

    private void selectCurrentDesign() {
        CardDesign selectedDesign = products.get(currentDesign);
        ownedCardsViewModel.setCurrentDesign(selectedDesign);
        AestheticAlert.showAlert("Design Selected", "You have selected a new card design.");
    }
}
