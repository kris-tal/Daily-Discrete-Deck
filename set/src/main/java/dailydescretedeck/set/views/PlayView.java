package dailydescretedeck.set.views;

import dailydescretedeck.set.models.BoardState;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PlayView extends StackPane {
    private final BoardState boardState;
    private final dailydescretedeck.view.BoardView boardView;

    public PlayView(BoardState bs) {
        this.boardState = bs;
        this.boardView = new dailydescretedeck.view.BoardView(bs.getBoard());

        setAlignment(Pos.CENTER);

        getChildren().add(boardView);
    }
}