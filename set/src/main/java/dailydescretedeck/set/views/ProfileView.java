package dailydescretedeck.set.views;

import dailydescretedeck.set.FileManagement.PlayerName;
import dailydescretedeck.set.viewmodels.ProfileViewModel;
import dailydescretedeck.set.viewmodels.Scenes;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.YearMonth;

public class ProfileView extends StackPane {
    private final ProfileViewModel profileViewModel;
    private YearMonth currentYearMonth;
    private Label monthYearLabel;
    private Scenes scenes;

    public ProfileView(ProfileViewModel pvm) {
        this.profileViewModel = pvm;
        this.currentYearMonth = YearMonth.now();
        this.monthYearLabel = new Label();
        this.scenes = new Scenes();
        initializeComponents();
    }

    private void initializeComponents() {
        Label headerLabel = new Label(new PlayerName().getName());
        headerLabel.setFont(Font.font("System Bold", 35));

        setStyle("-fx-background-color: thistle;");

        CalendarView calendarView = new CalendarView();
        VBox calendarContainer = new VBox(calendarView);
        calendarContainer.setSpacing(10);
        calendarContainer.setAlignment(Pos.TOP_CENTER);
        Button changeNameButton = new MyButton("Change Username");
        changeNameButton.setOnAction(event -> scenes.showUsernameAlert());

        Button backButton = new MyButton("Back to Menu");
        backButton.setOnAction(event -> scenes.showMenuView());

        ToolBar toolBar = createNavigationBar(calendarView);

        VBox layout = new VBox(headerLabel, toolBar, calendarContainer, changeNameButton, backButton);
        layout.setSpacing(20);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(30));
        getChildren().add(layout);
    }

    private ToolBar createNavigationBar(CalendarView calendarView) {
        Button previousMonthButton = new MyButton("<");
        previousMonthButton.setOnAction(e -> {
            currentYearMonth = currentYearMonth.minusMonths(1);
            calendarView.updateCalendar(currentYearMonth);
            monthYearLabel.setText(currentYearMonth.getMonth().getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.ENGLISH) + " " + currentYearMonth.getYear());
        });

        Button nextMonthButton = new MyButton(">");
        nextMonthButton.setOnAction(e -> {
            currentYearMonth = currentYearMonth.plusMonths(1);
            calendarView.updateCalendar(currentYearMonth);
            monthYearLabel.setText(currentYearMonth.getMonth().getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.ENGLISH) + " " + currentYearMonth.getYear());
        });

        monthYearLabel.setText(currentYearMonth.getMonth().getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.ENGLISH) + " " + currentYearMonth.getYear());

        ToolBar toolBar = new ToolBar(previousMonthButton, monthYearLabel, nextMonthButton);
        toolBar.setStyle("-fx-background-color: #E6D4E6;");
        return toolBar;
    }
}
