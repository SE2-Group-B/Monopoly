
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import se2.groupb.monopoly.Card;

public class CardTest {

    Card card1;
    Card card2;

    @Before
    public void init(){
        card1 =new Card(1, "Karte1");
        card2 =new Card(2, "Karte2");
    }
    @After
    public void tearDown(){
        card1 =null;
        card2 =null;
    }

    @Test
    public void testKonstruktor(){
        Assert.assertNotNull(card1);
    }

    @Test
    public void testGetId(){
        Assert.assertEquals(1, card1.getId());
    }

    @Test
    public void testSetId(){
        card1.setId(8);
        Assert.assertEquals(8, card1.getId());
    }

    @Test
    public void testGetName(){
        Assert.assertEquals("Karte2", card2.getName());
    }

    @Test
    public void testSetName(){
        card2.setName("Neuer Name");
        Assert.assertEquals("Neuer Name", card2.getName());
    }



}
