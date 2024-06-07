package dailydescretedeck.set;

import java.util.Optional;

import dailydescretedeck.set.models.Player;
import dailydescretedeck.set.models.SimpleBoardState;
import dailydescretedeck.set.services.PlayerName;
import dailydescretedeck.set.services.SavingService;
import dailydescretedeck.set.viewmodels.ProfileViewModel;

import dailydescretedeck.set.viewmodels.Scenes;
import dailydescretedeck.set.viewmodels.StoreViewModel;
import dailydescretedeck.set.views.MenuView;
import dailydescretedeck.set.views.PlayView;
import dailydescretedeck.set.views.ProfileView;
import dailydescretedeck.set.views.StoreView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class Set extends Application {
    private Stage primaryStage;
    private Player player;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        //wczytaj playera, jesli nie ma to okienko z imieniem i stworz nowego
        PlayerName playerName = new PlayerName();
        if(playerName.getName() == null){
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Wprowadź imię");
            dialog.setHeaderText("Witaj w grze!");
            dialog.setContentText("Proszę wprowadź swoje imię:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(name -> {
            playerName.setName(name); 
            SavingService.saveNameToFile("name.txt", name); 
        });
        }
        //this.player = new Player(playerName.getName());
       // Scenes.setPrimaryStage(primaryStage);
        //Scenes scenes = new Scenes();
        //scenes.showMenuView();
    }

    public static void main(String[] args) {
        launch();
    }
}
