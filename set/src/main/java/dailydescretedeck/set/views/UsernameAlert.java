package dailydescretedeck.set.views;

import dailydescretedeck.set.models.Dots;
import dailydescretedeck.set.models.Player;
import dailydescretedeck.set.services.PlayerName;
import dailydescretedeck.set.services.SavingService;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UsernameAlert extends VBox {
    public UsernameAlert() {
        display();
    }

    public void display() {
        System.out.println("UsernameAlert");
        setStyle("-fx-background-color: thistle;");
        setAlignment(Pos.CENTER);
        setSpacing(20);
        setPadding(new javafx.geometry.Insets(20));
        TextField usernameField = new TextField();
        usernameField.setStyle("-fx-background-color: #E6D4E6; -fx-prompt-text-fill: #a18da1; -fx-text-fill: #5e4c5e; -fx-background-radius: 40;");
        usernameField.setPromptText("Enter your username");
        usernameField.setPrefSize(200, 50);
        usernameField.setFont(new javafx.scene.text.Font("System", 24));
        usernameField.setAlignment(Pos.CENTER);
        Button submitButton = new MyButton("Submit");
        submitButton.setFont(new javafx.scene.text.Font("System", 18));

        PlayerName playerName = new PlayerName();

        submitButton.setOnAction(event -> {
            if(usernameField.getText().isEmpty()) {
                return;
            }
            else {
                String result = usernameField.getText();
                playerName.setName(result);
                SavingService.saveNameToFile("saves/name.txt", result);
                ((Stage)submitButton.getScene().getWindow()).close();
            }
        });

        getChildren().addAll(usernameField, submitButton);
    }
}
