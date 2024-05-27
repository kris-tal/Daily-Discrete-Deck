package dailydescretedeck.set.models;

import dailydescretedeck.set.views.carddesignes.DefaultCardDesign;

public class Player {
    private final String username;
    private final String password;
    private int OOPoints;
    private int money;
    private CardDesign cardDesignInUse;

    private static int playersCount = 0;

    public Player(String username, String pwd) {
        this.username = username;
        this.password = pwd;
        this.cardDesignInUse = new DefaultCardDesign();
        this.money = 1000;
        playersCount++;
    }

    public Player(int points, String username, String pwd) {
        this.OOPoints = points;
        this.username = username;
        this.password = pwd;
        this.money = 1000;
        playersCount++;
        System.out.printf("User constructor: %s %s; count = %d\n", username, password, playersCount);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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
