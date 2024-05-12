package dailydescretedeck.set.services;
import java.util.List;
import dailydescretedeck.set.models.Board;
import dailydescretedeck.set.models.Card;

public class AddNewCards {
    private Board board;
    private List<Card> tableCards;
    
    public AddNewCards(Board b, List<Card> cards) {
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
