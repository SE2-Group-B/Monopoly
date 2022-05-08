package se2.groupb.monopoly.network;


import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

import se2.groupb.monopoly.screens.HostGameScreen;
import se2.groupb.monopoly.screens.JoinGameScreen;

public class ServerFoundation {

    private Server server;

    public ServerFoundation(int tcpPort, int udpPort) {
        this.server = new Server(1_000_000, 1_000_000);
        // starting a tcp server connection at port 6333 and udp server at port 6334
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
                }
            }
        });
    }

    public void registerToKryo(){
        Kryo kryo = this.getServer().getKryo();
        // register request and response
        kryo.register(String.class);
        // string was registered
        System.out.println("*\n**\n*****\n***********\n**********************\n"+kryo.getRegistration(String.class)+"\n**********************\n***********\n*****\n**\n*");
    }

    public Server getServer() {
        return server;
    }


}
