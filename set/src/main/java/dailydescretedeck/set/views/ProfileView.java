package dailydescretedeck.set.views;

import dailydescretedeck.set.viewmodels.ProfileViewModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.YearMonth;

public class ProfileView extends StackPane {
    private final ProfileViewModel profileViewModel;
    private Runnable onMenu;
    private YearMonth currentYearMonth;
    private Label monthYearLabel;

    public ProfileView(ProfileViewModel pvm, Runnable onMenu) {
        this.profileViewModel = pvm;
        this.onMenu = onMenu;
        this.currentYearMonth = YearMonth.now();
        this.monthYearLabel = new Label();
        initializeComponents();
    }

    private void initializeComponents() {
        Label headerLabel = new Label("Profile");
        headerLabel.setFont(Font.font("System Bold", 20));

        CalendarView calendarView = new CalendarView();
        VBox calendarContainer = new VBox(calendarView);
        calendarContainer.setSpacing(10);
        calendarContainer.setAlignment(Pos.CENTER);

        Button backButton = new Button("Back to Menu");
        backButton.setOnAction(event -> onMenu.run());

        ToolBar toolBar = createNavigationBar(calendarView);

        VBox layout = new VBox(headerLabel, toolBar, calendarContainer, backButton);
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        getChildren().add(layout);
    }

    private ToolBar createNavigationBar(CalendarView calendarView) {
        Button previousMonthButton = new Button("<");
        previousMonthButton.setOnAction(e -> {
            currentYearMonth = currentYearMonth.minusMonths(1);
            calendarView.updateCalendar(currentYearMonth);
            monthYearLabel.setText(currentYearMonth.getMonth().getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.ENGLISH) + " " + currentYearMonth.getYear());
        });

        Button nextMonthButton = new Button(">");
        nextMonthButton.setOnAction(e -> {
            currentYearMonth = currentYearMonth.plusMonths(1);
            calendarView.updateCalendar(currentYearMonth);
            monthYearLabel.setText(currentYearMonth.getMonth().getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.ENGLISH) + " " + currentYearMonth.getYear());
        });

        monthYearLabel.setText(currentYearMonth.getMonth().getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.ENGLISH) + " " + currentYearMonth.getYear());

        ToolBar toolBar = new ToolBar(previousMonthButton, monthYearLabel, nextMonthButton);
        toolBar.setStyle("-fx-background-color: thistle;");
        return toolBar;
    }

    public void display(Stage stage) {
        Scene scene = new Scene(this, stage.getWidth(), stage.getHeight());
        scene.getRoot().setStyle("-fx-background-color: thistle;");
        stage.setScene(scene);
        stage.setTitle("Profile");
        stage.show();
    }
}
