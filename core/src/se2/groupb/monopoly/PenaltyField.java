package se2.groupb.monopoly;

public class PenaltyField extends Property {
    private int penalty;


    public PenaltyField(String name, int strafe) {
        super(name);
        this.penalty = strafe;

    }

    public int getPenalty() {return penalty;}

    public void setPenalty(int strafe) {this.penalty = strafe;}
}
