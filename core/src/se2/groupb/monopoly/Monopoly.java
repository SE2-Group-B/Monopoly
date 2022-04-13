package se2.groupb.monopoly;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;




public class Monopoly extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Music music;





	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		music = Gdx.audio.newMusic(Gdx.files.internal("OkLetsGOOO.mp3"));
		music.play();
		music.setVolume(100);

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



}
