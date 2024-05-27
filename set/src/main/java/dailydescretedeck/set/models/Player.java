package dailydescretedeck.set.models;

import dailydescretedeck.set.views.carddesignes.DefaultCardDesign;
import dailydescretedeck.set.views.carddesignes.EmoCardDesign;
import dailydescretedeck.set.views.carddesignes.JHCardDesign;

public class Player {
    private final String username;
    private final int password;      //to tam sie potem zhaszuje zamiast trzymac w stringu
    private int OOPoints;
    private static CardDesign cardDesignInUse;

    public Player(String username, int pwd) {
        this.username = username;
        this.password = pwd;
        this.cardDesignInUse = new DefaultCardDesign(); //new JHCardDesign();
    }

    public Player(int points, String username, int pwd) {
        this.OOPoints = points;
        this.username = username;
        this.password = pwd;
    }

    //moze trzeba nadpisac finalize ale nie wiem

    public String getUsername() {
        return username;
    }

    public int getPassword() {
        return password;
    }

    public int getPoints() {
        return OOPoints;
    }

    public static CardDesign getCardDesignInUse() {
        return cardDesignInUse;
    }

    public void updatePoints(int points) {
        this.OOPoints = points;
    }

}
