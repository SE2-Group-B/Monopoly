package se2.groupb.monopoly;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import se2.groupb.monopoly.network.ClientFoundation;
import se2.groupb.monopoly.network.ServerFoundation;
import se2.groupb.monopoly.screens.MainMenuScreen;
import se2.groupb.monopoly.screens.SensorScreen;


public class Monopoly extends Game {
    public SpriteBatch batch;
    Property[] arr;
    SensorScreen sensors;
    int[] diceRoll;
    private ClientFoundation client;
    int[] sums = new int[4];
    String[] placement = new String[4];
    private boolean offlineGame;

    /**
     * for later, when online game can be finished
     * do: add server from host
     * when player won close clients and server and return to main menu
     * addServer(ServerFoundation)
     * closeServer()
     */
    /*private ServerFoundation server;*/



    @Override
    public void create() {
        offlineGame = false;
        batch = new SpriteBatch();
        setScreen(new MainMenuScreen(this));
    }

//	public static Monopoly INSTANCE;
//
//	public Monopoly() {
//		if (INSTANCE == null) { INSTANCE = this; }
//	}


    @Override
    public void render() {
        super.render();

    }

    @Override
    public void dispose() {
        batch.dispose();

    }

    public void addClient(ClientFoundation client){
        this.client = client;
    }

    public ClientFoundation getClient(){
        return this.client;
    }

    public void removeClient(){
        this.client = null;
    }

    public int[] getSums() {
        return sums;
    }

    public void setSums(int[] sums) {
        this.sums = sums;
    }

    public String[] getPlacement() {
        return placement;
    }

    public void setPlacement(String[] placement) {
        this.placement = placement;
    }

    public boolean isOfflineGame() {
        return offlineGame;
    }

    public void setOfflineGame(boolean offlineGame) {
        this.offlineGame = offlineGame;
    }
}
