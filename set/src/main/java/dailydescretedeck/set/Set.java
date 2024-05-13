package dailydescretedeck.set;

import dailydescretedeck.set.models.Card;
import dailydescretedeck.set.views.CardView;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

import static javafx.application.Application.launch;

public class Set<I extends Number> extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        root.setAccessibleHelp("true");
        Scene scene = new Scene(root, Color.THISTLE);
        stage.setWidth(1080);
        stage.setHeight(720);
        stage.setResizable(true);
        try {
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/icon.png")));             //laze mi dac not null inaczej warning
            stage.getIcons().add(icon);
        } catch(RuntimeException image) { System.out.println("Image not found"); }

        stage.setTitle("Daily Descrete Deck");
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        //Card card = new Card(list);
        //CardView cardView = new CardView(card, 100, 100);
        //root.getChildren().add(cardView);
        stage.setScene(scene);
        stage.show();
    }

    static public void main(String[] args) {
        System.out.println("Daily Descrete Deck");
        launch(args);   //Application.launch()
    }
}
