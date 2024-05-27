package dailydescretedeck.set.views;

import dailydescretedeck.set.viewmodels.BoardViewModel;
import dailydescretedeck.set.viewmodels.PlayViewModel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PlayView extends Pane {
    private final PlayViewModel playViewModel;
    private final BoardView boardView;

    public PlayView(PlayViewModel playViewModel, Runnable onBackToMenu) {
        this.playViewModel = playViewModel;
        BoardViewModel boardViewModel = new BoardViewModel(playViewModel.getBoardState().getBoard());
        this.boardView = new BoardView(boardViewModel);

        Button backButton = new Button("Back to Menu");
        backButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");
        backButton.setOnAction(event -> onBackToMenu.run());

        double buttonWidth = 20;
        double buttonHeight = 40;
        double paneWidth = 1000;
        double paneHeight = 800;

        boardView.setLayoutX(0);
        boardView.setLayoutY(0);
        boardView.setPrefSize(paneWidth, paneHeight - buttonHeight - 20);

        backButton.setLayoutX((paneWidth - buttonWidth) / 2);
        backButton.setLayoutY(paneHeight - buttonHeight - 10);
        backButton.setPrefWidth(buttonWidth);
        backButton.setPrefHeight(buttonHeight);

        getChildren().addAll(boardView, backButton);
    }

    public void display(Stage stage) {
        Scene scene = new Scene(this, stage.getWidth(), stage.getHeight());
        scene.getRoot().setStyle("-fx-background-color: thistle;");
        stage.setScene(scene);
        stage.setTitle("Set");
        stage.show();
    }
}
