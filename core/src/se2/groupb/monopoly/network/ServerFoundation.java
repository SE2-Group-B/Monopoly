package se2.groupb.monopoly.network;


import com.badlogic.gdx.graphics.Color;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import se2.groupb.monopoly.Player;
import se2.groupb.monopoly.PlayerOperation;
import se2.groupb.monopoly.network.messages.NextTurnMessage;
import se2.groupb.monopoly.network.messages.PlayerInformation;
import se2.groupb.monopoly.network.messages.RoundCounter;

public class ServerFoundation {

    private Server server;
    int tcpPort;
    int udpPort;
    Random random;

    private PlayerInformation player1;
    private PlayerInformation player2;
    private PlayerInformation player3;
    private PlayerInformation player4;
    private ArrayList<PlayerInformation> players;

    private int countPlayers;

    // send the player who is allowed to move to clients
    private int currentPlayerID;

    private RoundCounter roundcount = new RoundCounter();
    // public int minigamecount;

    public ServerFoundation() {
        this.currentPlayerID = 1;
        this.countPlayers = 0;
        this.random = new Random();
        System.setProperty("java.net.preferIPv4Stack", "true");
        this.server = new Server(1_000_000, 1_000_000);

        setTcpPort(randomInt());
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Listeners for Client messages
        server.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof String) {
                    handleStringMessage(object.toString());
                }
                if (object instanceof RoundCounter) {
                    if (((RoundCounter) object).getRoundcount() >= 0 && ((RoundCounter) object).getRoundcount() <= 8 && ((RoundCounter) object).getRoundcount() > roundcount.getRoundcount()) {
                        roundcount = (RoundCounter) object;
                        server.sendToAllTCP(roundcount);
                    } else if (((RoundCounter) object).getRoundcount() >= 9) {
                        server.sendToAllTCP("FINISH");
                    }
                }
                if (object instanceof PlayerInformation) {
                    handlePlayerInformationMessage((PlayerInformation) object);
                }
                if(object instanceof NextTurnMessage){
                    handleNextTurnMessage((NextTurnMessage) object);
                }
            }
        });
    }

    // server initializes the players and sends information to client
    private void initPlayers(int countPlayers) {
        players = new ArrayList<>();
        if (countPlayers >= 2 && countPlayers <= 4) {
            this.player1 = new PlayerInformation(new Player(1, "Blue", 1000, new ArrayList<>(), 0, Color.BLUE));
            this.player2 = new PlayerInformation(new Player(2, "Red", 1000, new ArrayList<>(), 0, Color.RED));
            this.player1.setCurrentPlayerID(this.currentPlayerID);
            this.player2.setCurrentPlayerID(this.currentPlayerID);
            players.add(player1);
            players.add(player2);
        }
        if (countPlayers >= 3 && countPlayers <= 4) {
            this.player3 = new PlayerInformation(new Player(3, "Yellow", 1000, new ArrayList<>(), 0, Color.YELLOW));
            this.player3.setCurrentPlayerID(this.currentPlayerID);
            players.add(player3);
        }
        if (countPlayers >= 4) {
            this.player4 = new PlayerInformation(new Player(4, "Green", 1000, new ArrayList<>(), 0, Color.GREEN));
            this.player4.setCurrentPlayerID(this.currentPlayerID);
            players.add(player4);
        }

        if (countPlayers >= 2 && countPlayers <= 4) {
            sendPlayerInformation(players, "INITIALIZE_GAME");
        }
    }

    // send specific PlayerInformation (player) to corresponding client
    // we are just sending a playerInformation message, we must later specify message so client knows what to do
    // we can do that if we add a variable in PlayerInformation class, so message is unique
    //      -> eg. PlInfo: public String messageType, SerFound: messageType = "INIT"; Client: if messageType.equals("INIT") do ....
    public void sendPlayerInformation(ArrayList<PlayerInformation> players, String messageType) {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setMessageType(messageType);
        }

        if (messageType.equals("INITIALIZE_GAME") && !players.isEmpty()) {
            for (int i = 0; i < players.size(); i++) {
                players.get(i).setIsPlayer(true);
                server.sendToTCP(i + 1, players.get(i));
                System.out.println("server sends currentPlayer: " + players.get(i).getCurrentPlayerID() + " to client");
                for (int j = 0; j < players.size(); j++) {
                    if (j != i) {
                        players.get(i).setIsPlayer(false);
                        server.sendToTCP(i + 1, players.get(j));
                    }
                }
            }
        }
    }

    private void handleStringMessage(String object) {
        System.out.println("server got message: " + object);
        if (object.equals("HOST")) {
            // start game when 2-4 Players are connected
            if (server.getConnections().length >= 2 && server.getConnections().length <= 4) {
                countPlayers = server.getConnections().length;
                initPlayers(countPlayers);
                sendcount(roundcount);
                server.sendToAllTCP("START");
            } else { // wait for players if not all connected
                server.sendToAllTCP("WAITFORPLAYER");
            }
        }
    }

    private void handleNextTurnMessage(NextTurnMessage object){
        System.out.println("server received message: " + object.getId());
        System.out.println("server received message: " + object.getBankBalance());
        System.out.println("server received message: " + object.getPosition());
        incrementCurrentPlayer(countPlayers);
        object.setNextTurnPlayerID(getCurrentPlayerID());
        System.out.println("CurrentPlayerID: " + object.getNextTurnPlayerID());
        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).getPlayer().getId() == object.getId()){
                players.get(i).getPlayer().setBankBalance(object.getBankBalance());
                players.get(i).getPlayer().setNumOfTrainstaitions(object.getNumOfTrainstations());
                players.get(i).getPlayer().setPosition(object.getPosition());
                players.get(i).getPlayer().setMyProperties(object.getMyProperties());
            }
        }

        server.sendToAllTCP(object);
    }

    private void handlePlayerInformationMessage(PlayerInformation object) {
        System.out.println("server received message: " + object.getMessageType());
        if (object.getMessageType().equals("NEXTTURN")) {
            System.out.println("server got message :" + object.getPlayer().getName());
            incrementCurrentPlayer(countPlayers);
            if (countPlayers > 1 && countPlayers <= 4) {
                if (object.getPlayer().getId() == player1.getPlayer().getId()) {
                    player1 = object;
                    player1.setCurrentPlayerID(getCurrentPlayerID());
                    player1.setMessageType("STARTNEXTTURN");
                }
                if (object.getPlayer().getId() == player2.getPlayer().getId()) {
                    player2 = object;
                    player2.setCurrentPlayerID(getCurrentPlayerID());
                    player2.setMessageType("STARTNEXTTURN");
                }
            }
            if (countPlayers > 2 && countPlayers <= 4) {
                if (object.getPlayer().getId() == player3.getPlayer().getId()) {
                    player3 = object;
                    player3.setCurrentPlayerID(getCurrentPlayerID());
                    player3.setMessageType("STARTNEXTTURN");
                }
            }
            if (countPlayers > 3 && countPlayers <= 4) {
                if (object.getPlayer().getId() == player4.getPlayer().getId()) {
                    player4 = object;
                    player4.setCurrentPlayerID(getCurrentPlayerID());
                    player4.setMessageType("STARTNEXTTURN");
                }
            }
            server.sendToAllTCP(object);
        }
    }

    public int randomInt() {
        int r = random.nextInt() % 6000 + 1000;
        if (r < 0) r *= -1;
        return r;
    }

    public void sendcount(RoundCounter round) {
        server.sendToAllTCP(roundcount);
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

    // method to increment the currentPlayerID after a player has finished the move
    public void incrementCurrentPlayer(int countPlayers) {
        if (countPlayers == 2) {
            checkPlayerIDForMoves(countPlayers);
        }
        if (countPlayers == 3) {
            checkPlayerIDForMoves(countPlayers);
        }
        if (countPlayers == 4) {
            checkPlayerIDForMoves(countPlayers);
        }
    }

    public void checkPlayerIDForMoves(int countPlayers) {
        if (this.currentPlayerID == countPlayers) {
            this.currentPlayerID = 1;
        } else this.currentPlayerID++;
    }

    public int getCurrentPlayerID() {
        return currentPlayerID;
    }

    /************ Players ************/

    public PlayerInformation getPlayer1() {
        return player1;
    }

    public PlayerInformation getPlayer2() {
        return player2;
    }

    public PlayerInformation getPlayer3() {
        return player3;
    }

    public PlayerInformation getPlayer4() {
        return player4;
    }
}
