package dailydescretedeck.set.services;


public class TheBestTime {
    private static TheBestTime instance;
    private long time;

    private TheBestTime() {
        time = SavingService.loadNumberFromFile("saves/theBestTime.txt");
    }

    public static TheBestTime getInstance() {
        if (instance == null) {
            instance = new TheBestTime();
            instance.time = SavingService.loadNumberFromFile("saves/theBestTime.txt");
        }
        return instance;
    }

    public void newTime(long time) {
        this.time = time;
        SavingService.saveNumberToFile("saves/theBestTime.txt", time);
    }

    public long getTime() {
        return time;
    }
    public void resetTime() {
        this.time = 0;
        SavingService.saveNumberToFile("saves/theBestTime.txt", 0);
    }
}