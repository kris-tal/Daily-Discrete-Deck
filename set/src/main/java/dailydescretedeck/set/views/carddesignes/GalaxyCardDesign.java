package dailydescretedeck.set.views.carddesignes;

import dailydescretedeck.set.viewmodels.CardDesign;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class GalaxyCardDesign implements CardDesign {
    private static final Color[]colors = {
            Color.rgb(71, 25, 196),
            Color.rgb(31, 164, 190),
            Color.rgb(112, 27, 196),
            Color.rgb(102, 0, 102),
            Color.rgb(10, 103, 150),
            Color.rgb(60, 60, 60)
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
