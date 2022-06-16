package screenTests;

import org.junit.After;
import org.junit.Before;

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


}
