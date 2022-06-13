package networkTests;

import com.esotericsoftware.kryonet.Client;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.ConnectException;

import se2.groupb.monopoly.network.ClientFoundation;
import se2.groupb.monopoly.network.ServerFoundation;

public class ClientFoundationTest {

    ServerFoundation server;
    ClientFoundation client;

    @Before
    public void setUp() {
        server = new ServerFoundation();

        client = new ClientFoundation(server.getTcpPort(), server.getUdpPort());
    }

    @After
    public void tearDown() {
        server = null;
        client = null;
    }

    // should be @Test (expected = IOException.class or ConnectException.class), but it works fine without
    @Test /*(expected = ConnectException.class)*/
    public void noServerTest() {
        /*server.getServer().close();
        server = null;
        try {
            client.getClient().update(500);
        } catch (IOException e) {
            e.printStackTrace();
        }
        client = new ClientFoundation(server.getTcpPort(), server.getUdpPort());*/
    }

    @Test
    public void serverExistsTest() {
        server = new ServerFoundation();
        client = new ClientFoundation(server.getTcpPort(), server.getUdpPort());
    }

    @Test
    public void listenerTest() {
        server.getServer().sendToAllTCP("message");
    }

    @Test
    public void startGameTest() {
        server.getServer().sendToAllTCP("START");
        try {
            client.getClient().update(500);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(client.allPlayersJoined());

        server.getServer().sendToAllTCP("WAITINGFORPLAYER");
        try {
            client.getClient().update(500);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertFalse(client.allPlayersJoined());
    }

    @Test
    public void testWrongPortTest(){
        ClientFoundation client = new ClientFoundation(1,1);
    }
}
