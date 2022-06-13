package se2.groupb.monopoly;

public class Pot {
    private int amount;

    public Pot() {
        this.amount = 0;
    }

    public String donateToPot(Player player, int donation){
        player.changeMoney(-donation);
        this.amount += donation;
        return player.getName() + " donates " + donation + "€ to the Pot.";
    }

    public String winPot(Player player){
        player.changeMoney(this.amount);
        int oldamount = this.amount;
        this.amount = 0;
        return player.getName() + " won the Pot with " + oldamount + "€.";
    }

    public void addToPot(int amount){
        this.amount += amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
