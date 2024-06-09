package dailydescretedeck.set.views.carddesignes;

import dailydescretedeck.set.models.Dots;
import dailydescretedeck.set.viewmodels.CardDesign;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GalaxyCardDesign implements CardDesign {
    private static final Color[] colors = {
            Color.rgb(71, 25, 196),
            Color.rgb(31, 164, 190),
            Color.rgb(112, 27, 196),
            Color.rgb(102, 0, 102),
            Color.rgb(10, 103, 150),
            Color.rgb(60, 60, 60)
    };

    private static final Color backgroundColor = Color.WHITE;

    @Override
    public Color getColor(int idx) {
        return colors[idx - 1];
    }

    @Override
    public Shape getShape(double sq) {
        return new Circle(15 * sq);
    }

    @Override
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public List<Dots> getDotPositions() {
        return Arrays.asList(Dots.A1, Dots.A2, Dots.B1, Dots.B2, Dots.C1, Dots.C2);
    }
}
