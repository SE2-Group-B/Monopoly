
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import se2.groupb.monopoly.Trainstation;

public class TrainstationTest {
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
        Assert.assertEquals(500, nordbahnhof.getPrice());
    }

    @Test
    public void testSetKaufpreis(){
        nordbahnhof.setPrice(100);
        Assert.assertEquals(100, nordbahnhof.getPrice());
    }

    @Test
    public void testGetMiete(){
        Assert.assertEquals(200, nordbahnhof.getRent());
    }

    @Test
    public void testSetMiete(){
        nordbahnhof.setRent(111);
        Assert.assertEquals(111, nordbahnhof.getRent());
    }
}
