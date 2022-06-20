package screenTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se2.groupb.monopoly.Monopoly;
import se2.groupb.monopoly.screens.GameScreenAdapter;

public class GameScreenAdapterTest {
    Monopoly monopoly;
    GameScreenAdapter screen;

    @Before
    public void setUp() {

        monopoly = new Monopoly();
        screen = new GameScreenAdapter(monopoly) {


            @Override
            public boolean testPosition(float userPosX, float userPosY, float xPosButton, float yPosButton, float buttonSizeX, float buttonSizeY, float yPosOffset) {
                return super.testPosition(userPosX, userPosY, xPosButton, yPosButton, buttonSizeX, buttonSizeY, yPosOffset);
            }

            @Override
            public void switchScreenDelayed(GameScreenAdapter screen, float delay) {

            }

            @Override
            public ImageButton drawImageButtonTester(String texturePath, float x, float y, float size) {
                return super.drawImageButtonTester(texturePath, x, y, size);
            }
        };
    }

    @After
    public void tearDown() {
        monopoly = null;
        screen = null;
    }


    @Test
    public void positionCheckerX0Y0CheckWithinRegion() {
        int x = 0;
        int y = 0;
        assertTrue(screen.testPosition(x + 1, y + 1, x, y, 10, 10, y));
        assertTrue(screen.testPosition(x + 5, y + 5, x, y, 10, 10, y));
        assertTrue(screen.testPosition(x + 9, y + 9, x, y, 10, 10, y));
    }

    @Test
    public void positionCheckerX0Y0CheckOutsideRegion() {
        int x = 0;
        int y = 0;
        assertFalse(screen.testPosition(x + 0, y + 0, x, y, 10, 10, y));
        assertFalse(screen.testPosition(x + 10, y + 10, x, y, 10, 10, y));
        assertFalse(screen.testPosition(x + 15, y + 1, x, y, 10, 10, y));
        assertFalse(screen.testPosition(x + 1, y + 15, x, y, 10, 10, y));
        assertFalse(screen.testPosition(x + 15, y + 15, x, y, 10, 10, y));
    }

    @Test
    public void positionCheckerX100Y100CheckWithinRegion() {
        int x = 100;
        int y = 100;
        assertTrue(screen.testPosition(x + 1, y + 1, x, y, 10, 10, 0));
        assertTrue(screen.testPosition(x + 5, y + 5, x, y, 10, 10, 0));
        assertTrue(screen.testPosition(x + 9, y + 9, x, y, 10, 10, 0));
    }

    @Test
    public void positionCheckerX100Y100CheckOutsideRegion() {
        int x = 100;
        int y = 100;
        assertFalse(screen.testPosition(x + 0, y + 0, x, y, 10, 10, 0));
        assertFalse(screen.testPosition(x + 10, y + 10, x, y, 10, 10, 0));
        assertFalse(screen.testPosition(x + 15, y + 1, x, y, 10, 10, 0));
        assertFalse(screen.testPosition(x + 1, y + 15, x, y, 10, 10, 0));
        assertFalse(screen.testPosition(x + 15, y + 15, x, y, 10, 10, 0));
    }

    @Test
    public void positionCheckerX100Y100ButtonWithOffsetCheckWithinRegion() {
        int x = 100;
        int y = 100;
        int yOffset = 50;

        assertTrue(screen.testPosition(x + 1, y + yOffset + 1, x, y, 10, 10, yOffset));
        assertTrue(screen.testPosition(x + 5, y + yOffset + 5, x, y, 10, 10, yOffset));
        assertTrue(screen.testPosition(x + 9, y + yOffset + 9, x, y, 10, 10, yOffset));
        }

    @Test
    public void positionCheckerX100Y100ButtonWithOffsetCheckOutsideRegion() {
        int x = 100;
        int y = 100;
        int yOffset = 50;

        assertFalse(screen.testPosition(x + 0, y + yOffset + 0, x, y, 10, 10, yOffset));
        assertFalse(screen.testPosition(x + 10, y + yOffset + 10, x, y, 10, 10, yOffset));
        assertFalse(screen.testPosition(x + 15, y + yOffset + 1, x, y, 10, 10, yOffset));
        assertFalse(screen.testPosition(x + 1, y + yOffset + 15, x, y, 10, 10, yOffset));
        assertFalse(screen.testPosition(x + 15, y + yOffset + 15, x, y, 10, 10, yOffset));
        // if no offset, its out of the button area, so it doesn't click
        assertFalse(screen.testPosition(x + 15, y + 15, x, y, 10, 10, yOffset));
    }
    // Gdx.files throws error in unit test
    @Test
    public void drawImageTester() {
        //screen.drawImageButtonTester("images/MenuButtons/connect.png", 0,0,1920/3);
    }
}
