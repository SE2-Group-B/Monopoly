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



//    @Before
//    public void init(){
//        myPropertiesRot =new ArrayList<>();
//        myPropertiesGelb=new ArrayList<>();
//        hauptstraße=new Street("Hauptstraße", 220, false, 0,0,20, 50);
//        myPropertiesRot.add(hauptstraße);
//        nordbahnhof=new Trainstation("Nordbahnhof", false, 50);
//        myPropertiesRot.add(nordbahnhof);
//
////        rot=new Player(1, "Rot", 2000, myPropertiesRot,1 , Color.RED);
////        gelb=new Player(2, "Gelb", 2000, myPropertiesGelb,0 , Color.YELLOW);
//
//        ereigniskarten = new Deck();
//        ereigniskarten.initializeEreigniskartenStapel();
//
//        gemeinschaftskarten = new Deck();
//        gemeinschaftskarten.initializeGemeinschaftskartenStapel();
//    }
//
//    @After
//    public void teardown(){
//        rot=null;
//        myPropertiesRot =null;
//        hauptstraße=null;
//        nordbahnhof=null;
//    }
//
////    @Test
////    public void testPlayer(){
////        Player testFigur=new Player(1, "Rot", 2000, myPropertiesRot,1 , Color.RED);
////        Assert.assertNotNull(testFigur);
////    }
//
//
//    @Test
//    public void testGetId(){
//        Assert.assertEquals(1, rot.getId());
//    }
//
//    @Test
//    public void testSetId(){
//        rot.setId(10);
//        Assert.assertEquals(10, rot.getId());
//    }
//    @Test
//    public void testGetName(){
//        Assert.assertEquals("Rot", rot.getName());
//    }
//    @Test
//    public void testSetName(){
//        rot.setName("Vivienne");
//        Assert.assertEquals("Vivienne", rot.getName());
//    }
//    @Test
//    public void testSetKontostand(){
//        rot.setBankBalance(1200);
//        Assert.assertEquals(1200, rot.getBankBalance());
//    }
//    @Test
//    public void testGetKontostand(){
//        Assert.assertEquals(2000, rot.getBankBalance());
//    }
//    @Test
//    public void testGetMyProperties(){
//        Assert.assertEquals(myPropertiesRot, rot.getMyProperties());
//    }
//    @Test
//    public void testSetMyProperties(){
//        ArrayList<Property> myProperties2=new ArrayList<>();
//        myProperties2.add(nordbahnhof);
//        myProperties2.add(hauptstraße);
//        rot.setMyProperties(myProperties2);
//        Assert.assertEquals(myProperties2, rot.getMyProperties());
//    }
//
//    @Test
//    public void testGetNumOfTrainstaitions(){
//        Assert.assertEquals(1, rot.getNumOfTrainstaitions());
//    }
//
//    @Test
//    public void testSetNumOfTrainstaitions(){
//        rot.setNumOfTrainstaitions(4);
//        Assert.assertEquals(4, rot.getNumOfTrainstaitions());
//    }
//
//
//    @Test
//    public void testChangeMoneyPlus(){
//        rot.changeMoney(50);
//        Assert.assertEquals(2050, rot.getBankBalance());
//    }
//
//    @Test
//    public void testChangeMoneyMinus(){
//        rot.changeMoney(-2000);
//        Assert.assertEquals(0, rot.getBankBalance());
//    }
//
//    @Test
//    public void testChangeMoneyNull(){
//        rot.changeMoney(0);
//        Assert.assertEquals(2000, rot.getBankBalance());
//    }
//
//    @Test
//    public void testGetPosition(){
//        Assert.assertEquals(0,rot.getPosition());
//    }
//
//    @Test
//    public void testSetPosition(){
//        rot.setPosition(12);
//        Assert.assertEquals(12, rot.getPosition());
//    }
//
//    @Test
//    public void testGoToJailPos(){
//        rot.goToJail();
//        Assert.assertEquals(10, rot.getPosition());
//    }
//    @Test
//    public void testGoToJailPrison(){
//        rot.goToJail();
//        Assert.assertEquals(true, rot.getPrison());
//    }
//    @Test
//    public void testGetPrison(){
//        Assert.assertEquals(false, rot.getPrison());
//    }
//    @Test
//    public void testSetPrison(){
//        rot.goToJail();
//        rot.setPrison(false);
//        Assert.assertEquals(false, rot.getPrison());
//    }
//    @Test
//    public void testGetPrisonCount(){
//        rot.goToJail();
//        Assert.assertEquals(0, rot.getPrisonCount());
//    }
//    @Test
//    public void testSetPrisonCount(){
//        rot.setPrisonCount(4);
//        Assert.assertEquals(4, rot.getPrisonCount());
//    }
//
//    @Test
//    public void testRoundmoney(){
//        rot.roundmoney();
//        Assert.assertEquals(2200, rot.getBankBalance());
//    }
//    @Test
//    public void testPayToOtherPlayerString(){
//        String antwort=rot.payToOtherPlayer(gelb, 2000);
//        Assert.assertEquals("Rot stepped on Gelb's Property and payed 2000 rent.", antwort);
//    }
//    @Test
//    public void testPayToOtherPlayerStringBakrott(){
//        String antwort=rot.payToOtherPlayer(gelb, 4000);
//        Assert.assertEquals("Rot ist Bankrott.", antwort);
//    }
//    @Test
//    public void testPayToOtherPlayerBankAccount(){
//        rot.payToOtherPlayer(gelb, 2000);
//        Assert.assertEquals(0, rot.getBankBalance());
//        Assert.assertEquals(4000, gelb.getBankBalance());
//    }
//    @Test
//    public void testPayToOtherPlayerBankAccountBankrott(){
//        rot.payToOtherPlayer(gelb, 4000);
//        Assert.assertEquals(0, rot.getBankBalance());
//        Assert.assertEquals(6000, gelb.getBankBalance());
//    }
//
//
//    @Test
//    public void testMove(){
//        rot.move(12);
//        Assert.assertEquals(12, rot.getPosition());
//    }
//
//    @Test
//    public void testMoveUeberLos(){
//        rot.setPosition(38);
//        rot.move(5);
//        Assert.assertEquals(3, rot.getPosition());
//    }
//    @Test
//    public void testMoveUeberLosMoney(){
//        rot.setPosition(38);
//        rot.move(5);
//        Assert.assertEquals(2200, rot.getBankBalance());
//    }
//    @Test
//    public void testMovePrison(){
//        rot.goToJail();
//        rot.move(5);
//        Assert.assertEquals(10, rot.getPosition());
//        Assert.assertEquals(1, rot.getPrisonCount());
//        Assert.assertEquals(true, rot.getPrison());
//    }
//    @Test
//    public void testMovePrisonAlmostFree(){
//        rot.goToJail();
//        rot.setPrisonCount(3);
//        rot.move(5);
//        Assert.assertEquals(10, rot.getPosition());
//        Assert.assertEquals(0, rot.getPrisonCount());
//        Assert.assertEquals(false, rot.getPrison());
//    }
//    @Test
//    public void testMovePrisonFree(){
//        rot.goToJail();
//        rot.setPrisonCount(3);
//        rot.move(5);
//        rot.move(15);
//        Assert.assertEquals(25, rot.getPosition());
//        Assert.assertEquals(0, rot.getPrisonCount());
//        Assert.assertEquals(false, rot.getPrison());
//    }
//

}
