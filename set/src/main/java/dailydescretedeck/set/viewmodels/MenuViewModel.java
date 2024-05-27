package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.models.BoardState;
import dailydescretedeck.set.models.SimpleBoardState;
import dailydescretedeck.set.views.PlayView;
import dailydescretedeck.set.views.ProfileView;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;


import dailydescretedeck.set.views.StoreView;
import javafx.stage.Stage;

public class MenuViewModel {
    private Runnable showPlayView;
    private Runnable showStoreView;

    public MenuViewModel(Runnable showPlayView, Runnable showStoreView) {
        this.showPlayView = showPlayView;
        this.showStoreView = showStoreView;
    }

    public enum MenuOptions {
        PLAY,
        PROFILE,
        STORE,
        INSTRUCTIONS
    }

    public void handleInput(MenuOptions option) {
        switch (option) {
            case PLAY:
                System.out.println("Play Set");
                showPlayView.run();
                break;
            case PROFILE:
                ProfileViewModel profileViewModel = new ProfileViewModel();
                ProfileView profileView = new ProfileView(profileViewModel);
                profileView.display(stage);
                break;
                break;
            case STORE:
                System.out.println("Store");
                showStoreView.run();
                break;
            case INSTRUCTIONS:
                System.out.println("Instructions");
                // Implement instructions view logic if needed
                break;
        }
    }
}break;