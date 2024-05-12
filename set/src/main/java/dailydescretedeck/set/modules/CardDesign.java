package dailydescretedeck.set.modules;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public interface CardDesign {
    public Color getColor(int idx);
    public Shape getShape();
    public Color getBackgroundColor();
}
