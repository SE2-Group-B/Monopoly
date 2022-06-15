package se2.groupb.monopoly.network.messages;

public class RoundCounter {
    int roundcount;

    public RoundCounter() {
        this.roundcount = 0;
    }

    public int getRoundcount() {
        return roundcount;
    }

    public void setRoundcount(int roundcount) {
        this.roundcount = roundcount;
    }
}
