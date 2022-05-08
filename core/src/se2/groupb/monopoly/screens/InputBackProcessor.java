package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Server;

import se2.groupb.monopoly.Monopoly;

public class InputBackProcessor {

    private Monopoly monopoly;

    public InputBackProcessor(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    /**
     * Handling the back button of the phone
     * instead of closing the App do nothing
     */
    public void backDoesNothingProcessor() {
        InputProcessor backProcessor = new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if ((keycode == Input.Keys.BACK) || (keycode == Input.Keys.ESCAPE)) {
                    // do nothing
                }
                return false;
            }
        };
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
        Gdx.input.setInputProcessor(backProcessor);
    }

    /**
     * Handling the back button of the phone
     * instead of closing the App go to Main Menu
     */
    public void backToMainMenuProcessor() {
        InputProcessor backProcessor = new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if ((keycode == Input.Keys.BACK) || (keycode == Input.Keys.ESCAPE)) {
                    monopoly.setScreen(new MainMenuScreen(monopoly));
                }
                return false;
            }
        };
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
        Gdx.input.setInputProcessor(backProcessor);
    }

    /**
     * Handling the back button of the phone
     * instead of closing the App go to Main Menu from Host menu
     * disconnect the server and disconnect the client
     * TO-DO unregister from Kryo
     */
    public void HostMenuServerProcessor(final Server server, final Client client) {
        InputProcessor backProcessor = new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if ((keycode == Input.Keys.BACK) || (keycode == Input.Keys.ESCAPE)) {
                    monopoly.setScreen(new MainMenuScreen(monopoly));
                    /*server.close();
                    client.close();*/
                }
                return false;
            }
        };
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
        Gdx.input.setInputProcessor(backProcessor);
    }

    /**
     * Handling the back button of the phone
     * instead of closing the App go to Main Menu from Host menu
     * disconnect the client
     * TO-DO unregister from Kryo
     */
    public void JoinMenuServerProcessor(final Client client) {
        InputProcessor backProcessor = new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if ((keycode == Input.Keys.BACK) || (keycode == Input.Keys.ESCAPE)) {
                    monopoly.setScreen(new MainMenuScreen(monopoly));
                    //client.close();
                }
                return false;
            }
        };
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
        Gdx.input.setInputProcessor(backProcessor);
    }
}
