package dailydescretedeck.set.views;

import dailydescretedeck.set.viewmodels.CalendarViewModel;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CalendarView extends StackPane {
    private final CalendarViewModel calendarViewModel;
    private YearMonth currentYearMonth;
    private Label monthYearLabel;
    private Map<java.time.LocalDate, Long> setsMap = new HashMap<>();
    private Map<java.time.LocalDate, Long> endsMap = new HashMap<>();
    private Map<java.time.LocalDate, Long> timeMap = new HashMap<>();

    public CalendarView() {
        this.calendarViewModel = new CalendarViewModel();
        this.currentYearMonth = calendarViewModel.getCurrentYearMonth();
        this.monthYearLabel = new Label();
        this.setsMap = calendarViewModel.getSetsMap();
        this.endsMap = calendarViewModel.getEndsMap();
        this.timeMap = calendarViewModel.getTimeMap();
        this.getChildren().add(buildCalendar(currentYearMonth));
    }

    private GridPane buildCalendar(YearMonth yearMonth) {
        monthYearLabel.setText(yearMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + yearMonth.getYear());
        this.setsMap = calendarViewModel.getSetsMap();
        this.endsMap = calendarViewModel.getEndsMap();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(8);
        gridPane.setVgap(8);

        String[] daysOfWeek = {" Mon ", " Tue ", " Wed ", " Thu ", " Fri ", " Sat ", " Sun "};
        for (int i = 0; i < 7; i++) {
            Label dayLabel = new Label(daysOfWeek[i]);
            dayLabel.setFont(javafx.scene.text.Font.font("System Bold", 15));
            gridPane.add(dayLabel, i, 0);
        }

        java.time.LocalDate startDate = yearMonth.atDay(1);
        int startDayOfWeek = startDate.getDayOfWeek().getValue() - 1;
        int daysInMonth = yearMonth.lengthOfMonth();

        for (int dayOfMonth = 1; dayOfMonth <= daysInMonth; dayOfMonth++) {
            java.time.LocalDate date = yearMonth.atDay(dayOfMonth);
            Long sets = setsMap.getOrDefault(date, (long) 0);
            Long ends = endsMap.getOrDefault(date, (long) 0);
            DayPane dayPane = new DayPane(dayOfMonth, sets.longValue(), ends.longValue());

            int column = (startDayOfWeek + dayOfMonth - 1) % 7;
            int row = (startDayOfWeek + dayOfMonth - 1) / 7 + 1;
            gridPane.add(dayPane, column, row);
        }
        return gridPane;
    }

    public void updateCalendar(YearMonth yearMonth) {
        this.getChildren().clear();
        this.getChildren().add(buildCalendar(yearMonth));
    }
}
