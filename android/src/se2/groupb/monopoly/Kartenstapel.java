package se2.groupb.monopoly;

import java.util.ArrayList;
import java.util.Collections;

public class Kartenstapel {
    ArrayList<Karte> kartenStapel;

    public Kartenstapel(){
        kartenStapel=new ArrayList<>();
    }
    public void add(Karte k){
        kartenStapel.add(k);
    }
    public void shuffle(){
        Collections.shuffle(kartenStapel);
    }

    public static void main(String[] args) {
        Kartenstapel ereigniskarten = new Kartenstapel();
        Kartenstapel gemeinschaftskarten = new Kartenstapel();

        Karte e1 = new Karte(1, "Gehe 3 Felder zurück");
        ereigniskarten.add(e1);
        Karte e2 = new Karte(2, "Rücke vor bis auf Los!");
        ereigniskarten.add(e2);
        Karte e3 = new Karte(3, "Gehe 10 Felder zurück");
        ereigniskarten.add(e3);
        Karte e4 = new Karte(4, "Gehe 10 Felder weiter");
        ereigniskarten.add(e4);
        Karte e5 = new Karte(5, "Gehe 6 Felder weiter");
        ereigniskarten.add(e5);

        //5 zahle Karten
        Karte e6 = new Karte(6, "Du hast deine Netflix Gebühren nicht bezahlt. Zahle 100€");
        ereigniskarten.add(e6);
        Karte e7 = new Karte(7, "Du brauchst neue Uni Bücher! Zahle 100€");
        ereigniskarten.add(e7);
        Karte e8 = new Karte(8, "Es ist Schnittwoch. Du zahlst eine Runde Bier! Zahle 20€");
        ereigniskarten.add(e8);
        Karte e9 = new Karte(9, "Du brauchst ein Parkticket für die Uni. Zahle 40€");
        ereigniskarten.add(e9);
        Karte e10 = new Karte(10, "Du wirst unerwartet Elternteil. Babys sind teuer! Zahle 200€");
        ereigniskarten.add(e10);

        //5 bekomme Geld Karten
        Karte e11 = new Karte(11, "Du druckst dir dein eigenes Geld. Ziehe 250€ ein.");
        ereigniskarten.add(e11);
        Karte e12 = new Karte(12, "Du gibst regelmäßig Nachhilfe in Mathe. Ziehe 100€ ein");
        ereigniskarten.add(e12);
        Karte e13 = new Karte(13, "Du Glückspilz hast 100€ am Boden gefunden!");
        ereigniskarten.add(e13);
        Karte e14 = new Karte(14, "Du hilfst als Kellner aus. Ziehe 100€ ein. ");
        ereigniskarten.add(e14);
        Karte e15 = new Karte(15, "Du hast eine App programmiert und verkauft. Ziehe 500€ ein");
        ereigniskarten.add(e15);

        //5 sonstige Karten
        Karte e16 = new Karte(16, "Steuer Hinterziehung! Gehe direkt ins Gefängnis! Gehe nicht über Los!");
        ereigniskarten.add(e16);
        Karte e17 = new Karte(17, "Deine Häuser müssen renoviert werden. Zahle für jedes Haus 100€ und für jedes Hotel 150€.");
        ereigniskarten.add(e17);
        Karte e18 = new Karte(18, "Rücke vor bis zur xy Alle");
        ereigniskarten.add(e18);
        Karte e19 = new Karte(19, "Rücke vor bis zur xy Alle");
        ereigniskarten.add(e19);
        Karte e20 = new Karte(20, "Betrunken Auto gefahren? Ab ins Gefängnis!");
        ereigniskarten.add(e20);

        //positive Gemeinschaftskarten
        Karte g1=new Karte(21, "Es ist dein Geburtstag. Ziehe von jedem Spieler 100€ ein.");
        gemeinschaftskarten.add(g1);
        Karte g2=new Karte(22, "Du verkaufst dein altes Zeug auf Spock. Du erhältst 170€");
        gemeinschaftskarten.add(g2);
        Karte g3=new Karte(23, "Du erbst 400€");
        gemeinschaftskarten.add(g3);
        Karte g4=new Karte(24, "Du bekommst Geld zurück von deinem Steuerausgleich. Ziehe 350€ ein.");
        gemeinschaftskarten.add(g4);
        Karte g5=new Karte(25, "Du hast den zweiten Preis in einem Schönheitswettbewerb gewonnen. Ziehe 200€ ein.");
        gemeinschaftskarten.add(g5);
        Karte g6=new Karte(26, "Rücke vor bis auf Los!");
        gemeinschaftskarten.add(g6);
        Karte g7=new Karte(27, "Bank Irrtum zu deinem Gunsten. Ziehe 300€ ein.");
        gemeinschaftskarten.add(g7);
        Karte g8=new Karte(28, "Gehe vor bis zur xy Straße.");
        gemeinschaftskarten.add(g8);
        Karte g9=new Karte(29, "Du hattest ein gutes Jahr und bekommst eine Bonuszahlung von 500€");
        gemeinschaftskarten.add(g9);
        Karte g10=new Karte(30, "Du bekommst Geld zurück von deinem Steuerausgleich. Ziehe 350€ ein.");
        gemeinschaftskarten.add(g10);

        //negative
        Karte g11=new Karte(31, "Du hast den zweiten Preis in einem Schönheitswettbewerb gewonnen. Ziehe 200€ ein.");
        gemeinschaftskarten.add(g11);
        Karte g12=new Karte(32, "Steuernachzahlung. Bezahle 250€");
        gemeinschaftskarten.add(g12);
        Karte g13=new Karte(33, "Arzt Kosten: Zahle 100€");
        gemeinschaftskarten.add(g13);
        Karte g14=new Karte(34, "Dein Auto braucht ein Service. Zahle 250€");
        gemeinschaftskarten.add(g14);
        Karte g15=new Karte(35, "Du gehst Shoppen. Zahle 200€");
        gemeinschaftskarten.add(g15);
        Karte g16=new Karte(36, "Du hast ein gutes Herz und spendest an eine Hilfsorganisation 100€");
        gemeinschaftskarten.add(g16);

    }
}
