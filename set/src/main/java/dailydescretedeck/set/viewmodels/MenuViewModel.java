package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.models.BoardState;
import dailydescretedeck.set.models.SimpleBoardState;
import dailydescretedeck.set.views.PlayView;
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
                ProfileViewModel profileViewModel = new ProfileViewModel();
                ProfileView profileView = new ProfileView(profileViewModel, stage);
                profileView.display(stage);
                break;
            case STORE:
                System.out.println("Store");
                break;
            case INSTRUCTIONS:
                System.out.println("Instructions");
                break;
            case EXIT:
                System.out.println("Exit");
                stage.close();
                break;
        }
        //exit
    }
}