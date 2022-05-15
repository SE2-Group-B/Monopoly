package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import se2.groupb.monopoly.Monopoly;

public class KartenTestScreen extends ScreenAdapter {
    private Texture kartenHintergrund;

    /*public Texture getKartenHintergrund() {
        return kartenHintergrund;
    }

    public void setKartenHintergrund(Texture kartenHintergrund) {
        this.kartenHintergrund = kartenHintergrund;
    }*/

    public KartenTestScreen(Monopoly monopoly) {
        super(monopoly);
        kartenHintergrund = new Texture("images/KartenImages/Karte1.png");
    }

    @Override
    public void show() {
        /**
         * instead of closing the App go to Main Menu
         */
        InputBackProcessor inputProcessor = new InputBackProcessor(monopoly);
        inputProcessor.backToMainMenuProcessor();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        monopoly.batch.begin();
        monopoly.batch.draw(kartenHintergrund, (Gdx.graphics.getWidth()/2)-1000/2, (Gdx.graphics.getHeight()/2)-1300/2, 1000, 1300);
        //font.draw(batch, "lkjasd,", 100,100);
        //monopoly.batch.draw("Hello World",100,100);
        //drawMessageText(Batch batch,BitmapFont font, float x,  float y,float maxWidth);

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
