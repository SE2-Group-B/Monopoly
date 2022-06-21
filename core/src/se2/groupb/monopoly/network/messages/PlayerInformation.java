package se2.groupb.monopoly.network.messages;

import se2.groupb.monopoly.Player;

/**
 * This class is for specifying the player data serverside
 * each instance of this class will be a player
 * insert player data like current position, current money, current properties etc.
 * server gets all data from client and safes it into each PlayerInformation instance
 * server uses the data of this class to send data to clients
 * always update information if something happens in the game
 */
public class PlayerInformation {
    Player player;
    String messageType;
    //boolean for seeing if the player on client is the actual user or the other players
    boolean isPlayer;
    int currentPlayerID;

    public PlayerInformation() {
    }

    public PlayerInformation(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public boolean getIsPlayer() {
        return isPlayer;
    }

    public void setIsPlayer(boolean player) {
        isPlayer = player;
    }

    public int getCurrentPlayerID() {
        return currentPlayerID;
    }

    public void setCurrentPlayerID(int currentPlayerID) {
        this.currentPlayerID = currentPlayerID;
    }
}
