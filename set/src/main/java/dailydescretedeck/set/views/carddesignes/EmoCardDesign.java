package dailydescretedeck.set.views.carddesignes;

import dailydescretedeck.set.viewmodels.CardDesign;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class EmoCardDesign implements CardDesign {
    private static final Color[]colors = {
            Color.rgb(90, 90, 90),
            Color.rgb(70, 70, 70),
            Color.rgb(170, 170, 170),
            Color.rgb(140, 140, 140),
            Color.rgb(120, 120, 120),
            Color.rgb(60, 60, 60)
    };

    private static final Color backgroundColor = Color.rgb(20, 20, 20);

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
