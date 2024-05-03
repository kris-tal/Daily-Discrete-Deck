package viewandmodels.set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class hola {
    @FXML
    private Label byeText;

    @FXML
    protected void onByebyeButtonClick() {
        byeText.setText("Bye bye to JavaFX Application!");
    }
}