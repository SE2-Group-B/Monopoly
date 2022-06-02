import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import se2.groupb.monopoly.Trainstation;
import se2.groupb.monopoly.Property;
import se2.groupb.monopoly.Deck;
import se2.groupb.monopoly.Player;
import se2.groupb.monopoly.Street;

public class PlayerTest {
    Player rot;
    Player gelb;
    ArrayList<Property> myPropertiesRot;
    ArrayList<Property> myPropertiesGelb;
    Street hauptstraße;
    Trainstation nordbahnhof;
    Deck ereigniskarten;
    Deck gemeinschaftskarten;



    @Before
    public void init(){
        myPropertiesRot =new ArrayList<>();
        myPropertiesGelb=new ArrayList<>();
        hauptstraße=new Street("Hauptstraße", 220, false, 0,0,20, 50);
        myPropertiesRot.add(hauptstraße);
        nordbahnhof=new Trainstation("Nordbahnhof", false, 50);
        myPropertiesRot.add(nordbahnhof);

        rot=new Player(1, "Rot", 2000, myPropertiesRot,1 , Color.RED);
        gelb=new Player(2, "Gelb", 2000, myPropertiesGelb,0 , Color.YELLOW);

        ereigniskarten = new Deck();
        ereigniskarten.initializeEreigniskartenStapel();

        gemeinschaftskarten = new Deck();
        gemeinschaftskarten.initializeGemeinschaftskartenStapel();
    }

    @After
    public void teardown(){
        rot=null;
        myPropertiesRot =null;
        hauptstraße=null;
        nordbahnhof=null;
    }

    @Test
    public void testPlayer(){
        Player testFigur=new Player(1, "Rot", 2000, myPropertiesRot,1 , Color.RED);
        Assert.assertNotNull(testFigur);
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
        rot.setBankBalance(1200);
        Assert.assertEquals(rot.getBankBalance(), 1200);
    }
    @Test
    public void testGetKontostand(){
        Assert.assertEquals(rot.getBankBalance(), 2000);
    }
    @Test
    public void testGetMyProperties(){
        Assert.assertEquals(rot.getMyProperties(), myPropertiesRot);
    }
    @Test
    public void testSetMyProperties(){
        ArrayList<Property> myProperties2=new ArrayList<>();
        myProperties2.add(nordbahnhof);
        myProperties2.add(hauptstraße);
        rot.setMyProperties(myProperties2);
        Assert.assertEquals(rot.getMyProperties(),myProperties2);
    }

    @Test
    public void testGetNumOfTrainstaitions(){
        Assert.assertEquals(rot.getNumOfTrainstaitions(),1);
    }

    @Test
    public void testSetNumOfTrainstaitions(){
        rot.setNumOfTrainstaitions(4);
        Assert.assertEquals(rot.getNumOfTrainstaitions(),4);
    }


    @Test
    public void testChangeMoneyPlus(){
        rot.changeMoney(50);
        Assert.assertEquals(rot.getBankBalance(),2050);
    }

    @Test
    public void testChangeMoneyMinus(){
        rot.changeMoney(-2000);
        Assert.assertEquals(rot.getBankBalance(),0);
    }

    @Test
    public void testChangeMoneyNull(){
        rot.changeMoney(0);
        Assert.assertEquals(rot.getBankBalance(),2000);
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
    public void testGoToJailPos(){
        rot.goToJail();
        Assert.assertEquals(rot.getPosition(), 10);
    }
    @Test
    public void testGoToJailPrison(){
        rot.goToJail();
        Assert.assertEquals(rot.getPrison(), true);
    }
    @Test
    public void testGetPrison(){
        Assert.assertEquals(rot.getPrison(), false);
    }
    @Test
    public void testSetPrison(){
        rot.goToJail();
        rot.setPrison(false);
        Assert.assertEquals(rot.getPrison(), false);
    }
    @Test
    public void testGetPrisonCount(){
        rot.goToJail();
        Assert.assertEquals(rot.getPrisonCount(), 0);
    }
    @Test
    public void testSetPrisonCount(){
        rot.setPrisonCount(4);
        Assert.assertEquals(rot.getPrisonCount(), 4);
    }

    @Test
    public void testRoundmoney(){
        rot.roundmoney();
        Assert.assertEquals(rot.getBankBalance(), 2200);
    }
    @Test
    public void testPayToOtherPlayerString(){
        String antwort=rot.payToOtherPlayer(gelb, 2000);
        Assert.assertEquals(antwort, "Rot stepped on Gelb's Property and payed 2000 rent.");
    }
    @Test
    public void testPayToOtherPlayerStringBakrott(){
        String antwort=rot.payToOtherPlayer(gelb, 4000);
        Assert.assertEquals(antwort, "Rot ist Bankrott.");
    }
    @Test
    public void testPayToOtherPlayerBankAccount(){
        rot.payToOtherPlayer(gelb, 2000);
        Assert.assertEquals(rot.getBankBalance(),0);
        Assert.assertEquals(gelb.getBankBalance(), 4000);
    }
    @Test
    public void testPayToOtherPlayerBankAccountBankrott(){
        rot.payToOtherPlayer(gelb, 4000);
        Assert.assertEquals(rot.getBankBalance(),0);
        Assert.assertEquals(gelb.getBankBalance(), 6000);
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
    @Test
    public void testMoveUeberLosMoney(){
        rot.setPosition(38);
        rot.move(5);
        Assert.assertEquals(rot.getBankBalance(),2200);
    }
    @Test
    public void testMovePrison(){
        rot.goToJail();
        rot.move(5);
        Assert.assertEquals(rot.getPosition(),10);
        Assert.assertEquals(rot.getPrisonCount(),1);
        Assert.assertEquals(rot.getPrison(),true);
    }
    @Test
    public void testMovePrisonAlmostFree(){
        rot.goToJail();
        rot.setPrisonCount(3);
        rot.move(5);
        Assert.assertEquals(rot.getPosition(),10);
        Assert.assertEquals(rot.getPrisonCount(),0);
        Assert.assertEquals(rot.getPrison(),false);
    }
    @Test
    public void testMovePrisonFree(){
        rot.goToJail();
        rot.setPrisonCount(3);
        rot.move(5);
        rot.move(15);
        Assert.assertEquals(rot.getPosition(),25);
        Assert.assertEquals(rot.getPrisonCount(),0);
        Assert.assertEquals(rot.getPrison(),false);
    }

    /*
    @Test
    public void testZieheKarteCase1(){
        Assert.assertNotNull(rot.drawCard(ereigniskarten));
    }

    /*
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
