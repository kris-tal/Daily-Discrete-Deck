package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.models.BoardState;
import dailydescretedeck.set.models.Player;
import dailydescretedeck.set.models.SimpleBoardState;
import dailydescretedeck.set.views.*;

import dailydescretedeck.set.views.ProfileView;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.util.Objects;

public class MenuViewModel {
    public enum MenuOptions {
        PLAY,
        PROFILE,
        STORE,
        INSTRUCTIONS,
        EXIT
    }

    Player player;

    public void handleInput(MenuOptions option, Stage stage) {
        switch (option) {
            case PLAY:
                System.out.println("Play Set");

                PlayViewModel playViewModel = new PlayViewModel();
                PlayView playView = new PlayView(playViewModel, stage);
                playView.display(stage);
                break;
            case PROFILE:
                System.out.println("Profile");
                ProfileView profileView = new ProfileView(stage);
                profileView.display();

                break;
            case STORE:
                System.out.println("Store");
                StoreView storeView = new StoreView(stage, player);
                storeView.display();
                break;
            case INSTRUCTIONS:
                System.out.println("Instructions");
//                InstructionsView instructionsView = new InstructionsView();
//                instructionsView.display(stage);
                break;
            case EXIT:
                System.out.println("Exit");
                stage.close();
                break;
        }
        //exit
    }
}