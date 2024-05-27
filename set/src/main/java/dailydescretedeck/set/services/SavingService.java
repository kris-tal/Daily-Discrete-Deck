package dailydescretedeck.set.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.Map;

public class SavingService {
    public static void saveMapToFile(String name, Map<LocalDate, Integer> map) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name))) {
            oos.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadMapFromFile(String name, Map<LocalDate, Integer> map) {
        File file = new File(name);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created");
                } else {
                    System.out.println("File already exists");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Map<LocalDate, Integer> loadedMap = (Map<LocalDate, Integer>) ois.readObject();
            map.clear();
            map.putAll(loadedMap);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
