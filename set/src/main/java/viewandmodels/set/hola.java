package viewandmodels.set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class hola {
    @FXML
    private GridPane chessBoard;

    @FXML
    private void initialize() {
        // Tworzenie i dodawanie pól planszy
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Rectangle square = new Rectangle(50, 50); // Utworzenie kwadratu o wymiarach 50x50
                if ((row + col) % 2 == 0) {
                    square.setFill(javafx.scene.paint.Color.WHITE); // Ustawienie koloru białego dla pól parzystych
                } else {
                    square.setFill(javafx.scene.paint.Color.BLACK); // Ustawienie koloru czarnego dla pól nieparzystych
                }
                chessBoard.add(square, col, row); // Dodanie kwadratu do planszy
            }
        }
    }

    @FXML
    private Label byeText;

    @FXML
    protected void onByebyeButtonClick() {
        // Zmiana tekstu po kliknięciu przycisku
        byeText.setText("Bye bye to JavaFX Application!");
    }
}
