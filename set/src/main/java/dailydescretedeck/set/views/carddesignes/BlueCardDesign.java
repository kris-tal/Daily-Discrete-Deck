package dailydescretedeck.set.views.carddesignes;

import dailydescretedeck.set.models.CardDesign;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class BlueCardDesign implements CardDesign {
    private static final Color[]colors = {
            Color.rgb(40, 30, 255), // Pure blue
            Color.rgb(70, 130, 180), // Steel blue
            Color.rgb(100, 149, 237), // Cornflower blue
            Color.rgb(0, 191, 255), // Deep sky blue
            Color.rgb(135, 206, 235), // Sky blue
            Color.rgb(70, 130, 180)

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
