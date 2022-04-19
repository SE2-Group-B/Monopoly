package se2.groupb.monopoly;

import java.util.ArrayList;

public class Spielfigur {
    private int id;
    private String name;
    private int Kontostand;
    private ArrayList<Grundstueck> meineGrundstuecke;


    public Spielfigur(int id, String name, int kontostand, ArrayList<Grundstueck> meineGrundstuecke) {
        this.id = id;
        this.name = name;
        Kontostand = kontostand;
        this.meineGrundstuecke = meineGrundstuecke;
    }

    public int getId() {return id; }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getKontostand() {return Kontostand;}

    public void setKontostand(int kontostand) {Kontostand = kontostand;}

    public ArrayList<Grundstueck> getMeineGrundstuecke() {return meineGrundstuecke;}

    public void setMeineGrundstuecke(ArrayList<Grundstueck> meineGrundstuecke) {this.meineGrundstuecke = meineGrundstuecke;}

    public void aendereKontostand(int betrag){
        this.Kontostand=getKontostand()+betrag;
    }
}
