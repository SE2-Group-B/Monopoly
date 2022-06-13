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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import se2.groupb.monopoly.CreateGameField;
import se2.groupb.monopoly.Monopoly;
import se2.groupb.monopoly.network.ClientFoundation;

public class JoinGameScreen extends GameScreenAdapter {

    InputBackProcessor inputProcessor;

    ClientFoundation client;
    private boolean isConnected = false;

    private Stage stage;
    private ImageButton connectBtn;

    private float buttonSize;
    private float yPosInitialButtons;
    private float xPosButtons;
    private boolean buttonPressed = false;
    private boolean allConnected = false;
    private boolean isValidInput;

    private BitmapFont font;
    private GlyphLayout loadingText;
    private GlyphLayout connectedText;
    private GlyphLayout groupText;
    private GlyphLayout enterGroupNumberText;

    // text input from user
    private TextField userInput;
    private float xPosInput;
    private float yPosInput;
    private float inputWidth;
    private float inputHeight;

    public JoinGameScreen(Monopoly monopoly) {
        super(monopoly);
    }

    @Override
    public void show() {
        /**
         * instead of closing the App go to Main Menu
         */
        // setup custom InputProcessor
        inputProcessor = new InputBackProcessor(monopoly);

        // interaction text
        font = new BitmapFont();

        font.getData().setScale(3.5f);
        connectedText = new GlyphLayout(font, "");
        loadingText = new GlyphLayout(font, "Loading the Game");
        groupText = new GlyphLayout(font, "");
        enterGroupNumberText = new GlyphLayout(font, "Please enter group number:");

        // button size and initial button positions
        buttonSize = (float) (Gdx.graphics.getWidth() / 4D);
        xPosButtons = (float) (Gdx.graphics.getWidth() / 2D);
        yPosInitialButtons = (float) (Gdx.graphics.getHeight() / 20D);

        // make image button
        connectBtn = drawImageButton("images/MenuButtons/connect.png", xPosButtons, yPosInitialButtons, buttonSize);

        // input position and size
        inputHeight = Gdx.graphics.getHeight() / 8f;
        inputWidth = Gdx.graphics.getWidth() / 4f;
        yPosInput = Gdx.graphics.getHeight() * 0.75f - inputHeight / 2f;
        xPosInput = Gdx.graphics.getWidth() / 2f - inputWidth / 2f;

        // textField for user input
        Skin skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        userInput = new TextField("", skin);
        userInput.setPosition(xPosInput, yPosInput);
        userInput.setSize(inputWidth, inputHeight);
        userInput.setAlignment(1);

        // set up a stage for the ui
        stage = new Stage(new ScreenViewport());
        // add button and input to stage to perform rendering and take input
        stage.addActor(userInput);
        stage.addActor(connectBtn);
        // add a inputProcessor multiplexer so you get button input have a custom InputProcessor
        InputMultiplexer inputMultiplexer = new InputMultiplexer(inputProcessor.backToMainMenuProcessor(), stage);
        Gdx.input.setInputProcessor(inputMultiplexer);

        /**
         *                                                                          *
         * * * * * * * * * * * * * * * Button Listeners * * * * * * * * * * * * * * *
         *                                                                          *
         * connect client to a server
         */
        connectBtn.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                // justTouched() is used so button only does action once, when clicked
                if (Gdx.input.justTouched()) {
                    String inputText = userInput.getText();
                    // show Waiting for Players on screen if server was started
                    buttonPressed = true;
                    int port = 0;
                    if (isParsable(inputText)) {
                        port = Integer.parseInt(inputText);
                    }

                    if (!inputText.isEmpty() && port >= 1000 && port <= 7000) {
                        // bool for checking if user enters valid input
                        isValidInput = true;
                        if (!isConnected) {
                            // button press
                            // connect client (new client) to the server
                            client = new ClientFoundation(port, port);
                            if (client.getClient().isConnected()) {
                                // add client to monopoly
                                monopoly.addClient(client);
                                // new input processor that disconnects from server if user goes back
                                InputMultiplexer inputMultiplexer = new InputMultiplexer(inputProcessor.joinMenuServerProcessor(client.getClient()), stage);
                                Gdx.input.setInputProcessor(inputMultiplexer);
                                // if client is connected, show player that he has connection
                                isConnected = true;
                                connectedText.setText(font, "Joined server, waiting for players");
                            } else {
                                isConnected = false;
                                connectedText.setText(font, "Could not connect, please retry!");
                            }
                            return true;
                        }
                    } else {
                        isValidInput = false;
                    }

                    // if user enters invalid input show text
                    if (!isValidInput) {
                        groupText.setText(font, "The Groups range from 1000 to 7000");
                    }
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


        // start drawing text
        monopoly.batch.begin();

        if (buttonPressed && !allConnected) {
            // if server response: client connected is still missing
            font.draw(monopoly.batch, connectedText,
                    (float) (Gdx.graphics.getWidth() / 2D - connectedText.width / 2D), (yPosInitialButtons + 1.5f * connectBtn.getHeight() * connectBtn.getImage().getScaleY()));
        }
        if (buttonPressed && allConnected && isConnected) {
            // if server found and connected
            font.draw(monopoly.batch, loadingText,
                    (float) (Gdx.graphics.getWidth() / 2D - loadingText.width / 2D), (yPosInitialButtons + 1.5f * connectBtn.getHeight() * connectBtn.getImage().getScaleY()));
        }
        if (buttonPressed && !isConnected && !isValidInput) {
            font.draw(monopoly.batch, groupText,
                    (float) (Gdx.graphics.getWidth() / 2D - groupText.width / 2D), (yPosInitialButtons + 1.5f * connectBtn.getHeight() * connectBtn.getImage().getScaleY()));
        }

        font.draw(monopoly.batch, enterGroupNumberText,
                (float) (Gdx.graphics.getWidth() / 2D - enterGroupNumberText.width / 2D), (yPosInput + 1.5f * inputHeight ));

        if (isConnected) {
            if (client.allPlayersJoined()) {
                /**
                 * START THE GAME
                 * set the screen, user can't go to main menu anymore
                 */
                InputMultiplexer inputMultiplexer = new InputMultiplexer(inputProcessor.backDoesNothingProcessor(), stage);
                Gdx.input.setInputProcessor(inputMultiplexer);
                allConnected = true;
                switchScreenDelayed(this, 0.00000001f);
            } else {
                allConnected = false;
            }
        }
        monopoly.batch.end();


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

    public boolean isParsable(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
