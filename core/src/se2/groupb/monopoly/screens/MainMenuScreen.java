package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;


import se2.groupb.monopoly.CreateGameField;
import se2.groupb.monopoly.Monopoly;

public class MainMenuScreen extends GameScreenAdapter {


    private Texture exitButton;
    private Texture playButton;
    private Texture joinButton;
    private Texture sensorButton;
    private Texture kartenButton;
    private Texture gameFieldButton;
    private Texture BuyButton;

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
        super(monopoly);
    }

    @Override
    public void show() {
        exitButton = new Texture("images/MenuButtons/exit.png");
        playButton = new Texture("images/MenuButtons/host.png");
        joinButton = new Texture("images/MenuButtons/join.png");
        kartenButton = new Texture("images/MenuButtons/switch_view.png");
        sensorButton = new Texture("images/MenuButtons/switch_view.png");
        gameFieldButton=new Texture("images/MenuButtons/switch_view.png");
        BuyButton = new Texture("images/MenuButtons/switch_view.png");

        buttonSizeX = Gdx.graphics.getWidth() / 3;
        buttonSizeY = (int) (Gdx.graphics.getHeight() / (4.545454 * 2));

        xPosButtons = (float) (Gdx.graphics.getWidth() / 2D - buttonSizeX / 2D);
        yPosInitialButtons = (float) (Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4D);
        yPosOffsetButtons = (float) (-Gdx.graphics.getWidth() / 8D);

        /**
         * instead of closing the App do nothing
         */
        InputBackProcessor inputProcessor = new InputBackProcessor(monopoly);
        inputProcessor.backDoesNothingProcessor();
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
        monopoly.batch.draw(sensorButton, xPosButtons + 700, yPosInitialButtons + 3f * yPosOffsetButtons, buttonSizeX/2, buttonSizeY/2);
        //+500

        // Karten Button - nur zum Testen
        monopoly.batch.draw(kartenButton, xPosButtons + 000, yPosInitialButtons + 3f * yPosOffsetButtons, buttonSizeX/2, buttonSizeY/2);

        //Spielfeld Button
        monopoly.batch.draw(gameFieldButton, xPosButtons - 700, yPosInitialButtons + 3f * yPosOffsetButtons, buttonSizeX/2, buttonSizeY/2);
        //-500

        //Kaufbutton - erstmal zum Testen
        monopoly.batch.draw(kartenButton, xPosButtons + 1200, yPosInitialButtons + 3f * yPosOffsetButtons, buttonSizeX/2, buttonSizeY/2);

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
         * Pressing the KARTEN button leads to exiting the game
         */
        if (isCorrectPosition(userPosX, userPosY, xPosButtons+000, yPosInitialButtons, buttonSizeX, buttonSizeY, 3 * yPosOffsetButtons)
                && (Gdx.input.isTouched() || Gdx.input.isButtonPressed(Input.Buttons.LEFT))) {

            this.dispose();
            monopoly.setScreen(new KartenTestScreen(monopoly));

        }

        /**
         * Pressing the Sensor button leads to the Sensor Screen
         */
        if (isCorrectPosition(userPosX, userPosY, xPosButtons+700, yPosInitialButtons, buttonSizeX, buttonSizeY, 3 * yPosOffsetButtons)
                && (Gdx.input.isTouched() || Gdx.input.isButtonPressed(Input.Buttons.LEFT))) {

            this.dispose();
            monopoly.setScreen(new SensorScreen(monopoly));

        }

        /**
         * Pressing the Sensor button leads to the Sensor Screen
         */
        if (isCorrectPosition(userPosX, userPosY, xPosButtons+1000, yPosInitialButtons, buttonSizeX, buttonSizeY, 3 * yPosOffsetButtons)
                && (Gdx.input.isTouched() || Gdx.input.isButtonPressed(Input.Buttons.LEFT))) {

            this.dispose();
            monopoly.setScreen(new MonopolyScreen(monopoly));

        }



    /**
     * Pressing the GameField button leads to exiting the game
     */
        if (isCorrectPosition(userPosX, userPosY, xPosButtons-700, yPosInitialButtons, buttonSizeX, buttonSizeY, 3 * yPosOffsetButtons)
                && (Gdx.input.isTouched() || Gdx.input.isButtonPressed(Input.Buttons.LEFT))) {

        this.dispose();
        monopoly.setScreen(new CreateGameField(monopoly));

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


}
