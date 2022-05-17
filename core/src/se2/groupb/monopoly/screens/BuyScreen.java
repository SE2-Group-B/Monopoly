package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import se2.groupb.monopoly.Monopoly;

public class BuyScreen extends GameScreenAdapter {
    private BitmapFont font;
    private GlyphLayout waitingText;

    private Texture BuyButton;
    private int buttonSizeX;
    private int buttonSizeY;
    private float yPosInitialButtons;
    private float yPosOffsetButtons;
    private float xPosButtons;
    private int callOnce = 0;
    private boolean buttonPressed = false;



    public BuyScreen(Monopoly monopoly) {
        super(monopoly);
    }

    @Override
    public void show() {
        /**
         * instead of closing the App go to Main Menu
         */
        InputBackProcessor inputProcessor = new InputBackProcessor(monopoly);
        inputProcessor.backToMainMenuProcessor();

        BuyButton = new Texture("images/MenuButtons/connect.png");

        buttonSizeX = Gdx.graphics.getWidth() / 3;
        buttonSizeY = (int) (Gdx.graphics.getHeight() / (4.545454 * 2));

        xPosButtons = (float) (Gdx.graphics.getWidth() / 2D - buttonSizeX / 2D);
        yPosInitialButtons = (float) (Gdx.graphics.getHeight() / 20D);
        yPosOffsetButtons = (float) (-Gdx.graphics.getWidth() / 8D);


        font = new BitmapFont();
        font.getData().setScale(3.5f);
        waitingText = new GlyphLayout(font, "You bought that building");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float userPosX = (float) Gdx.input.getX();
        float userPosY = (float) Gdx.graphics.getHeight() - Gdx.input.getY();
        monopoly.batch.begin();

        monopoly.batch.draw(BuyButton, xPosButtons, yPosInitialButtons, buttonSizeX, buttonSizeY);

        if (isCorrectPosition(userPosX, userPosY, xPosButtons, yPosInitialButtons, buttonSizeX, buttonSizeY, 0 * yPosOffsetButtons)
                && Gdx.input.isTouched()) {

            callOnce++;

            if (callOnce == 1) {
                buttonPressed = true;
            }
        }

        if (buttonPressed) {
            font.draw(monopoly.batch, waitingText,
                    (float) (Gdx.graphics.getWidth() / 2D - waitingText.width / 2D), (yPosInitialButtons + 1.5f * buttonSizeY));
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
