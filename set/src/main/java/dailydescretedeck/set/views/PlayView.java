package dailydescretedeck.set.views;

import dailydescretedeck.set.viewmodels.BoardViewModel;
import dailydescretedeck.set.viewmodels.PlayViewModel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PlayView extends BorderPane {
    private final PlayViewModel playViewModel;
    private final BoardView boardView;

    public PlayView(PlayViewModel playViewModel, Runnable onBackToMenu) {
        this.playViewModel = playViewModel;
        BoardViewModel boardViewModel = new BoardViewModel(playViewModel.getBoardState().getBoard());
        this.boardView = new BoardView(boardViewModel);

        Button backButton = new Button("Back to Menu");
        backButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");
        backButton.setOnAction(event -> onBackToMenu.run());

        setCenter(boardView);
        setBottom(backButton);
        BorderPane.setAlignment(backButton, Pos.CENTER);
    }

    public void display(Stage stage) {
        Scene scene = new Scene(this, stage.getWidth(), stage.getHeight());
        scene.getRoot().setStyle("-fx-background-color: thistle;");
        stage.setScene(scene);
        stage.setTitle("Set");
        stage.show();
    }
}
