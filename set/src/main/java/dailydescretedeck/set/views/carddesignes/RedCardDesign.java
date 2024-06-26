package dailydescretedeck.set.views.carddesignes;

import dailydescretedeck.set.models.Dots;
import dailydescretedeck.set.viewmodels.CardDesign;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.Arrays;
import java.util.List;

public class RedCardDesign implements CardDesign {
    private static final Color[]colors = {
            Color.rgb(255, 30, 40), // Pure red
            Color.rgb(220, 20, 60), // Crimson
            Color.rgb(255, 99, 71), // Tomato
            Color.rgb(255, 105, 180), // Hot pink
            Color.rgb(255, 160, 122), // Light salmon
            Color.rgb(205, 92, 92)
    };

    private static final Color backgroundColor = Color.WHITE;

    public Color getColor(int idx) {
        return colors[idx - 1];
    }

    public Color[] getColors(int idx) {
        return colors;
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
