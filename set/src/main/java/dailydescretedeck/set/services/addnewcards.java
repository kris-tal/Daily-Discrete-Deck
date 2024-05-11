package dailydescretedeck.set.services;
import java.util.List;
import dailydescretedeck.set.modules.Board;
import dailydescretedeck.set.modules.Card;

public class addnewcards {
    private Board board;
    private List<Card> tableCards;
    
    public addnewcards(Board b, List<Card> cards) {
        this.board = b;
        this.tableCards = cards;
    }
    public void add(int number) {
        for (int i = 0; i < number; i++) {
            Card newCard = board.drawCard();
            if (newCard != null) {
                tableCards.add(newCard);
            } 
        }
        board.refresh();
    }
}
