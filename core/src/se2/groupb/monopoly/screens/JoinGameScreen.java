package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import se2.groupb.monopoly.Monopoly;

public class JoinGameScreen implements Screen {

    Monopoly monopoly;

    public JoinGameScreen(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    @Override
    public void show() {
        /**
         * instead of closing the App go to Main Menu
         */
        InputProcessors inputProcessor = new InputProcessors(monopoly);
        inputProcessor.NetworkMenuProcessor();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        monopoly.batch.begin();

        monopoly.batch.end();


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
