import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import se2.groupb.monopoly.CreateGameField;
import se2.groupb.monopoly.Monopoly;
import se2.groupb.monopoly.Player;
import se2.groupb.monopoly.Property;



public class CGFTest {
    Player player1;
    Player player2;
    Player player3;
    Player player4;
    ArrayList<Property> arrayList = new ArrayList();
    ArrayList<Property> arrayList2 = new ArrayList();
    ArrayList<Property> arrayList3 = new ArrayList();
    ArrayList<Property> arrayList4 = new ArrayList();
    Monopoly monopoly;
    //CreateGameField createGameField = new CreateGameField(monopoly);

    @Before
    public void init(){
        player1 = new Player(1, "Blue", 2000, arrayList, 0, Color.BLUE);
        player2 = new Player(2, "Red", 2000, arrayList2, 0, Color.RED);
        player3 = new Player(3, "Yellow", 2000, arrayList3, 0, Color.YELLOW);
        player4 = new Player(4, "Green", 2000, arrayList4, 0, Color.GREEN);
    }

    @After
    public void teardown(){player1 = player2 =  player3 = player4 = null;}

    @Test
    public void buyTest(){
        //createGameField.buying();
        Assert.assertEquals(2000, player1.getBankBalance());
    }
}
