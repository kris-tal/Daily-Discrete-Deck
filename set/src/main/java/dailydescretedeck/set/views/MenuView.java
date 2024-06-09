package dailydescretedeck.set.views;

import dailydescretedeck.set.viewmodels.MenuViewModel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MenuView extends VBox {
    private MenuViewModel menuViewModel;

    public MenuView(MenuViewModel mvm) {
        menuViewModel = mvm;
        initializeComponents();
    }


    private void initializeComponents() {
        System.out.println("MenuView");

        setStyle("-fx-background-color: thistle;");
        Button playSetButton = new MyButton("START");
        playSetButton.setOnAction(event -> menuViewModel.handleInput(MenuViewModel.MenuOptions.PLAY));

        Button storeButton = new MyButton("STORE");
        storeButton.setOnAction(event -> menuViewModel.handleInput(MenuViewModel.MenuOptions.STORE));

        Button profileButton = new MyButton("PROFILE");
        profileButton.setOnAction(event -> menuViewModel.handleInput(MenuViewModel.MenuOptions.PROFILE));

        Button instructionsButton = new MyButton("HOW TO PLAY");
        instructionsButton.setOnAction(event -> menuViewModel.handleInput(MenuViewModel.MenuOptions.INSTRUCTIONS));

        setSpacing(20);
        setAlignment(Pos.CENTER);
        getChildren().addAll(playSetButton, storeButton, profileButton, instructionsButton);
    }


}
