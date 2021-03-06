package se2.groupb.monopoly.network;

import com.badlogic.gdx.math.Vector3;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

import se2.groupb.monopoly.PlayerOperation;
import se2.groupb.monopoly.network.messages.NextTurnMessage;
import se2.groupb.monopoly.network.messages.PlayerInformation;
import se2.groupb.monopoly.network.messages.RoundCounter;

public class ClientFoundation {
    private Client client;
    boolean allJoined = false;
    boolean gameEnd = false;
    PlayerInformation player;
    private ArrayList<PlayerInformation> otherPlayers = new ArrayList<>();
    RoundCounter roundCounter;
    private NextTurnMessage nextTurnMessage;
    private Vector3 graphicalPosition;

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
                    handleStringMessages(object.toString());
                }
                if (object instanceof RoundCounter) {
                    roundCounter = (RoundCounter) object;
                }

                if (object instanceof PlayerInformation) {
                    handlePlayerInformationMessages((PlayerInformation) object);
                }
                if(object instanceof NextTurnMessage){
                    handleNextTurnMessage((NextTurnMessage) object);
                }
            }
        });
    }

    private void handleStringMessages(String object) {
        // if 4 Players (Server) connected then server sends message to all clients and starts game automatically
        if (object.equals("START")) {
            allJoined = true;
        } else if (object.equals("WAITINGFORPLAYER")) {
            allJoined = false;
        } else if (object.equals("FINISH")) {
            gameEnd = true;
        }
    }


    private void handlePlayerInformationMessages(PlayerInformation object) {
        if (object.getMessageType().equals("INITIALIZE_GAME")) {
            // Server sends initialization of players
            // then do something
            if (object.getIsPlayer()) {
                player = object;
            } else if (!object.getIsPlayer()) {
                object.getMessageType();
                otherPlayers.add(object);
            }
        }
        if(object.getMessageType().equals("STARTNEXTTURN")){
            for (int i = 0; i < otherPlayers.size(); i++) {
                if(otherPlayers.get(i).getPlayer().getId() == object.getPlayer().getId()){
                    otherPlayers.set(i, object);
                }
            }
        }
    }

    private void handleNextTurnMessage(NextTurnMessage object){
        this.nextTurnMessage = object;
        for (int i = 0; i < otherPlayers.size(); i++) {
            if(otherPlayers.get(i).getPlayer().getId() == object.getId()){
                otherPlayers.get(i).getPlayer().setBankBalance(object.getBankBalance());
                otherPlayers.get(i).getPlayer().setNumOfTrainstaitions(object.getNumOfTrainstations());
                otherPlayers.get(i).getPlayer().setPosition(object.getPosition());
                otherPlayers.get(i).getPlayer().setMyProperties(object.getMyProperties());
                otherPlayers.get(i).getPlayer().setGraphicalPosition(object.getGraphicalPosition());
            }
        }
        System.out.println("Client received from server: " + object.getId());
        System.out.println("Client received from server: " + object.getNextTurnPlayerID());
        System.out.println("Client received from server: " + object.getBankBalance());
        System.out.println("Client received from server: " + object.getPosition());
        System.out.println("Client received from server: " + object.getGraphicalPosition());
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

    public NextTurnMessage getNextTurnMessage(){
        return this.nextTurnMessage;
    }
}
