package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
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

public class JoinGameScreen extends GameScreenAdapter {

    InputBackProcessor inputProcessor;

    ClientFoundation client;
    private boolean isConnected = false;

    private Stage stage;
    private ImageButton connectBtn;

    private int buttonSize;
    private float yPosInitialButtons;
    private float xPosButtons;
    private boolean buttonPressed = false;
    private boolean allConnected = false;

    private BitmapFont font;
    private GlyphLayout waitingText;
    private GlyphLayout joinedText;

    public JoinGameScreen(Monopoly monopoly) {
        super(monopoly);
    }

    @Override
    public void show() {
        /**
         * instead of closing the App go to Main Menu
         */
        inputProcessor = new InputBackProcessor(monopoly);
        /*inputProcessor.backToMainMenuProcessor();*/

        // interaction text
        font = new BitmapFont();
        font.getData().setScale(3.5f);
        waitingText = new GlyphLayout(font, "No Server found, searching server");
        joinedText = new GlyphLayout(font, "Joined Room, Waiting for other Players");

        // button size and initial button positions
        buttonSize = Gdx.graphics.getWidth() / 3;
        xPosButtons = (float) (Gdx.graphics.getWidth() / 2D);
        yPosInitialButtons = (float) (Gdx.graphics.getHeight() / 20D);

        // make image button
        connectBtn = drawImageButton("images/MenuButtons/connect.png", xPosButtons, yPosInitialButtons, buttonSize);

        stage = new Stage(new ScreenViewport()); //Set up a stage for the ui
        stage.addActor(connectBtn); //Add the button to the stage to perform rendering and take input.
        Gdx.input.setInputProcessor(stage); //Start taking input from the ui

        /**
         *                                                                          *
         * * * * * * * * * * * * * * * Button Listeners * * * * * * * * * * * * * * *
         *                                                                          *
         * connect client to a server
         */
        connectBtn.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {

                if (!isConnected){
                    // button press
                    // connect client (new client) to the server
                    // TODO get input from user ->
                    //  user should enter Port by themselves for hosting a room with friends
                    //  send port on messenger
                    client = new ClientFoundation(6334, 6333);
                    isConnected = true;

                    // new input processor that disconnects server if you go back
                    // inputProcessor.JoinMenuServerProcessor(client.getClient());
                    // show Waiting for Players on screen if server was started
                    buttonPressed = true;

                    // send a message to server
                    client.getClient().sendUDP("Ich will einem Spiel beitreten");
                    return true;
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

        if (buttonPressed && !allConnected) {
            // if server response: client connected is still missing
            font.draw(monopoly.batch, waitingText,
                    (float) (Gdx.graphics.getWidth() / 2D - waitingText.width / 2D), (yPosInitialButtons + 1.5f * connectBtn.getHeight()));
        }
        if (buttonPressed && allConnected) {
            // if server found and connected
            font.draw(monopoly.batch, joinedText,
                    (float) (Gdx.graphics.getWidth() / 2D - waitingText.width / 2D), (yPosInitialButtons + 1.5f * connectBtn.getHeight()));
        }

        if (isConnected){
            if (client.allPlayersJoined()) {
                /**
                 * START THE GAME
                 * set the screen
                 */
                allConnected = true;
                switchScreenDelayed(this, 0.00000001f);
            } else {
                allConnected = false;
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
    public void switchScreenDelayed(final GameScreenAdapter screen, float delay) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                inputProcessor.backDoesNothingProcessor();
                screen.monopoly.setScreen(new CreateGameField(screen.monopoly));
            }
        }, delay);
    }

}
