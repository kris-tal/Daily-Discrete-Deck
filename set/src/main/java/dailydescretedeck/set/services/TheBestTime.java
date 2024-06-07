package dailydescretedeck.set.services;


public class TheBestTime {
    private static TheBestTime instance;
    private long time;

    private TheBestTime() {
        time = SavingService.loadNumberFromFile("theBestTime.txt");
    }

    public static TheBestTime getInstance() {
        if (instance == null) {
            instance = new TheBestTime();
            instance.time = SavingService.loadNumberFromFile("theBestTime.txt");
        }
        return instance;
    }

    public void newTime(long time) {
        this.time = time;
        SavingService.saveNumberToFile("theBestTime.txt", time);
    }

    public long getTime() {
        return time;
    }
    public void resetTime() {
        this.time = 0;
        SavingService.saveNumberToFile("theBestTime.txt", 0);
    }
}