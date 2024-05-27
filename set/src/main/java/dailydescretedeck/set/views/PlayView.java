package dailydescretedeck.set.views;

import dailydescretedeck.set.models.BoardState;
import dailydescretedeck.set.viewmodels.BoardViewModel;
import javafx.scene.layout.StackPane;

public class PlayView extends StackPane {
    private final BoardView boardView;

    public PlayView(BoardState boardState, Runnable onBackToMenu) {
        BoardViewModel boardViewModel = new BoardViewModel(boardState.getBoard());
        this.boardView = new BoardView(boardViewModel, onBackToMenu);

        getChildren().add(boardView);
    }
}
