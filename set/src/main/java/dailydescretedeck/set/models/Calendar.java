package dailydescretedeck.set.models;

import dailydescretedeck.set.services.SavingService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

public class Calendar {
    private YearMonth currentYearMonth;
    private static Map<LocalDate, Integer> setsMap = new HashMap<>();
    private static Map<LocalDate, Integer> endsMap = new HashMap<>();

    public Calendar() {
        this.currentYearMonth = YearMonth.now();
        SavingService.loadMapFromFile("setsMap.txt", setsMap);
        SavingService.loadMapFromFile("endsMap.txt", endsMap);
    }

    public YearMonth getCurrentYearMonth() {
        return currentYearMonth;
    }

    public void setCurrentYearMonth(YearMonth currentYearMonth) {
        this.currentYearMonth = currentYearMonth;
    }

    public static Map<LocalDate, Integer> getSetsMap() {
        return setsMap;
    }

    public static Map<LocalDate, Integer> getEndsMap() {
        return endsMap;
    }
    public static void setSetsMap(Map<LocalDate, Integer> setsMap) {
        Calendar.setsMap = setsMap;
        SavingService.saveMapToFile("setsMap.txt", setsMap);
    }
    public static void setEndsMap(Map<LocalDate, Integer> endsMap) {
        Calendar.endsMap = endsMap;
        SavingService.loadMapFromFile("endsMap.txt", endsMap);
    }
}