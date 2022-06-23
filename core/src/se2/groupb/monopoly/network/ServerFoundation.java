package se2.groupb.monopoly.network;


import com.badlogic.gdx.graphics.Color;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import se2.groupb.monopoly.Player;
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

    private int countPlayers;

    private RoundCounter roundcount = new RoundCounter();
    // public int minigamecount;

    public ServerFoundation() {
        countPlayers = 0;
        random = new Random();
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
            }
        });
    }

    // server initializes the players and sends information to client
    private void initPlayers(int countPlayers) {
        ArrayList<PlayerInformation> players = new ArrayList<>();
        if (countPlayers >= 2 && countPlayers <= 4) {
            this.player1 = new PlayerInformation(new Player(1, "Blue", 1000, new ArrayList<>(), 0, Color.BLUE, false));
            this.player2 = new PlayerInformation(new Player(2, "Red", 1000, new ArrayList<>(), 0, Color.RED, false));
            players.add(player1);
            players.add(player2);
        }
        if (countPlayers >= 3 && countPlayers <= 4) {
            this.player3 = new PlayerInformation(new Player(3, "Yellow", 1000, new ArrayList<>(), 0, Color.YELLOW, false));
            players.add(player3);
        }
        if (countPlayers >= 4) {
            this.player4 = new PlayerInformation(new Player(4, "Green", 1000, new ArrayList<>(), 0, Color.GREEN, false));
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
