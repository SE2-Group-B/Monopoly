package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;

import se2.groupb.monopoly.Monopoly;

public class InputProcessors {

    private Monopoly monopoly;

    public InputProcessors(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    public void MainMenuProcessor(){
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

    public void NetworkMenuProcessor(){
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
}
