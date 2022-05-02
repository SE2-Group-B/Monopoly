package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import se2.groupb.monopoly.Bahnhof;

public class BahnhofTest {
    Bahnhof nordbahnhof;

    @Before
    public void init(){
        nordbahnhof=new Bahnhof("Nordbahnhof", false, 200);
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
        Assert.assertFalse(nordbahnhof.isVerkauft());
    }

    @Test
    public void testIsVerkauftTrue(){
        nordbahnhof.setVerkauft(true);
        Assert.assertTrue(nordbahnhof.isVerkauft());
    }

    @Test
    public void testGetKaufpreis(){
        Assert.assertEquals(nordbahnhof.getKaufpreis(), 500);
    }

    @Test
    public void testSetKaufpreis(){
        nordbahnhof.setKaufpreis(100);
        Assert.assertEquals(nordbahnhof.getKaufpreis(), 100);
    }

    @Test
    public void testGetMiete(){
        Assert.assertEquals(nordbahnhof.getMiete(), 200);
    }

    @Test
    public void testSetMiete(){
        nordbahnhof.setMiete(111);
        Assert.assertEquals(nordbahnhof.getMiete(), 111);
    }

}
