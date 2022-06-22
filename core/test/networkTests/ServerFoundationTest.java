package networkTests;

import com.esotericsoftware.kryonet.Server;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

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
        server = new ServerFoundation();
        client1 = new ClientFoundation(server.getTcpPort(), server.getUdpPort());
        client2 = new ClientFoundation(server.getTcpPort(), server.getUdpPort());
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

    /*@Test

    public void startGameTest1Player() {
        client2.getClient().close();
        client1.getClient().sendUDP("HOST");
        Assert.assertTrue(client1.getClient().isConnected());
        Assert.assertFalse(client2.getClient().isConnected());
    }*/

    /*@Test
    public void startGameTest2Players() {
        client1.getClient().sendUDP("HOST");
        Assert.assertTrue(client1.getClient().isConnected());
    }*/

    /*@Test

    public void startGameTest4Players() {
        client3 = new ClientFoundation(server.getTcpPort(), server.getUdpPort());
        client4 = new ClientFoundation(server.getTcpPort(), server.getUdpPort());
        client1.getClient().sendUDP("HOST");
        Assert.assertTrue(client1.getClient().isConnected());
        Assert.assertTrue(client2.getClient().isConnected());
        Assert.assertTrue(client3.getClient().isConnected());
        Assert.assertTrue(client4.getClient().isConnected());
    }*/

    @Test
    public void startGameTest5Players(){
        client3 = new ClientFoundation(server.getTcpPort(), server.getUdpPort());
        client4 = new ClientFoundation(server.getTcpPort(), server.getUdpPort());
        ClientFoundation client5 = new ClientFoundation(server.getTcpPort(), server.getUdpPort());
        client1.getClient().sendTCP("HOST");
        Assert.assertTrue(client5.getClient().isConnected());
    }

    @Test
    public void getServerTest() {
        Assert.assertEquals(Server.class, server.getServer().getClass());
    }

    @Test
    public void randomizeTest(){
        ServerFoundation server2 = new ServerFoundation();
        Assert.assertNotEquals(server2.getTcpPort(), server.getTcpPort());
    }



}
