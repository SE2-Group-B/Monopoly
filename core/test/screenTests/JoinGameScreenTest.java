package screenTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se2.groupb.monopoly.Monopoly;
import se2.groupb.monopoly.screens.JoinGameScreen;

public class JoinGameScreenTest {
    Monopoly monopoly;
    JoinGameScreen screen;

    @Before
    public void setUp(){

        monopoly = new Monopoly();
        screen = new JoinGameScreen(monopoly);
    }

    @After
    public void tearDown(){
        monopoly = null;
        screen = null;
    }

    // How to test? crashes after: Gdx input and texture = new Texture("somewhere in assets folder");
    /*@Test
    public void showTest(){

        screen.show();

    }*/
    // same here
    /*@Test
    public void renderTest(){
        screen.render(0f);
    }*/


}
