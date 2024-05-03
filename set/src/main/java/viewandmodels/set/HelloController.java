package viewandmodels.set;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        // Zmiana tekstu etykiety
        welcomeText.setText("Welcome to JavaFX Application!");

        // Zmiana koloru tła etykiety
        welcomeText.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        // Wyświetlenie nowego okna dialogowego
        showAlert("Informacja", "Przycisk został kliknięty!");

        // Dodatkowe czynności...
    }

    // Metoda do wyświetlania okna dialogowego
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
