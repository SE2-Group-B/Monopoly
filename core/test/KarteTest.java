
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import se2.groupb.monopoly.Karte;

public class KarteTest {

    Karte karte1;
    Karte karte2;

    @Before
    public void init(){
        karte1 =new Karte(1, "Karte1");
        karte2=new Karte(2, "Karte2");
    }
    @After
    public void tearDown(){
        karte1=null;
        karte2=null;
    }

    @Test
    public void testKonstruktor(){
        Assert.assertNotNull(karte1);
    }

    @Test
    public void testGetId(){
        Assert.assertEquals(karte1.getId(), 1);
    }

    @Test
    public void testSetId(){
        karte1.setId(8);
        Assert.assertEquals(karte1.getId(),8);
    }

    @Test
    public void testGetName(){
        Assert.assertEquals(karte2.getName(),"Karte2");
    }

    @Test
    public void testSetName(){
        karte2.setName("Neuer Name");
        Assert.assertEquals(karte2.getName(), "Neuer Name");
    }



}
