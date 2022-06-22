import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se2.groupb.monopoly.Property;

public class PlayerOPTest {

    Property g;

    @Before
    public void init(){
        g=new Property("Grundstück");
    }
    @After
    public void  teardown (){
        g=null;
    }

    @Test
    public void buying(){

    }
}
