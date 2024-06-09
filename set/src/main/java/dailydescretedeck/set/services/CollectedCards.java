package dailydescretedeck.set.services;

import java.util.ArrayList;
import java.util.List;

public class CollectedCards {

    List<Class<?>> collectedCards;
    Class<?> myCard;
    
    public CollectedCards() {
        collectedCards = SavingService.loadClassesFromFile("saves/collectedCards.txt");
        myCard = SavingService.loadClassesFromFile("saves/myCard.txt").get(0);
    }

    public void addCard(Class<?> card) {
        collectedCards = SavingService.loadClassesFromFile("saves/collectedCards.txt");
        if (!collectedCards.contains(card)) {
            collectedCards.add(card);
            SavingService.saveClassesToFile("saves/collectedCards.txt", collectedCards);
        }
    }

    public void setMyCard(Class<?> card) {
        myCard = SavingService.loadClassesFromFile("saves/myCard.txt").get(0);
        myCard = card;
        SavingService.saveClassesToFile("saves/myCard.txt", List.of(myCard));
    }

    public List<Class<?>> getCollectedCards() {
        collectedCards = SavingService.loadClassesFromFile("saves/collectedCards.txt");
        return collectedCards;
    }

    public Class<?> getMyCard() {
        myCard = SavingService.loadClassesFromFile("saves/myCard.txt").get(0);
        return myCard;
    }
}
