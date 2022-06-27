import com.badlogic.gdx.graphics.Color;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import se2.groupb.monopoly.Player;
import se2.groupb.monopoly.PlayerOperation;
import se2.groupb.monopoly.Pot;

public class PlayerOPTest {

    Player red;
    Player blue;
    ArrayList<Player> playerList;
    PlayerOperation playerOperation;
    Pot pot;

    @Before
    public void init() {
        playerList = new ArrayList<>();
        red = new Player(1, "Red", 2000, new ArrayList<>(), 0, Color.RED);
        blue = new Player(2, "Blue", 2000, new ArrayList<>(), 1, Color.BLUE);
        playerList.add(red);
        playerList.add(blue);
        playerOperation = new PlayerOperation(playerList);
        pot = new Pot();
    }

    @After
    public void teardown() {
        red = null;
        blue = null;
        playerList = null;
        playerOperation = null;
        pot = null;
    }

    @Test
    public void testIsSomeonesPropertyFalse(){
        Assert.assertFalse(playerOperation.isSomeonesProperty(5));
    }

    @Test
    public void testIsSomeonesPropertyTrue(){
        red.setPosition(5);
        playerOperation.buying();
        Assert.assertTrue(playerOperation.isSomeonesProperty(5));
    }

    @Test
    public void testCheckSolePropertyLosEarnMoney(){
        red.move(40);
        playerOperation.checkCurrentProperty(red);
        Assert.assertEquals(2400, red.getBankBalance());
    }

    @Test
    public void testCheckSolePropertySofa(){
        pot.addToPot(200);
        red.move(20);
        playerOperation.setMoneyPotForOperation(pot);
        playerOperation.checkCurrentProperty(red);
        Assert.assertEquals(2200, red.getBankBalance());
        Assert.assertEquals(0, pot.getAmount());
    }

    @Test
    public void testCheckSolePropertyGoToJail(){
        red.move(30);
        playerOperation.checkCurrentProperty(red);
        Assert.assertTrue(red.getPrison());
        Assert.assertEquals(10, red.getPosition());
    }

    @Test
    public void testCheckSolePropertyVisitPrison(){
        red.move(10);
        String output = playerOperation.checkCurrentProperty(red);
        Assert.assertFalse(red.getPrison());
        Assert.assertEquals("Player Red is just visiting the prison", output);
    }

    @Test
    public void testCannotBuyPenaltyField(){
        red.move(4);
        playerOperation.buying();
        Assert.assertFalse(playerOperation.isSomeonesProperty(4));
    }

    @Test
    public void testNextPlayer(){
        playerOperation.nextPlayer();
        Assert.assertEquals(2, playerOperation.getCurrentPlayer().getId());
    }

    @Test
    public void testNextPlayerFalse(){
        playerOperation.nextPlayer();
        Assert.assertNotEquals(1, playerOperation.getCurrentPlayer().getId());
    }

    @Test
    public void testNextPlayerReset(){
        playerOperation.nextPlayer();
        playerOperation.nextPlayer();
        Assert.assertEquals(1, playerOperation.getCurrentPlayer().getId());
    }

    @Test
    public void testIsEnemyPropertyFalse(){
        red.move(5);
        Assert.assertFalse(playerOperation.isEnemyProperty(red.getPosition()));
    }

    @Test
    public void testIsEnemyPropertyTrue(){
        red.move(5);
        playerOperation.buying();
        playerOperation.nextPlayer();
        blue.move(5);
        Assert.assertTrue(playerOperation.isEnemyProperty(blue.getPosition()));
    }

    @Test
    public void testGetCurrentPlayer(){
        Assert.assertEquals(red, playerOperation.getCurrentPlayer());
        playerOperation.nextPlayer();
        Assert.assertEquals(blue, playerOperation.getCurrentPlayer());
    }

    @Test
    public void testGetPlayerById(){
        Assert.assertEquals(red, playerOperation.getPlayerById(1));
        Assert.assertEquals(blue, playerOperation.getPlayerById(2));
    }

    @Test
    public void testGetPropertyOwner(){
        red.move(5);
        playerOperation.buying();
        Assert.assertEquals(red, playerOperation.getPropertyOwner(5));
    }

    @Test
    public void testCheckCurrentPropertyPenalty(){
        red.move(4);
        playerOperation.setMoneyPotForOperation(pot);
        playerOperation.checkCurrentProperty(red);
        Assert.assertEquals(200, pot.getAmount());
        Assert.assertEquals(1800, red.getBankBalance());
    }

    @Test
    public void currentPropertyNormal() {
        String something = playerOperation.checkCurrentProperty(red);
        Assert.assertEquals("Player Red landed directly on GO and earned 400$", something);
    }

    @Test
    public void currentPropertyPrison() {
        red.setPosition(30);
        String something = playerOperation.checkCurrentProperty(red);
        Assert.assertEquals("Player Red went to prison", something);
    }

    @Test
    public void currentPropertyPrisonvisit() {
        red.setPosition(10);
        String something = playerOperation.checkCurrentProperty(red);
        Assert.assertEquals("Player Red is just visiting the prison", something);
    }

    @Test
    public void nextPlayer() {
        String something = playerOperation.nextPlayer();
        Assert.assertEquals("It's Blue's turn now", something);
    }

    @Test
    public void buyingStreet() {
        red.setPosition(1);
        String something = playerOperation.buying();
        Assert.assertEquals("Player Red bought Badstraße for 40€", something);
    }

    @Test
    public void buyingTrain() {
        red.setPosition(25);
        String something = playerOperation.buying();
        Assert.assertEquals("Player Red bought Nordbahnhof for 500€", something);
    }

    @Test
    public void buyingStreetfromother() {
        red.setPosition(1);
        playerOperation.buying();
        playerOperation.nextPlayer();
        blue.setPosition(1);
        String something = playerOperation.buying();
        Assert.assertEquals("You can't buy this Property", something);
    }

    @Test
    public void buyingStreetwitout() {
        red.setPosition(1);
        red.setBankBalance(0);
        String something = playerOperation.buying();
        Assert.assertEquals("Player Red bought nothing because he is too poor for that building", something);
    }

    @Test
    public void buyingtwice() {
        red.setPosition(1);
        playerOperation.buying();
        String something = playerOperation.buying();
        Assert.assertEquals("Du hast was zugekauft", something);
    }
}
