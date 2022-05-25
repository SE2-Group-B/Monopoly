package se2.groupb.monopoly.network;


import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

public class ServerFoundation {

    private Server server;
    int tcpPort;
    int udpPort;

    private int currentPlayerID = 0;

    public ServerFoundation() {
        System.setProperty("java.net.preferIPv4Stack", "true");
        this.server = new Server(1_000_000, 1_000_000);
        try {
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        setTcpPort(randomizePorts());
        setUdpPort(getTcpPort());

        // register kryo network
        Network.register(server);
        // starting a tcp server connection
        this.bindServer(tcpPort, udpPort);

    }

    private void bindServer(final int tcpPort, final int udpPort) {
        this.server.start();


        try {
            this.server.bind(tcpPort, udpPort);
            System.out.println("Ports opened at: " + tcpPort + " : " + udpPort);
            // System.out.println("Server IP: " + getLocalIpAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Listeners for Client messages
        server.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof String) {
                    System.out.println("\nNumber of Players connected: " + server.getConnections().length);
                    System.out.println("Server received message:\t" + object + "\n");

                    if (object.equals("HOST")) {
                        // start game when 4 Players are connected
                        if (server.getConnections().length == 2) {
                            server.sendToAllTCP("START");
                        } else { // wait for players if not all connected
                            server.sendToAllTCP("WAITFORPLAYER");
                        }
                    }
                }
            }
        });


    }

    private int randomizePorts() {
        int r = new Random().nextInt() % 6000 + 1000;
        if (r < 0) r *= -1;
        return r;
    }

    public void setTcpPort(int tcpPort) {
        this.tcpPort = tcpPort;
    }

    public void setUdpPort(int udpPort) {
        this.udpPort = udpPort;
    }

    public Server getServer() {
        return server;
    }

    public int getTcpPort() {
        return tcpPort;
    }

    public int getUdpPort() {
        return udpPort;
    }
}
