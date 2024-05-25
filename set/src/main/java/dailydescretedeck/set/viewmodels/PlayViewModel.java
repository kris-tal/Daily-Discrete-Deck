package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.models.BoardState;
import dailydescretedeck.set.models.SimpleBoardState;

public class PlayViewModel {
    private BoardState boardState;

    public PlayViewModel() {
        this.boardState = new SimpleBoardState(7);
    }

    public void handleInput(String input) {
        // input handling
    }

    public void updateGameState() {
        boardState.update();
    }

    public BoardState getBoardState() {
        return boardState;
    }
}