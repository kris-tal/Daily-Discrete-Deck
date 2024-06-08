package dailydescretedeck.set.services;

public class Money {

    long money;

    public Money() {
        money = SavingService.loadNumberFromFile("money.txt");
    }

    void addMoney(long money) {
        this.money = SavingService.loadNumberFromFile("money.txt");
        this.money += money;
        SavingService.saveNumberToFile("money.txt", this.money);
    }

    public long getMoney() {
        money = SavingService.loadNumberFromFile("money.txt");
        return money;
    }

    public void spendMoney(long money) {
        this.money = SavingService.loadNumberFromFile("money.txt");
        this.money -= money;
        SavingService.saveNumberToFile("money.txt", this.money);
    }
    
}
