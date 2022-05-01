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

    public void MainMenuProcessor() {
        /**
         * Handling the back button of the phone
         * instead of closing the App do nothing
         */
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

    public void JoinMenuProcessor() {
        /**
         * Handling the back button of the phone
         * instead of closing the App go to Main Menu
         */
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

    public void HostMenuProcessor() {
        /**
         * Handling the back button of the phone
         * instead of closing the App go to Main Menu
         */
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

    public void HostMenuServerProcessor(final Server server, final Client client) {
        /**
         * Handling the back button of the phone
         * instead of closing the App go to Main Menu
         */
        InputProcessor backProcessor = new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if ((keycode == Input.Keys.BACK) || (keycode == Input.Keys.ESCAPE)) {
                    monopoly.setScreen(new MainMenuScreen(monopoly));
                    server.close();
                    client.close();
                }
                return false;
            }
        };
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
        Gdx.input.setInputProcessor(backProcessor);
    }
}
