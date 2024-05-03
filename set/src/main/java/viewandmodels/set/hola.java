package viewandmodels.set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class hola {

    @FXML
    private Label byeText;

    @FXML
    protected void onByebyeButtonClick() {
        // Zmiana tekstu po kliknięciu przycisku
        byeText.setText("Bye bye to JavaFX Application!");
    }
}
