package networkTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se2.groupb.monopoly.network.ClientFoundation;
import se2.groupb.monopoly.network.Network;
import se2.groupb.monopoly.network.ServerFoundation;

public class NetworkTest {

    Network network;
    ClientFoundation client;
    ServerFoundation server;

    @Before
    public void setUp() {
        network = new Network();
        server = new ServerFoundation(6333, 6333);
        client = new ClientFoundation(6333, 6333);
    }

    @After
    public void tearDown() {
        network = null;
        server = null;
        client = null;
    }

    @Test
    public void registerTest() {
        Network.register(server.getServer());
        Network.register(client.getClient());
    }
}
