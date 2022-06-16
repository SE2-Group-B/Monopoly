package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import se2.groupb.monopoly.Monopoly;
import se2.groupb.monopoly.screens.HomeScreen;

public class StartScreen extends ScreenAdapter {
    SpriteBatch batch;
    Texture img;
    Music music;

    Monopoly monopoly;


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
        batch.draw(img, ((float) Gdx.graphics.getWidth()/2)-img.getWidth(), 0, (float)img.getWidth()*3, (float) img.getHeight()*3);

        batch.end();
//        if (Gdx.input.isTouched()) Monopoly.INSTANCE.setScreen(new HomeScreen());
            if (Gdx.input.isTouched()) monopoly.setScreen(new HomeScreen());
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();

    }
}
