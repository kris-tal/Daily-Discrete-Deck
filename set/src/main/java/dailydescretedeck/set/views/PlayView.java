package dailydescretedeck.set.views;

import dailydescretedeck.set.viewmodels.BoardViewModel;
import dailydescretedeck.set.viewmodels.PlayViewModel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PlayView extends StackPane {
    private final PlayViewModel playViewModel;
    private final BoardView boardView;

    public PlayView(PlayViewModel playViewModel, Runnable onBackToMenu) {
        this.playViewModel = playViewModel;
        BoardViewModel boardViewModel = new BoardViewModel(playViewModel.getBoardState().getBoard());
        this.boardView = new BoardView(boardViewModel);
        setAlignment(Pos.CENTER);
        getChildren().add(boardView);
    }

    public void display(Stage stage) {
        Scene scene = new Scene(this, stage.getWidth(), stage.getHeight());
        scene.getRoot().setStyle("-fx-background-color: thistle;");
        stage.setScene(scene);
        stage.setTitle("Set");
        stage.show();
    }
}
