package se2.groupb.monopoly;

public class PenaltyField extends Property {
    private int strafe;


    public PenaltyField(String name, int strafe) {
        super(name);
        this.strafe = strafe;

    }

    public int getStrafe() {return strafe;}

    public void setStrafe(int strafe) {this.strafe = strafe;}
}
