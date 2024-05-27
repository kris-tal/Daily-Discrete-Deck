package dailydescretedeck.set.views;

import dailydescretedeck.set.viewmodels.ProfileViewModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ProfileView extends StackPane {
    private final ProfileViewModel profileViewModel;
    private Runnable onMenu;

    public ProfileView(ProfileViewModel pvm, Runnable onMenu) {
        this.profileViewModel = pvm;
        this.onMenu = onMenu;
        initializeComponents();
    }

    private void initializeComponents() {
        CalendarView calendarView = new CalendarView();
        Label headerLabel = new Label("Profile");
        headerLabel.setFont(Font.font("System Bold", 20));
        VBox layout = new VBox(headerLabel, calendarView);
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        getChildren().add(layout);

        Button backButton = new Button("Back to Menu");
        backButton.setOnAction(event -> onMenu.run());
        getChildren().add(backButton);
    }

    public void display(Stage stage) {
        Scene scene = new Scene(this, stage.getWidth(), stage.getHeight());
        scene.getRoot().setStyle("-fx-background-color: thistle;");
        stage.setScene(scene);
        stage.setTitle("Profile");
        stage.show();
    }
}
