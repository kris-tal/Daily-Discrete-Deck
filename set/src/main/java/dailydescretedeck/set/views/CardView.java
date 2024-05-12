package dailydescretedeck.set.views;

import dailydescretedeck.set.models.Card;
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

    private final Rectangle cardBackground;
    private final ArrayList<Shape> fields;

    public CardView(Card card, double X, double Y) {
        this.card = card;
        this.group = new Group();
        this.width = 120;
        this.height = 180;
        this.clicked = false;
        this.cardBackground = new Rectangle(width, height);
        this.cardBackground.setFill(card.getDesign().getBackgroundColor());
        this.cardBackground.setArcHeight(35);
        this.cardBackground.setArcWidth(35);
        this.cardBackground.setX(X);
        this.cardBackground.setY(Y);
        this.cardBackground.setStrokeWidth(0);
        this.cardBackground.setStroke(Color.rgb(116, 97, 116));
        this.group.getChildren().add(cardBackground);
        this.fields = new ArrayList<>();
        for(Integer field : card.getFields()) {
            Shape shape = card.getDesign().getShape();
            if(field == 1) {
                shape.setLayoutX(X + this.width / 120 * 34);
                shape.setLayoutY(Y + this.height / 180 * 36);
                shape.setFill(card.getDesign().getColor(field));
                this.fields.add(shape);
            }
            else if(field == 2) {
                shape.setLayoutX(X + this.width / 120 * 86);
                shape.setLayoutY(Y + this.height / 180 * 36);
                shape.setFill(card.getDesign().getColor(field));
                this.fields.add(shape);
            }
            else if(field == 3) {
                shape.setLayoutX(X + this.width / 120 * 34);
                shape.setLayoutY(Y + this.height / 180 * 90);
                shape.setFill(card.getDesign().getColor(field));
                this.fields.add(shape);
            }
            else if(field == 4) {
                shape.setLayoutX(X + this.width / 120 * 86);
                shape.setLayoutY(Y + this.height / 180 * 90);
                shape.setFill(card.getDesign().getColor(field));
                this.fields.add(shape);
            }
            else if(field == 5) {
                shape.setLayoutX(X + this.width / 120 * 34);
                shape.setLayoutY(Y + this.height / 180 * 144);
                shape.setFill(card.getDesign().getColor(field));
                this.fields.add(shape);
            }
            else if(field == 6) {
                shape.setLayoutX(X + this.width / 120 * 86);
                shape.setLayoutY(Y + this.height / 180 * 144);
                shape.setFill(card.getDesign().getColor(field));
                this.fields.add(shape);
            }

        }
        this.group.getChildren().addAll(fields);
        super.getChildren().add(this.group);

        setOnMouseClicked(event -> clicked());
    }

    private void clicked() {
        System.out.println("clicked");
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

    @Override
    public ObservableList<Node> getChildren() {
        ArrayList<Node> list = new ArrayList<>();
        list.add(cardBackground);
        list.addAll(fields);
        return super.getChildren();
    }

    //obsluga interakcji

    //zaznaczenie
}
