package viewandmodels.set;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class Board {
    @FXML
    private GridPane chessBoard;

    // Metoda inicjalizacyjna, która jest wywoływana po załadowaniu pliku FXML
    @FXML
    public void initialize() {
        // Wstawianie pól planszy
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Rectangle square = new Rectangle(50, 50); // Tworzenie kwadratu o wymiarach 50x50
                if ((row + col) % 2 == 0) {
                    square.setFill(javafx.scene.paint.Color.WHITE); // Ustawienie koloru białego dla pól parzystych
                } else {
                    square.setFill(javafx.scene.paint.Color.BLACK); // Ustawienie koloru czarnego dla pól nieparzystych
                }
                chessBoard.add(square, col, row); // Dodanie kwadratu do planszy
            }
        }
    }
}
