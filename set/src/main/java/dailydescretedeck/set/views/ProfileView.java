package dailydescretedeck.set.views;

import dailydescretedeck.set.viewmodels.CalendarViewModel;
import dailydescretedeck.set.viewmodels.PlayViewModel;
import dailydescretedeck.set.viewmodels.ProfileViewModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ProfileView extends StackPane {
    private final ProfileViewModel profileViewModel;
    private CalendarView calendarView;

    public ProfileView(ProfileViewModel pvm) {
        this.profileViewModel = pvm;
        this.calendarView = new CalendarView();
        Label headerLabel = new Label("Profile");
        headerLabel.setFont(Font.font("System Bold", 20));
        VBox layout = new VBox(headerLabel, calendarView);
        VBox.setMargin(headerLabel, new javafx.geometry.Insets(10, 0, 10, 0));
        layout.setPadding(new Insets(30));
        getChildren().add(layout);
    }

    public void display(Stage stage) {      //jak to zrobic
        ProfileViewModel profileViewModel = new ProfileViewModel();
        ProfileView profileView = new ProfileView(profileViewModel);
        Scene scene = new Scene(profileView, stage.getWidth(),stage.getHeight());
        scene.getRoot().setStyle("-fx-background-color: thistle;");
        stage.setScene(scene);
        stage.setTitle("Set");
        stage.show();
    }
}
