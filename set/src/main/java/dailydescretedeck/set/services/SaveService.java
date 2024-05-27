package dailydescretedeck.set.services;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class SaveService {
    public void saveSetsMapToFile(Map<LocalDate, Integer> setsMap) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("setsMap.txt"))) {
            oos.writeObject(setsMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public Map<LocalDate, Integer> loadSetsMapFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("setsMap.txt"))) {
            return (Map<LocalDate, Integer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    public void saveEndsMapToFile(Map<LocalDate, Integer> endsMap) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("endsMap.txt"))) {
            oos.writeObject(endsMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public Map<LocalDate, Integer> loadEndsMapFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("endsMap.txt"))) {
            return (Map<LocalDate, Integer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }
}