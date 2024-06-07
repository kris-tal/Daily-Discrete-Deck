package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.models.Player;
import dailydescretedeck.set.models.SimpleBoardState;
import dailydescretedeck.set.views.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Scenes {
    private static Stage primaryStage;
    private Player player;

    public void showMenuView() {
        MenuViewModel menuViewModel = new MenuViewModel(player);
        MenuView menuView = new MenuView(menuViewModel);
        Scene scene = new Scene(menuView, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Menu");
        primaryStage.show();
    }

    public void showPlayView() {
        SimpleBoardState boardState = new SimpleBoardState(7);
        PlayView playView = new PlayView(boardState);
        Scene scene = new Scene(playView, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Set");
    }

    public void showProfileView() {
        System.out.println("Profile");
        ProfileViewModel profileViewModel = new ProfileViewModel();
        ProfileView profileView = new ProfileView(profileViewModel);
        Scene scene = new Scene(profileView, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Profile");
    }

    public void showStoreView() {
        StoreViewModel storeViewModel = new StoreViewModel(player);
        StoreView storeView = new StoreView(storeViewModel);
        Scene scene = new Scene(storeView, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Store");
    }

    public void showCartView(StoreViewModel storeViewModel) {
        CartView cartView = new CartView(storeViewModel);
        Scene scene = new Scene(cartView, 400, 600);
        primaryStage.setScene(scene);
    }

    public void showBuyCardsView(StoreViewModel storeViewModel) {
        BuyCardsViewModel buyCardsViewModel = new BuyCardsViewModel();
        BuyCardsView buyCardsView = new BuyCardsView(buyCardsViewModel, storeViewModel);
        Scene scene = new Scene(buyCardsView, primaryStage.getHeight(), primaryStage.getWidth());
        primaryStage.setScene(scene);
    }

    public static void setPrimaryStage(Stage ps) {
        primaryStage = ps;
    }
}
