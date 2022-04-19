package se2.groupb.monopoly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class StartScreen extends ScreenAdapter {
    SpriteBatch batch;
    Texture img;
    Music music;


    public StartScreen() {
        batch = new SpriteBatch();
        img = new Texture("Boi.jpg");


        music = Gdx.audio.newMusic(Gdx.files.internal("start_with_Garrix.mp3"));
        music.play();
        music.setVolume(100);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);
        batch.begin();
        batch.draw(img, (Gdx.graphics.getWidth()/2)-img.getWidth(), 0, img.getWidth()*3, img.getHeight()*3);

        batch.end();
        if (Gdx.input.isTouched()) Monopoly.INSTANCE.setScreen(new HomeScreen());

    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();

    }
}
