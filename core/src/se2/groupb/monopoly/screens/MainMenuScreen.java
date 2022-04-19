package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
    private float yPosInitialButtons;
    private float yPosOffsetButtons;
    private float xPosButtons;

    private float userPosX;
    private float userPosY;

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
        joinButton = new Texture("images/play_button_active.png");

        buttonSizeX = Gdx.graphics.getWidth() / 10;
        buttonSizeY = Gdx.graphics.getHeight() / 10;

        xPosButtons = (float) (Gdx.graphics.getWidth() / 2D - buttonSizeX / 2D);
        yPosInitialButtons = (float) (Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4D);
        yPosOffsetButtons = (float) (-Gdx.graphics.getWidth() / 8D);
    }

    @Override
    public void render(float delta) {
        userPosX = (float) Gdx.input.getX();
        userPosY = (float) Gdx.graphics.getHeight() - Gdx.input.getY();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        monopoly.batch.begin();

        // Host Game Button
        monopoly.batch.draw(playButton, xPosButtons, yPosInitialButtons, buttonSizeX, buttonSizeY);

        // Join Game Button
        monopoly.batch.draw(joinButton, xPosButtons, yPosInitialButtons + yPosOffsetButtons, buttonSizeX, buttonSizeY);

        // Exit game Button
        monopoly.batch.draw(exitButton, xPosButtons, yPosInitialButtons + 2f * yPosOffsetButtons, buttonSizeX, buttonSizeY);

        // Pressing the buttons leads to different screens
        // borders of host button
        if (userPosX > xPosButtons && userPosX < xPosButtons + buttonSizeX && userPosY > yPosInitialButtons && userPosY < yPosInitialButtons + buttonSizeY) {
            /**
             * if area is touched go into game
             * TODO:
             * make screen for hosting a game and let players go into online multiplayer
             */
            if (Gdx.input.isTouched() || Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                this.dispose();
                monopoly.setScreen(new MonopolyScreen(monopoly));
            }
        }

        //borders of join button
        if (userPosX > xPosButtons && userPosX < xPosButtons + buttonSizeX && userPosY > yPosInitialButtons + yPosOffsetButtons && userPosY < yPosInitialButtons + yPosOffsetButtons + buttonSizeY) {
            /**
             * TODO:
             * make screen for joining a game via code
             */
            if (Gdx.input.isTouched() || Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                monopoly.batch.draw(new Texture("images/badlogic.jpg"), xPosButtons, (float)(yPosInitialButtons + yPosOffsetButtons));
            }
        }

        //borders of exit button if clicked then exit app
        if (userPosX > xPosButtons && userPosX < xPosButtons + buttonSizeX && userPosY > (float)(yPosInitialButtons + 2 * yPosOffsetButtons) && userPosY < yPosInitialButtons + 2 * yPosOffsetButtons + buttonSizeY) {
            if (Gdx.input.isTouched() || Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                this.dispose();
                Gdx.app.exit();
            }
        }

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
        exitButton.dispose();
        playButton.dispose();
        joinButton.dispose();
    }
}
