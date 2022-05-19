package se2.groupb.monopoly.network;


import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class ServerFoundation {

    private Server server;

    public ServerFoundation(int tcpPort, int udpPort) {
        System.setProperty("java.net.preferIPv4Stack", "true");
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
            System.out.println("Server IP: " + getLocalIpAddress());
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
                        if (server.getConnections().length == 1) {
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

    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
