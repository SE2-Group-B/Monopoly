import com.badlogic.gdx.graphics.Color;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import se2.groupb.monopoly.Player;
import se2.groupb.monopoly.Pot;
import se2.groupb.monopoly.Property;

public class PotTest {

    Pot myPot;
    Player rot;
    ArrayList<Property> myPropertiesRot;

    @Before
    public void init(){
        myPot=new Pot();
        myPropertiesRot=new ArrayList<>();
        rot=new Player(1, "Rot", 2000, myPropertiesRot,1 , Color.RED);
    }

    @After
    public void teardown(){
        myPot=null;
        rot=null;
        myPropertiesRot=null;
    }

    @Test
    public void testPotKonstruktor(){
        Pot testPot=new Pot();
        Assert.assertNotNull(testPot);
    }

    @Test
    public void testGetAmount(){
        Assert.assertEquals(0, myPot.getAmount());
    }

    @Test
    public void testAddToPot(){
        myPot.addToPot(300);
        Assert.assertEquals(300, myPot.getAmount());
    }

    @Test
    public void testDonateToPotString(){
        Assert.assertEquals("Rot donates 100€ to the Pot.", myPot.donateToPot(rot, 100));
    }

    @Test
    public void testDonateToPotAmount(){
        myPot.donateToPot(rot, 200);
        Assert.assertEquals(200, myPot.getAmount());
    }

    @Test
    public void testDonateToPotPlayerBankBalance(){
        myPot.donateToPot(rot, 200);
        Assert.assertEquals(1800, rot.getBankBalance());
    }

    @Test
    public void testWinPotString(){
        myPot.addToPot(500);
        Assert.assertEquals("Rot won the Pot with 500€.", myPot.winPot(rot));
    }

    @Test
    public void testWinPotPlayerBankAccount(){
        myPot.addToPot(500);
        myPot.winPot(rot);
        Assert.assertEquals(2500, rot.getBankBalance());
    }

    @Test
    public void testWinPotEmpty(){
        myPot.addToPot(400);
        myPot.winPot(rot);
        Assert.assertEquals(0, myPot.getAmount());
    }
}
