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
        this.Kontostand=getKontostand()+betrag;
    }
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void move(int zahl){
        if(getPosition()+zahl>=40) {
            setPosition((getPosition()+zahl % 39)-1);
        } else {
            setPosition(getPosition()+zahl);
        }
    }
}
