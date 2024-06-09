package dailydescretedeck.set.views.carddesignes;

import dailydescretedeck.set.viewmodels.CardDesign;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class BeachCardDesign implements CardDesign {
    private static final Color[] colors = {
            Color.rgb(121, 134, 203),
            Color.rgb(255, 183, 77),
            Color.rgb(244, 143, 177),
            Color.rgb(38, 166, 154),
            Color.rgb(126, 87, 194),
            Color.rgb(255, 138, 101)
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
}
