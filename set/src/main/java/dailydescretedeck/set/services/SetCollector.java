package dailydescretedeck.set.services;
import java.time.*;
import java.util.concurrent.*;

public class SetCollector {
    private static SetCollector instance;
    private long sets;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private SetCollector() {
        sets = SavingService.loadNumberFromFile("setsCollected.txt");
        resetSetsAtMidnight();
    }

    public static SetCollector getInstance() {
        if (instance == null) {
            instance = new SetCollector();
            instance.sets = SavingService.loadNumberFromFile("setsCollected.txt");
        }
        return instance;
    }

    public void addSets(long sets) {
        this.sets += sets;
        SavingService.saveNumberToFile("setsCollected.txt", ", Sets Collected: ", sets);
    }

    public long getSets() {
        return sets;
    }

    private void resetSetsAtMidnight() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextMidnight = now.toLocalDate().plusDays(1).atStartOfDay();
        Duration duration = Duration.between(now, nextMidnight);
        long initialDelay = duration.getSeconds();

        scheduler.scheduleAtFixedRate(() -> {
            sets = 0;
            SavingService.saveNumberToFile("setsCollected.txt", ", Sets Collected: ", sets);
        }, initialDelay, TimeUnit.DAYS.toSeconds(1), TimeUnit.SECONDS);
    }
}