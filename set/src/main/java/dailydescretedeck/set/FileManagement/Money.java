package dailydescretedeck.set.FileManagement;

public class Money {

    long money;

    public Money() {

        //money = SavingService.loadNumberFromFile("saves/money.txt");
        money = 1000;
    }

    public void addMoney(long money) {
        this.money = SavingService.loadNumberFromFile("saves/money.txt");
        this.money += money;
        SavingService.saveNumberToFile("saves/money.txt", this.money);
    }

    public long getMoney() {
        money = SavingService.loadNumberFromFile("saves/money.txt");
        return money;
    }

    public void spendMoney(long money) {
        this.money = SavingService.loadNumberFromFile("saves/money.txt");
        this.money -= money;
        SavingService.saveNumberToFile("saves/money.txt", this.money);
    }
    
}
