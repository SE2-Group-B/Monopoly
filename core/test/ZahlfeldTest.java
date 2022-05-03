import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import se2.groupb.monopoly.Zahlfeld;

public class ZahlfeldTest {
    Zahlfeld einkommenssteuer;

    @Before
    public void init(){
        einkommenssteuer=new Zahlfeld("Einkommenssteuer",200);
    }
    @After
    public void teardown(){
        einkommenssteuer=null;
    }
    @Test
    public void testZahlfeld(){
        Assert.assertNotNull(einkommenssteuer);
    }
    @Test
    public void testGetStrafe(){
        Assert.assertEquals(einkommenssteuer.getStrafe(), 200);
    }
    @Test
    public void testSetStrafe(){
        einkommenssteuer.setStrafe(300);
        Assert.assertEquals(einkommenssteuer.getStrafe(), 300);
    }
}
