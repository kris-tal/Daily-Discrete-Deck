package dailydescretedeck.set;

import dailydescretedeck.set.models.Player;
import dailydescretedeck.set.models.SimpleBoardState;
import dailydescretedeck.set.viewmodels.PlayViewModel;
import dailydescretedeck.set.viewmodels.ProfileViewModel;
import dailydescretedeck.set.viewmodels.StoreViewModel;
import dailydescretedeck.set.views.MenuView;
import dailydescretedeck.set.views.PlayView;
import dailydescretedeck.set.views.ProfileView;
import dailydescretedeck.set.views.StoreView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        MenuView menuView = new MenuView(this::showPlayView, this::showStoreView, this::showProfileView);
        Scene scene = new Scene(menuView, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Menu");
        primaryStage.show();
    }

    private void showPlayView() {
        SimpleBoardState boardState = new SimpleBoardState(7);
        PlayView playView = new PlayView(boardState, this::showMenu);
        Scene scene = new Scene(playView, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Set");
    }

    private void showProfileView() {
        ProfileViewModel profileViewModel = new ProfileViewModel();
        ProfileView profileView = new ProfileView(profileViewModel, this::showMenu);
        Scene scene = new Scene(profileView, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Profile");
    }

    private void showStoreView() {
        StoreView storeView = new StoreView(storeViewModel, primaryStage, this::showMenu);
        Scene scene = new Scene(storeView, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Store");
    }

    public static void main(String[] args) {
        launch();
    }
}
