package dailydescretedeck.set.views;

import dailydescretedeck.set.models.Card;
import dailydescretedeck.set.models.Dots;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class CardView extends Pane {
    private final Card card;
    private final Group group;
    private double width;
    private double height;
    private boolean clicked;
    static private boolean disabled = false;
    private boolean thiscarddisabled;

    public final Rectangle cardBackground;
    private final ArrayList<Shape> fields;

    public CardView(Card card, double X, double Y, double sq) {
        this.card = card;
        thiscarddisabled = false;
        this.group = new Group();
        this.width = 120 * sq;
        this.height = 180 * sq;
        this.clicked = false;
        this.cardBackground = new Rectangle(width, height);
        this.cardBackground.setFill(card.getDesign().getBackgroundColor());
        this.cardBackground.setArcHeight(35 * sq);
        this.cardBackground.setArcWidth(35 * sq);
        this.cardBackground.setX(X);
        this.cardBackground.setY(Y);
        this.cardBackground.setStrokeWidth(0);
        this.cardBackground.setStroke(Color.rgb(116, 97, 116));
        this.group.getChildren().add(cardBackground);
        this.fields = new ArrayList<>();
        for(Dots field : card.getFields()) {
            Shape shape = card.getDesign().getShape(sq);
            if(field == Dots.A1) {
                shape.setLayoutX(X + this.width / 120 * 34);
                shape.setLayoutY(Y + this.height / 180 * 36);
                shape.setFill(card.getDesign().getColor(1));
                this.fields.add(shape);
            }
            else if(field == Dots.A2) {
                shape.setLayoutX(X + this.width / 120 * 86);
                shape.setLayoutY(Y + this.height / 180 * 36);
                shape.setFill(card.getDesign().getColor(2));
                this.fields.add(shape);
            }
            else if(field == Dots.B1) {
                shape.setLayoutX(X + this.width / 120 * 34);
                shape.setLayoutY(Y + this.height / 180 * 90);
                shape.setFill(card.getDesign().getColor(3));
                this.fields.add(shape);
            }
            else if(field == Dots.B2) {
                shape.setLayoutX(X + this.width / 120 * 86);
                shape.setLayoutY(Y + this.height / 180 * 90);
                shape.setFill(card.getDesign().getColor(4));
                this.fields.add(shape);
            }
            else if(field == Dots.C1) {
                shape.setLayoutX(X + this.width / 120 * 34);
                shape.setLayoutY(Y + this.height / 180 * 144);
                shape.setFill(card.getDesign().getColor(5));
                this.fields.add(shape);
            }
            else if(field == Dots.C2) {
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
        System.out.println("clicked");
        if(disabled) return;
        if(thiscarddisabled) return;
        if(!this.clicked) {
            this.cardBackground.setStrokeWidth(3.0);
            this.cardBackground.setStroke(Color.rgb(116, 97, 116));
            this.clicked = true;
        }
        else {
            this.cardBackground.setStrokeWidth(0);
            this.clicked = false;
        }
    }

    public void select() {
        this.cardBackground.setFill(((Color)this.cardBackground.getFill()).darker());
        for(Shape field : fields) {
            field.setFill(((Color)field.getFill()).darker());
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
        this.cardBackground.setFill(((Color)this.cardBackground.getFill()).darker());
        for(Shape field : fields) {
            field.setFill(((Color)field.getFill()).darker());
        }
    }

    //obsluga interakcji

    //zaznaczenie
}
