package dailydescretedeck.set.views;

import dailydescretedeck.set.models.BoardState;
import dailydescretedeck.set.views.BoardView;
import dailydescretedeck.set.models.SimpleBoardState;
import dailydescretedeck.set.viewmodels.PlayViewModel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PlayView extends StackPane {
    private final PlayViewModel playViewModel;
    private BoardView boardView;

    public PlayView() {
        this.playViewModel = new PlayViewModel();
        this.boardView = new BoardView(playViewModel.getBoardState().getBoard());
        setAlignment(Pos.CENTER);
        getChildren().add(boardView);
    }

    public PlayView(PlayViewModel pvm) {
        this.playViewModel = pvm;
        this.boardView = new BoardView(playViewModel.getBoardState().getBoard());
        setAlignment(Pos.CENTER);
        getChildren().add(boardView);
    }

    public void display(Stage stage) {
        PlayView playView = new PlayView(playViewModel);
        Scene scene = new Scene(playView, stage.getWidth(),stage.getHeight());
        scene.getRoot().setStyle("-fx-background-color: thistle;");
        stage.setScene(scene);
        stage.setTitle("Set");
        stage.show();
    }
}