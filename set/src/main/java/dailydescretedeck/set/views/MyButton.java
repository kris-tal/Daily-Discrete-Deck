package dailydescretedeck.set.views;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class MyButton extends Button {
    public MyButton(String text) {
        super(text);
        applyStyle(this);
    }

    private void applyStyle(Button... buttons) {
        setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");
        setFont(Font.font("System", 20));
        setOnMouseEntered(event -> {
            setCursor(Cursor.HAND);
            //setStyle("-fx-background-color: #746174; -fx-text-fill: #E6D4E6; -fx-background-radius: 40;");
            setStyle("-fx-background-color: #f2ebf2; -fx-text-fill: #746174; -fx-background-radius: 40;");

        });
        setOnMouseExited(event -> {
            setCursor(Cursor.DEFAULT);
            setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");
        });

    }
}
