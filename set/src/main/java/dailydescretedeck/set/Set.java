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
        this.primaryStage = stage;
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
        this.player = new Player(playerName.getName());
        System.out.println("Player: " + playerName.getName());
        try {
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/icon.png")));
            stage.getIcons().add(icon);
        } catch(RuntimeException image) { System.out.println("Image not found"); }
        Scenes.setPrimaryStage(primaryStage);
        scenes = new Scenes();
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
