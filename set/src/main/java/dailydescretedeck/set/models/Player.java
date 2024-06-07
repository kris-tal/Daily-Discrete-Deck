package dailydescretedeck.set.models;

import dailydescretedeck.set.viewmodels.CardDesign;
import dailydescretedeck.set.views.carddesignes.DefaultCardDesign;

public class Player {
    private final String username;
    private static int OOPoints;
    private static int money;
    private CardDesign cardDesignInUse;

    public Player(String username) {
        this.username = username;
        this.cardDesignInUse = new DefaultCardDesign();
        this.money = 1000;
    }

    public Player(int points, String username, String pwd) {
        this.OOPoints = points;
        this.username = username;
        this.money = 1000;
    }

    public String getUsername() {
        return username;
    }

    public int getPoints() {
        return OOPoints;
    }

    public CardDesign getCardDesignInUse() {
        return cardDesignInUse;
    }

    public void updatePoints(int points) {
        this.OOPoints = points;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int amount) {
        this.money += amount;
    }

    public boolean spendMoney(int amount) {
        if (money >= amount) {
            this.money -= amount;
            return true;
        }
        return false;
    }
}
