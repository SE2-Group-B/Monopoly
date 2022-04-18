package se2.groupb.monopoly;

import com.badlogic.gdx.ApplicationAdapter;
<<<<<<< Updated upstream
=======
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
>>>>>>> Stashed changes
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

<<<<<<< Updated upstream
public class Monopoly extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 1, 1, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
=======
import javafx.application.Application;


public class Monopoly extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    Music music;


    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        music = Gdx.audio.newMusic(Gdx.files.internal("OkLetsGOOO.mp3"));
        music.play();
        music.setVolume(100);
    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 1, 1, 1);

        int yPosInitialButtons = Gdx.graphics.getHeight() / 4;
        int xPosButtons = Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 12;

        batch.begin();

        // Buttons for user interaction
        BitmapFont menuFont = new BitmapFont();
        menuFont.getData().setScale(8,8);
        menuFont.draw(batch, "START",  xPosButtons, yPosInitialButtons);

		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) || Gdx.input.isTouched()){
			Gdx.app.exit();
		}
        batch.draw(img, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();

    }


>>>>>>> Stashed changes
}
