package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.models.BoardState;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class PlayViewModel {
    private BoardState boardState;
    private IntegerProperty sets;

    public PlayViewModel(BoardState boardState) {
        this.boardState = boardState;
        this.sets = new SimpleIntegerProperty(boardState.getBoard().getNumberSets());
    }

    public IntegerProperty setsProperty() {
        return sets;
    }

    public void updateSets() {
        sets.set(boardState.getBoard().getNumberSets());
    }

    public void reset() {
        boardState.reset();
        updateSets();
    }

    public void update() {
        boardState.update();
        updateSets();
    }
}
