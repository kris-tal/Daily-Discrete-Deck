package dailydescretedeck.set.views.carddesignes;

import dailydescretedeck.set.viewmodels.CardDesign;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class OrangeCardDesign implements CardDesign {
    private static final Color[] colors = {
            Color.rgb(255, 152, 0),
            Color.rgb(240, 204, 15),
            Color.rgb(255, 60, 40),
            Color.rgb(255, 183, 77),
            Color.rgb(255, 241, 118),
            Color.rgb(255, 111, 0)
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
