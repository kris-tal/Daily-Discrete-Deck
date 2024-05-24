package dailydescretedeck.set.models;

import javafx.scene.control.Alert;
import javafx.stage.Modality;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dailydescretedeck.set.services.Feature;


public class Board {
    private List<Card> cards = new ArrayList<>();
    private Deck deck;
    private int sets;

    public Board(int n) {
        this.deck = new Deck();
        sets = 0;
        for(int i = 0; i < n; i++) {
            ArrayList<Dots> list = new ArrayList<>();
            list.addAll(deck.drawCard().getFields());
            Card sc = new Card(list);
            addCard(sc);
        }
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card addCard(){
        Card card = deck.drawCard();
        if(card.getFields().size() != 0) cards.add((Card) card);
        else {
            card = deck.drawCard();
            cards.add(card);
        }
        return card;
    }
    public void drawCardFromDeck() {
        while(cards.size() < 7 && deck.size() > 0){
            Card card = deck.drawCard();
            if(card.getFields().size() != 0) cards.add(card);
        }
    }

    public List<Card> getDeck() {
        return deck.getRemainingCards();
    }

    public int getNumberSets() {
        return sets;
    }

    public Map<Dots, Integer> preparationToCount(List<Card> cards) {
        Map<Dots, Integer> map = new HashMap<>();
        for (Card card : cards) {
            for (int i = 0; i < card.getFields().size(); i++) {
                if (map.containsKey(card.getFields().get(i))) {
                    map.put(card.getFields().get(i), map.get(card.getFields().get(i)) + 1);
                } else {
                    map.put(card.getFields().get(i), 1);
                }
            }
        }
        return map;
    }

    public Card Xor(ArrayList<Card> cards) {
        Map<Dots, Integer> map = preparationToCount(cards);
        ArrayList<Dots> numbers = new ArrayList<>();
        for(Map.Entry<Dots, Integer> entry : map.entrySet())
        {
            if(entry.getValue() % 2 != 0)
            {
                numbers.add(entry.getKey());
            }
        }
        return new Card(numbers);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public void removeCards(List<Card> selectedCards) {
        for (Card c : selectedCards) {
            int index = cards.indexOf(c);
            if (index != -1) {
                cards.remove(c);
                Card newCard = deck.drawCard();
                if (newCard != null) {
                    if(newCard.getFields().size() != 0) cards.add(index, newCard);
                    else {
                        newCard = deck.drawCard();
                        cards.add(index, newCard);
                    }
                }
            }
        }
        if(cards.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Koniec gry");
            alert.setHeaderText(null);
            alert.setContentText("Wygrałeś!");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean isSetOk(List<Card> cards) {
        Map<Dots, Integer> map = preparationToCount(cards);
        if(map.keySet().size() == 0) return false;
        for (Integer value : map.values()) {
            if (value % 2 != 0) {
                return false;
            }
        }
        
        sets++;
        return true;
    }


    public List<Card> getSet() {
        List<List<Card>> everything = new ArrayList<>();
        Feature.fill(everything, new ArrayList<>(), cards, 0);
        for (List<Card> list : everything) {
            if(isSetOk(list) && list.size() != 0) return list;
        }
        return null;
    }

    public List<Card> getNotSet() {
        List<Card> everythingGood = getSet();
        List<Card> everything = new ArrayList<>(cards);
        everything.removeAll(everythingGood);
        return everything;
    }
}