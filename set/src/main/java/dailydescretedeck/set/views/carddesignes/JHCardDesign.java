package dailydescretedeck.set.views.carddesignes;

import dailydescretedeck.set.viewmodels.CardDesign;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class JHCardDesign implements CardDesign {
    private static final Color[]colors = {
            Color.rgb(39, 139, 242),
            Color.rgb(250, 80, 182),
            Color.rgb(235, 160, 222),
            Color.rgb(240, 216, 34),
            Color.rgb(39, 201, 242),
            Color.rgb(161, 64, 207)
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
