package dailydescretedeck.set.services;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.*;
import java.util.*;
import java.util.concurrent.*;

public class SetCollector {
    private static SetCollector instance;
    private int sets;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private SetCollector() {
        sets = loadSetsFromFile();
        resetSetsAtMidnight();
    }

    public static SetCollector getInstance() {
        if (instance == null) {
            instance = new SetCollector();
        }
        return instance;
    }

    public void addSets(int sets) {
        this.sets += sets;
        saveSetsToFile();
    }

    public int getSets() {
        return sets;
    }

    private int loadSetsFromFile() {
        LocalDate currentDate = LocalDate.now();
        String fileName = "/saves/setsCollected_" + currentDate + ".txt";
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

    private void saveSetsToFile() {
        LocalDate currentDate = LocalDate.now();
        String fileName = "/saves/setsCollected_" + currentDate + ".txt";
        Path path = Paths.get(fileName);
        String dataToWrite = "Date: " + currentDate + ", Sets Collected: " + sets;
        try {
            Files.write(path, Collections.singletonList(dataToWrite), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void resetSetsAtMidnight() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextMidnight = now.toLocalDate().plusDays(1).atStartOfDay();
        Duration duration = Duration.between(now, nextMidnight);
        long initialDelay = duration.getSeconds();

        scheduler.scheduleAtFixedRate(() -> {
            sets = 0;
            saveSetsToFile();
        }, initialDelay, TimeUnit.DAYS.toSeconds(1), TimeUnit.SECONDS);
    }
}