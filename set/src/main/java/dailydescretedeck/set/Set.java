package dailydescretedeck.set;

import java.util.Objects;
import java.util.Optional;

import dailydescretedeck.set.models.Player;
import dailydescretedeck.set.services.PlayerName;
import dailydescretedeck.set.services.SavingService;
import dailydescretedeck.set.viewmodels.Scenes;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Set extends Application {
    private Scenes scenes;
    private Stage primaryStage;
    private Player player;

    @Override
    public void start(Stage stage) {
        scenes = new Scenes();
        this.primaryStage = stage;
        PlayerName playerName = new PlayerName();
        if(playerName.getName() == null){
            scenes.showUsernameAlert();
        }
        this.player = new Player(playerName.getName());
        System.out.println("Player: " + playerName.getName());
        try {
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/icon.png")));
            stage.getIcons().add(icon);
        } catch(RuntimeException image) { System.out.println("Image not found"); }
        Scenes.setPrimaryStage(primaryStage);

        scenes.setPlayer(player);
        scenes.showMenuView();

       /*primaryStage.setOnCloseRequest(event -> {
            event.consume(); 
            handleClosing(primaryStage, event);
        });*/
    }

    /*private void handleClosing(Stage stage, WindowEvent event) {
        
    }*/

    public static void main(String[] args) {
        launch();
    }
}
