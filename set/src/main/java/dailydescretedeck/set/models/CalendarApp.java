package dailydescretedeck.set.models;
import dailydescretedeck.set.services.SaveService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Pos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import dailydescretedeck.set.services.SetCollector;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

public class CalendarApp extends Application {
    private SaveService saveService = new SaveService();
    private YearMonth currentYearMonth;
    private Label monthYearLabel;
    private Map<LocalDate, Integer> setsMap = new HashMap<>(); 
    private Map<LocalDate, Integer> endsMap = new HashMap<>();

    public void saveSetsMapToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("setsMap.txt"))) {
            oos.writeObject(setsMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadSetsMapFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("setsMap.txt"))) {
            setsMap = (Map<LocalDate, Integer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveEndsMapToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("endsMap.txt"))) {
            oos.writeObject(endsMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadEndsMapFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("endsMap.txt"))) {
            endsMap = (Map<LocalDate, Integer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) {
        setsMap = saveService.loadSetsMapFromFile();
        endsMap = saveService.loadEndsMapFromFile();
        currentYearMonth = YearMonth.now();
        monthYearLabel = new Label();

        try (BufferedReader reader = new BufferedReader(new FileReader("setsCollected.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                LocalDate date = LocalDate.parse(parts[0].substring(6));
                int sets = Integer.parseInt(parts[1].substring(16));
                setsMap.put(date, sets);
            }
            saveSetsMapToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("endsCollected.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                LocalDate date = LocalDate.parse(parts[0].substring(6));
                int ends = Integer.parseInt(parts[1].substring(16));
                endsMap.put(date, ends);
            }
            saveEndsMapToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BorderPane borderPane = new BorderPane();
        GridPane calendarGrid = buildCalendar(currentYearMonth);
        borderPane.setCenter(calendarGrid);

        Button previousMonthButton = new Button("<");
        previousMonthButton.setOnAction(e -> {
            currentYearMonth = currentYearMonth.minusMonths(1);
            GridPane newCalendarGrid = buildCalendar(currentYearMonth);
            borderPane.setCenter(newCalendarGrid);
        });

        Button nextMonthButton = new Button(">");
        nextMonthButton.setOnAction(e -> {
            currentYearMonth = currentYearMonth.plusMonths(1);
            GridPane newCalendarGrid = buildCalendar(currentYearMonth);
            borderPane.setCenter(newCalendarGrid);
        });

        HBox navigationBar = new HBox(previousMonthButton, monthYearLabel, nextMonthButton);
        HBox.setHgrow(monthYearLabel, Priority.ALWAYS);
        monthYearLabel.setAlignment(Pos.CENTER);
        borderPane.setTop(navigationBar);

        Scene scene = new Scene(borderPane, 600, 400);
        stage.setScene(scene);
        stage.show();
        SetCollector.getInstance();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> saveService.saveSetsMapToFile(setsMap)));
    }


    private GridPane buildCalendar(YearMonth yearMonth) {
        monthYearLabel.setText(yearMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + yearMonth.getYear());
    
        GridPane gridPane = new GridPane();
        gridPane.setHgap(0); 
        gridPane.setVgap(0); 
    
        String[] daysOfWeek = {" Mon ", " Tue ", " Wed ", " Thu ", " Fri ", " Sat ", " Sun "};
        for (int i = 0; i < 7; i++) {
            Label dayLabel = new Label(daysOfWeek[i]);
            gridPane.add(dayLabel, i, 0);
        }
    
        LocalDate startDate = yearMonth.atDay(1);
        int startDayOfWeek = (startDate.getDayOfWeek().getValue() + 5) % 7;
        int daysInMonth = yearMonth.lengthOfMonth();
    
        for (int dayOfMonth = 1; dayOfMonth <= daysInMonth; dayOfMonth++) {
            LocalDate date = yearMonth.atDay(dayOfMonth);
            int sets = setsMap.getOrDefault(date, 0);
            int ends = endsMap.getOrDefault(date, 0); 
    
            Label dateLabel = new Label(Integer.toString(dayOfMonth));
            Label setsLabel = new Label("Sets: " + sets);
            Label endsLabel = new Label("Ends: " + ends); 
            VBox dayBox = new VBox(dateLabel, setsLabel, endsLabel); 
    
            Rectangle rect = new Rectangle(40, 40); 
            rect.setFill(Color.LIGHTGRAY); 
            rect.setStroke(Color.BLACK); 
    
            StackPane dayPane = new StackPane();
            dayPane.getChildren().addAll(rect, dayBox);
    
            int column = (startDayOfWeek + dayOfMonth - 1) % 7;
            int row = (startDayOfWeek + dayOfMonth - 1) / 7 + 1;
            gridPane.add(dayPane, column, row);
        }
        return gridPane;
    }

    public static void main(String[] args) {
        
        launch(args);
    }
}