package dailydescretedeck.set.services;

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
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(name))) {
            map = (Map<LocalDate, Integer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
