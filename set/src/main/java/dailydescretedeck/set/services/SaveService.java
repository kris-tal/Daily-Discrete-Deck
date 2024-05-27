package dailydescretedeck.set.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.Map;

public class SaveService {
    public static void saveMapToFile(String name, Map<LocalDate, Integer> map) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name))) {
            oos.writeObject(map);
        } catch (IOException e) {
            System.out.println(name + " not found");
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadMapFromFile(String name, Map<LocalDate, Integer> map) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(name))) {
            map.clear();
            map.putAll((Map<LocalDate, Integer>) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(name + " not found");
            e.printStackTrace();
        }
    }
}
