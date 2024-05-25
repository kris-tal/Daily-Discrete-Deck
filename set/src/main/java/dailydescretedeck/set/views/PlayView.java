package dailydescretedeck.set.views;

import dailydescretedeck.set.viewmodels.PlayViewModel;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

public class PlayView extends StackPane {
    private final PlayViewModel playViewModel;
    private BoardView boardView;

    public PlayView(PlayViewModel pvm) {
        this.playViewModel = pvm;
        this.boardView = new BoardView(playViewModel.getBoardState().getBoard());
        setAlignment(Pos.CENTER);
        getChildren().add(boardView);
    }

    public void display() {
        boardView.redrawBoard();
    }
}