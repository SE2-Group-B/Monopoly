package se2.groupb.monopoly;


import static se2.groupb.monopoly.screens.GameScreenAdapter.drawImageButton;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;

import se2.groupb.monopoly.network.ClientFoundation;
import se2.groupb.monopoly.network.ServerFoundation;
import se2.groupb.monopoly.screens.GameScreenAdapter;
import se2.groupb.monopoly.screens.InputBackProcessor;
import se2.groupb.monopoly.screens.MainMenuScreen;
import se2.groupb.monopoly.screens.SensorScreen;


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
