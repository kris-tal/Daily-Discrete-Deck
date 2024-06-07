package dailydescretedeck.set;

import dailydescretedeck.set.models.Player;
import dailydescretedeck.set.models.SimpleBoardState;
import dailydescretedeck.set.viewmodels.ProfileViewModel;
import dailydescretedeck.set.viewmodels.Scenes;
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

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        //wczytaj playera, jesli nie ma to okienko z imieniem i stworz nowego
        this.player = new Player("Player1");
        Scenes.setPrimaryStage(primaryStage);
        Scenes scenes = new Scenes();
        scenes.showMenuView();
    }

    public static void main(String[] args) {
        launch();
    }
}
