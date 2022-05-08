package se2.groupb.monopoly.network;


import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.util.Arrays;

import se2.groupb.monopoly.screens.HostGameScreen;
import se2.groupb.monopoly.screens.JoinGameScreen;

public class ServerFoundation {

    private Server server;

    public ServerFoundation(int tcpPort, int udpPort) {
        this.server = new Server(1_000_000, 1_000_000);
        // register kryo network
        Network.register(server);
        // starting a tcp server connection
        this.bindServer(tcpPort, udpPort);
    }

    private void bindServer(final int tcpPort, final int udpPort) {
        this.server.start();

        try {
            this.server.bind(tcpPort, udpPort);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Listeners for Client messages
        server.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (object instanceof String) {
                    System.out.println("\nServer received message:\t" + object + "\n");

                    if (object.equals("HOST")){
                        // start game when 4 Players are connected
                        if (server.getConnections().length == 1){
                            server.sendToAllTCP("START");
                        } else { // wait for players if not all connected
                            server.sendToAllTCP("WAITFORPLAYER");
                        }
                    }
                }




            }
        });


    }

    public Server getServer() {
        return server;
    }


}
