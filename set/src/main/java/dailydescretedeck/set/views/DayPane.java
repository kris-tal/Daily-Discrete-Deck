package dailydescretedeck.set.views;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;


public class DayPane extends StackPane {
    private VBox data;
    private int setNumber;
    private int endNumber;
    private Map<Integer, Color> rectColor;

    public DayPane(int dayOfMonth, int sets, int ends) {
        rectColor = Map.of(0, Color.WHITE, 1, Color.rgb(210,230,181), 2, Color.rgb(193,219,155), 3, Color.rgb(177,207,134), 4, Color.rgb(160,193,114), 5, Color.rgb(142,177,92));

        Label dateLabel = new Label(Integer.toString(dayOfMonth));
        dateLabel.setFont(Font.font("System Bold", FontWeight.BOLD, 15));
        dateLabel.setTextFill(rectColor.get(sets).darker().darker().darker());
        //Label setsLabel = new Label("Sets: " + sets);
        //Label endsLabel = new Label("Ends: " + ends);
        this.data = new VBox(dateLabel);

        Rectangle rect = new Rectangle(40, 40);
        rect.setArcHeight(30);
        rect.setArcWidth(30);
        rect.setFill(rectColor.get(sets));
        rect.setStrokeWidth(2);
        rect.setStroke(rectColor.get(sets).darker());

        getChildren().addAll(rect, data);
        data.setAlignment(Pos.CENTER);

        //dodac logike zeby na klikniecie pokazywala sie ilosc setow i endow ale to chyba w viewmodelu
    }
}
