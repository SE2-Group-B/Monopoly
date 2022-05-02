package test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import java.util.ArrayList;
import org.junit.Test;

import se2.groupb.monopoly.Karte;
import se2.groupb.monopoly.Kartenstapel;

public class KartenstapelTest {
    Karte k1;
    Karte k2;
    Karte k3;
    Kartenstapel kartenstapel;

    @Before
    public void init(){
        k1 =new Karte(1, "Karte1");
        k2=new Karte(2, "Karte2");
        k3=new Karte(3, "Karte3");
        kartenstapel=new Kartenstapel();
        kartenstapel.add(k1);
        kartenstapel.add(k2);
        kartenstapel.add(k3);
    }

    @After
    public void tearDown(){
        k1=null;
        k2=null;
        k3=null;
        kartenstapel=null;
    }

    @Test
    public void testKonstruktor(){
        Kartenstapel k=new Kartenstapel();
        Assert.assertNotNull(k);
    }

    @Test
    public void testAdd(){
        Assert.assertEquals(kartenstapel.getKartenStapel().get(0), k1);
        Assert.assertEquals(kartenstapel.getKartenStapel().get(1), k2);
        Assert.assertEquals(kartenstapel.getKartenStapel().get(2), k3);
    }

    @Test
    public void testAddContains(){
        Assert.assertTrue(kartenstapel.getKartenStapel().contains(k1));
        Assert.assertTrue(kartenstapel.getKartenStapel().contains(k2));
        Assert.assertTrue(kartenstapel.getKartenStapel().contains(k3));
    }

    @Test
    public void testShuffleStillContains(){
        kartenstapel.shuffle();
        Assert.assertTrue(kartenstapel.getKartenStapel().contains(k1));
        Assert.assertTrue(kartenstapel.getKartenStapel().contains(k2));
        Assert.assertTrue(kartenstapel.getKartenStapel().contains(k3));
    }

    @Test
    public void testShuffle(){
        Kartenstapel kartenstapel2=new Kartenstapel();
        kartenstapel2.add(k1);
        kartenstapel2.add(k2);
        kartenstapel2.add(k3);
        kartenstapel.shuffle();
        kartenstapel2.shuffle();
        Assert.assertNotEquals(kartenstapel2.getKartenStapel(), kartenstapel.getKartenStapel());
    }

    @Test
    public void testGetKartenstapelNotNull(){
        Assert.assertNotNull(kartenstapel.getKartenStapel());
    }

    @Test
    public void testGetKartenstapel(){
        Assert.assertEquals(kartenstapel.getKartenStapel().get(0), k1);
        Assert.assertEquals(kartenstapel.getKartenStapel().get(1), k2);
        Assert.assertEquals(kartenstapel.getKartenStapel().get(2), k3);
    }

    @Test
    public void testSetKartenstapelNotNull(){
        ArrayList<Karte> karteArraylist=new ArrayList<>();
        karteArraylist.add(k1);
        karteArraylist.add(k2);
        kartenstapel.setKartenStapel(karteArraylist);
        Assert.assertNotNull(kartenstapel.getKartenStapel());
    }

    @Test
    public void testSetKartenstapel(){
        ArrayList<Karte> karteArraylist=new ArrayList<>();
        karteArraylist.add(k1);
        karteArraylist.add(k2);
        kartenstapel.setKartenStapel(karteArraylist);
        Assert.assertEquals(kartenstapel.getKartenStapel().get(0), k1);
        Assert.assertEquals(kartenstapel.getKartenStapel().get(1), k2);
    }

    @Test
    public void testGetIndex(){
        Assert.assertEquals(kartenstapel.getIndex(),0);
    }

    @Test
    public void testSetIndex(){
        kartenstapel.setIndex(3);
        Assert.assertEquals(kartenstapel.getIndex(),3);
    }

    @Test
    public void testGetNextCardNotNull(){
        Assert.assertNotNull(kartenstapel.getNextCard());
    }

    @Test
    public void testGetNextCardFirst(){
        Assert.assertEquals(kartenstapel.getNextCard(),k1);
    }

    @Test
    public void testGetNextCardSecond(){
        Karte karte1=kartenstapel.getNextCard();
        Assert.assertEquals(kartenstapel.getNextCard(),k2);
    }

    @Test
    public void testGetNextCardLastCard(){
        kartenstapel.setIndex(2);
        Assert.assertEquals(kartenstapel.getNextCard(),k3);
    }

    @Test
    public void testGetNextCardIndex(){
        kartenstapel.setIndex(2);
        kartenstapel.getNextCard();
        Assert.assertEquals(kartenstapel.getIndex(),0);
    }

    @Test
    public void testGetNextCardOutOfBounce(){
        kartenstapel.setIndex(25);
        Assert.assertNotNull(kartenstapel.getNextCard());
        Assert.assertEquals(kartenstapel.getIndex(),1);
    }

}
