package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.utils.ScreenUtils;

import se2.groupb.monopoly.CreateGameField;
import se2.groupb.monopoly.Monopoly;

public class WinningScreen extends GameScreenAdapter{

    private BitmapFont font;
    private GlyphLayout waitingText;
    private Environment environment;


    CreateGameField createGameField = new CreateGameField();

    SpriteBatch spriteBatch;

    private String text = "Und die Sieger sind:";
    /*private String first = "Sieger: " + createGameField.placement[0] + " with:" + createGameField.sums[0];
    private String second = "Zweiter: " + createGameField.placement[1] + " with:" + createGameField.sums[1];
    private String third = "Dritter: " + createGameField.placement[2] + " with:" + createGameField.sums[2];
    private String fourth = "Vierter: " + createGameField.placement[3] + " with:" + createGameField.sums[3];*/
    private String first = "Sieger: Player 1 with: 2000$";
    private String second = "Zweiter: Player 2 with: 1800$";
    private String third = "Dritter: Player 3 with: 1600$";
    private String fourth = "Vierter: Player 4 with: 100$";


    public WinningScreen(Monopoly monopoly) {
        super(monopoly);
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1f));
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
        font.getData().setScale(7f);
        waitingText = new GlyphLayout(font, text);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float userPosX = (float) Gdx.input.getX();
        float userPosY = (float) Gdx.graphics.getHeight() - Gdx.input.getY();
        monopoly.batch.begin();
        spriteBatch.begin();

        font.setColor(Color.BLACK);
        font.draw(spriteBatch, text, (float) (Gdx.graphics.getWidth()/3.75),(Gdx.graphics.getHeight()/2)+250);
        font.draw(spriteBatch, first, (float) (Gdx.graphics.getWidth()/3.75),(Gdx.graphics.getHeight()/2)+150);
        font.draw(spriteBatch, second, (float) (Gdx.graphics.getWidth()/3.75), (Gdx.graphics.getHeight()/2)+50);
        font.draw(spriteBatch, third, (float) (Gdx.graphics.getWidth()/3.75),(Gdx.graphics.getHeight()/2)-50);
        font.draw(spriteBatch, fourth, (float) (Gdx.graphics.getWidth()/3.75), (Gdx.graphics.getHeight()/2)-150);

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
        spriteBatch.dispose();
    }

    @Override
    public void switchScreenDelayed(GameScreenAdapter screen, float delay) {

    }

}
