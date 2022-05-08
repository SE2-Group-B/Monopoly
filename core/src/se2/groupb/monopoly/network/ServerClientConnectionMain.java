package se2.groupb.monopoly.network;

/**
 * test class to look if the connection is working
 */
public class ServerClientConnectionMain {
    public static void main(String[] args) {
        ServerFoundation instance = new ServerFoundation(6333,6333);
        ClientFoundation clients = new ClientFoundation(6333,6333);
        ClientFoundation client2 = new ClientFoundation(6333,6333);
        clients.getClient().sendUDP("Hallo ich bin ein Client");
    }
}
