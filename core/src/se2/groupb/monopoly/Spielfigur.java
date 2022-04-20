package se2.groupb.monopoly;

import java.util.ArrayList;

public class Spielfigur {
    private int id;
    private String name;
    private int position;
    private int Kontostand;
    private ArrayList<Grundstueck> meineGrundstuecke;
    private int anzahlBahnhoefe;




    public Spielfigur(int id, String name, int kontostand, ArrayList<Grundstueck> meineGrundstuecke, int anzahlBahnhoefe) {
        this.id = id;
        this.name = name;
        this.Kontostand = kontostand;
        this.meineGrundstuecke = meineGrundstuecke;
        this.anzahlBahnhoefe=anzahlBahnhoefe;
        this.position=0;
    }

    public int getId() {return id; }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getKontostand() {return Kontostand;}

    public void setKontostand(int kontostand) {Kontostand = kontostand;}

    public ArrayList<Grundstueck> getMeineGrundstuecke() {return meineGrundstuecke;}

    public void setMeineGrundstuecke(ArrayList<Grundstueck> meineGrundstuecke) {this.meineGrundstuecke = meineGrundstuecke;}

    public void setId(int id) {
        this.id = id;
    }

    public int getAnzahlBahnhoefe() {
        return anzahlBahnhoefe;
    }

    public void setAnzahlBahnhoefe(int anzahlBahnhoefe) {
        this.anzahlBahnhoefe = anzahlBahnhoefe;
    }

    public void aendereKontostand(int betrag){
        setKontostand(getKontostand()+betrag);
    }
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    private void move(int augenzahl){
        setPosition((getPosition()+augenzahl) % 40);
    }

    public void zieheKarte(Kartenstapel ks){
        Karte k=ks.getNextCard();

        switch(k.getId()){
            case 1:
                move(-3);
                break;
            case 2:
                setPosition(0);
                break;
            case 3:
                move(-10);
                break;
            case 4:
                move(10);
                break;
            case 5:
                move(6);
                break;
            case 6:
            case 7:
                aendereKontostand(-100);
                break;
            case 8:
                aendereKontostand(-20);
                break;
            case 9:
                aendereKontostand(-40);
                break;
            case 10:
                aendereKontostand(-200);
                break;
            case 11:
                aendereKontostand(250);
                break;
            case 12:
            case 13:
            case 14:
                aendereKontostand(100);
                break;
            case 15:
                aendereKontostand(500);
                break;
            case 16:
                setPosition(10);
                break;
            case 17:
                setPosition(16);
                break;
            case 18:
                setPosition(24);
                break;
            case 19:
                setPosition(3);
                break;
            case 20:
                setPosition(10);
                break;
            case 21:
                aendereKontostand(220);
                break;
            case 22:
                aendereKontostand(170);
                break;
            case 23:
                aendereKontostand(550);
                break;
            case 24:
                aendereKontostand(350);
                break;
            case 25:
                aendereKontostand(200);
                break;
            case 26:
                setPosition(0);
                break;
            case 27:
                aendereKontostand(300);
                break;
            case 28:
                setPosition(37);
                break;
            case 29:
                aendereKontostand(510);
                break;
            case 30:
                aendereKontostand(350);
                break;
            case 31:
                aendereKontostand(222);
                break;
            case 32:
                aendereKontostand(-250);
                break;
            case 33:
                aendereKontostand(-100);
                break;
            case 34:
                aendereKontostand(-250);
                break;
            case 35:
                aendereKontostand(-200);
                break;
            case 36:
                aendereKontostand(-100);
                break;
            default:
                break;
        }
    }
}
