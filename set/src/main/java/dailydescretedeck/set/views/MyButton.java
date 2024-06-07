package dailydescretedeck.set.views;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class MyButton extends Button {
    public MyButton(String text) {
        super(text);
        applyStyle(this);
    }

    private void applyStyle(Button... buttons) {
        for (Button button : buttons) {
            button.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-background-radius: 40;");
            button.setFont(Font.font("System", 24));
        }
    }
}
