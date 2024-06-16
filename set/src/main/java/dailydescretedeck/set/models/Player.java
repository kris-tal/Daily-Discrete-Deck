package dailydescretedeck.set.models;

import dailydescretedeck.set.services.Money;
import javafx.beans.property.ListProperty;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String username;
    private static int OOPoints;
    private static Money money;
    private static CardDesigns cardDesignInUse;
    private List<CardDesigns> ownedDesigns;

    public Player(String username) {
        this.username = username;
        this.cardDesignInUse = CardDesigns.DEFAULT;
        //this.cardDesignInUse = new FantasyCardDesign();
        this.money = new Money();
        this.ownedDesigns = new ArrayList<>();
        this.ownedDesigns.add(cardDesignInUse);
    }

    public Player(int points, String username, String pwd) {
        this.OOPoints = points;
        this.username = username;
        this.money = new Money();
    }

    public String getUsername() {
        return username;
    }

    public int getPoints() {
        return OOPoints;
    }

    public static CardDesigns getCardDesignInUse() {
        return cardDesignInUse;
    }

    public void updatePoints(int points) {
        this.OOPoints = points;
    }

    public long getMoney() {
        return money.getMoney();
    }


    public void addMoney(int amount) {
        this.money.addMoney(amount);;
    }

    public boolean spendMoney(int amount) {
        if (money.getMoney() >= amount) {
            this.money.spendMoney(amount); 
            return true;
        }
        return false;
    }

    public void addDesigns(ListProperty<Product> cartItems) {
        for (Product p : cartItems) {
            ownedDesigns.add(p.getDesign());
        }
    }

    public List<CardDesigns> getOwnedDesigns() {
        return ownedDesigns;
    }

    public void setDesign(CardDesigns selectedDesign) {
        this.cardDesignInUse = selectedDesign;
    }
}
