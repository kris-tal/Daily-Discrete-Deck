package dailydescretedeck.set.services;

import java.util.ArrayList;
import java.util.List;

public class CollectedCards {

    List<Class<?>> collectedCards;
    Class<?> myCard;
    
    public CollectedCards() {
        collectedCards = SavingService.loadClassesFromFile("collectedCards.txt");
        myCard = SavingService.loadClassesFromFile("myCard.txt").get(0);
    }

    public void addCard(Class<?> card) {
        collectedCards = SavingService.loadClassesFromFile("collectedCards.txt");
        if (!collectedCards.contains(card)) {
            collectedCards.add(card);
            SavingService.saveClassesToFile("collectedCards.txt", collectedCards);
        }
    }

    public void setMyCard(Class<?> card) {
        myCard = SavingService.loadClassesFromFile("myCard.txt").get(0);
        myCard = card;
        SavingService.saveClassesToFile("myCard.txt", List.of(myCard));
    }

    public List<Class<?>> getCollectedCards() {
        collectedCards = SavingService.loadClassesFromFile("collectedCards.txt");
        return collectedCards;
    }

    public Class<?> getMyCard() {
        myCard = SavingService.loadClassesFromFile("myCard.txt").get(0);
        return myCard;
    }
}
