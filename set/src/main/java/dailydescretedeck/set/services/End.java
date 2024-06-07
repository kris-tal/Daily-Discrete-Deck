package dailydescretedeck.set.services;


public class End {
    private static End instance;
    private long ends;

    private End() {
        ends = SavingService.loadNumberFromFile("ends.txt");
    }

    public static End getInstance() {
        if (instance == null) {
            instance = new End();
            instance.ends = SavingService.loadNumberFromFile("ends.txt");
        }
        return instance;
    }

    public void addEnds(long ends) {
        this.ends = SavingService.loadNumberFromFile("ends.txt");
        this.ends += ends;
        SavingService.saveNumberToFile("ends.txt", ends);
    }

    public long getEnds() {
        return ends;
    }
    public void resetEnds() {
        this.ends = 0;
        SavingService.saveNumberToFile("ends.txt", 0);
    }
}