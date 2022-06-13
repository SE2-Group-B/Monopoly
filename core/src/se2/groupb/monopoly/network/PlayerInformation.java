package se2.groupb.monopoly.network;

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

    public PlayerInformation(Player player) {
        this.player = player;
    }
}
