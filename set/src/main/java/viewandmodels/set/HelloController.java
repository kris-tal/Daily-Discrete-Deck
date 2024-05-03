package viewandmodels.set;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Random;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        String[] welcomeMessages = {
                "Welcome to JavaFX Application!",
                "Nice to see you here!",
                "Hello, dear user!",
                "Greetings from JavaFX!"
        };

        Random random = new Random();
        int index = random.nextInt(welcomeMessages.length);
        String randomWelcomeMessage = welcomeMessages[index];

        welcomeText.setText(randomWelcomeMessage);
    }
}
