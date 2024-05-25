package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.models.BoardState;

public class PlayViewModel {
    private BoardState boardState;

    public PlayViewModel(BoardState boardState) {
        this.boardState = boardState;
    }

    public void handleInput(String input) {
        // input handling
    }

    public void updateGameState() {
        // logic
    }

    public BoardState getBoardState() {
        return boardState;
    }
}