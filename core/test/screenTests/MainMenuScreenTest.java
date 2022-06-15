package screenTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import se2.groupb.monopoly.Monopoly;
import se2.groupb.monopoly.screens.MainMenuScreen;

public class MainMenuScreenTest {
    Monopoly monopoly;
    MainMenuScreen screen;

    @Before
    public void setUp() {
        monopoly = new Monopoly();
        screen = new MainMenuScreen(monopoly);
    }


    @After
    public void tearDown() {
        monopoly = null;
        screen = null;
    }

    // How to test? crashes after: texture = new Texture("somewhere in assets folder");
    /*@Test
    public void showTest(){

        screen.show();

    }*/
}
