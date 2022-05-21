package se2.groupb.monopoly.network;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class ClientFoundation {
    private Client client;
    boolean allJoined = false;

    public ClientFoundation(int tcpPort, int udpPort) {
        System.setProperty("java.net.preferIPv4Stack", "true");
        this.client = new Client();
        InetAddress ip;
        boolean serverExists = false;

        System.out.println("Client IP: " + getLocalIpAddress());

        ip = client.discoverHost(6333, 5000);
        System.out.println("Host discovered: " + ip);

        for (int i = 0; i < 5; i++) {
            if (ip != null) {
                serverExists = true;
                break;
            }

            if (ip != null) break;
            try {
                Thread.currentThread().sleep(1000);
                ip = client.discoverHost(6333, 5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // discover network connections
        /*for (int i = 0; i < 1000000; i++) {

            if (ip != null) break;
            try{
                Thread.currentThread().sleep(1000);
                Enumeration interfaces = NetworkInterface.getNetworkInterfaces();
                while (interfaces.hasMoreElements()){
                   NetworkInterface n = (NetworkInterface) interfaces.nextElement();
                    System.out.println(n.getDisplayName());
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        }*/


        if (serverExists) {
            Network.register(client);
            startConnection(client, ip, tcpPort, udpPort, 100);
        }
    }

    private void startConnection(Client client, InetAddress host, int tcpPort, int udpPort, int maxBlockingTime) {
        client.start();
        try {
            client.connect(maxBlockingTime, host, tcpPort, udpPort);
        } catch (IOException e) {
            e.printStackTrace();
        }

        client.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof String) {
                    System.out.println("\nClient received message:\t" + object + "\n");
                    // if 4 Players (Server) connected then server sends message to all clients and starts game automatically
                    if (object.equals("START")) {
                        allJoined = true;
                    } else if (object.equals("WAITINGFORPLAYER")) {
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
    public boolean allPlayersJoined() {
        return allJoined;
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
