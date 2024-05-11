package dailydescretedeck.set.views.carddesignes;

import dailydescretedeck.set.models.CardDesign;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class DefaultCardDesign implements CardDesign {
    private static final Color []colors = {
            Color.CORAL, Color.RED, Color.GOLD, Color.YELLOWGREEN, Color.DEEPSKYBLUE, Color.MEDIUMORCHID
    };
    private static final Shape shape = new Circle();

    public Color getColor(int idx) {
        return colors[idx - 1];
    }
    public Shape getShape() {
        return shape;
    }
}
