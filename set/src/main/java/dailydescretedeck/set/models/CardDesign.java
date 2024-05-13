package dailydescretedeck.set.models;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public interface CardDesign {
    public Color getColor(int idx);
    public Shape getShape(double sq);
    public Color getBackgroundColor();

}
