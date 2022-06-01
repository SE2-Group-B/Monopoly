//package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import se2.groupb.monopoly.Property;

public class propertyTest {

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
    public void testGrundstueck(){
        Property g1=new Property("Grundstück");
        Assert.assertNotNull(g1);
    }
    @Test
    public void testGetName(){
        Assert.assertEquals(g.getName(), "Grundstück");
    }

    @Test
    public void testSetName(){
        g.setName("Neues Grundstück");
        Assert.assertEquals(g.getName(), "Neues Grundstück");
    }
    @Test
    public void testGetOwnerID(){
        Assert.assertEquals(g.getOwnerId(), 0);
    }

    @Test
    public void testSetOwnerID(){
        g.setOwnerId(2);
        Assert.assertEquals(g.getOwnerId(), 2);
    }

}
