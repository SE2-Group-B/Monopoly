import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import se2.groupb.monopoly.PenaltyField;

public class PenaltyFieldTest {
    PenaltyField einkommenssteuer;

    @Before
    public void init(){
        einkommenssteuer=new PenaltyField("Einkommenssteuer",200);
    }
    @After
    public void teardown(){
        einkommenssteuer=null;
    }

    @Test
    public void testPenalty(){
        PenaltyField einkommenssteuer2=new PenaltyField("ab",500);
        Assert.assertNotNull(einkommenssteuer2);
    }
    @Test
    public void testGetPenalty(){
        Assert.assertEquals(200, einkommenssteuer.getPenalty());
    }
    @Test
    public void testSetPenalty(){
        einkommenssteuer.setPenalty(300);
        Assert.assertEquals(300, einkommenssteuer.getPenalty());
    }
}
