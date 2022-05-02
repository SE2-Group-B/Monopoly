package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import se2.groupb.monopoly.Monopoly;
import se2.groupb.monopoly.network.ClientFoundation;
import se2.groupb.monopoly.network.ServerFoundation;

public class HostGameScreen implements Screen {

    Monopoly monopoly;

    InputBackProcessor inputProcessor;

    ServerFoundation instance;
    ClientFoundation client;
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

    public HostGameScreen(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    @Override
    public void show() {
        /**
         * instead of closing the App go to Main Menu
         * close server if leaving
         */
        inputProcessor = new InputBackProcessor(monopoly);
        inputProcessor.backToMainMenuProcessor();

        connectButton = new Texture("images/MenuButtons/connect.png");

        buttonSizeX = Gdx.graphics.getWidth() / 3;
        buttonSizeY = (int) (Gdx.graphics.getHeight() / (4.545454 * 2));

        xPosButtons = (float) (Gdx.graphics.getWidth() / 2D - buttonSizeX / 2D);
        yPosInitialButtons = Gdx.graphics.getHeight() / 20;
        yPosOffsetButtons = (float) (-Gdx.graphics.getWidth() / 8D);

        font = new BitmapFont();
        font.getData().setScale(3.5f);
        waitingText = new GlyphLayout(font, "Waiting for other Players");

    }

    @Override
    public void render(float delta) {

        float userPosX = (float) Gdx.input.getX();
        float userPosY = (float) Gdx.graphics.getHeight() - Gdx.input.getY();

        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        monopoly.batch.begin();

        // Host Game Button
        monopoly.batch.draw(connectButton, xPosButtons, yPosInitialButtons, buttonSizeX, buttonSizeY);

        /**
         * Pressing the Host Game button leads to HostGameScreen
         */
        if (isCorrectPosition(userPosX, userPosY, xPosButtons, yPosInitialButtons, buttonSizeX, buttonSizeY, 0 * yPosOffsetButtons)
                && Gdx.input.isTouched()) {

            callOnce++;

            if (callOnce == 1) {
                // starting a server to host a game
                instance = new ServerFoundation();
                instance.registerToKryo();
                // connect client (the host) to the server
                client = new ClientFoundation();
                client.registerToKryo();
                // new input processor that disconnects server if you go back
                inputProcessor.HostMenuServerProcessor(instance.getServer(), client.getClient());
                // show Waiting for Players on screen if server was started
                buttonPressed = true;

                // send a message to server
                client.getClient().sendUDP("Ich will ein Spiel hosten");
            }
        }

        if (buttonPressed) {
            font.draw(monopoly.batch, waitingText,
                    (Gdx.graphics.getWidth() / 2 - waitingText.width / 2), (yPosInitialButtons + 1.5f* buttonSizeY));
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
