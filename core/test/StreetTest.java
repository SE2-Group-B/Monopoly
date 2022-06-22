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
        Assert.assertEquals(150, hauptstraße.getPrice());
    }
    @Test
    public void testSetPrice(){
        hauptstraße.setPrice(200);
        Assert.assertEquals(200, hauptstraße.getPrice());
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
        Assert.assertEquals(0, hauptstraße.getHouse());
    }
    @Test
    public void testSetHouse(){
        hauptstraße.setHouse(2);
        Assert.assertEquals(2, hauptstraße.getHouse());
    }
    @Test
    public void testGetHotel(){
        Assert.assertEquals(0, hauptstraße.getHotel());
    }
    @Test
    public void testSetHotel(){
        hauptstraße.setHotel(2);
        Assert.assertEquals(2, hauptstraße.getHotel());
    }
    @Test
    public void testGetRent(){
        Assert.assertEquals(20, hauptstraße.getRent());
    }
    @Test
    public void testSetRent(){
        hauptstraße.setRent(30);
        Assert.assertEquals(30, hauptstraße.getRent());
    }
    @Test
    public void testGetHousePrice(){
        Assert.assertEquals(50, hauptstraße.getHousePrice());
    }
    @Test
    public void testSetHousePrice(){
        hauptstraße.setHousePrice(55);
        Assert.assertEquals(55, hauptstraße.getHousePrice());
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
