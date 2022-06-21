package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import se2.groupb.monopoly.Monopoly;

public class MainMenuScreen extends GameScreenAdapter {

    private Stage stage;


    /**
     * Constructor
     * setup main menu with Monopoly game
     */
    public MainMenuScreen(Monopoly monopoly) {
        super(monopoly);
    }

    @Override
    public void show() {

        InputBackProcessor inputProcessor = new InputBackProcessor(monopoly);
        float buttonSize = (float) (Gdx.graphics.getWidth() / 3D);

        float xPosButtons = (float) (Gdx.graphics.getWidth() / 2D);
        float yPosInitialButtons = (float) (Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4D);
        float yPosOffsetButtons = (float) (-Gdx.graphics.getWidth() / 8D);

        ImageButton hostBtn = drawImageButton("images/MenuButtons/host.png", xPosButtons, yPosInitialButtons, buttonSize);
        ImageButton joinBtn = drawImageButton("images/MenuButtons/join.png", xPosButtons, yPosInitialButtons + yPosOffsetButtons, buttonSize);
        ImageButton exitBtn = drawImageButton("images/MenuButtons/exit.png", xPosButtons, yPosInitialButtons + 2 * yPosOffsetButtons, buttonSize);
        ImageButton offlineButton = drawImageButton("images/MenuButtons/switch_view.png", xPosButtons, yPosInitialButtons + 3 * yPosOffsetButtons, buttonSize);


        stage = new Stage(new ScreenViewport());
        stage.addActor(hostBtn);
        stage.addActor(joinBtn);
        stage.addActor(exitBtn);
        stage.addActor(offlineButton);

        // add a inputProcessor multiplexer so you get button input have a custom InputProcessor
        InputMultiplexer inputMultiplexer = new InputMultiplexer(inputProcessor.backDoesNothingProcessor(), stage);
        Gdx.input.setInputProcessor(inputMultiplexer);

        hostBtn.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (Gdx.input.justTouched()) {
                    monopoly.setScreen(new HostGameScreen(monopoly));
                    return true;
                }
                return false;
            }
        });

        joinBtn.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (Gdx.input.justTouched()) {
                    monopoly.setScreen(new JoinGameScreen(monopoly));
                    return true;
                }
                return false;
            }
        });

        exitBtn.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (Gdx.input.justTouched()) {
                    Gdx.app.exit();
                }
                return false;
            }
        });

        offlineButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (Gdx.input.justTouched()) {
                    monopoly.setOfflineGame(true);
                    monopoly.setScreen(new MonopolyScreen(monopoly));
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void switchScreenDelayed(GameScreenAdapter screen, float delay) {
        /**
         * we don't use this method here
         */
    }
}
