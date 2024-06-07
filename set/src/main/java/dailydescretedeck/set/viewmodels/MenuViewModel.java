package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.models.Player;

public class MenuViewModel {
    private Scenes scenes;
    private Player player;

    public MenuViewModel(Player player) {
        scenes = new Scenes();
        this.player = player;
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
                scenes.showStoreView(player);
                break;
            case INSTRUCTIONS:
                break;
        }
    }
}
