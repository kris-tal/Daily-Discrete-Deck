package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.models.Calendar;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Map;

public class CalendarViewModel {
    private Calendar calendar;

    public CalendarViewModel() {
        this.calendar = new Calendar();
    }

    public YearMonth getCurrentYearMonth() {
        return calendar.getCurrentYearMonth();
    }

    public void setCurrentYearMonth(YearMonth currentYearMonth) {
        calendar.setCurrentYearMonth(currentYearMonth);
    }

    public Map<LocalDate, Integer> getSetsMap() {
        return calendar.getSetsMap();
    }

    public Map<LocalDate, Integer> getEndsMap() {
        return calendar.getEndsMap();
    }

    public void saveSetsMapToFile() {
        calendar.saveSetsMapToFile();
    }

    public void saveEndsMapToFile() {
        calendar.saveEndsMapToFile();
    }
}