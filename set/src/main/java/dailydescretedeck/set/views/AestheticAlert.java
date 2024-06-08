package dailydescretedeck.set.views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AestheticAlert {
    public static void showAlert(String title, String message) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setTitle(title);

        Label messageLabel = new Label(message);
        messageLabel.setWrapText(true);
        messageLabel.setStyle("-fx-font-size: 14pt; -fx-text-fill: #333333; -fx-padding: 20px;");

        Button okButton = new Button("OK");
        okButton.setStyle("-fx-background-color: #E6D4E6; -fx-text-fill: #746174; -fx-font-size: 12pt; -fx-background-radius: 20;");
        okButton.setOnAction(e -> dialogStage.close());

        VBox vbox = new VBox(20, messageLabel, okButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-padding: 20px; -fx-background-color: #FFFFFF; -fx-border-color: #E6D4E6; -fx-border-width: 2px; -fx-border-radius: 20;");

        Scene scene = new Scene(vbox);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }
}
