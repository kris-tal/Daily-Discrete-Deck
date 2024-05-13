package dailydescretedeck.set;

import dailydescretedeck.set.models.BoardState;
import dailydescretedeck.set.models.Card;
import dailydescretedeck.set.models.SimpleBoardState;
import dailydescretedeck.set.services.Game;
import dailydescretedeck.set.views.CardView;
import dailydescretedeck.set.views.PlayView;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

import static javafx.application.Application.launch;

/*

public class Set<I extends Number> extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, Color.THISTLE);
        stage.setWidth(1080);
        stage.setHeight(720);
        stage.setResizable(true);

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

 */


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Set<I extends Number> extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BoardState boardState = new SimpleBoardState(7);
        PlayView playView = new PlayView(boardState);
        Game game = new Game(boardState);

        Scene scene = new Scene(playView,1000, 800);
        scene.getRoot().setStyle("-fx-background-color: thistle;");
        try {
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/icon.png")));
            stage.getIcons().add(icon);
        } catch(RuntimeException image) { System.out.println("Image not found"); }

        stage.setTitle("Daily Descrete Deck");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
