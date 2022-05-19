import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import se2.groupb.monopoly.Street;

public class StreetTest {
    Street hauptstraße;

    @Before
    public void init(){
        hauptstraße=new Street("Hauptstraße", 150, false, 0,0,20,50 );
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
        Assert.assertEquals(hauptstraße.getPrice(),150);
    }
    @Test
    public void testSetPreis(){
        hauptstraße.setPrice(200);
        Assert.assertEquals(hauptstraße.getPrice(),200);
    }
    @Test
    public void testIsVerkauft(){
        Assert.assertFalse(hauptstraße.isSold());
    }
    @Test
    public void testSetVerkauft(){
        hauptstraße.setSold(true);
        Assert.assertTrue(hauptstraße.isSold());
    }
    @Test
    public void testGetHaus(){
        Assert.assertEquals(hauptstraße.getHouse(),0);
    }
    @Test
    public void testSetHaus(){
        hauptstraße.setHouse(2);
        Assert.assertEquals(hauptstraße.getHouse(),2);
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
        Assert.assertEquals(hauptstraße.getRent(),20);
    }
    @Test
    public void testSetMiete(){
        hauptstraße.setRent(30);
        Assert.assertEquals(hauptstraße.getRent(),30);
    }
    @Test
    public void testGetHausPreis(){
        Assert.assertEquals(hauptstraße.getHousePrice(),50);
    }
    @Test
    public void testSetHausPreis(){
        hauptstraße.setHousePrice(55);
        Assert.assertEquals(hauptstraße.getHousePrice(),55);
    }
}
