
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import java.util.ArrayList;
import org.junit.Test;

import se2.groupb.monopoly.Card;
import se2.groupb.monopoly.Deck;

public class DeckTest {
    Card k1;
    Card k2;
    Card k3;
    Deck deck;

    @Before
    public void init(){
        k1 =new Card(1, "Karte1");
        k2=new Card(2, "Karte2");
        k3=new Card(3, "Karte3");
        deck =new Deck();
        deck.add(k1);
        deck.add(k2);
        deck.add(k3);
    }

    @After
    public void tearDown(){
        k1=null;
        k2=null;
        k3=null;
        deck =null;
    }

    @Test
    public void testKonstruktor(){
        Deck k=new Deck();
        Assert.assertNotNull(k);
    }

    @Test
    public void testAdd(){
        Assert.assertEquals(deck.getDeck().get(0), k1);
        Assert.assertEquals(deck.getDeck().get(1), k2);
        Assert.assertEquals(deck.getDeck().get(2), k3);
    }

    @Test
    public void testAddContains(){
        Assert.assertTrue(deck.getDeck().contains(k1));
        Assert.assertTrue(deck.getDeck().contains(k2));
        Assert.assertTrue(deck.getDeck().contains(k3));
    }

    @Test
    public void testShuffleStillContains(){
        deck.shuffle();
        Assert.assertTrue(deck.getDeck().contains(k1));
        Assert.assertTrue(deck.getDeck().contains(k2));
        Assert.assertTrue(deck.getDeck().contains(k3));
    }

    //Wie teste ich Shuffle Methode?
    /*@Test
    public void testShuffle(){
        Kartenstapel kartenstapel2=new Kartenstapel();
        kartenstapel2.add(k1);
        kartenstapel2.add(k2);
        kartenstapel2.add(k3);
        kartenstapel.shuffle();
        kartenstapel2.shuffle();
        Assert.assertNotEquals(kartenstapel2.getKartenStapel(), kartenstapel.getKartenStapel());
    }*/

    @Test
    public void testGetKartenstapelNotNull(){
        Assert.assertNotNull(deck.getDeck());
    }

    @Test
    public void testGetKartenstapel(){
        Assert.assertEquals(deck.getDeck().get(0), k1);
        Assert.assertEquals(deck.getDeck().get(1), k2);
        Assert.assertEquals(deck.getDeck().get(2), k3);
    }

    @Test
    public void testSetKartenstapelNotNull(){
        ArrayList<Card> cardArraylist =new ArrayList<>();
        cardArraylist.add(k1);
        cardArraylist.add(k2);
        deck.setDeck(cardArraylist);
        Assert.assertNotNull(deck.getDeck());
    }

    @Test
    public void testSetKartenstapel(){
        ArrayList<Card> cardArraylist =new ArrayList<>();
        cardArraylist.add(k1);
        cardArraylist.add(k2);
        deck.setDeck(cardArraylist);
        Assert.assertEquals(deck.getDeck().get(0), k1);
        Assert.assertEquals(deck.getDeck().get(1), k2);
    }

    @Test
    public void testGetIndex(){
        Assert.assertEquals(deck.getIndex(),0);
    }

    @Test
    public void testSetIndex(){
        deck.setIndex(3);
        Assert.assertEquals(deck.getIndex(),3);
    }

    @Test
    public void testGetNextCardNotNull(){
        Assert.assertNotNull(deck.getNextCard());
    }

    @Test
    public void testGetNextCardFirst(){
        Assert.assertEquals(deck.getNextCard(),k1);
    }

    @Test
    public void testGetNextCardSecond(){
        Card card1 = deck.getNextCard();
        Assert.assertEquals(deck.getNextCard(),k2);
    }

    @Test
    public void testGetNextCardLastCard(){
        deck.setIndex(2);
        Assert.assertEquals(deck.getNextCard(),k3);
    }

    @Test
    public void testGetNextCardIndex(){
        deck.setIndex(2);
        deck.getNextCard();
        Assert.assertEquals(deck.getIndex(),0);
    }

    @Test
    public void testGetNextCardOutOfBounce(){
        deck.setIndex(33);
        deck.getNextCard();
        Assert.assertEquals(deck.getIndex(),1);
    }
    @Test
    public void testGetNextCardOutOfBounceKarte(){
        deck.setIndex(33);
        Assert.assertNotNull(deck.getNextCard());
    }
    @Test
    public void testInitializeEreigniskartenStapelCard1(){

    }

}
