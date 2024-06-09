package dailydescretedeck.set.views.carddesignes;

import dailydescretedeck.set.models.Dots;
import dailydescretedeck.set.viewmodels.CardDesign;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.Arrays;
import java.util.List;

public class AdventureCardDesign implements CardDesign {
    private static final Color[]colors = {
            Color.rgb(165,165,95), //46,139,87
            Color.rgb(78,77,47), //(188,143,143)
            Color.rgb(96,96,96), //(139,69,19)
            Color.rgb(116,156,54), // (205,133,63)
            Color.rgb(45,36,16), //(188,143,143)
            Color.rgb(17,19,28) //(107,142,35)
    };

    private static final Color backgroundColor = Color.WHITE;

    public Color getColor(int idx) {
        return colors[idx - 1];
    }


    public Shape getShape(double sq) {
        return new Circle(15 * sq);
    }
    public Color getBackgroundColor() {
        return backgroundColor;
    }
    public List<Dots> getDotPositions() {
        return Arrays.asList(Dots.A1, Dots.A2, Dots.B1, Dots.B2, Dots.C1, Dots.C2);
    }
}
