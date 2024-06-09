package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.models.Dots;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;

public interface CardDesign {
    public Color getColor(int idx);
    //public Color[] getColors();
    public Shape getShape(double sq);
    public Color getBackgroundColor();

    List<Dots> getDotPositions();
}
