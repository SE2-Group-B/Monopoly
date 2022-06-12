package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
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

    private float buttonSize;
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

        // draw text
        font = new BitmapFont();
        font.getData().setScale(3.5f);
        waitingText = new GlyphLayout(font, "Waiting for other Players");
        connectedText = new GlyphLayout(font, "");
        loadingText = new GlyphLayout(font, "Loading the Game");

        // button size
        buttonSize = (float) (Gdx.graphics.getWidth() / 4D);

        // initial position of buttons and y offset
        xPosButtons = (float) (Gdx.graphics.getWidth() / 2D);
        yPosInitialButtons = (float) (Gdx.graphics.getHeight() / 20D);
        yPosOffsetButtons = (float) (-Gdx.graphics.getWidth() / 8D);

        // Buttons for connecting to Server and starting the game
        connectBtn = drawImageButton("images/MenuButtons/connect.png", xPosButtons, yPosInitialButtons, buttonSize);
        startBtn = drawImageButton("images/MenuButtons/start_game.png", xPosButtons, yPosInitialButtons - yPosOffsetButtons, buttonSize);

        stage = new Stage(new ScreenViewport()); //Set up a stage for the ui
        stage.addActor(connectBtn); //Add the button to the stage to perform rendering and take input.
        InputMultiplexer inputMultiplexer = new InputMultiplexer(inputProcessor.backToMainMenuProcessor(), stage);
        Gdx.input.setInputProcessor(inputMultiplexer); //Start taking input from the ui

        /**
         *                                                                          *
         * * * * * * * * * * * * * * * Button Listeners * * * * * * * * * * * * * * *
         *                                                                          *
         * start a server, connect as client
         *
         * TODO: show ports on user screen
         * sometimes crashes when connecting?
         */
        connectBtn.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                // justTouched() is used so button only does action once, when clicked
                if (Gdx.input.justTouched()) {
                    if (!isConnected) {
                        // starting a server to host a game
                        instance = new ServerFoundation();

                        // connect client (the host) to the server
                        client = new ClientFoundation(instance.getTcpPort(), instance.getUdpPort());
                        System.out.println(client.getClient().isConnected());
                        if (client.getClient().isConnected()) {
                            // add client to monopoly
                            monopoly.addClient(client);
                            isConnected = true;
                            connectedText.setText(font, "Your Room Number is: " + instance.getTcpPort());

                            // new input processor that disconnects server if you go back
                            InputMultiplexer inputMultiplexer = new InputMultiplexer(inputProcessor.hostMenuServerProcessor(instance.getServer(), client.getClient()), stage);
                            Gdx.input.setInputProcessor(inputMultiplexer);

                            stage.addActor(startBtn);
                        } else {
                            isConnected = false;
                            connectedText.setText(font, "Could not connect, please retry!");
                        }

                        return true;
                    }
                    if (!client.getClient().isConnected()) isConnected = false;
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
                // justTouched() is used so button only does action once, when clicked
                if (Gdx.input.justTouched()) {
                    client.getClient().sendUDP("HOST");

                    buttonPressed = true;
                    // draw rectangle above old text since it does not vanish when loading the game

                    if (client.allPlayersJoined()) {
                        /**
                         * START THE GAME
                         * set the screen, user can't go to main menu anymore
                         */
                        InputMultiplexer inputMultiplexer = new InputMultiplexer(inputProcessor.backDoesNothingProcessor(), stage);
                        Gdx.input.setInputProcessor(inputMultiplexer);
                        allJoined = true;
                        switchScreenDelayed(getScreen(), 0.000000001f);
                        return true;
                    } else allJoined = false;
                }
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
        font.draw(monopoly.batch, connectedText,
                (float) (Gdx.graphics.getWidth() / 2D - connectedText.width / 2D), (yPosInitialButtons - yPosOffsetButtons + 1.5f * connectBtn.getHeight() * connectBtn.getImage().getScaleY()));

        if (!allJoined && buttonPressed) {
            font.draw(monopoly.batch, waitingText,
                    (float) (Gdx.graphics.getWidth() / 2D - waitingText.width / 2D), (yPosInitialButtons + 1.5f * connectBtn.getHeight() * connectBtn.getImage().getScaleY()));
        } else if (allJoined && buttonPressed) {
            font.draw(monopoly.batch, loadingText,
                    (float) (Gdx.graphics.getWidth() / 2D - loadingText.width / 2D), (yPosInitialButtons + 1.5f * connectBtn.getHeight() * connectBtn.getImage().getScaleY()));
        }

        monopoly.batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
        stage.dispose();
        monopoly.dispose();
    }

    @Override
    public void switchScreenDelayed(final GameScreenAdapter screen, float delay) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                screen.monopoly.setScreen(new CreateGameField(screen.monopoly));
            }
        }, delay);
    }

    public GameScreenAdapter getScreen() {
        return this;
    }


}
