package dailydescretedeck.set.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class End {
    private static End instance;
    private int ends;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private End() {
        ends = loadEndsFromFile();
        resetEndsAtMidnight();
    }

    public static End getInstance() {
        if (instance == null) {
            instance = new End();
        }
        return instance;
    }

    public void addEnds(int ends) {
        this.ends += ends;
        saveEndsToFile();
    }

    public int getEnds() {
        return ends;
    }
   
    private int loadEndsFromFile() {
        String fileName = "ends.txt";
        Path path = Paths.get(fileName);
        if (Files.exists(path)) {
            try {
                List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
                if (!lines.isEmpty()) {
                    String[] parts = lines.get(0).split(", ");
                    return Integer.parseInt(parts[1].substring(16));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    private void saveEndsToFile() {
        LocalDate currentDate = LocalDate.now();
        String fileName = "ends.txt";
        Path path = Paths.get(fileName);
        String dataToWrite = "Date: " + currentDate + ", Ends: " + ends;
        try {
            Files.write(path, Collections.singletonList(dataToWrite), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void resetEndsAtMidnight() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextMidnight = now.toLocalDate().plusDays(1).atStartOfDay();
        Duration duration = Duration.between(now, nextMidnight);
        long initialDelay = duration.getSeconds();

        scheduler.scheduleAtFixedRate(() -> {
            ends = 0;
            saveEndsToFile();
        }, initialDelay, TimeUnit.DAYS.toSeconds(1), TimeUnit.SECONDS);
    }
}