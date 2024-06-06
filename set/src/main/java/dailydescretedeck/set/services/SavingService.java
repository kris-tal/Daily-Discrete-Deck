package dailydescretedeck.set.services;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SavingService {
    public static void saveMapToFile(String name, Map<LocalDate, Long> map) {
        File file = new File(name);
        try {
            if (!file.exists() && !file.createNewFile()) {
                return;
            }
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(map);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<LocalDate, Long> loadMapFromFile(String name) {
        File file = new File(name);
        Map<LocalDate, Long> map = new HashMap<>();
        try {
            if (!file.exists() && !file.createNewFile()) {
                return map;
            }
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                map = (Map<LocalDate, Long>) ois.readObject();
            } catch (EOFException ignored) {
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static long loadNumberFromFile(String fileName) {
        Path path = Paths.get(fileName);
        if (Files.exists(path)) {
            try {
                List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
                if (!lines.isEmpty()) {
                    String[] parts = lines.get(0).split(", ");
                    return Long.parseLong(parts[1].substring(16));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static void saveNumberToFile(String fileName, String text, long number) {
        LocalDate currentDate = LocalDate.now();
        Path path = Paths.get(fileName);
        String dataToWrite = "Date: " + currentDate + text + number;
        try {
            Files.write(path, Collections.singletonList(dataToWrite), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}