package dailydescretedeck.set;


import dailydescretedeck.set.models.Player;
import dailydescretedeck.set.models.SimpleBoardState;
import dailydescretedeck.set.viewmodels.MenuViewModel;
import dailydescretedeck.set.viewmodels.StoreViewModel;
import dailydescretedeck.set.views.MenuView;
import dailydescretedeck.set.views.PlayView;
import dailydescretedeck.set.views.StoreView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Set extends Application {
    private Stage primaryStage;
    private Player player;
    private StoreViewModel storeViewModel;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        this.player = new Player("Player1", "password");
        this.storeViewModel = new StoreViewModel(player);

        showMenu();
    }

    private void showMenu() {
        MenuView menuView = new MenuView(this::showPlayView, this::showStoreView);
        Scene scene = new Scene(menuView, 1000, 800);
        scene.setUserData(this);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Menu");
        primaryStage.show();
    }

    private void showPlayView() {
        SimpleBoardState boardState = new SimpleBoardState(7);
        PlayView playView = new PlayView(boardState, this::showMenu);
        Scene scene = new Scene(playView, 1000, 800);
        scene.setUserData(this);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Set");
    }

    private void showStoreView() {
        StoreView storeView = new StoreView(storeViewModel, primaryStage, this::showMenu);
        Scene scene = new Scene(storeView, 1000, 800);
        scene.setUserData(this);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Store");
    }

    @Override
    public void start(Stage stage) throws Exception {
        MenuViewModel menuViewModel = new MenuViewModel();
        MenuView menuView = new MenuView(menuViewModel, stage);
        Scene scene = new Scene(menuView, 1000, 800);
        try {
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/icon.png")));
            stage.getIcons().add(icon);
        } catch(RuntimeException image) { System.out.println("Image not found"); }
        scene.getRoot().setStyle("-fx-background-color: thistle;");
        stage.setScene(scene);
        stage.setTitle("Set");
        stage.show();

        menuView.display(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}







