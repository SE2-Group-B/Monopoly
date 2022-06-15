package networkTests.messageTests;

import com.badlogic.gdx.graphics.Color;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import se2.groupb.monopoly.Player;
import se2.groupb.monopoly.Property;
import se2.groupb.monopoly.network.messages.PlayerInformation;

public class PlayerInformationTest {

    PlayerInformation playerInfo;

    @Before
    public void setUp() {
        playerInfo = new PlayerInformation();
    }

    @After
    public void tearDown() {
        playerInfo = null;
    }

    @Test
    public void testEmptyConstructor() {
        Assert.assertEquals(null, playerInfo.getPlayer());

        Assert.assertEquals(false, playerInfo.getIsPlayer());

        Assert.assertEquals(null, playerInfo.getMessageType());
    }

    @Test
    public void testPlayer() {
        Player p = new Player(1, "Blue", 1000, new ArrayList<Property>(), 0, Color.BLUE);
        playerInfo = new PlayerInformation(p);

        Assert.assertEquals(1, playerInfo.getPlayer().getId());
        Assert.assertEquals("Blue", playerInfo.getPlayer().getName());

        Assert.assertEquals(p, playerInfo.getPlayer());


        Player s = new Player(2, "Red", 1000, new ArrayList<Property>(), 0, Color.RED);
        playerInfo.setPlayer(s);

        Assert.assertEquals(s, playerInfo.getPlayer());
        Assert.assertNotEquals(s, p);
        Assert.assertNotEquals(p, playerInfo.getPlayer());
    }

    @Test
    public void testMessageType() {
        Player p = new Player(1, "Blue", 1000, new ArrayList<Property>(), 0, Color.BLUE);
        playerInfo = new PlayerInformation(p);
        Assert.assertEquals( null, playerInfo.getMessageType());
        playerInfo.setMessageType("SOMETYPE");
        Assert.assertEquals( "SOMETYPE", playerInfo.getMessageType());
    }

    @Test
    public void testIsPlayer(){
        Player p = new Player(1, "Blue", 1000, new ArrayList<Property>(), 0, Color.BLUE);
        playerInfo = new PlayerInformation(p);
        Assert.assertEquals( false, playerInfo.getIsPlayer());
        playerInfo.setIsPlayer(true);
        Assert.assertEquals( true, playerInfo.getIsPlayer());
    }
}