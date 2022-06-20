package se2.groupb.monopoly;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import se2.groupb.monopoly.network.ClientFoundation;
import se2.groupb.monopoly.screens.MainMenuScreen;


public class Monopoly extends Game {

    private ClientFoundation client;

    public SpriteBatch batch;
    public boolean offlineGamecount;

    public Monopoly(){

   }

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

    public boolean isOfflineGamecount() {
        return offlineGamecount;
    }

    public void setOfflineGamecount(boolean offlineGamecount) {
        this.offlineGamecount = offlineGamecount;
    }
}
