package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.models.SimpleBoardState;

public class PlayViewModel {
    private SimpleBoardState boardState;

    public PlayViewModel(SimpleBoardState boardState) {
        this.boardState = boardState;
    }

    public SimpleBoardState getBoardState() {
        return boardState;
    }
}
