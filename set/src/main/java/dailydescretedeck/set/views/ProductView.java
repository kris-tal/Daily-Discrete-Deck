package dailydescretedeck.set.views;

import dailydescretedeck.set.models.Product;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

public class ProductView extends Pane {
    private Product product;
    private Circle circle;
    private boolean selected = false;

    public ProductView(Product product, double radius) {
        this.product = product;
        circle = new Circle(radius);
        circle.setFill(getProductColor(product.getName()));
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(2);
        circle.setStrokeType(StrokeType.OUTSIDE);

        getChildren().add(circle);

        setOnMouseEntered(event -> circle.setFill(darkenColor((Color) circle.getFill(), 0.1)));
        setOnMouseExited(event -> {
            if (!selected) {
                circle.setFill(getProductColor(product.getName()));
            }
        });

        setOnMouseClicked(event -> select());
    }

    private Color getProductColor(String name) {
        switch (name) {
            case "Red Dots":
                return Color.RED;
            case "Blue Dots":
                return Color.BLUE;
            case "Green Dots":
                return Color.GREEN;
            case "Gold Dots":
                return Color.GOLD;
            case "Yellow Dots":
                return Color.YELLOW;
            case "Orange Dots":
                return Color.ORANGE;
            case "Gray Dots":
                return Color.OLIVE;
            case "Brown Dots":
                return Color.BROWN;
            default:
                return Color.GRAY;
        }
    }

    public void select() {
        if (!selected) {
            circle.setFill(darkenColor((Color) circle.getFill(), 0.5));
            selected = true;
            new Thread(() -> {
                try {
                    Thread.sleep(100); // Wait for 100 milliseconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                circle.setFill(getProductColor(product.getName()));
            }).start();
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
