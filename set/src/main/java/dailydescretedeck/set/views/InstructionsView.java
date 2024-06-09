package dailydescretedeck.set.views;

import dailydescretedeck.set.models.Card;
import dailydescretedeck.set.models.Dots;
import dailydescretedeck.set.viewmodels.CardDesign;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InstructionsView extends HBox {
    public InstructionsView() {
        display();
    }

    public void display() {
        setStyle("-fx-background-color: thistle;");
        VBox leftPane = new VBox();
        leftPane.setPadding(new javafx.geometry.Insets(20));
        HBox rightPane = new HBox();
        rightPane.setPadding(new javafx.geometry.Insets(20));
        GridPane gridPane = new GridPane();
        gridPane.add(leftPane, 0, 0);
        gridPane.add(rightPane, 1, 0);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(80);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(20);
        gridPane.getColumnConstraints().addAll(column1, column2);

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        gridPane.setPrefSize(screenBounds.getWidth(), screenBounds.getHeight());

        gridPane.getColumnConstraints().addAll(column1, column2);
        Label instructions = new Label();
        instructions.setFont(new javafx.scene.text.Font("System", 16));
        instructions.setWrapText(true);
        instructions.setText(
                "Setup:\n" +
                "- There are 63 unique cards\n" +
                "- Each card may have one dot in one of six different colors, or no dot at all\n" +
                "- At the start, 7 cards are laid out on the board\n\n" +
                "Gameplay:\n" +
                "- A SET is a group of at least 3 cards where each colored dot appears an even number of times across the selected cards\n" +
                "- Choose any subset of cards you think will form a SET.\n" +
                "- If correct, those cards are removed from the game.\n" +
                "- After each move, new cards are added from the stack to maintain 7 cards on the board\n" +
                "- If the stack runs out and there are fewer than 7 cards left, you must form a SET from these remaining cards\n\n" +
                "Winning:\n" +
                "- The game continues until there are no cards left\n" +
                "- If you select an incorrect SET, you lose the game\n" +
                "- If you find all the SETs, you win, the faster the better \n\n" +
                "Good luck!");

        instructions.setPrefSize(screenBounds.getWidth(), screenBounds.getHeight());
        leftPane.getChildren().add(instructions);
        List<CardView> cards = new ArrayList<>();
        List<Dots> list = Arrays.asList(Dots.A1, Dots.A2, Dots.B1, Dots.B2, Dots.C1, Dots.C2);

        double cardWidth = 120;
        double cardSize = 1;

        VBox cardContainer = new VBox();
        cardContainer.setSpacing(10);
        cardContainer.setPadding(new javafx.geometry.Insets(20));
        cardContainer.setAlignment(Pos.BASELINE_CENTER);

        /*
        cards.add(new CardView((CardDesign) new ArrayList<>(Arrays.asList(Dots.A1, Dots.B2, Dots.B1)), 0, 0, 1));
        cards.add(new CardView((CardDesign) new ArrayList<>(Arrays.asList(Dots.A2, Dots.B2, Dots.C1, Dots.C2)),0 , 0, 1));
        cards.add(new CardView((CardDesign) new ArrayList<>(Arrays.asList(Dots.A1, Dots.A2, Dots.B1, Dots.C1, Dots.C2)), 0, 0, 1));
        cardContainer.getChildren().addAll(cards);
        rightPane.getChildren().add(cardContainer);

         */
        getChildren().add(gridPane);


    }
}
