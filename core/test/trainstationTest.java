
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import se2.groupb.monopoly.Trainstation;

public class trainstationTest {
    Trainstation nordbahnhof;

    @Before
    public void init(){
        nordbahnhof=new Trainstation("Nordbahnhof", false, 200);
    }
    @After
    public void teardown(){
        nordbahnhof=null;
    }

    @Test
    public void testBahnhof(){
        Assert.assertNotNull(nordbahnhof);
    }

    @Test
    public void testIsVerkauftFalse(){
        Assert.assertFalse(nordbahnhof.isSold());
    }

    @Test
    public void testIsVerkauftTrue(){
        nordbahnhof.setSold(true);
        Assert.assertTrue(nordbahnhof.isSold());
    }

    @Test
    public void testGetKaufpreis(){
        Assert.assertEquals(nordbahnhof.getPrice(), 500);
    }

    @Test
    public void testSetKaufpreis(){
        nordbahnhof.setPrice(100);
        Assert.assertEquals(nordbahnhof.getPrice(), 100);
    }

    @Test
    public void testGetMiete(){
        Assert.assertEquals(nordbahnhof.getRent(), 200);
    }

    @Test
    public void testSetMiete(){
        nordbahnhof.setRent(111);
        Assert.assertEquals(nordbahnhof.getRent(), 111);
    }

}
