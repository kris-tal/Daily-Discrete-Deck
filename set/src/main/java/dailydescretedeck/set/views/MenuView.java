package dailydescretedeck.set.views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MenuView extends VBox {
    private Runnable onPlay;
    private Runnable onStore;
    private Runnable onProfile;

    public MenuView(Runnable onPlay, Runnable onStore, Runnable onProfile) {
        this.onPlay = onPlay;
        this.onStore = onStore;
        this.onProfile = onProfile;
        initializeComponents();
    }

    private void initializeComponents() {
        Button playSetButton = new Button("START");
        playSetButton.setOnAction(event -> onPlay.run());

        Button storeButton = new Button("STORE");
        storeButton.setOnAction(event -> onStore.run());

        Button profileButton = new Button("PROFILE");
        profileButton.setOnAction(event -> onProfile.run());

        setSpacing(20);
        setAlignment(Pos.CENTER);
        getChildren().addAll(playSetButton, storeButton, profileButton);

        applyStyles(playSetButton, storeButton, profileButton);
    }

    private void applyStyles(Button... buttons) {
        for (Button button : buttons) {
            button.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");
            button.setFont(Font.font("System", 24));
        }
    }
}
