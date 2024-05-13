package dailydescretedeck.set;

import dailydescretedeck.set.models.BoardState;
import dailydescretedeck.set.models.SimpleBoardState;
import dailydescretedeck.set.views.PlayView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Set extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BoardState boardState = new SimpleBoardState(7);
        PlayView playView = new PlayView(boardState);

        Scene scene = new Scene(playView,1000, 800);
        scene.getRoot().setStyle("-fx-background-color: thistle;");
        stage.setScene(scene);
        stage.setTitle("Set");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}