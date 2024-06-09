package dailydescretedeck.set.views;

import java.util.HashMap;
import java.util.Map;

import dailydescretedeck.set.services.SavingService;
import javafx.scene.layout.VBox;

public class CalendarStats extends VBox {
    private Map<java.time.LocalDate, Long> setsMap = new HashMap<>();
    private Map<java.time.LocalDate, Long> endsMap = new HashMap<>();
    private Map<java.time.LocalDate, Long> timeMap = new HashMap<>();
    public CalendarStats() {
        setsMap = SavingService.loadMapFromFile("saves/setsMap.txt");
        endsMap = SavingService.loadMapFromFile("saves/endsMap.txt");
        timeMap = SavingService.loadMapFromFile("saves/timeMap.txt");
    }
}
