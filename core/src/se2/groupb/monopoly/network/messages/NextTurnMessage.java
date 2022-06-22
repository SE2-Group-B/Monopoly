package se2.groupb.monopoly.network.messages;

import java.util.ArrayList;

import se2.groupb.monopoly.PlayerOperation;
import se2.groupb.monopoly.Property;

public class NextTurnMessage {
    private int id;
    private int bankBalance;
    private int position;
    private ArrayList<Property> myProperties;
    private int numOfTrainstations;
    private int nextTurnPlayerID;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(int bankBalance) {
        this.bankBalance = bankBalance;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ArrayList<Property> getMyProperties() {
        return myProperties;
    }

    public void setMyProperties(ArrayList<Property> myProperties) {
        this.myProperties = myProperties;
    }

    public int getNumOfTrainstations() {
        return numOfTrainstations;
    }

    public void setNumOfTrainstations(int numOfTrainstations) {
        this.numOfTrainstations = numOfTrainstations;
    }

    public int getNextTurnPlayerID() {
        return nextTurnPlayerID;
    }

    public void setNextTurnPlayerID(int nextTurnPlayerID) {
        this.nextTurnPlayerID = nextTurnPlayerID;
    }
}
