package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.models.BoardState;
import dailydescretedeck.set.models.SimpleBoardState;
import dailydescretedeck.set.views.PlayView;

public class MenuViewModel {
    public enum MenuOptions {
        PLAY,
        PROFILE,
        STORE,
        INSTRUCTIONS
    }

    public void handleInput(MenuOptions option) {
        switch (option) {
            case PLAY:
                BoardState boardState = new SimpleBoardState(7);
                PlayView playView = new PlayView(new PlayViewModel(boardState));
                break;
            case PROFILE:
                System.out.println("Profile");
                break;
            case STORE:
                System.out.println("Store");
                break;
        case INSTRUCTIONS:
                System.out.println("Instructions");
                break;
        }
        //exit
    }
}