package dailydescretedeck.set.models;

import dailydescretedeck.set.services.SaveService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

public class Calendar {
    private SaveService saveService = new SaveService();
    private YearMonth currentYearMonth;
    private Map<LocalDate, Integer> setsMap = new HashMap<>();
    private Map<LocalDate, Integer> endsMap = new HashMap<>();

    public Calendar() {
        this.currentYearMonth = YearMonth.now();
        this.setsMap = saveService.loadSetsMapFromFile();
        this.endsMap = saveService.loadEndsMapFromFile();
    }

    public YearMonth getCurrentYearMonth() {
        return currentYearMonth;
    }

    public void setCurrentYearMonth(YearMonth currentYearMonth) {
        this.currentYearMonth = currentYearMonth;
    }

    public Map<LocalDate, Integer> getSetsMap() {
        return setsMap;
    }

    public Map<LocalDate, Integer> getEndsMap() {
        return endsMap;
    }

    public void saveSetsMapToFile() {
        saveService.saveSetsMapToFile(setsMap);
    }

    public void saveEndsMapToFile() {
        saveService.saveEndsMapToFile(endsMap);
    }
}