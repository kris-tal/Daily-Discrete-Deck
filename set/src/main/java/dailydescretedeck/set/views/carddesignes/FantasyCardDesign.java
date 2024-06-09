package dailydescretedeck.set.views.carddesignes;

import dailydescretedeck.set.viewmodels.CardDesign;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class FantasyCardDesign implements CardDesign {
    private static final Color[]colors = {
            Color.rgb (229, 184, 210),
            Color.rgb (197, 140, 137),
            Color.rgb (176, 123, 140),
            Color.rgb(155, 92, 135),
            Color.rgb(98, 66, 94),
            Color.rgb(49, 34, 68)

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
