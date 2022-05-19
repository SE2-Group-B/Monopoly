//package test;

import org.junit.Assert;
import org.junit.Test;

import se2.groupb.monopoly.Property;

public class propertyTest {

    @Test
    public void testGrundstueck(){
        Property g=new Property("Grundstück");
        Assert.assertNotNull(g);
    }
    @Test
    public void testGetName(){
        Property g=new Property("Grundstück");
        Assert.assertEquals(g.getName(), "Grundstück");
    }

    @Test
    public void testSetName(){
        Property g=new Property("Grundstück");
        g.setName("Neues Grundstück");
        Assert.assertEquals(g.getName(), "Neues Grundstück");
    }

}
