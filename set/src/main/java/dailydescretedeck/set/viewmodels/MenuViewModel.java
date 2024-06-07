package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.models.Player;

public class MenuViewModel {
    private Scenes scenes;

    public MenuViewModel() {
        scenes = new Scenes();
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
                scenes.showPlayView();
                break;
            case PROFILE:
                scenes.showProfileView();
                break;
            case STORE:
                scenes.showStoreView();
                break;
            case INSTRUCTIONS:
                break;
        }
    }
}
