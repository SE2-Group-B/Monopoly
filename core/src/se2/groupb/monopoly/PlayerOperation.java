package se2.groupb.monopoly;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class PlayerOperation {
    private LogicalGameField logicalGameField;
    private ArrayList<Player> playerList;
    private Pot moneyPot;
    private int currentPlayerId;
    private int playerCount;
    private Texture cardBackground;
    private Deck communityCards;
    private Deck eventCards;
    private boolean showCard;
    private boolean bought;
    private String playerString;

    public PlayerOperation() {
        /**
         * not used
         */
    }

    public PlayerOperation(ArrayList<Player> playerList) {
        this.playerList = playerList;
        logicalGameField = new LogicalGameField();
        currentPlayerId = 1;
        this.playerCount = playerList.size();
        this.playerString = "Player " + getCurrentPlayer().getName();
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
        int playerPosition = player.getPosition();
        Property p = logicalGameField.getGameField()[playerPosition];
        String output = playerString + " is on " + p.getName();
        if (p instanceof Street) {
            if (isEnemyProperty(playerPosition)) {
                output = getCurrentPlayer().payToOtherPlayer(getPropertyOwner(playerPosition), ((Street) p).getRent());
            }
        } else if (p instanceof Trainstation) {
            if (isEnemyProperty(playerPosition)) {
                output = getCurrentPlayer().payToOtherPlayer(getPropertyOwner(playerPosition), ((Trainstation) p).getRent() * getPropertyOwner(playerPosition).getNumOfTrainstaitions());
            }
        } else if (p instanceof PenaltyField) {
            output = moneyPot.donateToPot(getCurrentPlayer(), ((PenaltyField) p).getPenalty());
            System.out.println(output);
        } else {
            output = checkSoleProperty(p);
        }
        return output;
    }

    public boolean isEnemyProperty(int position) {
//        for(Player player : playerList){
//            if(player.getId() != getCurrentPlayer().getId()){
//                for(Property p : player.getMyProperties()){
//                    if(p.getName().equals(logicalGameField.getGameField()[position].getName())){
//                        return true;
//                    }
//                }
//            }
//        }
//
//        return false;
        return (isSomeonesProperty(position) && (getCurrentPlayer().getId() != getPropertyOwner(position).getId()));
    }

    public String checkSoleProperty(Property property) {
        String output = playerString;
        switch (property.getName()) {
            case "Los":
                getCurrentPlayer().changeMoney(400);
                output += " landed directly on GO and earned 400$";
                break;
            case "Gemeinschaftsfeld":
                output += " stepped on a community field";
                cardBackground = getCurrentPlayer().drawCard(communityCards);
                showCard = true;
                break;
            case "Ereignisfeld":
                output += " stepped on an event field";
                cardBackground = getCurrentPlayer().drawCard(eventCards);
                showCard = true;
                break;
            case "Gefängnis":
                if (getCurrentPlayer().getPrison()) {
                    int hefn = 4 - getCurrentPlayer().getPrisonCount();
                    output += " sits " + hefn + " more rounds in prison";
                } else {
                    output += " is just visiting the prison";
                }
                break;
            case "Sofa":
                output = moneyPot.winPot(getCurrentPlayer());
                break;
            case "Gehe ins Gefängnis":
                getCurrentPlayer().goToJail();
                output += " went to prison";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + property.getName());
        }
        return output;
    }

    public String nextPlayer() {
        if (getCurrentPlayer().getId() == playerCount) {
            currentPlayerId = 1;
        } else {
            currentPlayerId++;
        }

        return "It's " + getCurrentPlayer().getName() + "'s turn now";
    }

    public void setMoneyPotForOperation(Pot pot) {
        this.moneyPot = pot;
    }

    public String buying() {
        int playerPosition = getCurrentPlayer().getPosition();
        Property p = logicalGameField.getGameField()[playerPosition];
        String output = playerString + " bought ";
        if (!isSomeonesProperty(playerPosition)) {
            if (p instanceof Street) {
                if (((Street) p).getPrice() < getCurrentPlayer().getBankBalance()) {
                    bought = true;
                    logicalGameField.getGameField()[playerPosition].setOwnerId(getCurrentPlayer().getId());
                    output += p.getName() + " for " + ((Street) p).getPrice() + "€";
                    getCurrentPlayer().changeMoney(-((Street) p).getPrice());
                } else {
                    output += "nothing because he is too poor for that building";
                }
            } else if (p instanceof Trainstation) {
                if (((Trainstation) p).getPrice() < getCurrentPlayer().getBankBalance()) {
                    bought = true;
                    logicalGameField.getGameField()[playerPosition].setOwnerId(getCurrentPlayer().getId());
                    getCurrentPlayer().setNumOfTrainstaitions(getCurrentPlayer().getNumOfTrainstaitions() + 1);
                    output += p.getName() + " for " + ((Trainstation) p).getPrice() + "€";
                    getCurrentPlayer().changeMoney(-((Trainstation) p).getPrice());
                } else {
                    output += "nothing because you have no money for this station";
                }
            } else {
                bought = false;
                output = "You can't buy this Property";
            }
        } else if(isSomeonesProperty(playerPosition)){
            if (p instanceof Street) {
                if (((Street) p).getPrice() < getCurrentPlayer().getBankBalance()) {
                    bought = true;
                    int price = ((Street) p).getHouse() * ((Street) p).getHousePrice();
                    getCurrentPlayer().payToOtherPlayer(getPropertyOwner(playerPosition),-((Street) p).getPrice()+ price);
                    logicalGameField.getGameField()[playerPosition].setOwnerId(getCurrentPlayer().getId());
                    output += p.getName() + " for " + ((Street) p).getPrice() + "€";
                } else {
                    output += "nothing because he is too poor for that building";
                }
            }
        } else if (getCurrentPlayer().getId() == p.getOwnerId()) {
            output = "Du hast was zugekauft";
            ((Street) p).buyhouse();
        } else {
            bought = false;
            output = "You can't buy this Property";
        }
        return output;
    }

    public void setCommunityCards(Deck communityCards) {
        this.communityCards = communityCards;
    }

    public void setEventCards(Deck eventCards) {
        this.eventCards = eventCards;
    }

    public Texture getCardTexture() {
        return this.cardBackground;
    }

    public boolean getCardBoolean() {
        return this.showCard;
    }

    public void setCardBoolean(boolean showCard) {
        this.showCard = showCard;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public void setCurrentPlayerId(int currentPlayerId) {
        this.currentPlayerId = currentPlayerId;
    }
}
