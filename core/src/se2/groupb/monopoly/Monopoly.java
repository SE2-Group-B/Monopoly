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
}
