package se2.groupb.monopoly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class HomeScreen extends ScreenAdapter {
    SpriteBatch batch;
    Texture img;
    Music music;

    public HomeScreen() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");

        music = Gdx.audio.newMusic(Gdx.files.internal("OkLetsGOOO.mp3"));
        music.play();
        music.setVolume(100);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
        if (Gdx.input.isTouched()) Monopoly.INSTANCE.setScreen(new CreateGameField());


    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();

    }

}
