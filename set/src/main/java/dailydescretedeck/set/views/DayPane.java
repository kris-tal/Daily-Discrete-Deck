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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class DayPane extends StackPane {
    private VBox data;
    private int setNumber;
    private int endNumber;

    private Color chooseColor(int sets) {
        if(sets == 0) return Color.WHITE;
        if(sets < 4) return Color.rgb(210,230,181);
        if(sets < 8) return Color.rgb(193,219,155);
        if(sets < 12) return Color.rgb(177,207,134);
        if(sets < 18) return Color.rgb(160,193,114);
        return Color.rgb(142,177,92);
    }

    public DayPane(int dayOfMonth, int sets, int ends) {

        Label dateLabel = new Label(Integer.toString(dayOfMonth));
        dateLabel.setFont(Font.font("System Bold", FontWeight.BOLD, 15));
        dateLabel.setTextFill(chooseColor(sets).darker().darker().darker());
        //Label setsLabel = new Label("Sets: " + sets);
        //Label endsLabel = new Label("Ends: " + ends);
        this.data = new VBox(dateLabel);

        Rectangle rect = new Rectangle(40, 40);
        rect.setArcHeight(30);
        rect.setArcWidth(30);
        rect.setFill(chooseColor(sets));
        rect.setStrokeWidth(2);
        rect.setStroke(chooseColor(sets).darker());

        getChildren().addAll(rect, data);
        data.setAlignment(Pos.CENTER);

        //dodac logike zeby na klikniecie pokazywala sie ilosc setow i endow ale to chyba w viewmodelu
    }
}
