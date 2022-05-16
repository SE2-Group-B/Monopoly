package se2.groupb.monopoly;

import java.util.ArrayList;
import java.util.Collections;

public class Kartenstapel {
    ArrayList<Karte> kartenStapel;
    int index;


    public Kartenstapel(){
        kartenStapel=new ArrayList<>();
        index=0;
    }

    public void add(Karte k){
        kartenStapel.add(k);
    }

    public void shuffle(){
        Collections.shuffle(kartenStapel);
    }

    public ArrayList<Karte> getKartenStapel() {
        return kartenStapel;
    }

    public void setKartenStapel(ArrayList<Karte> kartenStapel) {
        this.kartenStapel = kartenStapel;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Karte getNextCard(){
        int i=getIndex();
        if(i<kartenStapel.size()-1){
            Karte k=kartenStapel.get(i);
            setIndex(i+1);
            return k;
        }else if(i==kartenStapel.size()-1){ //letzte Karte im Stapel -> shuffle & index 0 setzten
            Karte k=kartenStapel.get(i);
            shuffle();
            setIndex(0);
            return k;
        }
        else{//index out of bounce
            setIndex(0);
            i=0;
            shuffle();
            Karte k=kartenStapel.get(getIndex());
            setIndex(i+1);
            return k;
        }

    }

    public void initializeEreigniskartenStapel(){
        this.kartenStapel.add(new Karte(1, "Gehe 3 Felder zurück"));
        this.kartenStapel.add(new Karte(2, "Rücke vor bis auf Los!"));
        this.kartenStapel.add(new Karte(3, "Gehe 10 Felder zurück"));
        this.kartenStapel.add(new Karte(4, "Gehe 10 Felder weiter"));
        this.kartenStapel.add(new Karte(5, "Gehe 6 Felder weiter"));
        this.kartenStapel.add(new Karte(6, "Du hast deine Netflix Gebühren nicht bezahlt. Zahle 100€"));
        this.kartenStapel.add(new Karte(7, "Du brauchst neue Uni Bücher! Zahle 100€"));
        this.kartenStapel.add(new Karte(8, "Es ist Schnittwoch. Du zahlst eine Runde Bier! Zahle 20€"));
        this.kartenStapel.add(new Karte(9, "Du brauchst ein Parkticket für die Uni. Zahle 40€"));
        this.kartenStapel.add(new Karte(10, "Du wirst unerwartet Elternteil. Babys sind teuer! Zahle 200€"));
        this.kartenStapel.add(new Karte(11, "Du druckst dir dein eigenes Geld. Ziehe 250€ ein."));
        this.kartenStapel.add(new Karte(12, "Du gibst regelmäßig Nachhilfe in Mathe. Ziehe 100€ ein"));
        this.kartenStapel.add(new Karte(13, "Du Glückspilz hast 100€ am Boden gefunden!"));
        this.kartenStapel.add(new Karte(14, "Du hilfst als Kellner aus. Ziehe 100€ ein. "));
        this.kartenStapel.add(new Karte(15, "Du hast eine App programmiert und verkauft. Ziehe 500€ ein"));
        this.kartenStapel.add(new Karte(16, "Steuer Hinterziehung! Gehe direkt ins Gefängnis! Gehe nicht über Los!"));
        this.kartenStapel.add(new Karte(17, "Rücke vor bis zur Münchnerstraße"));
        this.kartenStapel.add(new Karte(18, "Rücke vor bis zum Opernplatz"));
        this.kartenStapel.add(new Karte(19, "Rücke vor bis zur Turmstraße"));
        this.kartenStapel.add(new Karte(20, "Betrunken Auto gefahren? Ab ins Gefängnis!"));
    }

    public void initializeGemeinschaftskartenStapel(){
        this.kartenStapel.add(new Karte(21, "Es ist dein Geburtstag. Ziehe 220€ ein."));
        this.kartenStapel.add(new Karte(22, "Du verkaufst dein altes Zeug auf Spock. Du erhältst 170€"));
        this.kartenStapel.add(new Karte(23, "Du erbst 550€"));
        this.kartenStapel.add(new Karte(24, "Du bekommst Geld zurück von deinem Steuerausgleich. Ziehe 350€ ein."));
        this.kartenStapel.add(new Karte(25, "Du hast den zweiten Preis in einem Schönheitswettbewerb gewonnen. Ziehe 200€ ein."));
        this.kartenStapel.add(new Karte(26, "Rücke vor bis auf Los!"));
        this.kartenStapel.add(new Karte(27, "Bank Irrtum zu deinem Gunsten. Ziehe 300€ ein."));
        this.kartenStapel.add(new Karte(28, "Gehe vor bis zur Parkstraße."));
        this.kartenStapel.add(new Karte(29, "Du hattest ein gutes Jahr und bekommst eine Bonuszahlung von 510€"));
        this.kartenStapel.add(new Karte(30, "Du bekommst Geld zurück von deinem Steuerausgleich. Ziehe 350€ ein."));
        this.kartenStapel.add(new Karte(31, "Du hast den zweiten Preis in einem Schönheitswettbewerb gewonnen. Ziehe 222€ ein."));
        this.kartenStapel.add(new Karte(32, "Steuernachzahlung. Bezahle 250€"));
        this.kartenStapel.add(new Karte(33, "Arzt Kosten: Zahle 100€"));
        this.kartenStapel.add(new Karte(34, "Dein Auto braucht ein Service. Zahle 250€"));
        this.kartenStapel.add(new Karte(35, "Du gehst Shoppen. Zahle 200€"));
        this.kartenStapel.add(new Karte(36, "Du hast ein gutes Herz und spendest an eine Hilfsorganisation 100€"));
    }



}
