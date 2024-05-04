package dailydescretedeck.set;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Objects;

import static javafx.application.Application.launch;

public class Set extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));      //???? boje sie usuwac nie wiem co to
        Group root = new Group();
        Scene scene = new Scene(root, Color.LIGHTSLATEGRAY);
        stage.setWidth(1080);
        stage.setHeight(720);
        stage.setResizable(false);
        try {
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/icon.png")));             //laze mi dac not null inaczej warning
            stage.getIcons().add(icon);
        } catch(RuntimeException image) { System.out.println("Image not found"); }

        stage.setTitle("Daily Descrete Deck");

        stage.setScene(scene);
        stage.show();
    }

    static public void main(String[] args) {
        System.out.println("Daily Descrete Deck");
        launch(args);   //Application.launch()
    }
}
