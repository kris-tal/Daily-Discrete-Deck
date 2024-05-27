package dailydescretedeck.set;
import dailydescretedeck.set.models.Player;
import dailydescretedeck.set.viewmodels.MenuViewModel;
import dailydescretedeck.set.views.MenuView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Set extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        MenuViewModel menuViewModel = new MenuViewModel();
        MenuView menuView = new MenuView(menuViewModel, stage);
        Player player = new Player("Kozikonator", 666);
        Scene scene = new Scene(menuView, 1000, 800);
        try {
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/icon.png")));
            stage.getIcons().add(icon);
        } catch(RuntimeException image) { System.out.println("Image not found"); }
        scene.getRoot().setStyle("-fx-background-color: thistle;");
        stage.setScene(scene);
        stage.setTitle("Set");
        stage.show();


        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });

        menuView.display();
    }

    public static void main(String[] args) {
        launch();
    }
}