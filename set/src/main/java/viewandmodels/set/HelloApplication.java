package viewandmodels.set;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("bye.fxml"));

        Scene scene1 = new Scene(fxmlLoader.load(), 320, 240);
        Scene scene2 = new Scene(fxmlLoader2.load(), 320, 240);

        Stage secondStage = new Stage();

        primaryStage.setTitle("Hello!");
        primaryStage.setScene(scene1);

        secondStage.setTitle("Bye!");
        secondStage.setScene(scene2);

        primaryStage.show();
        secondStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
