package se2.groupb.monopoly;

public class Zahlfeld extends Grundstueck{
    private int strafe;

    public Zahlfeld(String name, int strafe) {
        super(name);
        this.strafe = strafe;
    }

    public int getStrafe() {return strafe;}

    public void setStrafe(int strafe) {this.strafe = strafe;}

    //public void zahlen() {Spielfigur.kontostand - strafe;}
    //Im nächsten Sprint die private Methode umschreiben
}
