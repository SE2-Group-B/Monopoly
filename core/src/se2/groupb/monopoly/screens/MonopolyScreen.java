package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;


import se2.groupb.monopoly.Monopoly;

public class MonopolyScreen implements Screen {
    Texture img;
    Music music;
    private BitmapFont menuFont;
    private Monopoly monopoly;

    public MonopolyScreen(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    @Override
    public void show() {
        img = new Texture("images/badlogic.jpg");
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/OkLetsGOOO.mp3"));
        music.play();
        music.setVolume(100);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);
        int yPosInitialButtons = Gdx.graphics.getHeight() / 4;
        int xPosButtons = Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 12;

        // Start Game text on screen
        menuFont = new BitmapFont();
        menuFont.setColor(0,0,0,1);
        menuFont.getData().setScale(4,4);


        monopoly.batch.begin();

        // Tap screen to go to main menu
        menuFont.draw(monopoly.batch, "START",  xPosButtons, yPosInitialButtons);
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) || Gdx.input.isTouched()){
            // switch Screen
            // monopoly.setScreen(new MainMenuScreen(monopoly));
        }
        monopoly.batch.draw(img, 0, 0);

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
        /*img.dispose();
        menuFont.dispose();*/
    }
}
