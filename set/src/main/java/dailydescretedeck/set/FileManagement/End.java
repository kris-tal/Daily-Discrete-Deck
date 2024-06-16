package dailydescretedeck.set.FileManagement;


public class End {
    private static End instance;
    private long ends;

    private End() {
        ends = SavingService.loadNumberFromFile("saves/ends.txt");
    }

    public static End getInstance() {
        if (instance == null) {
            instance = new End();
            instance.ends = SavingService.loadNumberFromFile("saves/ends.txt");
        }
        return instance;
    }

    public void addEnds(long ends) {
        this.ends = SavingService.loadNumberFromFile("saves/ends.txt");
        this.ends += ends;
        SavingService.saveNumberToFile("saves/ends.txt", this.ends);
    }

    public long getEnds() {
        return ends;
    }
    public void resetEnds() {
        this.ends = 0;
        SavingService.saveNumberToFile("saves/ends.txt", 0);
    }
}