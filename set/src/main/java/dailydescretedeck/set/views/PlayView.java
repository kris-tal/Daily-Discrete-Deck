package dailydescretedeck.set.views;

import dailydescretedeck.set.viewmodels.PlayViewModel;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class PlayView extends StackPane {
    private PlayViewModel playViewModel;
    private Runnable showMenuCallback;

    public PlayView(PlayViewModel playViewModel, Runnable showMenuCallback) {
        this.playViewModel = playViewModel;
        this.showMenuCallback = showMenuCallback;

        Button backButton = new Button("Back to Menu");
        backButton.setOnAction(e -> showMenuCallback.run());

        getChildren().add(backButton);
    }
}
