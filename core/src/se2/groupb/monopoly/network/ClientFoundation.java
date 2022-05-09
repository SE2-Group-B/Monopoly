package se2.groupb.monopoly.network;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

public class ClientFoundation {
    private Client client;
    boolean allJoined = false;

    public ClientFoundation(int tcpPort, int udpPort) {
        this.client = new Client();
        Network.register(client);
        startConnection(client, "localhost", tcpPort, udpPort, 100);
    }

    private void startConnection(Client client, String host, int tcpPort, int udpPort, int maxBlockingTime) {
        client.start();
        try {
            client.connect(maxBlockingTime, host, tcpPort, udpPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (object instanceof String) {
                    System.out.println("\nClient received message:\t" + object + "\n");
                    // if 4 Players (Server) connected then server sends message to all clients and starts game automatically
                    if (object.equals("START")) {
                        allJoined = true;
                    } else if (object.equals("WAITINGFORPLAYER")){
                        allJoined = false;
                    }
                }
            }
        });
    }

    public Client getClient() {
        return client;
    }

    /**
     * Getter Method to use in screens to see if all players joined
     */
    public boolean allPlayersJoined(){
        return allJoined;
    }


}
