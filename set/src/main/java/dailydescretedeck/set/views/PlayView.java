package dailydescretedeck.set.views;

import dailydescretedeck.set.models.BoardState;
import dailydescretedeck.set.viewmodels.BoardViewModel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PlayView extends StackPane {
    private final BoardView boardView;
    private Runnable onMenu;

    public PlayView(BoardState boardState) {
        BoardViewModel boardViewModel = new BoardViewModel(boardState.getBoard());
        this.boardView = new BoardView(boardViewModel); //onMenu
        //this.onMenu = onMenu;

        initializeComponents();
    }

    private void initializeComponents() {
        // Dodaj BoardView
        getChildren().add(boardView);

        // Dodaj przycisk powrotu do menu
        Button backButton = new Button("Back to Menu");
        backButton.setOnAction(event -> onMenu.run());
        StackPane.setAlignment(backButton, Pos.TOP_LEFT); // Ustawienie przycisku w lewym górnym rogu
        getChildren().add(backButton);
    }

    public void display(Stage stage) {
        Scene scene = new Scene(this, stage.getWidth(), stage.getHeight());
        scene.getRoot().setStyle("-fx-background-color: thistle;");
        stage.setScene(scene);
        stage.setTitle("Set");
        stage.show();
    }
}
