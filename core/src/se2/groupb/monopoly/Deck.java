package se2.groupb.monopoly;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    ArrayList<Card> cards;
    int index;


    public Deck(){
        cards =new ArrayList<>();
        index=0;
    }

    public void add(Card k){
        cards.add(k);
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Card getNextCard(){
        int i=getIndex();
        if(i< cards.size()-1){
            Card k= cards.get(i);
            setIndex(i+1);
            return k;
        }else if(i== cards.size()-1){ //letzte Karte im Stapel -> shuffle & index 0 setzten
            Card k= cards.get(i);
            shuffle();
            setIndex(0);
            return k;
        }
        else{//index out of bounce
            setIndex(0);
            i=0;
            shuffle();
            Card k= cards.get(getIndex());
            setIndex(i+1);
            return k;
        }

    }

    public void initializeEreigniskartenStapel(){
        this.cards.add(new Card(1, "Gehe 3 Felder zurück"));
        this.cards.add(new Card(2, "Rücke vor bis auf Los!"));
        this.cards.add(new Card(3, "Gehe 10 Felder zurück"));
        this.cards.add(new Card(4, "Gehe 10 Felder weiter"));
        this.cards.add(new Card(5, "Gehe 6 Felder weiter"));
        this.cards.add(new Card(6, "Du hast deine Netflix Gebühren nicht bezahlt. Zahle 100€"));
        this.cards.add(new Card(7, "Du brauchst neue Uni Bücher! Zahle 100€"));
        this.cards.add(new Card(8, "Es ist Schnittwoch. Du zahlst eine Runde Bier! Zahle 20€"));
        this.cards.add(new Card(9, "Du brauchst ein Parkticket für die Uni. Zahle 40€"));
        this.cards.add(new Card(10, "Du wirst unerwartet Elternteil. Babys sind teuer! Zahle 200€"));
        this.cards.add(new Card(11, "Du druckst dir dein eigenes Geld. Ziehe 250€ ein."));
        this.cards.add(new Card(12, "Du gibst regelmäßig Nachhilfe in Mathe. Ziehe 100€ ein"));
        this.cards.add(new Card(13, "Du Glückspilz hast 100€ am Boden gefunden!"));
        this.cards.add(new Card(14, "Du hilfst als Kellner aus. Ziehe 100€ ein. "));
        this.cards.add(new Card(15, "Du hast eine App programmiert und verkauft. Ziehe 500€ ein"));
        this.cards.add(new Card(16, "Steuer Hinterziehung! Gehe direkt ins Gefängnis! Gehe nicht über Los!"));
        this.cards.add(new Card(17, "Rücke vor bis zur Münchnerstraße"));
        this.cards.add(new Card(18, "Rücke vor bis zum Opernplatz"));
        this.cards.add(new Card(19, "Rücke vor bis zur Turmstraße"));
        this.cards.add(new Card(20, "Betrunken Auto gefahren? Ab ins Gefängnis!"));
    }

    public void initializeGemeinschaftskartenStapel(){
        this.cards.add(new Card(21, "Es ist dein Geburtstag. Ziehe 220€ ein."));
        this.cards.add(new Card(22, "Du verkaufst dein altes Zeug auf Spock. Du erhältst 170€"));
        this.cards.add(new Card(23, "Du erbst 550€"));
        this.cards.add(new Card(24, "Du bekommst Geld zurück von deinem Steuerausgleich. Ziehe 350€ ein."));
        this.cards.add(new Card(25, "Du hast den zweiten Preis in einem Schönheitswettbewerb gewonnen. Ziehe 200€ ein."));
        this.cards.add(new Card(26, "Rücke vor bis auf Los!"));
        this.cards.add(new Card(27, "Bank Irrtum zu deinem Gunsten. Ziehe 300€ ein."));
        this.cards.add(new Card(28, "Gehe vor bis zur Parkstraße."));
        this.cards.add(new Card(29, "Du hattest ein gutes Jahr und bekommst eine Bonuszahlung von 510€"));
        this.cards.add(new Card(30, "Du bekommst Geld zurück von deinem Steuerausgleich. Ziehe 350€ ein."));
        this.cards.add(new Card(31, "Du hast den zweiten Preis in einem Schönheitswettbewerb gewonnen. Ziehe 222€ ein."));
        this.cards.add(new Card(32, "Steuernachzahlung. Bezahle 250€"));
        this.cards.add(new Card(33, "Arzt Kosten: Zahle 100€"));
        this.cards.add(new Card(34, "Dein Auto braucht ein Service. Zahle 250€"));
        this.cards.add(new Card(35, "Du gehst Shoppen. Zahle 200€"));
        this.cards.add(new Card(36, "Du hast ein gutes Herz und spendest an eine Hilfsorganisation 100€"));
    }



}
