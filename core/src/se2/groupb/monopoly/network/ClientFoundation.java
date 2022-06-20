package se2.groupb.monopoly.network;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

import se2.groupb.monopoly.network.messages.PlayerInformation;
import se2.groupb.monopoly.network.messages.RoundCounter;

public class ClientFoundation {
    private Client client;
    boolean allJoined = false;
    boolean gameEnd = false;
    PlayerInformation player;
    private ArrayList<PlayerInformation> otherPlayers = new ArrayList<>();
    RoundCounter roundCounter;

    public ClientFoundation(int tcpPort, int udpPort) {
        System.setProperty("java.net.preferIPv4Stack", "true");
        this.client = new Client();
        InetAddress ip = null;
        boolean serverExists = false;

        for (int i = 0; i < 5; i++) {
            if (ip != null) {
                serverExists = true;
                break;
            }
            try {
                ip = client.discoverHost(udpPort, 500);
                if (ip != null) Gdx.app.log("connected on host ", ip.toString());
                else Gdx.app.log("not connected ", "No host discovered!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


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
                    handleStringMessages(object.toString());
                }
                if (object instanceof RoundCounter) {
                    roundCounter = (RoundCounter) object;
                }

                if (object instanceof PlayerInformation) {
                    System.out.println("Client received message: " + ((PlayerInformation) object).getMessageType());
                    handlePlayerInformationMessages((PlayerInformation) object);
                }
            }
        });
    }

    private void handleStringMessages(String object){
        if (object.equals("START")) {
            allJoined = true;
        } else if (object.equals("WAITINGFORPLAYER")) {
            allJoined = false;
        } else if (object.equals("FINISH")) {
            gameEnd = true;
        }
    }

    private void handlePlayerInformationMessages(PlayerInformation object){
        if (((PlayerInformation) object).getMessageType().equals("INITIALIZE_GAME")) {
            // Server sends initialization of players
            // then do something
            if (((PlayerInformation) object).getIsPlayer()) {
                player = (PlayerInformation) object;
            } else if (!((PlayerInformation) object).getIsPlayer()) {
                ((PlayerInformation) object).getMessageType();
                otherPlayers.add((PlayerInformation) object);
            }
        }
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

    public PlayerInformation getPlayer() {
        return player;
    }

    public ArrayList<PlayerInformation> getOtherPlayers() {
        return otherPlayers;
    }

    public RoundCounter getRoundCounter() {
        return roundCounter;
    }
}
