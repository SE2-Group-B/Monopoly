package screenTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se2.groupb.monopoly.Monopoly;
import se2.groupb.monopoly.screens.HostGameScreen;


public class HostGameScreenTest {

    Monopoly monopoly;
    HostGameScreen screen;

    @Before
    public void setUp() {

        monopoly = new Monopoly();
        screen = new HostGameScreen(monopoly);
    }

    @After
    public void tearDown() {
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

    @Test
    public void positionCheckerX0Y0() {
        int x = 0;
        int y = 0;
        assertTrue(screen.testPosition(x + 1, x + 1, x, y, 10, 10, y));
        assertTrue(screen.testPosition(x + 5, x + 5, x, y, 10, 10, y));
        assertTrue(screen.testPosition(x + 9, x + 9, x, y, 10, 10, y));
        assertFalse(screen.testPosition(x + 0, x + 0, x, y, 10, 10, y));
        assertFalse(screen.testPosition(x + 10, x + 10, x, y, 10, 10, y));
        assertFalse(screen.testPosition(x + 15, x + 1, x, y, 10, 10, y));
        assertFalse(screen.testPosition(x + 1, x + 15, x, y, 10, 10, y));
        assertFalse(screen.testPosition(x + 15, x + 15, x, y, 10, 10, y));
    }

    @Test
    public void positionCheckerX100Y100() {
        int x = 100;
        int y = 100;
        assertTrue(screen.testPosition(x + 1, x + 1, x, y, 10, 10, 0));
        assertTrue(screen.testPosition(x + 5, x + 5, x, y, 10, 10, 0));
        assertTrue(screen.testPosition(x + 9, x + 9, x, y, 10, 10, 0));
        assertFalse(screen.testPosition(x + 0, x + 0, x, y, 10, 10, 0));
        assertFalse(screen.testPosition(x + 10, x + 10, x, y, 10, 10, 0));
        assertFalse(screen.testPosition(x + 15, x + 1, x, y, 10, 10, 0));
        assertFalse(screen.testPosition(x + 1, x + 15, x, y, 10, 10, 0));
        assertFalse(screen.testPosition(x + 15, x + 15, x, y, 10, 10, 0));
    }

    @Test
    public void positionCheckerX100Y100withOffset() {
        int x = 100;
        int y = 100;
        int yOffset = 50;

        assertTrue(screen.testPosition(x + 1, y + yOffset + 1, x, y, 10, 10, yOffset));
        assertTrue(screen.testPosition(x + 5, y + yOffset + 5, x, y, 10, 10, yOffset));
        assertTrue(screen.testPosition(x + 9, y + yOffset + 9, x, y, 10, 10, yOffset));
        assertFalse(screen.testPosition(x + 0, y + yOffset + 0, x, y, 10, 10, yOffset));
        assertFalse(screen.testPosition(x + 10, y + yOffset + 10, x, y, 10, 10, yOffset));
        assertFalse(screen.testPosition(x + 15, y + yOffset + 1, x, y, 10, 10, yOffset));
        assertFalse(screen.testPosition(x + 1, y + yOffset + 15, x, y, 10, 10, yOffset));
        assertFalse(screen.testPosition(x + 15, y + yOffset + 15, x, y, 10, 10, yOffset));
        // if no offset, its out of the button area, so it doesn't click
        assertFalse(screen.testPosition(x + 15, y + 15, x, y, 10, 10, yOffset));
    }


    @Test
    public void resizeTest() {
        screen.resize(0, 0);
    }

    @Test
    public void pauseTest() {
        screen.pause();
    }

    @Test
    public void resumeTest() {
        screen.resume();
    }

    @Test
    public void hideTest() {
        screen.hide();
    }

    @Test
    public void disposeTest() {
        screen.dispose();
    }
}
