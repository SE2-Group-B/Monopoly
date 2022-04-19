package se2.groupb.monopoly;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;


public class Monopoly extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    Music music;
    private BitmapFont menuFont;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("images/badlogic.jpg");
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/OkLetsGOOO.mp3"));
        music.play();
        music.setVolume(100);
    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 1, 1, 1);

        int yPosInitialButtons = Gdx.graphics.getHeight() / 4;
        int xPosButtons = Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 12;

        // Start Game text on screen
        menuFont = new BitmapFont();
        menuFont.setColor(0,0,0,1);
        menuFont.getData().setScale(4,4);


        batch.begin();

        // Tap screen to go to main menu
        menuFont.draw(batch, "START",  xPosButtons, yPosInitialButtons);
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) || Gdx.input.isTouched()){
			// switch Screen
            Gdx.app.exit();
		}
        // batch.draw(img, 0, 0);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        menuFont.dispose();
    }
}
