package dailydescretedeck.set;

import dailydescretedeck.set.models.Player;
import dailydescretedeck.set.models.SimpleBoardState;
import dailydescretedeck.set.viewmodels.MenuViewModel;
import dailydescretedeck.set.viewmodels.PlayViewModel;
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
        MenuViewModel menuViewModel = new MenuViewModel(this::showPlayView, this::showStoreView);
        MenuView menuView = new MenuView(menuViewModel, primaryStage);
        Scene scene = new Scene(menuView, 1000, 800);
        scene.setUserData(this);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Menu");
        primaryStage.show();
    }

    private void showPlayView() {
        SimpleBoardState boardState = new SimpleBoardState(7);
        PlayViewModel playViewModel = new PlayViewModel(boardState);
        PlayView playView = new PlayView(playViewModel, this::showMenu);
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

    public static void main(String[] args) {
        launch();
    }
}
