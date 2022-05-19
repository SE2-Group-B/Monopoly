package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import se2.groupb.monopoly.Monopoly;
import se2.groupb.monopoly.network.ClientFoundation;

public class JoinGameScreen extends GameScreenAdapter {

    InputBackProcessor inputProcessor;

    ClientFoundation client;
    private boolean isConnected = false;
    private int callOnce = 0; // so that the connect button only calls server create function once

    private Texture connectButton;
    private int buttonSizeX;
    private int buttonSizeY;
    private float yPosInitialButtons;
    private float yPosOffsetButtons;
    private float xPosButtons;
    private boolean buttonPressed = false;

    private BitmapFont font;
    private GlyphLayout waitingText;

    public JoinGameScreen(Monopoly monopoly) {
        super(monopoly);
    }

    @Override
    public void show() {
        /**
         * instead of closing the App go to Main Menu
         */
        inputProcessor = new InputBackProcessor(monopoly);
        inputProcessor.backToMainMenuProcessor();

        connectButton = new Texture("images/MenuButtons/connect.png");

        buttonSizeX = Gdx.graphics.getWidth() / 3;
        buttonSizeY = (int) (Gdx.graphics.getHeight() / (4.545454 * 2));

        xPosButtons = (float) (Gdx.graphics.getWidth() / 2D - buttonSizeX / 2D);
        yPosInitialButtons = (float) (Gdx.graphics.getHeight() / 20D);
        yPosOffsetButtons = (float) (-Gdx.graphics.getWidth() / 8D);

        font = new BitmapFont();
        font.getData().setScale(3.5f);
        waitingText = new GlyphLayout(font, "Joined Room, Waiting for other Players");
    }

    @Override
    public void render(float delta) {
        float userPosX = (float) Gdx.input.getX();
        float userPosY = (float) Gdx.graphics.getHeight() - Gdx.input.getY();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        monopoly.batch.begin();

        // Host Game Button
        monopoly.batch.draw(connectButton, xPosButtons, yPosInitialButtons, buttonSizeX, buttonSizeY);

        if (isCorrectPosition(userPosX, userPosY, xPosButtons, yPosInitialButtons, buttonSizeX, buttonSizeY, 0 * yPosOffsetButtons)
                && Gdx.input.isTouched()) {

            callOnce++;

            if (callOnce == 1) {
                // connect client (new client) to the server
                client = new ClientFoundation(6334,6333);

                // new input processor that disconnects server if you go back
                inputProcessor.JoinMenuServerProcessor(client.getClient());
                // show Waiting for Players on screen if server was started
                buttonPressed = true;

                // send a message to server
                client.getClient().sendUDP("Ich will einem Spiel beitreten");
            }
        }

        if (buttonPressed) {
            // if server response: client connected is still missing
            font.draw(monopoly.batch, waitingText,
                    (float) (Gdx.graphics.getWidth() / 2D - waitingText.width / 2D), (yPosInitialButtons + 1.5f * buttonSizeY));
        }

        if (isConnected) {
            if (client.allPlayersJoined()) {
                /**
                 * START THE GAME
                 * set the screen
                 */
                monopoly.setScreen(new MonopolyScreen(monopoly));
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

    }

    @Override
    public void switchScreenDelayed(GameScreenAdapter screen, float delay) {

    }

}
