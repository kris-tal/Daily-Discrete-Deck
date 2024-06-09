package dailydescretedeck.set.models;

import dailydescretedeck.set.services.SavingService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

public class Calendar {
    private YearMonth currentYearMonth;
    private Map<LocalDate, Long> setsMap = new HashMap<>();

    public Calendar() {
        this.currentYearMonth = YearMonth.now();
        setsMap = SavingService.loadMapFromFile("saves/setsMap.txt");
    }

    public YearMonth getCurrentYearMonth() {
        return currentYearMonth;
    }

    public void setCurrentYearMonth(YearMonth currentYearMonth) {
        this.currentYearMonth = currentYearMonth;
    }

    public Map<LocalDate, Long> getSetsMap() {
        setsMap = SavingService.loadMapFromFile("saves/setsMap.txt");
        return setsMap;
    }
}