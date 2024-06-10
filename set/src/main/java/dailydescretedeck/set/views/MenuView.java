package dailydescretedeck.set.views;

import dailydescretedeck.set.viewmodels.MenuViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.Objects;

public class MenuView extends VBox {
    private MenuViewModel menuViewModel;

    public MenuView(MenuViewModel mvm) {
        menuViewModel = mvm;
        initializeComponents();
    }


    private void initializeComponents() {

        VBox buttonsLayout = new VBox();
        Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/dailydescretedeck/set/images/logo.png")));
        ImageView logoView = new ImageView(logo);
        logoView.setPreserveRatio(true);
        setAlignment(Pos.CENTER);

        logoView.fitWidthProperty().bind(widthProperty().multiply(0.5));
        logoView.fitHeightProperty().bind(heightProperty().multiply(0.5));



        setStyle("-fx-background-color: thistle;");
        Button playSetButton = new MyButton("START");
        playSetButton.prefWidthProperty().bind(logoView.fitWidthProperty());
        playSetButton.prefHeightProperty().bind(logoView.fitHeightProperty().multiply(0.2));
        playSetButton.setOnAction(event -> menuViewModel.handleInput(MenuViewModel.MenuOptions.PLAY));

        Button storeButton = new MyButton("STORE");
        storeButton.prefWidthProperty().bind(logoView.fitWidthProperty());
        storeButton.prefHeightProperty().bind(logoView.fitHeightProperty().multiply(0.2));
        storeButton.setOnAction(event -> menuViewModel.handleInput(MenuViewModel.MenuOptions.STORE));

        Button profileButton = new MyButton("PROFILE");
        profileButton.setOnAction(event -> menuViewModel.handleInput(MenuViewModel.MenuOptions.PROFILE));
        profileButton.prefWidthProperty().bind(logoView.fitWidthProperty());
        profileButton.prefHeightProperty().bind(logoView.fitHeightProperty().multiply(0.2));

        Button instructionsButton = new MyButton("HOW TO PLAY");
        instructionsButton.prefWidthProperty().bind(logoView.fitWidthProperty());
        instructionsButton.prefHeightProperty().bind(logoView.fitHeightProperty().multiply(0.2));
        instructionsButton.setOnAction(event -> menuViewModel.handleInput(MenuViewModel.MenuOptions.INSTRUCTIONS));

        setSpacing(20);
        buttonsLayout.setAlignment(Pos.CENTER);
        buttonsLayout.setSpacing(20);
        buttonsLayout.setPadding(new javafx.geometry.Insets(20));
        buttonsLayout.getChildren().addAll(playSetButton, storeButton, profileButton, instructionsButton);
        getChildren().addAll(logoView, buttonsLayout);
    }


}
