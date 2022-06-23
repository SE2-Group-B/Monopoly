import com.badlogic.gdx.graphics.Color;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import se2.groupb.monopoly.LogicalGameField;
import se2.groupb.monopoly.Player;
import se2.groupb.monopoly.PlayerOperation;

public class PlayerOPTest {

    Player rot, blue;
    ArrayList<Player> playerList = new ArrayList<>(2);
    PlayerOperation playerOperation;

    @Before
    public void init(){
        rot = new Player(1, "Rot", 2000,new ArrayList<>(),1 ,Color.RED);
        blue = new Player(2, "Blue", 2000,new ArrayList<>(),1 ,Color.BLUE);
        playerList.add(rot);
        playerList.add(blue);
        playerOperation = new PlayerOperation(playerList);
    }
    @After
    public void  teardown (){

    }


    @Test
    public void currentPropertyNormal(){
        String something = playerOperation.checkCurrentProperty(rot);
        Assert.assertEquals("Player Rot landed directly on GO and earned 400$", something);
    }

    @Test
    public void currentPropertyPrison(){
        rot.setPosition(30);
        String something = playerOperation.checkCurrentProperty(rot);
        Assert.assertEquals("Player Rot went to prison", something);
    }

    @Test
    public void currentPropertyPrisonvisit(){
        rot.setPosition(10);
        String something = playerOperation.checkCurrentProperty(rot);
        Assert.assertEquals("Player Rot is just visiting the prison", something);
    }

    @Test
    public void nextPlayer() {
        String something = playerOperation.nextPlayer();
        Assert.assertEquals("It's Blue's turn now",something);
    }

    @Test
    public void buyingStreet(){
        rot.setPosition(1);
        String something = playerOperation.buying();
        Assert.assertEquals("Player Rot bought Badstraße for 40€", something);
    }

    @Test
    public void buyingTrain(){
        rot.setPosition(25);
        String something = playerOperation.buying();
        Assert.assertEquals("Player Rot bought Nordbahnhof for 500€", something);
    }

    @Test
    public void buyingStreetfromother(){
        rot.setPosition(1);
        playerOperation.buying();
        playerOperation.nextPlayer();
        blue.setPosition(1);
        String something = playerOperation.buying();
        Assert.assertEquals("You can't buy this Property", something);
    }

    @Test
    public void buyingStreetwitout(){
        rot.setPosition(1);
        rot.setBankBalance(0);
        String something = playerOperation.buying();
        Assert.assertEquals("Player Rot bought nothing because he is too poor for that building", something);
    }

    @Test
    public void buyingtwice(){
        rot.setPosition(1);
        playerOperation.buying();
        String something = playerOperation.buying();
        Assert.assertEquals("Du hast was zugekauft", something);
    }

    /*@Test
    public void Ereignis(){
        rot.setPosition(22);
        String something = playerOperation.checkCurrentProperty(rot);
        Assert.assertEquals("true", something);
    }

    @Test
    public void Gemeinschaft(){
        rot.setPosition(17);
        String something = playerOperation.checkCurrentProperty(rot);
        Assert.assertEquals("true", something);
    }*/
}
