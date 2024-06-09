package dailydescretedeck.set.services;


public class PlayerName {
    private String name;
    public PlayerName() {
        name = SavingService.loadNameFromFile("saves/name.txt");
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
