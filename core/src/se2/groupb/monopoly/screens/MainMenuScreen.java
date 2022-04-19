package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;


import se2.groupb.monopoly.Monopoly;

public class MainMenuScreen implements Screen {

    private Monopoly monopoly;

    private Texture exitButton;
    private Texture playButton;
    private Texture joinButton;
    private int buttonSizeX;
    private int buttonSizeY;
    private int yPosInitialButtons;
    private int yPosOffsetButtons;
    private int xPosButtons;


    /**
     * Constructor
     * setup main menu with Monopoly game
     */
    public MainMenuScreen(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    @Override
    public void show() {
        exitButton = new Texture("images/exit_button_inactive.png");
        playButton = new Texture("images/play_button_inactive.png");
        joinButton = new Texture("images/play_button_inactive.png");

        buttonSizeX = Gdx.graphics.getWidth() / 10;
        buttonSizeY = Gdx.graphics.getHeight() / 10;

        yPosInitialButtons = Gdx.graphics.getHeight() / 4;
        xPosButtons = Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 12;
        yPosInitialButtons = Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4;
        yPosOffsetButtons = -Gdx.graphics.getWidth() / 8;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        monopoly.batch.begin();

        // Host Game Button
        monopoly.batch.draw(playButton, xPosButtons, yPosInitialButtons, buttonSizeX, buttonSizeY);

        // Join Game Button
        monopoly.batch.draw(joinButton, xPosButtons, yPosInitialButtons + yPosOffsetButtons, buttonSizeX, buttonSizeY);


        // Exit game Button
        monopoly.batch.draw(exitButton, xPosButtons, yPosInitialButtons + 2*yPosOffsetButtons, buttonSizeX, buttonSizeY);

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
