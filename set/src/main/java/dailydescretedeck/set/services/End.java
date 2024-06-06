package dailydescretedeck.set.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class End {
    private static End instance;
    private long ends;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private End() {
        ends = SavingService.loadNumberFromFile("ends.txt");
        resetEndsAtMidnight();
    }

    public static End getInstance() {
        if (instance == null) {
            instance = new End();
            instance.ends = SavingService.loadNumberFromFile("ends.txt");
        }
        return instance;
    }

    public void addEnds(long ends) {
        this.ends += ends;
        SavingService.saveNumberToFile("ends.txt", ", Ends: ", ends);
    }

    public long getEnds() {
        return ends;
    }
    private void resetEndsAtMidnight() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextMidnight = now.toLocalDate().plusDays(1).atStartOfDay();
        Duration duration = Duration.between(now, nextMidnight);
        long initialDelay = duration.getSeconds();

        scheduler.scheduleAtFixedRate(() -> {
            ends = 0;
            SavingService.saveNumberToFile("ends.txt", ", Ends: ", ends);
        }, initialDelay, TimeUnit.DAYS.toSeconds(1), TimeUnit.SECONDS);
    }
}