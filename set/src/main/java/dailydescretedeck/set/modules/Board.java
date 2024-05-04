package dailydescretedeck.set.modules;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Board extends GridPane {

    private final int size = 8;
    private final Circle[][] circles = new Circle[size][size];

    public Board() {
        drawChessboard();
    }

    private void drawChessboard() {
        boolean isWhite = true;

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Color color = isWhite ? Color.WHITE : Color.BLACK;
                Circle circle = new Circle(20, color);
                circle.setOnMouseClicked(event -> handleSquareClick(circle));
                add(circle, col, row);
                circles[row][col] = circle;

                isWhite = !isWhite;
            }
            isWhite = !isWhite;
        }
    }

    private void handleSquareClick(Circle circle) {
        Color backgroundColor = getBackgroundColor(circle);
        if (circle.getFill() == Color.RED) {
            circle.setFill(backgroundColor);
        } else {
            circle.setFill(Color.RED);
        }
    }

    private Color getBackgroundColor(Circle circle) {
        int rowIndex = GridPane.getRowIndex(circle);
        int colIndex = GridPane.getColumnIndex(circle);
        return (rowIndex + colIndex) % 2 == 0 ? Color.WHITE : Color.BLACK;
    }
}
