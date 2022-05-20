package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


import se2.groupb.monopoly.CreateGameField;
import se2.groupb.monopoly.Monopoly;
import se2.groupb.monopoly.network.ClientFoundation;
import se2.groupb.monopoly.network.ServerFoundation;

public class HostGameScreen extends GameScreenAdapter {

    // input handling processor
    InputBackProcessor inputProcessor;

    // network variables
    ServerFoundation instance;
    ClientFoundation client;
    private boolean isConnected = false;

    // state booleans
    private boolean buttonPressed = false;
    private boolean allJoined = false;

    // text variables
    private BitmapFont font;
    private GlyphLayout waitingText;
    private GlyphLayout connectedText;
    private GlyphLayout loadingText;

    // button variables
    private Stage stage;
    private ImageButton connectBtn;
    private ImageButton startBtn;

    private int buttonWidth;
    private int buttonHeight;
    private float yPosInitialButtons;
    private float yPosOffsetButtons;
    private float xPosButtons;

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
        // inputProcessor.backToMainMenuProcessor();

        // draw text
        font = new BitmapFont();
        font.getData().setScale(3.5f);
        waitingText = new GlyphLayout(font, "Waiting for other Players");
        connectedText = new GlyphLayout(font, "Connected to Server");
        loadingText = new GlyphLayout(font, "Loading the Game");

        // button size
        buttonWidth = Gdx.graphics.getWidth() / 3;
        buttonHeight = (int) (Gdx.graphics.getHeight() / (4.545454 * 2));

        // initial position of buttons and y offset
        xPosButtons = (float) (Gdx.graphics.getWidth() / 2D - buttonWidth / 2D);
        yPosInitialButtons = (float) (Gdx.graphics.getHeight() / 20D);
        yPosOffsetButtons = (float) (-Gdx.graphics.getWidth() / 8D);

        // Button for connecting to Server
        connectBtn = super.drawImageButton("images/MenuButtons/connect.png");
        connectBtn.setX(xPosButtons);
        connectBtn.setY(yPosInitialButtons);
        connectBtn.setSize(buttonWidth, buttonHeight);

        // Button to start Game
        startBtn = drawImageButton("images/MenuButtons/start_game.png");
        startBtn.setX(xPosButtons);
        startBtn.setY(yPosInitialButtons - yPosOffsetButtons);
        startBtn.setSize(buttonWidth, buttonHeight);

        stage = new Stage(new ScreenViewport()); //Set up a stage for the ui
        stage.addActor(connectBtn); //Add the button to the stage to perform rendering and take input.
        Gdx.input.setInputProcessor(stage); //Start taking input from the ui

        /**
         *                                                                          *
         * * * * * * * * * * * * * * * Button Listeners * * * * * * * * * * * * * * *
         *                                                                          *
         * start a server, connect as client
         */
        connectBtn.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (!isConnected) {
                    // starting a server to host a game
                    instance = new ServerFoundation(6334, 6333);

                    // connect client (the host) to the server
                    client = new ClientFoundation(6334, 6333);
                    isConnected = true;

                    // new input processor that disconnects server if you go back
                    // inputProcessor.HostMenuServerProcessor(instance.getServer(), client.getClient());

                    // send a message to server
                    client.getClient().sendUDP("Ich will ein Spiel hosten");

                    stage.addActor(startBtn);

                    return true;
                }

                return false;
            }
        });

        /**
         * send message to server to start game
         * if all players joined game starts, else message on screen
         */
        startBtn.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                client.getClient().sendUDP("HOST");

                buttonPressed = true;
                // draw rectangle above old text since it does not vanish when loading the game

                if (client.allPlayersJoined()) {
                    allJoined = true;
                    switchScreenDelayed(getScreen(), 0.000000001f);
                    return true;
                } else allJoined = false;
                return false;
            }
        });
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime()); //Perform ui logic
        stage.draw(); //Draw the ui


        monopoly.batch.begin();
        if (isConnected && !buttonPressed) {

            font.draw(monopoly.batch, connectedText,
                    (float) (Gdx.graphics.getWidth() / 2D - connectedText.width / 2D), (yPosInitialButtons + 1.5f * buttonHeight));
        }
        if (!allJoined && buttonPressed) {
            font.draw(monopoly.batch, waitingText,
                    (float) (Gdx.graphics.getWidth() / 2D - waitingText.width / 2D), (yPosInitialButtons + 1.5f * buttonHeight));
        } else if (allJoined && buttonPressed) {
            font.draw(monopoly.batch, loadingText,
                    (float) (Gdx.graphics.getWidth() / 2D - loadingText.width / 2D), (yPosInitialButtons + 1.5f * buttonHeight));
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
    public void switchScreenDelayed(final GameScreenAdapter screen, float delay) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                inputProcessor.backDoesNothingProcessor();
                screen.monopoly.setScreen(new CreateGameField(screen.monopoly));
            }
        }, delay);
    }

    public GameScreenAdapter getScreen() {
        return this;
    }




}
