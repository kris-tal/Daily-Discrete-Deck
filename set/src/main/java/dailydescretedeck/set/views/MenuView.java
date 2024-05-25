package dailydescretedeck.set.views;

import dailydescretedeck.set.viewmodels.MenuViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.Objects;

public class MenuView extends StackPane {
    private MenuViewModel menuViewModel;

    public MenuView(MenuViewModel viewModel) {
        this.menuViewModel = viewModel;//////
    }

    public void display() {
        Pane wrapperPane = new Pane();
        VBox buttonsVbox = new VBox();
        VBox logoVbox = new VBox();

        Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/dailydescretedeck/set/images/logo.png")));
        ImageView logoView = new ImageView(logo);
        logoView.setPreserveRatio(true);

        Button playSetButton = new Button("PLAY SET");
        Button profileButton = new Button("PROFILE");
        Button storeButton = new Button("STORE");
        Button instructionsButton = new Button("STORE");

        DoubleBinding shorterEdge = (DoubleBinding) Bindings.min(widthProperty(), heightProperty());
        DoubleBinding width = widthProperty().divide(2);
        DoubleBinding height = heightProperty().divide(10);

        logoView.setFitHeight(shorterEdge.doubleValue() / 6);
        logoView.setFitWidth(shorterEdge.doubleValue() / 18);
        logoView.fitWidthProperty().bind(shorterEdge);

        playSetButton.prefWidthProperty().bind(width);
        playSetButton.prefHeightProperty().bind(height);
        profileButton.prefWidthProperty().bind(width);
        profileButton.prefHeightProperty().bind(height);
        storeButton.prefWidthProperty().bind(width);
        storeButton.prefHeightProperty().bind(height);
        instructionsButton.prefWidthProperty().bind(width);
        instructionsButton.prefHeightProperty().bind(height);


        playSetButton.setFont(Font.font("System", shorterEdge.divide(50).doubleValue()));
        playSetButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 60;");
        profileButton.setFont(Font.font("System", shorterEdge.divide(50).doubleValue()));
        profileButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 60;");
        storeButton.setFont(Font.font("System", shorterEdge.divide(50).doubleValue()));
        storeButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 60;");
        instructionsButton.setFont(Font.font("System", shorterEdge.divide(50).doubleValue()));
        instructionsButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 60;");

        logoVbox.prefHeightProperty().bind(heightProperty());
        logoVbox.translateYProperty().bind(heightProperty().divide(5));
        logoVbox.translateXProperty().bind(widthProperty().divide(2).subtract(width.divide(2)));
        //logoVbox.setPadding(new Insets(width.get() / 100));


        buttonsVbox.prefHeightProperty().bind(heightProperty());
        buttonsVbox.translateYProperty().bind(heightProperty().divide(5).multiply(2));
        buttonsVbox.translateXProperty().bind(widthProperty().divide(2).subtract(width.divide(2)));
        //buttonsVbox.setPadding(new Insets(width.get() / 100));
        buttonsVbox.setSpacing(height.doubleValue() / 4);

        VBox.setMargin(buttonsVbox, new Insets(height.get() / 2));

        playSetButton.setOnAction(event -> handleInput(MenuViewModel.MenuOptions.PLAY));
        profileButton.setOnAction(event -> handleInput(MenuViewModel.MenuOptions.PROFILE));
        storeButton.setOnAction(event -> handleInput(MenuViewModel.MenuOptions.STORE));
        instructionsButton.setOnAction(event -> handleInput(MenuViewModel.MenuOptions.INSTRUCTIONS));

        logoVbox.getChildren().add(logoView);
        buttonsVbox.getChildren().addAll(playSetButton, profileButton, storeButton, instructionsButton);
        wrapperPane.getChildren().addAll(logoVbox, buttonsVbox);
        getChildren().add(wrapperPane);

    }

    public void handleInput(MenuViewModel.MenuOptions input) {
        menuViewModel.handleInput(input);
    }
}
