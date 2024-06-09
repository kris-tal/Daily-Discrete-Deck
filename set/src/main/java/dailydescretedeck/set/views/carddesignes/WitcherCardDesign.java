package dailydescretedeck.set.views.carddesignes;

import dailydescretedeck.set.viewmodels.CardDesign;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class WitcherCardDesign implements CardDesign {
    private static final Color[]colors = {
            Color.rgb(0, 0, 0),
            Color.rgb(112, 89, 76),
            Color.rgb(79, 46, 30),
            Color.rgb (139, 100, 73),
            Color.rgb (156, 131, 91),
            Color.rgb(173, 146, 120)
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
