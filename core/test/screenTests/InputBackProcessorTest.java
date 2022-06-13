package screenTests;

import org.junit.After;
import org.junit.Before;

import se2.groupb.monopoly.Monopoly;
import se2.groupb.monopoly.screens.InputBackProcessor;

public class InputBackProcessorTest {
    InputBackProcessor inputBackProcessor;
    Monopoly monopoly;

    @Before
    public void setUp(){
        monopoly = new Monopoly();
        inputBackProcessor = new InputBackProcessor(monopoly);
    }

    @After
    public void tearDown(){
        monopoly = null;
        inputBackProcessor = null;
    }
    // Gdx input error, how to test
   /* @Test
    public void backNothingTest(){
        inputBackProcessor.backDoesNothingProcessor();
    }*/

    /*@Test
    public void backMainMenuTest(){
        inputBackProcessor.backToMainMenuProcessor();
    }*/

    // connecting works but gdx input fails the test
    /*@Test
    public void hostScreenServerTest(){
        ServerFoundation server = new ServerFoundation(6333,6333);
        ClientFoundation client = new ClientFoundation(6333,6333);

        inputBackProcessor.HostMenuServerProcessor(server.getServer(), client.getClient());
    }*/

    /*@Test
    public void joinScreenServerTest(){
        ServerFoundation server = new ServerFoundation(6333,6333);
        ClientFoundation client = new ClientFoundation(6333,6333);
        inputBackProcessor.JoinMenuServerProcessor(client.getClient());
    }*/
}
