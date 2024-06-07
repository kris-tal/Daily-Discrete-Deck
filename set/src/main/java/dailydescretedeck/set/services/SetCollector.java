package dailydescretedeck.set.services;

public class SetCollector {
    private static SetCollector instance = null;
    private long sets;

    private SetCollector() {
        sets = SavingService.loadNumberFromFile("setsCollected.txt");
    }

    public static SetCollector getInstance() {
        if (instance == null) {
            instance = new SetCollector();
            instance.sets = SavingService.loadNumberFromFile("setsCollected.txt");
        }
        return instance;
    }

    public void addSets(long sets) {
        this.sets = SavingService.loadNumberFromFile("setsCollected.txt");
        this.sets += sets;
        SavingService.saveNumberToFile("setsCollected.txt", this.sets);
    }
    public void resetsSets() {
        this.sets = 0;
        SavingService.saveNumberToFile("setsCollected.txt", 0);
    }

    public long getSets() {
        return sets;
    }
}