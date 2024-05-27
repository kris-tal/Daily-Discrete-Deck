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

                PlayViewModel playViewModel = new PlayViewModel();
                PlayView playView = new PlayView(playViewModel, stage);
                playView.display(stage);
                break;
            case PROFILE:
                ProfileView profileView = new ProfileView(stage);
                profileView.display();

                break;
            case STORE:
                StoreView storeView = new StoreView(stage, player);
                storeView.display();
                break;
            case INSTRUCTIONS:
//                InstructionsView instructionsView = new InstructionsView();
//                instructionsView.display(stage);
                break;
            case EXIT:
                stage.close();
                break;
        }
        //exit
    }
}