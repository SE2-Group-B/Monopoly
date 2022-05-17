package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import se2.groupb.monopoly.CreateGameField;
import se2.groupb.monopoly.Monopoly;
import se2.groupb.monopoly.network.ClientFoundation;
import se2.groupb.monopoly.network.ServerFoundation;

public class HostGameScreen extends GameScreenAdapter {

    InputBackProcessor inputProcessor;

    ServerFoundation instance;
    ClientFoundation client;
    private boolean isConnected = false;
    private int callOnce = 0; // so that the connect button only calls server create function once

    private Texture connectButton;
    private Texture startGameButton;
    private int buttonSizeX;
    private int buttonSizeY;
    private float yPosInitialButtons;
    private float yPosOffsetButtons;
    private float xPosButtons;
    private boolean buttonPressed = false;

    private BitmapFont font;
    private GlyphLayout waitingText;
    private GlyphLayout connectedText;

    private ShapeRenderer shapeRenderer;;

    public HostGameScreen(Monopoly monopoly) {
        super(monopoly);
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
        startGameButton = new Texture("images/MenuButtons/start_game.png");

        buttonSizeX = Gdx.graphics.getWidth() / 3;
        buttonSizeY = (int) (Gdx.graphics.getHeight() / (4.545454 * 2));

        xPosButtons = (float) (Gdx.graphics.getWidth() / 2D - buttonSizeX / 2D);
        yPosInitialButtons = (float) (Gdx.graphics.getHeight() / 20D);
        yPosOffsetButtons = (float) (-Gdx.graphics.getWidth() / 8D);

        font = new BitmapFont();
        font.getData().setScale(3.5f);
        waitingText = new GlyphLayout(font, "Waiting for other Players");
        connectedText = new GlyphLayout(font, "Connected to Server");

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
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
                instance = new ServerFoundation(6333, 6333);

                // connect client (the host) to the server
                client = new ClientFoundation(6333, 6333);
                isConnected = true;

                // new input processor that disconnects server if you go back
                inputProcessor.HostMenuServerProcessor(instance.getServer(), client.getClient());
                // show Waiting for Players on screen if server was started
                buttonPressed = true;

                // send a message to server
                client.getClient().sendUDP("Ich will ein Spiel hosten");
            }
        }


        if (buttonPressed) {
            font.draw(monopoly.batch, connectedText,
                    (float) (Gdx.graphics.getWidth() / 2D - waitingText.width / 2D), (yPosInitialButtons + 1.5f * buttonSizeY));
        }

        if (isConnected) {
            // once player is connected draw Start Game Button
            monopoly.batch.draw(startGameButton, xPosButtons, yPosInitialButtons - yPosOffsetButtons, buttonSizeX, buttonSizeY);
            // check if button was pressed
            if (isCorrectPosition(userPosX, userPosY, xPosButtons, yPosInitialButtons - yPosOffsetButtons, buttonSizeX, buttonSizeY, 0 * yPosOffsetButtons)
                    && Gdx.input.isTouched()) {
                // send the server the message to start the game
                client.getClient().sendUDP("HOST");

                buttonPressed = false;
                // draw rectangle above old text since it does not vanish when loading the game
                monopoly.batch.end();
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(0.2f,0.2f,0.2f,1);
                shapeRenderer.rect((float) (Gdx.graphics.getWidth() / 2D - waitingText.width / 2D), (yPosInitialButtons + 1.5f * buttonSizeY - buttonSizeY/2), 600, 100);
                shapeRenderer.end();
                monopoly.batch.begin();
                // draw new text
                font.draw(monopoly.batch, waitingText,
                        (float) (Gdx.graphics.getWidth() / 2D - waitingText.width / 2D), (yPosInitialButtons + 1.5f * buttonSizeY));

            }

            // if all players joined then switch the view
            if (client.allPlayersJoined()) {
                /**
                 * START THE Game
                 * set the screen
                 */
                inputProcessor.backDoesNothingProcessor();
                monopoly.setScreen(new CreateGameField(monopoly));
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

}
