//package test;

import org.junit.Assert;
import org.junit.Test;

import se2.groupb.monopoly.Grundstueck;

public class GrundstueckTest {

    @Test
    public void testGrundstueck(){
        Grundstueck g=new Grundstueck("Grundstück");
        Assert.assertNotNull(g);
    }
    @Test
    public void testGetName(){
        Grundstueck g=new Grundstueck("Grundstück");
        Assert.assertEquals(g.getName(), "Grundstück");
    }

    @Test
    public void testSetName(){
        Grundstueck g=new Grundstueck("Grundstück");
        g.setName("Neues Grundstück");
        Assert.assertEquals(g.getName(), "Neues Grundstück");
    }

}
