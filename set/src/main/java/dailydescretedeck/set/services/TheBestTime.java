package dailydescretedeck.set.services;

import java.time.*;
import java.util.concurrent.*;

public class TheBestTime {
    private static TheBestTime instance;
    private long time;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private TheBestTime() {
        time = SavingService.loadNumberFromFile("theBestTime.txt");
        resetTimeAtMidnight();
    }

    public static TheBestTime getInstance() {
        if (instance == null) {
            instance = new TheBestTime();
            instance.time = SavingService.loadNumberFromFile("theBestTime.txt");
        }
        return instance;
    }

    public void newTime(long time) {
        this.time = time;
        SavingService.saveNumberToFile("theBestTime.txt", ", Time: ", time);
    }

    public long getTime() {
        return time;
    }

    private void resetTimeAtMidnight() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextMidnight = now.toLocalDate().plusDays(1).atStartOfDay();
        Duration duration = Duration.between(now, nextMidnight);
        long initialDelay = duration.getSeconds();

        scheduler.scheduleAtFixedRate(() -> {
            time = 0;
            SavingService.saveNumberToFile("theBestTime.txt", ", Time: ", time);
        }, initialDelay, TimeUnit.DAYS.toSeconds(1), TimeUnit.SECONDS);
    }
}