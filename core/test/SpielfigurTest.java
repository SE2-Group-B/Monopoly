import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import se2.groupb.monopoly.Bahnhof;
import se2.groupb.monopoly.Grundstueck;
import se2.groupb.monopoly.Karte;
import se2.groupb.monopoly.Kartenstapel;
import se2.groupb.monopoly.Spielfigur;
import se2.groupb.monopoly.Strasse;

public class SpielfigurTest {
    Spielfigur rot;
    ArrayList<Grundstueck> meineGrundstuecke;
    Strasse hauptstraße;
    Bahnhof nordbahnhof;
    Kartenstapel ereigniskarten;
    Karte e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11,e12,e13,e14, e15,e16,e17,e18,e19,e20;
    Karte g1, g2, g3,g4,g5,g6,g7,g8,g9,g10,g11,g12,g13,g14,g15,g16;
    Kartenstapel gemeinschaftskarten;

    /*
    @Before
    public void init(){
        meineGrundstuecke=new ArrayList<>();
        hauptstraße=new Strasse("Hauptstraße", 220, false, 0,0,20, 50);
        meineGrundstuecke.add(hauptstraße);
        nordbahnhof=new Bahnhof("Nordbahnhof", false, 50);
        meineGrundstuecke.add(nordbahnhof);
        rot=new Spielfigur(1, "Rot", 2000, meineGrundstuecke,1 , Color.RED);

        ereigniskarten = new Kartenstapel();
         e1 = new Karte(1, "Gehe 3 Felder zurück");
        ereigniskarten.add(e1);
         e2 = new Karte(2, "Rücke vor bis auf Los!");
        ereigniskarten.add(e2);
         e3 = new Karte(3, "Gehe 10 Felder zurück");
        ereigniskarten.add(e3);
         e4 = new Karte(4, "Gehe 10 Felder weiter");
        ereigniskarten.add(e4);
         e5 = new Karte(5, "Gehe 6 Felder weiter");
        ereigniskarten.add(e5);
         e6 = new Karte(6, "Du hast deine Netflix Gebühren nicht bezahlt. Zahle 100€");
        ereigniskarten.add(e6);
         e7 = new Karte(7, "Du brauchst neue Uni Bücher! Zahle 100€");
        ereigniskarten.add(e7);
         e8 = new Karte(8, "Es ist Schnittwoch. Du zahlst eine Runde Bier! Zahle 20€");
        ereigniskarten.add(e8);
         e9 = new Karte(9, "Du brauchst ein Parkticket für die Uni. Zahle 40€");
        ereigniskarten.add(e9);
         e10 = new Karte(10, "Du wirst unerwartet Elternteil. Babys sind teuer! Zahle 200€");
        ereigniskarten.add(e10);
         e11 = new Karte(11, "Du druckst dir dein eigenes Geld. Ziehe 250€ ein.");
        ereigniskarten.add(e11);
         e12 = new Karte(12, "Du gibst regelmäßig Nachhilfe in Mathe. Ziehe 100€ ein");
        ereigniskarten.add(e12);
         e13 = new Karte(13, "Du Glückspilz hast 100€ am Boden gefunden!");
        ereigniskarten.add(e13);
         e14 = new Karte(14, "Du hilfst als Kellner aus. Ziehe 100€ ein. ");
        ereigniskarten.add(e14);
         e15 = new Karte(15, "Du hast eine App programmiert und verkauft. Ziehe 500€ ein");
        ereigniskarten.add(e15);
         e16 = new Karte(16, "Steuer Hinterziehung! Gehe direkt ins Gefängnis! Gehe nicht über Los!");
        ereigniskarten.add(e16);
         e17 = new Karte(17, "Rücke vor bis zur Münchnerstraße"); //index 16
        ereigniskarten.add(e17);
         e18 = new Karte(18, "Rücke vor bis zum Opernplatz"); //24
        ereigniskarten.add(e18);
         e19 = new Karte(19, "Rücke vor bis zur Turmstraße"); //3
        ereigniskarten.add(e19);
         e20 = new Karte(20, "Betrunken Auto gefahren? Ab ins Gefängnis!");
        ereigniskarten.add(e20);

        gemeinschaftskarten = new Kartenstapel();
         g1=new Karte(21, "Es ist dein Geburtstag. Ziehe 220€ ein.");
        gemeinschaftskarten.add(g1);
         g2=new Karte(22, "Du verkaufst dein altes Zeug auf Spock. Du erhältst 170€");
        gemeinschaftskarten.add(g2);
         g3=new Karte(23, "Du erbst 550€");
        gemeinschaftskarten.add(g3);
         g4=new Karte(24, "Du bekommst Geld zurück von deinem Steuerausgleich. Ziehe 350€ ein.");
        gemeinschaftskarten.add(g4);
         g5=new Karte(25, "Du hast den zweiten Preis in einem Schönheitswettbewerb gewonnen. Ziehe 200€ ein.");
        gemeinschaftskarten.add(g5);
         g6=new Karte(26, "Rücke vor bis auf Los!");
        gemeinschaftskarten.add(g6);
         g7=new Karte(27, "Bank Irrtum zu deinem Gunsten. Ziehe 300€ ein.");
        gemeinschaftskarten.add(g7);
         g8=new Karte(28, "Gehe vor bis zur Parkstraße."); //index 37
        gemeinschaftskarten.add(g8);
         g9=new Karte(29, "Du hattest ein gutes Jahr und bekommst eine Bonuszahlung von 510€");
        gemeinschaftskarten.add(g9);
         g10=new Karte(30, "Du bekommst Geld zurück von deinem Steuerausgleich. Ziehe 350€ ein.");
        gemeinschaftskarten.add(g10);
         g11=new Karte(31, "Du hast den zweiten Preis in einem Schönheitswettbewerb gewonnen. Ziehe 222€ ein.");
        gemeinschaftskarten.add(g11);
         g12=new Karte(32, "Steuernachzahlung. Bezahle 250€");
        gemeinschaftskarten.add(g12);
         g13=new Karte(33, "Arzt Kosten: Zahle 100€");
        gemeinschaftskarten.add(g13);
         g14=new Karte(34, "Dein Auto braucht ein Service. Zahle 250€");
        gemeinschaftskarten.add(g14);
         g15=new Karte(35, "Du gehst Shoppen. Zahle 200€");
        gemeinschaftskarten.add(g15);
         g16=new Karte(36, "Du hast ein gutes Herz und spendest an eine Hilfsorganisation 100€");
        gemeinschaftskarten.add(g16);
    }

    @After
    public void teardown(){
        rot=null;
        meineGrundstuecke=null;
        hauptstraße=null;
        nordbahnhof=null;
    }

    @Test
    public void testSpielfigur(){
        Assert.assertNotNull(rot);
    }

    @Test
    public void testGetId(){
        Assert.assertEquals(rot.getId(), 1);
    }

    @Test
    public void testSetId(){
        rot.setId(10);
        Assert.assertEquals(rot.getId(), 10);
    }
    @Test
    public void testGetName(){
        Assert.assertEquals(rot.getName(), "Rot");
    }
    @Test
    public void testSetName(){
        rot.setName("Vivienne");
        Assert.assertEquals(rot.getName(), "Vivienne");
    }
    @Test
    public void testSetKontostand(){
        rot.setKontostand(1200);
        Assert.assertEquals(rot.getKontostand(), 1200);
    }
    @Test
    public void testGetKontostand(){
        Assert.assertEquals(rot.getKontostand(), 2000);
    }
    @Test
    public void testGetMeineGrundstuecke(){
        Assert.assertEquals(rot.getMeineGrundstuecke(),meineGrundstuecke);
    }
    @Test
    public void testSetMeineGrundstuecke(){
        ArrayList<Grundstueck> meineGrundstuecke2=new ArrayList<>();
        meineGrundstuecke2.add(nordbahnhof);
        meineGrundstuecke2.add(hauptstraße);
        rot.setMeineGrundstuecke(meineGrundstuecke2);
        Assert.assertEquals(rot.getMeineGrundstuecke(),meineGrundstuecke2);
    }
    @Test
    public void testGetAnzahlBahnhoefe(){
        Assert.assertEquals(rot.getAnzahlBahnhoefe(),1);
    }

    @Test
    public void testSetAnzahlBahnhoefe(){
        rot.setAnzahlBahnhoefe(4);
        Assert.assertEquals(rot.getAnzahlBahnhoefe(),4);
    }


    @Test
    public void testAendereKontostandPlus(){
        rot.changeMoney(50);
        Assert.assertEquals(rot.getKontostand(),2050);
    }

    @Test
    public void testAendereKontostandMinus(){
        rot.changeMoney(-2000);
        Assert.assertEquals(rot.getKontostand(),0);
    }

    @Test
    public void testAendereKontostandNull(){
        rot.changeMoney(0);
        Assert.assertEquals(rot.getKontostand(),2000);
    }

    @Test
    public void testGetPosition(){
        Assert.assertEquals(rot.getPosition(),0);
    }

    @Test
    public void testSetPosition(){
        rot.setPosition(12);
        Assert.assertEquals(rot.getPosition(),12);
    }

    @Test
    public void testMove(){
        rot.move(12);
        Assert.assertEquals(rot.getPosition(),12);
    }

    @Test
    public void testMoveUeberLos(){
        rot.setPosition(38);
        rot.move(5);
        Assert.assertEquals(rot.getPosition(),3);
    }
    /*
    @Test
    public void testZieheKarteCase1(){
        Texture kartenbild=new Texture("images/KartenImages/Karte1.png");
        rot.setPosition(4);
        Assert.assertEquals(rot.zieheKarte(ereigniskarten), kartenbild);
        Assert.assertEquals(rot.getPosition(),1);
    }

    @Test
    public void testZieheKarteCase2(){
        Texture kartenbild=new Texture("images/KartenImages/Karte2.png");
        rot.setPosition(4);
        ereigniskarten.setIndex(1);
        Assert.assertEquals(rot.zieheKarte(ereigniskarten), kartenbild);
        Assert.assertEquals(rot.getPosition(),0);
    }
    @Test
    public void testZieheKarteCase3(){
        Texture kartenbild=new Texture("images/KartenImages/Karte3.png");
        rot.setPosition(10);
        ereigniskarten.setIndex(2);
        Assert.assertEquals(rot.zieheKarte(ereigniskarten), kartenbild);
        Assert.assertEquals(rot.getPosition(),0);
    }
    @Test
    public void testZieheKarteCase4(){
        Texture kartenbild=new Texture("images/KartenImages/Karte4.png");
        rot.setPosition(10);
        ereigniskarten.setIndex(3);
        Assert.assertEquals(rot.zieheKarte(ereigniskarten), kartenbild);
        Assert.assertEquals(rot.getPosition(),20);
    }
    @Test
    public void testZieheKarteCase5(){
        Texture kartenbild=new Texture("images/KartenImages/Karte5.png");
        rot.setPosition(4);
        ereigniskarten.setIndex(4);
        Assert.assertEquals(rot.zieheKarte(ereigniskarten), kartenbild);
        Assert.assertEquals(rot.getPosition(),10);
    }
    @Test
    public void testZieheKarteCase6(){
        Texture kartenbild=new Texture("images/KartenImages/Karte6.png");
        rot.setKontostand(2000);
        ereigniskarten.setIndex(5);
        Assert.assertEquals(rot.zieheKarte(ereigniskarten), kartenbild);
        Assert.assertEquals(rot.getKontostand(),1900);
    }
    @Test
    public void testZieheKarteCase7(){
        Texture kartenbild=new Texture("images/KartenImages/Karte7.png");
        rot.setKontostand(2000);
        ereigniskarten.setIndex(6);
        Assert.assertEquals(rot.zieheKarte(ereigniskarten), kartenbild);
        Assert.assertEquals(rot.getKontostand(),1900);
    }
    @Test
    public void testZieheKarteCase8(){
        Texture kartenbild=new Texture("images/KartenImages/Karte8.png");
        rot.setKontostand(100);
        ereigniskarten.setIndex(7);
        Assert.assertEquals(rot.zieheKarte(ereigniskarten), kartenbild);
        Assert.assertEquals(rot.getKontostand(),80);
    }
    @Test
    public void testZieheKarteCase9(){
        Texture kartenbild=new Texture("images/KartenImages/Karte9.png");
        rot.setKontostand(80);
        ereigniskarten.setIndex(8);
        Assert.assertEquals(rot.zieheKarte(ereigniskarten), kartenbild);
        Assert.assertEquals(rot.getKontostand(),40);
    }
    @Test
    public void testZieheKarteCase10(){
        Texture kartenbild=new Texture("images/KartenImages/Karte10.png");
        rot.setKontostand(2000);
        ereigniskarten.setIndex(9);
        Assert.assertEquals(rot.zieheKarte(ereigniskarten), kartenbild);
        Assert.assertEquals(rot.getKontostand(),1800);
    }
    @Test
    public void testZieheKarteCase11(){
        Texture kartenbild=new Texture("images/KartenImages/Karte11.png");
        rot.setKontostand(2000);
        ereigniskarten.setIndex(10);
        Assert.assertEquals(rot.zieheKarte(ereigniskarten), kartenbild);
        Assert.assertEquals(rot.getKontostand(),2250);
    }
    @Test
    public void testZieheKarteCase12(){
        Texture kartenbild=new Texture("images/KartenImages/Karte12.png");
        rot.setKontostand(2000);
        ereigniskarten.setIndex(11);
        Assert.assertEquals(rot.zieheKarte(ereigniskarten), kartenbild);
        Assert.assertEquals(rot.getKontostand(),2100);
    }
    @Test
    public void testZieheKarteCase13(){
        Texture kartenbild=new Texture("images/KartenImages/Karte13.png");
        rot.setKontostand(2000);
        ereigniskarten.setIndex(12);
        Assert.assertEquals(rot.zieheKarte(ereigniskarten), kartenbild);
        Assert.assertEquals(rot.getKontostand(),2100);
    }
    @Test
    public void testZieheKarteCase14(){
        Texture kartenbild=new Texture("images/KartenImages/Karte14.png");
        rot.setKontostand(2000);
        ereigniskarten.setIndex(13);
        Assert.assertEquals(rot.zieheKarte(ereigniskarten), kartenbild);
        Assert.assertEquals(rot.getKontostand(),2100);
    }
    @Test
    public void testZieheKarteCase15(){
        Texture kartenbild=new Texture("images/KartenImages/Karte15.png");
        rot.setKontostand(2000);
        ereigniskarten.setIndex(14);
        Assert.assertEquals(rot.zieheKarte(ereigniskarten), kartenbild);
        Assert.assertEquals(rot.getKontostand(),2500);
    }
    @Test
    public void testZieheKarteCase16(){
        Texture kartenbild=new Texture("images/KartenImages/Karte16.png");
        ereigniskarten.setIndex(15);
        Assert.assertEquals(rot.zieheKarte(ereigniskarten), kartenbild);
        Assert.assertEquals(rot.getPosition(),10);
    }
    @Test
    public void testZieheKarteCase17(){
        Texture kartenbild=new Texture("images/KartenImages/Karte17.png");
        ereigniskarten.setIndex(16);
        Assert.assertEquals(rot.zieheKarte(ereigniskarten), kartenbild);
        Assert.assertEquals(rot.getPosition(),16);
    }
    @Test
    public void testZieheKarteCase18(){
        Texture kartenbild=new Texture("images/KartenImages/Karte18.png");
        ereigniskarten.setIndex(17);
        Assert.assertEquals(rot.zieheKarte(ereigniskarten), kartenbild);
        Assert.assertEquals(rot.getPosition(),24);
    }
    @Test
    public void testZieheKarteCase19(){
        Texture kartenbild=new Texture("images/KartenImages/Karte19.png");
        ereigniskarten.setIndex(18);
        Assert.assertEquals(rot.zieheKarte(ereigniskarten), kartenbild);
        Assert.assertEquals(rot.getPosition(),3);
    }
    @Test
    public void testZieheKarteCase20(){
        Texture kartenbild=new Texture("images/KartenImages/Karte20.png");
        ereigniskarten.setIndex(19);
        Assert.assertEquals(rot.zieheKarte(ereigniskarten), kartenbild);
        Assert.assertEquals(rot.getPosition(),10);
    }
    @Test
    public void testZieheKarteCase21(){
        Texture kartenbild=new Texture("images/KartenImages/Karte21.png");
        gemeinschaftskarten.setIndex(0);
        rot.setKontostand(1000);
        Assert.assertEquals(rot.zieheKarte(gemeinschaftskarten), kartenbild);
        Assert.assertEquals(rot.getKontostand(),1220);
    }
    @Test
    public void testZieheKarteCase22(){
        Texture kartenbild=new Texture("images/KartenImages/Karte22.png");
        gemeinschaftskarten.setIndex(1);
        rot.setKontostand(1000);
        Assert.assertEquals(rot.zieheKarte(gemeinschaftskarten), kartenbild);
        Assert.assertEquals(rot.getKontostand(),1170);
    }
    @Test
    public void testZieheKarteCase23(){
        Texture kartenbild=new Texture("images/KartenImages/Karte23.png");
        gemeinschaftskarten.setIndex(2);
        rot.setKontostand(1000);
        Assert.assertEquals(rot.zieheKarte(gemeinschaftskarten), kartenbild);
        Assert.assertEquals(rot.getKontostand(),1550);
    }
    @Test
    public void testZieheKarteCase24(){
        Texture kartenbild=new Texture("images/KartenImages/Karte24.png");
        gemeinschaftskarten.setIndex(3);
        rot.setKontostand(1000);
        Assert.assertEquals(rot.zieheKarte(gemeinschaftskarten), kartenbild);
        Assert.assertEquals(rot.getKontostand(),1350);
    }
    @Test
    public void testZieheKarteCase25(){
        Texture kartenbild=new Texture("images/KartenImages/Karte25.png");
        gemeinschaftskarten.setIndex(4);
        rot.setKontostand(1000);
        Assert.assertEquals(rot.zieheKarte(gemeinschaftskarten), kartenbild);
        Assert.assertEquals(rot.getKontostand(),1200);
    }
    @Test
    public void testZieheKarteCase26(){
        Texture kartenbild=new Texture("images/KartenImages/Karte26.png");
        gemeinschaftskarten.setIndex(5);
        rot.setPosition(10);
        Assert.assertEquals(rot.zieheKarte(gemeinschaftskarten), kartenbild);
        Assert.assertEquals(rot.getPosition(),0);
    }
    @Test
    public void testZieheKarteCase27(){
        Texture kartenbild=new Texture("images/KartenImages/Karte27.png");
        gemeinschaftskarten.setIndex(6);
        rot.setKontostand(1000);
        Assert.assertEquals(rot.zieheKarte(gemeinschaftskarten), kartenbild);
        Assert.assertEquals(rot.getKontostand(),1300);
    }
    @Test
    public void testZieheKarteCase28(){
        Texture kartenbild=new Texture("images/KartenImages/Karte28.png");
        gemeinschaftskarten.setIndex(7);
        rot.setPosition(2);
        Assert.assertEquals(rot.zieheKarte(gemeinschaftskarten), kartenbild);
        Assert.assertEquals(rot.getPosition(),37);
    }
    @Test
    public void testZieheKarteCase29(){
        Texture kartenbild=new Texture("images/KartenImages/Karte29.png");
        gemeinschaftskarten.setIndex(8);
        rot.setKontostand(2);
        Assert.assertEquals(rot.zieheKarte(gemeinschaftskarten), kartenbild);
        Assert.assertEquals(rot.getKontostand(),512);
    }
    @Test
    public void testZieheKarteCase30(){
        Texture kartenbild=new Texture("images/KartenImages/Karte30.png");
        gemeinschaftskarten.setIndex(9);
        rot.setKontostand(2);
        Assert.assertEquals(rot.zieheKarte(gemeinschaftskarten), kartenbild);
        Assert.assertEquals(rot.getKontostand(),352);
    }
    @Test
    public void testZieheKarteCase31(){
        Texture kartenbild=new Texture("images/KartenImages/Karte31.png");
        gemeinschaftskarten.setIndex(10);
        rot.setKontostand(2);
        Assert.assertEquals(rot.zieheKarte(gemeinschaftskarten), kartenbild);
        Assert.assertEquals(rot.getKontostand(),224);
    }
    @Test
    public void testZieheKarteCase32(){
        Texture kartenbild=new Texture("images/KartenImages/Karte32.png");
        gemeinschaftskarten.setIndex(11);
        rot.setKontostand(250);
        Assert.assertEquals(rot.zieheKarte(gemeinschaftskarten), kartenbild);
        Assert.assertEquals(rot.getKontostand(),0);
    }
    @Test
    public void testZieheKarteCase33(){
        Texture kartenbild=new Texture("images/KartenImages/Karte33.png");
        gemeinschaftskarten.setIndex(12);
        rot.setKontostand(200);
        Assert.assertEquals(rot.zieheKarte(gemeinschaftskarten), kartenbild);
        Assert.assertEquals(rot.getKontostand(),100);
    }
    @Test
    public void testZieheKarteCase34(){
        Texture kartenbild=new Texture("images/KartenImages/Karte34.png");
        gemeinschaftskarten.setIndex(13);
        rot.setKontostand(300);
        Assert.assertEquals(rot.zieheKarte(gemeinschaftskarten), kartenbild);
        Assert.assertEquals(rot.getKontostand(),50);
    }
    @Test
    public void testZieheKarteCase35(){
        Texture kartenbild=new Texture("images/KartenImages/Karte35.png");
        gemeinschaftskarten.setIndex(14);
        rot.setKontostand(200);
        Assert.assertEquals(rot.zieheKarte(gemeinschaftskarten), kartenbild);
        Assert.assertEquals(rot.getKontostand(),0);
    }

    @Test
    public void testZieheKarteCase36(){
        Texture kartenbild=new Texture("images/KartenImages/Karte36.png");
        gemeinschaftskarten.setIndex(15);
        rot.setKontostand(200);
        Assert.assertEquals(rot.zieheKarte(gemeinschaftskarten), kartenbild);
        Assert.assertEquals(rot.getKontostand(),100);
    }

    @Test
    public void testZieheKarteCaseDefaultNegativ(){
        Kartenstapel neuerStapel=new Kartenstapel();
        Karte falscheKarte=new Karte(-5, "sdlkfjasldk");
        neuerStapel.add(falscheKarte);
        Assert.assertNull(rot.zieheKarte(neuerStapel));
    }

    @Test
    public void testZieheKarteCaseDefault(){
        Kartenstapel neuerStapel=new Kartenstapel();
        Karte falscheKarte=new Karte(37, "sdlkfjasldk");
        neuerStapel.add(falscheKarte);
        Assert.assertNull(rot.zieheKarte(neuerStapel));
    }

     */

}
