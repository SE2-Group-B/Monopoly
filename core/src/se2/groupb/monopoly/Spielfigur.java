package se2.groupb.monopoly;

import com.badlogic.gdx.graphics.Texture;

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
        this.anzahlBahnhoefe = anzahlBahnhoefe;
        this.position = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKontostand() {
        return Kontostand;
    }

    public void setKontostand(int kontostand) {
        Kontostand = kontostand;
    }

    public ArrayList<Grundstueck> getMeineGrundstuecke() {
        return meineGrundstuecke;
    }

    public void setMeineGrundstuecke(ArrayList<Grundstueck> meineGrundstuecke) {
        this.meineGrundstuecke = meineGrundstuecke;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnzahlBahnhoefe() {
        return anzahlBahnhoefe;
    }

    public void setAnzahlBahnhoefe(int anzahlBahnhoefe) {
        this.anzahlBahnhoefe = anzahlBahnhoefe;
    }

    public void increaseMoney(int betrag) {
        setKontostand(getKontostand() + betrag);
    }

    public void decreaseMoney(int betrag){setKontostand(getKontostand() - betrag);}

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void move(int augenzahl) {
        setPosition((getPosition() + augenzahl) % 40);
        if((getPosition() + augenzahl) > 39){
            roundmoney();
        }
    }

    public void roundmoney() {
        setKontostand((getKontostand() + 200));
    }


    public Texture zieheKarte(Kartenstapel ks) {
        Karte k = ks.getNextCard();
        Texture kartenbild = null;

        switch (k.getId()) {
            case 1:
                kartenbild = new Texture("images/KartenImages/Karte1.png");
                move(-3);
                break;
            case 2:
                kartenbild = new Texture("images/KartenImages/Karte2.png");
                setPosition(0);
                break;
            case 3:
                kartenbild = new Texture("images/KartenImages/Karte3.png");
                move(-10);
                break;
            case 4:
                kartenbild = new Texture("images/KartenImages/Karte4.png");
                move(10);
                break;
            case 5:
                kartenbild = new Texture("images/KartenImages/Karte5.png");
                move(6);
                break;
            case 6:
                kartenbild = new Texture("images/KartenImages/Karte6.png");
                decreaseMoney(100);
                break;
            case 7:
                kartenbild = new Texture("images/KartenImages/Karte7.png");
                decreaseMoney(100);
                break;
            case 8:
                kartenbild = new Texture("images/KartenImages/Karte8.png");
                decreaseMoney(20);
                break;
            case 9:
                kartenbild = new Texture("images/KartenImages/Karte9.png");
                decreaseMoney(40);
                break;
            case 10:
                kartenbild = new Texture("images/KartenImages/Karte10.png");
                decreaseMoney(200);
                break;
            case 11:
                kartenbild = new Texture("images/KartenImages/Karte11.png");
                increaseMoney(250);
                break;
            case 12:
                kartenbild = new Texture("images/KartenImages/Karte12.png");
                increaseMoney(100);
                break;
            case 13:
                kartenbild = new Texture("images/KartenImages/Karte13.png");
                increaseMoney(100);
                break;
            case 14:
                kartenbild = new Texture("images/KartenImages/Karte14.png");
                increaseMoney(100);
                break;
            case 15:
                kartenbild = new Texture("images/KartenImages/Karte15.png");
                increaseMoney(500);
                break;
            case 16:
                kartenbild = new Texture("images/KartenImages/Karte16.png");
                setPosition(10);
                break;
            case 17:
                kartenbild = new Texture("images/KartenImages/Karte17.png");
                setPosition(16);
                break;
            case 18:
                kartenbild = new Texture("images/KartenImages/Karte18.png");
                setPosition(24);
                break;
            case 19:
                kartenbild = new Texture("images/KartenImages/Karte19.png");
                setPosition(3);
                break;
            case 20:
                kartenbild = new Texture("images/KartenImages/Karte20.png");
                setPosition(10);
                break;
            case 21:
                kartenbild = new Texture("images/KartenImages/Karte21.png");
                increaseMoney(220);
                break;
            case 22:
                kartenbild = new Texture("images/KartenImages/Karte22.png");
                increaseMoney(170);
                break;
            case 23:
                kartenbild = new Texture("images/KartenImages/Karte23.png");
                increaseMoney(550);
                break;
            case 24:
                kartenbild = new Texture("images/KartenImages/Karte24.png");
                increaseMoney(350);
                break;
            case 25:
                kartenbild = new Texture("images/KartenImages/Karte25.png");
                increaseMoney(200);
                break;
            case 26:
                kartenbild = new Texture("images/KartenImages/Karte26.png");
                setPosition(0);
                break;
            case 27:
                kartenbild = new Texture("images/KartenImages/Karte27.png");
                increaseMoney(300);
                break;
            case 28:
                kartenbild = new Texture("images/KartenImages/Karte28.png");
                setPosition(37);
                break;
            case 29:
                kartenbild = new Texture("images/KartenImages/Karte29.png");
                increaseMoney(510);
                break;
            case 30:
                kartenbild = new Texture("images/KartenImages/Karte30.png");
                increaseMoney(350);
                break;
            case 31:
                kartenbild = new Texture("images/KartenImages/Karte31.png");
                increaseMoney(222);
                break;
            case 32:
                kartenbild = new Texture("images/KartenImages/Karte32.png");
                decreaseMoney(250);
                break;
            case 33:
                kartenbild = new Texture("images/KartenImages/Karte33.png");
                decreaseMoney(100);
                break;
            case 34:
                kartenbild = new Texture("images/KartenImages/Karte34.png");
                decreaseMoney(250);
                break;
            case 35:
                kartenbild = new Texture("images/KartenImages/Karte35.png");
                decreaseMoney(200);
                break;
            case 36:
                kartenbild = new Texture("images/KartenImages/Karte36.png");
                decreaseMoney(100);
                break;
            default:
                break;

        }
        return kartenbild;
    }
}
