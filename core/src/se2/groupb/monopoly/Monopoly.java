package se2.groupb.monopoly;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import se2.groupb.monopoly.network.ClientFoundation;
import se2.groupb.monopoly.screens.MainMenuScreen;


public class Monopoly extends Game {
    public SpriteBatch batch;
    private ClientFoundation client;
    private boolean offlineGame;


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

    public boolean isOfflineGame() {
        return offlineGame;
    }

    public void setOfflineGame(boolean offlineGame) {
        this.offlineGame = offlineGame;
    }
}
