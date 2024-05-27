package dailydescretedeck.set.services;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class SavingService {
    public static void saveMapToFile(String name, Map<LocalDate, Integer> map) {
        File file = new File(name);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                } else {
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
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
                    map = new HashMap<>();
                } else {
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Map<LocalDate, Integer> loadedMap = (Map<LocalDate, Integer>) ois.readObject();
            map.clear();
            map.putAll(loadedMap);
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
