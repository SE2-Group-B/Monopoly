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
    public void testStreet(){
        Assert.assertNotNull(hauptstraße);
    }
    @Test
    public void testGetPrice(){
        Assert.assertEquals(hauptstraße.getPrice(),150);
    }
    @Test
    public void testSetPrice(){
        hauptstraße.setPrice(200);
        Assert.assertEquals(hauptstraße.getPrice(),200);
    }
    @Test
    public void testIsSold(){
        Assert.assertFalse(hauptstraße.isSold());
    }
    @Test
    public void testSetSold(){
        hauptstraße.setSold(true);
        Assert.assertTrue(hauptstraße.isSold());
    }
    @Test
    public void testGetHouse(){
        Assert.assertEquals(hauptstraße.getHouse(),0);
    }
    @Test
    public void testSetHouse(){
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
    public void testGetRent(){
        Assert.assertEquals(hauptstraße.getRent(),20);
    }
    @Test
    public void testSetRent(){
        hauptstraße.setRent(30);
        Assert.assertEquals(hauptstraße.getRent(),30);
    }
    @Test
    public void testGetHousePrice(){
        Assert.assertEquals(hauptstraße.getHousePrice(),50);
    }
    @Test
    public void testSetHousePrice(){
        hauptstraße.setHousePrice(55);
        Assert.assertEquals(hauptstraße.getHousePrice(),55);
    }

    /*@Test
    public void testincreaseRent(){
        hauptstraße.increasemiete();
        Assert.assertEquals(hauptstraße.getRent(), 24);
    }*/

    @Test
    public void testbuyHouse(){
        hauptstraße.buyhouse();
        Assert.assertEquals(1,hauptstraße.getHouse());
        Assert.assertEquals(24,hauptstraße.getRent());
    }

    @Test
    public void testfalseHouse(){
        for (int i = 1; i <= 4; i++) {
            hauptstraße.buyhouse();
        }
        Assert.assertEquals(0,hauptstraße.getHouse());
        Assert.assertEquals(false, hauptstraße.buyhouse());
    }
    @Test
    public void testbuyHotel(){
        for (int i = 1; i <= 3; i++) {
            hauptstraße.buyhouse();
        }
        Assert.assertEquals(0, hauptstraße.getHouse());
        Assert.assertEquals(1, hauptstraße.getHotel());
        Assert.assertEquals(36, hauptstraße.getRent());
    }
}
