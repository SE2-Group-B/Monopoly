package networkTests;

import com.esotericsoftware.kryonet.Server;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import se2.groupb.monopoly.network.ClientFoundation;
import se2.groupb.monopoly.network.ServerFoundation;

public class ServerFoundationTest {

    ServerFoundation server;
    ClientFoundation client1;
    ClientFoundation client2;
    ClientFoundation client3;
    ClientFoundation client4;

    @Before
    public void setUp() {
        server = new ServerFoundation(6333, 6333);
        client1 = new ClientFoundation(6333, 6333);
        client2 = new ClientFoundation(6333, 6333);

    }

    @After
    public void tearDown() {
        server = null;
        client1 = null;
        client2 = null;
        client3 = null;
        client4 = null;
    }

    @Test
    public void listenerTest() {
        client1.getClient().sendTCP("test");
    }

    @Test
    public void startGameTest() {
        client1.getClient().sendUDP("HOST");
        client3 = new ClientFoundation(6333, 6333);
        client4 = new ClientFoundation(6333, 6333);

        client1.getClient().sendUDP("HOST");
    }

    @Test
    public void getServerTest() {
        Assert.assertEquals(Server.class, server.getServer().getClass());
    }
}
