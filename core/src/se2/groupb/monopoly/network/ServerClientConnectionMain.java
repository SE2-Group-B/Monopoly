package se2.groupb.monopoly.network;

import java.util.Arrays;

/**
 * test class to look if the connection is working
 */
public class ServerClientConnectionMain {

    public static void main(String[] args) {
        ServerFoundation instance = new ServerFoundation(6333, 6333);
        ClientFoundation clients = new ClientFoundation(6333, 6333);
        ClientFoundation client2 = new ClientFoundation(6333, 6333);
        clients.getClient().close();
        for (int i = 0; i < instance.getServer().getConnections().length; i++) {
            System.out.println(Arrays.stream(instance.getServer().getConnections()).toArray()[i]
            );
        }
        clients.getClient().sendUDP("Hallo ich bin ein Client");
        instance.getServer().getConnections();
    }
}
