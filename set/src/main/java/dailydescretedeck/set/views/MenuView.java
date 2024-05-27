package dailydescretedeck.set.views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MenuView extends VBox {
    public MenuView(Runnable onPlay, Runnable onStore) {
        Button playButton = new Button("START");
        playButton.setOnAction(event -> onPlay.run());

        Button storeButton = new Button("STORE");
        storeButton.setOnAction(event -> onStore.run());

        setSpacing(20);
        setAlignment(Pos.CENTER);
        getChildren().addAll(playButton, storeButton);
    }
}
