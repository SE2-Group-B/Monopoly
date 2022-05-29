package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import se2.groupb.monopoly.CreateGameField;
import se2.groupb.monopoly.Monopoly;

public class WinningScreen extends GameScreenAdapter{

    private BitmapFont font;
    private GlyphLayout waitingText;


    CreateGameField createGameField = new CreateGameField();

    SpriteBatch spriteBatch;

    private String text = "Hallo";
    private String first = "Sieger: " + createGameField.placement[0] + " with:" + createGameField.sums[0];
    private String second = "Zweiter: " + createGameField.placement[1] + " with:" + createGameField.sums[1];
    private String third = "Dritter: " + createGameField.placement[2] + " with:" + createGameField.sums[2];
    private String fourth = "Vierter: " + createGameField.placement[3] + " with:" + createGameField.sums[3];


    public WinningScreen(Monopoly monopoly) {
        super(monopoly);
    }

    @Override
    public void show() {
        /**
         * instead of closing the App go to Main Menu
         */
        InputBackProcessor inputProcessor = new InputBackProcessor(monopoly);
        inputProcessor.backToMainMenuProcessor();

        font = new BitmapFont();
        spriteBatch = new SpriteBatch();
        font.getData().setScale(3.5f);
        waitingText = new GlyphLayout(font, text);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float userPosX = (float) Gdx.input.getX();
        float userPosY = (float) Gdx.graphics.getHeight() - Gdx.input.getY();
        monopoly.batch.begin();
        spriteBatch.begin();

        font.draw(spriteBatch, text, Gdx.graphics.getHeight()/2,Gdx.graphics.getWidth()/2);
        font.draw(spriteBatch, text, Gdx.graphics.getHeight()/2,Gdx.graphics.getWidth()+50);
        font.draw(spriteBatch, text, Gdx.graphics.getHeight()/2,Gdx.graphics.getWidth()+75);
        /*font.draw(spriteBatch, first, Gdx.graphics.getHeight()/2,Gdx.graphics.getWidth()+50);
        font.draw(spriteBatch, second, Gdx.graphics.getHeight()/2,Gdx.graphics.getWidth()+75);
        font.draw(spriteBatch, third, Gdx.graphics.getHeight()/2,Gdx.graphics.getWidth()+100);
        font.draw(spriteBatch, fourth, Gdx.graphics.getHeight()/2,Gdx.graphics.getWidth()+125);*/

        spriteBatch.end();
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
