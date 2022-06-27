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
    Player red;
    ArrayList<Property> myPropertiesRot;

    @Before
    public void init(){
        myPot=new Pot();
        myPropertiesRot=new ArrayList<>();
        red =new Player(1, "Red", 2000, myPropertiesRot,1 , Color.RED);
    }

    @After
    public void teardown(){
        myPot=null;
        red =null;
        myPropertiesRot=null;
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
    public void testDonateToPotAmount(){
        myPot.donateToPot(red, 200);
        Assert.assertEquals(200, myPot.getAmount());
    }

    @Test
    public void testDonateToPotPlayerBankBalance(){
        myPot.donateToPot(red, 200);
        Assert.assertEquals(1800, red.getBankBalance());
    }

    @Test
    public void testWinPotPlayerBankAccount(){
        myPot.addToPot(500);
        myPot.winPot(red);
        Assert.assertEquals(2500, red.getBankBalance());
    }

    @Test
    public void testWinPotEmpty(){
        myPot.addToPot(400);
        myPot.winPot(red);
        Assert.assertEquals(0, myPot.getAmount());
    }
}
