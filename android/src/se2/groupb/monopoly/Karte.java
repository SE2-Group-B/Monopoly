package se2.groupb.monopoly;

import java.util.ArrayList;

public class Karte {
    int id;
    String name;

    public Karte(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        ArrayList<Karte> ereigniskarten = new ArrayList<>();
        ArrayList<Karte> gemeinschaftskarten = new ArrayList<>();

        //5 tu das und das Karten
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

    }

}
