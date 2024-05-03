package viewandmodels.set;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("bye.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Scene scene2 = new Scene(fxmlLoader2.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setScene(scene2);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}