package dailydescretedeck.set.views;

import dailydescretedeck.set.models.Product;
import dailydescretedeck.set.viewmodels.CardDesign;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

public class ProductView extends HBox {
    private Product product;
    private Circle[] circles;
    private Color[] originalColors;
    private boolean selected = false;

    public ProductView(Product product) {
        this.product = product;
        this.circles = new Circle[6];
        this.originalColors = new Color[6];

        CardDesign design = product.getDesign();

        setAlignment(Pos.CENTER);
        setSpacing(5);

        for (int i = 0; i < circles.length; i++) {
            circles[i] = new Circle();
            originalColors[i] = design.getColor(i + 1);
            circles[i].setFill(originalColors[i]);
            circles[i].setStroke(Color.BLACK);
            circles[i].setStrokeWidth(1);
            circles[i].setStrokeType(StrokeType.OUTSIDE);

            circles[i].radiusProperty().bind(widthProperty().multiply(0.04));

            getChildren().add(circles[i]);
        }

        setOnMouseEntered(event -> setCirclesColor(0.1));
        setOnMouseExited(event -> {
            if (!selected) {
                resetCirclesColor();
            }
        });

        setOnMouseClicked(event -> select());
    }

    public void select() {
        if (!selected) {
            setCirclesColor(0.5);
            selected = true;
        }
        else {
            resetCirclesColor();
            selected = false;
        }
    }

    private void setCirclesColor(double factor) {
        for (Circle circle : circles) {
            circle.setFill(darkenColor((Color) circle.getFill(), factor));
        }
    }

    private void resetCirclesColor() {
        for (int i = 0; i < circles.length; i++) {
            circles[i].setFill(originalColors[i]);
        }
    }

    private Color darkenColor(Color color, double factor) {
        return color.deriveColor(0, 1, 1 - factor, 1);
    }

    public boolean isSelected() {
        return selected;
    }

    public Product getProduct() {
        return product;
    }
}
