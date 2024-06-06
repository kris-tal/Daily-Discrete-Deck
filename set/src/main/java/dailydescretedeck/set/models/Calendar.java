package dailydescretedeck.set.models;

import dailydescretedeck.set.services.SavingService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

public class Calendar {
    private YearMonth currentYearMonth;
    private static Map<LocalDate, Long> setsMap = new HashMap<>();
    private static Map<LocalDate, Long> endsMap = new HashMap<>();

    public Calendar() {
        this.currentYearMonth = YearMonth.now();
        setsMap = SavingService.loadMapFromFile("setsMap.txt");
        endsMap = SavingService.loadMapFromFile("endsMap.txt");
    }

    public YearMonth getCurrentYearMonth() {
        return currentYearMonth;
    }

    public void setCurrentYearMonth(YearMonth currentYearMonth) {
        this.currentYearMonth = currentYearMonth;
    }

    public static Map<LocalDate, Long> getSetsMap() {
        return setsMap;
    }

    public static Map<LocalDate, Long> getEndsMap() {
        return endsMap;
    }
    public static void setSetsMap(Map<LocalDate, Long> setsMap) {
        Calendar.setsMap = setsMap;
        SavingService.saveMapToFile("setsMap.txt", setsMap);
    }
    public static void setEndsMap(Map<LocalDate, Long> endsMap) {
        Calendar.endsMap = endsMap;
        SavingService.saveMapToFile("endsMap.txt", endsMap);
    }
}