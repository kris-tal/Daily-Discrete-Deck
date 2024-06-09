package dailydescretedeck.set.views;

import dailydescretedeck.set.models.Card;
import dailydescretedeck.set.models.Dots;
import dailydescretedeck.set.viewmodels.CardDesign;
import dailydescretedeck.set.viewmodels.CardViewModel;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class CardView extends Pane {
    private final CardViewModel cardViewModel;
    private Group group;
    private double width;
    private double height;
    private boolean clicked;
    static private boolean disabled = false;
    private boolean thiscarddisabled;

    public Rectangle cardBackground;
    private ArrayList<Shape> fields;

    public CardView(CardViewModel cardViewModel, double X, double Y, double sq) {
        this.cardViewModel = cardViewModel;
        thiscarddisabled = false;
        buildCard(X, Y, sq);
    }

    public CardView(CardDesign design, double X, double Y, double sq) {
        List<Dots> dots = design.getDotPositions();
        this.cardViewModel = new CardViewModel(dots, design);
        thiscarddisabled = false;
        buildCard(X, Y, sq);
    }

    public CardView(List<Dots> existingFields, CardDesign design, double X, double Y, double sq) {
        this.cardViewModel = new CardViewModel(existingFields, design);
        thiscarddisabled = false;
        buildCard(X, Y, sq);
    }

    private void buildCard(double X, double Y, double sq) {
        this.group = new Group();
        this.width = 120 * sq;
        this.height = 180 * sq;
        this.clicked = false;
        this.cardBackground = new Rectangle(width, height);
        this.cardBackground.setFill(cardViewModel.getDesign().getBackgroundColor());
        this.cardBackground.setArcHeight(35 * sq);
        this.cardBackground.setArcWidth(35 * sq);
        this.cardBackground.setX(X);
        this.cardBackground.setY(Y);
        this.cardBackground.setStrokeWidth(0);
        this.cardBackground.setStroke(Color.rgb(116, 97, 116));
        this.group.getChildren().add(cardBackground);
        this.fields = new ArrayList<>();
        for (Dots field : card.getFields()) {
            Shape shape = card.getDesign().getShape(sq);
            if (field == Dots.A1) {
                shape.setLayoutX(X + this.width / 120 * 34);
                shape.setLayoutY(Y + this.height / 180 * 36);
                shape.setFill(card.getDesign().getColor(1));
                this.fields.add(shape);
            } else if (field == Dots.A2) {
                shape.setLayoutX(X + this.width / 120 * 86);
                shape.setLayoutY(Y + this.height / 180 * 36);
                shape.setFill(card.getDesign().getColor(2));
                this.fields.add(shape);
            } else if (field == Dots.B1) {
                shape.setLayoutX(X + this.width / 120 * 34);
                shape.setLayoutY(Y + this.height / 180 * 90);
                shape.setFill(card.getDesign().getColor(3));
                this.fields.add(shape);
            } else if (field == Dots.B2) {
                shape.setLayoutX(X + this.width / 120 * 86);
                shape.setLayoutY(Y + this.height / 180 * 90);
                shape.setFill(card.getDesign().getColor(4));
                this.fields.add(shape);
            } else if (field == Dots.C1) {
                shape.setLayoutX(X + this.width / 120 * 34);
                shape.setLayoutY(Y + this.height / 180 * 144);
                shape.setFill(card.getDesign().getColor(5));
                this.fields.add(shape);
            } else if (field == Dots.C2) {
                shape.setLayoutX(X + this.width / 120 * 86);
                shape.setLayoutY(Y + this.height / 180 * 144);
                shape.setFill(card.getDesign().getColor(6));
                this.fields.add(shape);
            }
        }
        this.group.getChildren().addAll(fields);
        super.getChildren().add(this.group);

        setOnMouseClicked(event -> clicked());
    }

    public void clicked() {
        if (disabled) return;
        if (thiscarddisabled) return;
        if (!this.clicked) {
            this.cardBackground.setStrokeWidth(3.0);
            this.cardBackground.setStroke(Color.rgb(116, 97, 116));
            this.clicked = true;
        } else {
            this.cardBackground.setStrokeWidth(0);
            this.clicked = false;
        }
    }

    public void unclick() {
        this.cardBackground.setStrokeWidth(0);
        this.clicked = false;
    }

    static public void disableCards() {
        disabled = true;
    }

    static public void enableCards() {
        disabled = false;
    }

    public void disableThisCard() {
        thiscarddisabled = true;
    }

    @Override
    public ObservableList<Node> getChildren() {
        ArrayList<Node> list = new ArrayList<>();
        list.add(cardBackground);
        list.addAll(fields);
        return super.getChildren();
    }

    public void selectNotSelected() {
        this.cardBackground.setFill(((Color) this.cardBackground.getFill()).darker());
        for (Shape field : fields) {
            field.setFill(((Color) field.getFill()).darker());
        }
    }

    public void updateScale(double sq) {
        this.width = 120 * sq;
        this.height = 180 * sq;
        this.cardBackground.setWidth(width);
        this.cardBackground.setHeight(height);
        this.cardBackground.setArcHeight(35 * sq);
        this.cardBackground.setArcWidth(35 * sq);

        for (int i = 0; i < fields.size(); i++) {
            Shape shape = fields.get(i);
            if (i == 0) {
                shape.setLayoutX(this.width / 120 * 34);
                shape.setLayoutY(this.height / 180 * 36);
            } else if (i == 1) {
                shape.setLayoutX(this.width / 120 * 86);
                shape.setLayoutY(this.height / 180 * 36);
            } else if (i == 2) {
                shape.setLayoutX(this.width / 120 * 34);
                shape.setLayoutY(this.height / 180 * 90);
            } else if (i == 3) {
                shape.setLayoutX(this.width / 120 * 86);
                shape.setLayoutY(this.height / 180 * 90);
            } else if (i == 4) {
                shape.setLayoutX(this.width / 120 * 34);
                shape.setLayoutY(this.height / 180 * 144);
            } else if (i == 5) {
                shape.setLayoutX(this.width / 120 * 86);
                shape.setLayoutY(this.height / 180 * 144);
            }
            shape.setScaleX(sq/2.2);
            shape.setScaleY(sq/2.2);
        }
    }
}
