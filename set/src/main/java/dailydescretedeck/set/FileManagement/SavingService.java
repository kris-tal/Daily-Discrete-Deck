package dailydescretedeck.set.FileManagement;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import dailydescretedeck.set.models.CardDesigns;

public class SavingService {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
                String line = lines.get(0);
                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    return Long.parseLong(matcher.group());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    return 0;
}

    public static void saveNumberToFile(String fileName, long number) {
        Path path = Paths.get(fileName);
        String dataToWrite = number +"";
        try {
            Files.write(path, Collections.singletonList(dataToWrite), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveNameToFile(String fileName, String text) {
        Path path = Paths.get(fileName);
        String dataToWrite = text;
        try {
            Files.write(path, Collections.singletonList(dataToWrite), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String loadNameFromFile(String fileName) {
        Path path = Paths.get(fileName);
        if (Files.exists(path)) {
            try {
                List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
                if (!lines.isEmpty()) {
                    return lines.get(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public static void saveDateToFile(String fileName, LocalDate localDate) {
        Path path = Paths.get(fileName);
        String dataToWrite = dateTimeFormatter.format(localDate);
        try {
            Files.write(path, Collections.singletonList(dataToWrite), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LocalDate loadDateFromFile(String fileName) {
        Path path = Paths.get(fileName);
        if (Files.exists(path)) {
            try {
                List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
                if (!lines.isEmpty()) {
                    return LocalDate.parse(lines.get(0), dateTimeFormatter);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void saveClassesToFile(String fileName, ArrayList<CardDesigns> classes) {
        if (classes == null) {
            classes = new ArrayList<>(); 
        }
        Path path = Paths.get(fileName);
        List<String> classNames = classes.stream().map(Enum::name).collect(Collectors.toList());
        try {
            Files.write(path, classNames, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

public static ArrayList<CardDesigns> loadClassesFromFile(String fileName) {
    Path path = Paths.get(fileName);
    if (Files.exists(path)) {
        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            return lines.stream().map(line -> {
                try {
                    return CardDesigns.valueOf(line);
                } catch (IllegalArgumentException e) {
                    return null;
                }
            }).filter(Objects::nonNull).collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
        }
    } 
    ArrayList<CardDesigns> defaultList = new ArrayList<>();
    defaultList.add(CardDesigns.DEFAULT);
    return defaultList;
}
}