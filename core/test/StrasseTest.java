import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import se2.groupb.monopoly.Strasse;

public class StrasseTest {
    Strasse hauptstraße;

    @Before
    public void init(){
        hauptstraße=new Strasse("Hauptstraße", 150, false, 0,0,20,50 );
    }

    @After
    public void teardown(){
        hauptstraße=null;
    }

    @Test
    public void testStrasse(){
        Assert.assertNotNull(hauptstraße);
    }
    @Test
    public void testGetPreis(){
        Assert.assertEquals(hauptstraße.getPreis(),150);
    }
    @Test
    public void testSetPreis(){
        hauptstraße.setPreis(200);
        Assert.assertEquals(hauptstraße.getPreis(),200);
    }
    @Test
    public void testIsVerkauft(){
        Assert.assertFalse(hauptstraße.isVerkauft());
    }
    @Test
    public void testSetVerkauft(){
        hauptstraße.setVerkauft(true);
        Assert.assertTrue(hauptstraße.isVerkauft());
    }
    @Test
    public void testGetHaus(){
        Assert.assertEquals(hauptstraße.getHaus(),0);
    }
    @Test
    public void testSetHaus(){
        hauptstraße.setHaus(2);
        Assert.assertEquals(hauptstraße.getHaus(),2);
    }
    @Test
    public void testGetHotel(){
        Assert.assertEquals(hauptstraße.getHotel(),0);
    }
    @Test
    public void testSetHotel(){
        hauptstraße.setHotel(2);
        Assert.assertEquals(hauptstraße.getHotel(),2);
    }
    @Test
    public void testGetMiete(){
        Assert.assertEquals(hauptstraße.getMiete(),20);
    }
    @Test
    public void testSetMiete(){
        hauptstraße.setMiete(30);
        Assert.assertEquals(hauptstraße.getMiete(),30);
    }
    @Test
    public void testGetHausPreis(){
        Assert.assertEquals(hauptstraße.getHausPreis(),50);
    }
    @Test
    public void testSetHausPreis(){
        hauptstraße.setHausPreis(55);
        Assert.assertEquals(hauptstraße.getHausPreis(),55);
    }
}
