package dailydescretedeck.set.views;

import dailydescretedeck.set.viewmodels.CalendarViewModel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Pos;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CalendarView extends StackPane {
    private final CalendarViewModel calendarViewModel;
    private YearMonth currentYearMonth;
    private Label monthYearLabel;
    private Map<LocalDate, Integer> setsMap = new HashMap<>();
    private Map<LocalDate, Integer> endsMap = new HashMap<>();

    public CalendarView() {
        this.calendarViewModel = new CalendarViewModel();
        this.currentYearMonth = calendarViewModel.getCurrentYearMonth();
        this.monthYearLabel = new Label();
        this.setsMap = calendarViewModel.getSetsMap();
        this.endsMap = calendarViewModel.getEndsMap();
        this.getChildren().add(buildCalendar(currentYearMonth));
    }

    private GridPane buildCalendar(YearMonth yearMonth) {
        monthYearLabel.setText(yearMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + yearMonth.getYear());

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        String[] daysOfWeek = {" Mon ", " Tue ", " Wed ", " Thu ", " Fri ", " Sat ", " Sun "};
        for (int i = 0; i < 7; i++) {
            Label dayLabel = new Label(daysOfWeek[i]);
            gridPane.add(dayLabel, i, 0);
        }

        LocalDate startDate = yearMonth.atDay(1);
        int startDayOfWeek = (startDate.getDayOfWeek().getValue() + 5) % 7;
        int daysInMonth = yearMonth.lengthOfMonth();

        for (int dayOfMonth = 1; dayOfMonth <= daysInMonth; dayOfMonth++) {
            LocalDate date = yearMonth.atDay(dayOfMonth);
            int sets = setsMap.getOrDefault(date, 0);
            int ends = endsMap.getOrDefault(date, 0);

            Label dateLabel = new Label(Integer.toString(dayOfMonth));
            Label setsLabel = new Label("Sets: " + sets);
            Label endsLabel = new Label("Ends: " + ends);
            VBox dayBox = new VBox(dateLabel, setsLabel, endsLabel);

            Rectangle rect = new Rectangle(40, 40);
            rect.setFill(Color.LIGHTGRAY);
            rect.setStroke(Color.BLACK);

            StackPane dayPane = new StackPane();
            dayPane.getChildren().addAll(rect, dayBox);

            int column = (startDayOfWeek + dayOfMonth - 1) % 7;
            int row = (startDayOfWeek + dayOfMonth - 1) / 7 + 1;
            gridPane.add(dayPane, column, row);
        }
        return gridPane;
    }

    public Scene createScene() {
        BorderPane borderPane = new BorderPane();
        GridPane calendarGrid = buildCalendar(currentYearMonth);
        borderPane.setCenter(calendarGrid);
        BorderPane.setAlignment(calendarGrid, Pos.CENTER);

        Button previousMonthButton = new Button("<");
        previousMonthButton.setOnAction(e -> {
            currentYearMonth = currentYearMonth.minusMonths(1);
            GridPane newCalendarGrid = buildCalendar(currentYearMonth);
            borderPane.setCenter(newCalendarGrid);
        });

        Button nextMonthButton = new Button(">");
        nextMonthButton.setOnAction(e -> {
            currentYearMonth = currentYearMonth.plusMonths(1);
            GridPane newCalendarGrid = buildCalendar(currentYearMonth);
            borderPane.setCenter(newCalendarGrid);
        });

        HBox navigationBar = new HBox(previousMonthButton, monthYearLabel, nextMonthButton);
        HBox.setHgrow(monthYearLabel, Priority.ALWAYS);
        monthYearLabel.setAlignment(Pos.CENTER);
        borderPane.setTop(navigationBar);

        return new Scene(borderPane, 1000, 800);
    }
}