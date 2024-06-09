package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.models.Player;
import dailydescretedeck.set.models.SimpleBoardState;
import dailydescretedeck.set.services.PlayerName;
import dailydescretedeck.set.services.SavingService;
import dailydescretedeck.set.views.*;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.util.Optional;

public class Scenes {
    private static Stage primaryStage;
    public static Player player;
    private static BuyCardsViewModel buyCardsViewModel;

    public void showMenuView() {
        MenuViewModel menuViewModel = new MenuViewModel();
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

    public void showInstructionsView() {
        InstructionsView instructionsView = new InstructionsView();
        Scene scene = new Scene(instructionsView, 520, 720);
        Stage newWindow = new Stage();
        newWindow.setScene(scene);
        newWindow.setResizable(false);
        newWindow.setTitle("Instructions");
        newWindow.show();
    }

    public void showStoreView() {
        StoreViewModel storeViewModel = new StoreViewModel(player);
        StoreView storeView = new StoreView(storeViewModel);
        Scene scene = new Scene(storeView, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Store");
    }

    public void showCartView() {
        if (buyCardsViewModel == null) {
            buyCardsViewModel = new BuyCardsViewModel(player);
        }
        CartView cartView = new CartView(buyCardsViewModel);
        Scene scene = new Scene(cartView, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cart");
    }

    public void showBuyCardsView() {
        if (buyCardsViewModel == null) {
            buyCardsViewModel = new BuyCardsViewModel(player);
        }
        BuyCardsView buyCardsView = new BuyCardsView(buyCardsViewModel);
        Scene scene = new Scene(buyCardsView, 1000, 800);
        primaryStage.setScene(scene);
    }

    public void showOwnedCardsView() {
        OwnedCardsViewModel ownedCardsViewModel = new OwnedCardsViewModel();
        OwnedCardsView ownedCardsView = new OwnedCardsView(ownedCardsViewModel);
        Scene scene = new Scene(ownedCardsView, 1000, 800);
        primaryStage.setScene(scene);
    }

    public void showUsernameAlert() {
        UsernameAlert usernameAlert = new UsernameAlert();
        Scene scene = new Scene(usernameAlert, 300, 200);
        Stage newWindow = new Stage();
        newWindow.setScene(scene);
        newWindow.setResizable(false);
        newWindow.setTitle("Set");
        newWindow.showAndWait();
    }

    public static void setPrimaryStage(Stage ps) {
        primaryStage = ps;
    }

    public static void setPlayer(Player player) {
        Scenes.player = player;
    }

    public static Player getPlayer() {
        return player;
    }

    public boolean hasItemsInCart() {
        return buyCardsViewModel != null && buyCardsViewModel.hasItemsInCart();
    }
}
