package dailydescretedeck.set.viewmodels;

import javafx.stage.Stage;

public class MenuViewModel {
    public interface ViewSwitcher {
        void switchView();
    }

    private ViewSwitcher playViewSwitcher;
    private Stage stage;

    public MenuViewModel(ViewSwitcher playViewSwitcher, Stage stage) {
        this.playViewSwitcher = playViewSwitcher;
        this.stage = stage;
    }

    public void handleInput(MenuOptions option) {
        switch (option) {
            case PLAY:
                System.out.println("Play Set");
                playViewSwitcher.switchView();
                break;
            case EXIT:
                System.out.println("Exit");
                stage.close();
                break;
        }
    }

    public enum MenuOptions {
        PLAY,
        EXIT
    }
}
