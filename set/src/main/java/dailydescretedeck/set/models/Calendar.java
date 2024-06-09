package dailydescretedeck.set.models;

import dailydescretedeck.set.services.SavingService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

public class Calendar {
    private YearMonth currentYearMonth;
    private Map<LocalDate, Long> setsMap = new HashMap<>();
    private Map<LocalDate, Long> endsMap = new HashMap<>();
    private Map<LocalDate, Long> timeMap = new HashMap<>();

    public Calendar() {
        this.currentYearMonth = YearMonth.now();
        setsMap = SavingService.loadMapFromFile("saves/setsMap.txt");
        endsMap = SavingService.loadMapFromFile("saves/endsMap.txt");
        timeMap = SavingService.loadMapFromFile("saves/timeMap.txt");
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

    public Map<LocalDate, Long> getEndsMap() {
        endsMap = SavingService.loadMapFromFile("saves/endsMap.txt");
        return endsMap;
    }
    public Map<LocalDate, Long> getTimeMap() {
        timeMap = SavingService.loadMapFromFile("saves/timeMap.txt");
        return timeMap;
    }
}