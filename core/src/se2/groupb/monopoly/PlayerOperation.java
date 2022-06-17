package se2.groupb.monopoly;

import java.util.ArrayList;

public class PlayerOperation {
    private LogicalGameField logicalGameField;
    private ArrayList<Player> playerList;
    private Player currentPlayer;
    private Pot moneyPot;
    private int currentPlayerId;
    private int playerCount;

    public PlayerOperation(ArrayList<Player> playerList) {
        this.playerList = playerList;
        logicalGameField = new LogicalGameField();
        currentPlayerId = 1;
    }

    public boolean isSomeonesProperty(int position) {
        return logicalGameField.getGameField()[position].getOwnerId() != 0;
    }

    public Player getPropertyOwner(int pos) {
        return getPlayerById(logicalGameField.getGameField()[pos].getOwnerId());
    }

    public Player getCurrentPlayer() {
        return getPlayerById(currentPlayerId);
    }

    public Player getPlayerById(int id) {
        return playerList.get(id - 1);
    }

    public String checkCurrentProperty(Player player) {
        currentPlayer = player;
        int playerPosition = player.getPosition();
        Property p = logicalGameField.getGameField()[playerPosition];
        String output = "Player " + player.getName() + " is on " + p.getName();
        if (p instanceof Street) {
            if (isEnemyProperty(playerPosition)) {
                output = currentPlayer.payToOtherPlayer(getPropertyOwner(playerPosition), ((Street) p).getRent());
            }
        } else if (p instanceof Trainstation) {
            if (isEnemyProperty(playerPosition)) {
                output = currentPlayer.payToOtherPlayer(getPropertyOwner(playerPosition), ((Trainstation) p).getRent() * getPropertyOwner(playerPosition).getNumOfTrainstaitions());
            }
        } else if (p instanceof PenaltyField) {
            output = moneyPot.donateToPot(currentPlayer, ((PenaltyField) p).getPenalty());
        } else {
            output = checkSoleProperty(p);
        }
        return output;
    }

    public boolean isEnemyProperty(int position) {
        return (isSomeonesProperty(position) && (currentPlayer.getId() != getPropertyOwner(position).getId()));
    }

    public String checkSoleProperty(Property property) {
        String output = "Player " + currentPlayer.getName();
        switch (property.getName()) {
            case "Los":
                currentPlayer.changeMoney(200);
                output += " landed directly on GO and earned 400€";
                break;
            case "Gemeinschaftsfeld":
                output += " stepped on a Gemeinschaftsfeld.";
//                kartenHintergrund = getCurrentPlayer().drawCard(gemeinschaftskartenDeck);
//                showCard = true;
                break;
            case "Ereignisfeld":
                output += " stepped on a Ereignisfeld.";
//                kartenHintergrund = getCurrentPlayer().drawCard(ereigniskartenDeck);
//                showCard = true;
                break;
            case "Gefängnis":
                if (currentPlayer.getPrison()) {
                    int hefn = 4 - currentPlayer.getPrisonCount();
                    output += " sits " + hefn + " more rounds in prison";
                } else {
                    output += " is just visiting the prison";
                }
                break;
            case "Sofa":
                output = moneyPot.winPot(currentPlayer);
                break;
            case "Gehe ins Gefängnis":
//                currentPlayer.move(gameField.positions[10]);
                currentPlayer.goToJail();
                output += " went to prison";
                break;
        }
        return output;
    }

    public String nextPlayer() {
        if (currentPlayer.getId() == playerCount) {
            currentPlayerId = 1;
        } else {
            currentPlayerId++;
        }
        return "It's " + getCurrentPlayer().getName() + "'s turn now";
    }

    public void setMoneyPotForOperation(Pot pot) {
        this.moneyPot = pot;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public String buying() {
        int playerPosition = getCurrentPlayer().getPosition();
        Property p = logicalGameField.getGameField()[playerPosition];
        String output = "Player " + getCurrentPlayer().getName();
        if (!isSomeonesProperty(playerPosition)) {
            if (p instanceof Street) {
                logicalGameField.getGameField()[playerPosition].setOwnerId(getCurrentPlayer().getId());
                output += " bought " + p.getName() + " for " + ((Street) p).getPrice() + "€";
                getCurrentPlayer().changeMoney(-((Street) p).getPrice());
            } else if (p instanceof Trainstation) {
                logicalGameField.getGameField()[playerPosition].setOwnerId(getCurrentPlayer().getId());
                getCurrentPlayer().setNumOfTrainstaitions(getCurrentPlayer().getNumOfTrainstaitions()+1);
                output += " bought " + p.getName() + " for " + ((Trainstation) p).getPrice() + "€";;
                getCurrentPlayer().changeMoney(-((Trainstation) p).getPrice());
            } else {
                output = "You can't buy this Property";
            }
        }else {
            output = "You can't buy this Property";
        }
        return output;
    }
}
