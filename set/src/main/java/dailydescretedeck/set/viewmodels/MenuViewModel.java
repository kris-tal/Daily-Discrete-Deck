package dailydescretedeck.set.viewmodels;

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
                showPlayView.run();
                break;
            case PROFILE:
                // Implement profile view logic if needed
                break;
            case STORE:
                showStoreView.run();
                break;
            case INSTRUCTIONS:
                // Implement instructions view logic if needed
                break;
        }
    }
}
