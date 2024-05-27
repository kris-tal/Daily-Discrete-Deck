package dailydescretedeck.set.views;

import dailydescretedeck.set.viewmodels.CalendarViewModel;
import dailydescretedeck.set.viewmodels.PlayViewModel;
import dailydescretedeck.set.viewmodels.ProfileViewModel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ProfileView extends StackPane {
    private final ProfileViewModel profileViewModel;
    private CalendarView calendarView;

    public ProfileView(ProfileViewModel pvm) {
        this.profileViewModel = pvm;
        this.calendarView = new CalendarView();
//        setAlignment(Pos.CENTER);
        getChildren().add(calendarView);
    }

    public void display(Stage stage) {
        ProfileViewModel profileViewModel = new ProfileViewModel();
        ProfileView profileView = new ProfileView(profileViewModel);
        Scene scene = new Scene(profileView,stage.getWidth(),stage.getHeight());
        scene.getRoot().setStyle("-fx-background-color: thistle;");
        stage.setScene(scene);
        stage.setTitle("Set");
        stage.show();
    }
}
