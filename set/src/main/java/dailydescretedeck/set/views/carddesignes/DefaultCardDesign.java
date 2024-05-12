package dailydescretedeck.set.views.carddesignes;

import dailydescretedeck.set.modules.CardDesign;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class DefaultCardDesign implements CardDesign {
    private static final Color []colors = {
            Color.CORAL, Color.RED, Color.GOLD, Color.YELLOWGREEN, Color.DEEPSKYBLUE, Color.MEDIUMORCHID
    };
    private static final Color backgroundColor = Color.WHITE;

    public Color getColor(int idx) {
        return colors[idx - 1];
    }
    public Shape getShape() {
        return new Circle(15);
    }
    public Color getBackgroundColor() {
        return backgroundColor;
    }
}