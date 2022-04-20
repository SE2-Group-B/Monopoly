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
    private Texture sensorButton;
    private int buttonSizeX;
    private int buttonSizeY;
    private float yPosInitialButtons;
    private float yPosOffsetButtons;
    private float xPosButtons;


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
        sensorButton = new Texture("images/play_button_inactive.png");

        buttonSizeX = Gdx.graphics.getWidth() / 10;
        buttonSizeY = Gdx.graphics.getHeight() / 10;

        xPosButtons = (float) (Gdx.graphics.getWidth() / 2D - buttonSizeX / 2D);
        yPosInitialButtons = (float) (Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4D);
        yPosOffsetButtons = (float) (-Gdx.graphics.getWidth() / 8D);
    }

    @Override
    public void render(float delta) {
        float userPosX = (float) Gdx.input.getX();
        float userPosY = (float) Gdx.graphics.getHeight() - Gdx.input.getY();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        monopoly.batch.begin();

        // Host Game Button
        monopoly.batch.draw(playButton, xPosButtons, yPosInitialButtons, buttonSizeX, buttonSizeY);

        // Join Game Button
        monopoly.batch.draw(joinButton, xPosButtons, yPosInitialButtons + yPosOffsetButtons, buttonSizeX, buttonSizeY);

        // Exit game Button
        monopoly.batch.draw(exitButton, xPosButtons, yPosInitialButtons + 2f * yPosOffsetButtons, buttonSizeX, buttonSizeY);

        // Sensor Button
        monopoly.batch.draw(sensorButton, xPosButtons, yPosInitialButtons + 3f * yPosOffsetButtons, buttonSizeX, buttonSizeY);

        /**
         * Pressing the Host Game button leads to HostGameScreen
         */
        if (isCorrectPosition(userPosX, userPosY, xPosButtons, yPosInitialButtons, buttonSizeX, buttonSizeY, 0 * yPosOffsetButtons)
                && (Gdx.input.isTouched() || Gdx.input.isButtonPressed(Input.Buttons.LEFT))) {

            this.dispose();
            monopoly.setScreen(new HostGameScreen(monopoly));

        }

        /**
         * Pressing the Join Game button leads to JoinGameScreen
         */
        if (isCorrectPosition(userPosX, userPosY, xPosButtons, yPosInitialButtons, buttonSizeX, buttonSizeY, 1 * yPosOffsetButtons)
                && (Gdx.input.isTouched() || Gdx.input.isButtonPressed(Input.Buttons.LEFT))) {

            this.dispose();
            monopoly.setScreen(new JoinGameScreen(monopoly));

        }

        /**
         * Pressing the Exit button leads to exiting the game
         */
        if (isCorrectPosition(userPosX, userPosY, xPosButtons, yPosInitialButtons, buttonSizeX, buttonSizeY, 2 * yPosOffsetButtons)
                && (Gdx.input.isTouched() || Gdx.input.isButtonPressed(Input.Buttons.LEFT))) {

            this.dispose();
            Gdx.app.exit();

        }

        /**
         * Pressing the Sensor button leads to the Sensor Screen
         */
        if (isCorrectPosition(userPosX, userPosY, xPosButtons, yPosInitialButtons, buttonSizeX, buttonSizeY, 3 * yPosOffsetButtons)
                && (Gdx.input.isTouched() || Gdx.input.isButtonPressed(Input.Buttons.LEFT))) {

            this.dispose();
            monopoly.setScreen(new SensorScreen(monopoly));

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
    }

    /****************** Methods ******************/

    private static boolean isCorrectPosition(float userPosX, float userPosY, float xPosButton, float yPosButton, float buttonSizeX, float buttonSizeY, float yPosOffset) {
        return (userPosX > xPosButton && userPosX < xPosButton + buttonSizeX && userPosY > (yPosButton + yPosOffset) && userPosY < yPosButton + yPosOffset + buttonSizeY);
    }
}
