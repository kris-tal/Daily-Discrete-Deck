package dailydescretedeck.set.FileManagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dailydescretedeck.set.models.CardDesigns;

public class CollectedCards {

    ArrayList<CardDesigns> collectedCards;
    CardDesigns myCard;
    
    public CollectedCards() {
        collectedCards = SavingService.loadClassesFromFile("saves/collectedCards.txt");
        myCard = SavingService.loadClassesFromFile("saves/myCard.txt").get(0);
    }

    public void addCards(List<CardDesigns> card) {
        collectedCards = SavingService.loadClassesFromFile("saves/collectedCards.txt");
        for (CardDesigns c : card) {
            if (!collectedCards.contains(c)) {
                collectedCards.add(c);
            }
        }
        SavingService.saveClassesToFile("saves/collectedCards.txt", collectedCards);
    }

    public void setMyCard(CardDesigns card) {
        myCard = SavingService.loadClassesFromFile("saves/myCard.txt").get(0);
        myCard = card;
        SavingService.saveClassesToFile("saves/myCard.txt", new ArrayList<>(Collections.singletonList(myCard)));
    }

    public ArrayList<CardDesigns> getCollectedCards() {
        collectedCards = SavingService.loadClassesFromFile("saves/collectedCards.txt");
        return collectedCards;
    }

    public CardDesigns getMyCard() {
        myCard = SavingService.loadClassesFromFile("saves/myCard.txt").get(0);
        return myCard;
    }
}
