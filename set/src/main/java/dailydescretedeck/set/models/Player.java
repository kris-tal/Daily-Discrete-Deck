package dailydescretedeck.set.models;

import dailydescretedeck.set.views.carddesignes.DefaultCardDesign;

public class Player {
    private final String username;
    private final String password;      //to tam sie potem zhaszuje zamiast trzymac w stringu
    private int OOPoints;
    private CardDesign cardDesignInUse;

    private static int playersCount = 0;

    public Player(String username, String pwd) {
        this.username = username;
        this.password = pwd;
        this.cardDesignInUse = new DefaultCardDesign();
        playersCount++;
        System.out.printf( "User constructor: %s %s; count = %d\n",
                username, password, playersCount);
    }

    public Player(int points, String username, String pwd) {
        this.OOPoints = points;
        this.username = username;
        this.password = pwd;

        playersCount++;
        System.out.printf( "User constructor: %s %s; count = %d\n",
                username, password, playersCount);
    }

    //moze trzeba nadpisac finalize ale nie wiem

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

}
