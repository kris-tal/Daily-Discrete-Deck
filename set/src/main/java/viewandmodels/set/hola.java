package viewandmodels.set;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class hola {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}