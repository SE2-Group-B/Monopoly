package se2.groupb.monopoly.network;

/**
 * test class to look if the connection is working
 */
public class ServerClientConnectionMain {
    public static void main(String[] args) {
        ServerFoundation instance = new ServerFoundation();
        ClientFoundation clients = new ClientFoundation();
        ClientFoundation client2 = new ClientFoundation();
        clients.getClient().sendUDP("Hallo ich bin ein Client");
        clients.getClient().close();
        instance.getServer().close();
    }
}
