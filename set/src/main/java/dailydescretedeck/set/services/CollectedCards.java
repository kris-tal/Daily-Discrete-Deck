package dailydescretedeck.set.services;

import java.util.ArrayList;
import java.util.List;

public class CollectedCards {

    List<Class<?>> collectedCards;
    Class<?> myCard;
    
    public CollectedCards() {
        collectedCards = SavingService.loadClassesFromFile("collectedCards");
        myCard = SavingService.loadClassesFromFile("myCard").get(0);
    }

    public void addCard(Class<?> card) {
        collectedCards = SavingService.loadClassesFromFile("collectedCards");
        if (!collectedCards.contains(card)) {
            collectedCards.add(card);
            SavingService.saveClassesToFile("collectedCards", collectedCards);
        }
    }

    public void setMyCard(Class<?> card) {
        myCard = SavingService.loadClassesFromFile("myCard").get(0);
        myCard = card;
        SavingService.saveClassesToFile("myCard", List.of(myCard));
    }

    public List<Class<?>> getCollectedCards() {
        collectedCards = SavingService.loadClassesFromFile("collectedCards");
        return collectedCards;
    }

    public Class<?> getMyCard() {
        myCard = SavingService.loadClassesFromFile("myCard").get(0);
        return myCard;
    }
}
