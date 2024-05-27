package dailydescretedeck.set.models;

import javafx.scene.control.Alert;
import java.nio.file.Path;
import javafx.stage.Modality;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dailydescretedeck.set.services.End;
import dailydescretedeck.set.services.Feature;


public class Board {
    private List<Card> cards;
    private Deck deck;
    private int sets;

    public Board(int n) {
        this.cards = new ArrayList<>();
        this.deck = new Deck();
        this.sets = 0;
        for (int i = 0; i < n; i++) {
            this.cards.add(deck.drawCard());
        }
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public Deck getDeck() {
        return deck;
    }

    public int getNumberSets() {
        return sets;
    }

    public void incrementSets() {
        sets++;
    }

    public void reset() {
        this.cards.clear();
        this.deck = new Deck();
        this.sets = 0;
        for (int i = 0; i < 7; i++) {
            this.cards.add(deck.drawCard());
        }
    }

    public void update() {
        while (cards.size() < 7 && deck.size() > 0) {
            cards.add(deck.drawCard());
        }
    }

    public void drawCardFromDeck() {
        while (cards.size() < 7 && deck.size() > 0) {
            cards.add(deck.drawCard());
        }
    }
}
