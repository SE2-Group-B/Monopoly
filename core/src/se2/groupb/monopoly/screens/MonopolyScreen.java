package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;


import java.util.ArrayList;

import se2.groupb.monopoly.Grundstueck;
import se2.groupb.monopoly.Monopoly;
import se2.groupb.monopoly.Strasse;

public class MonopolyScreen implements Screen {
    Texture img;
    Music music;
    private BitmapFont menuFont;
    private Monopoly monopoly;
    private Grundstueck[] spielfeld;

    public MonopolyScreen(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    @Override
    public void show() {
        img = new Texture("images/badlogic.jpg");
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/OkLetsGOOO.mp3"));
        music.play();
        music.setVolume(100);

        //erzeuge Spielfeld:
        spielfeld=new Grundstueck[40];
        Grundstueck Los=new Grundstueck(0, "Los");
        spielfeld[0]=Los;
        Grundstueck Badstraße=new Strasse(1, "Badstraße", 40,false, 0, 0, 10,  50);
        spielfeld[1]=Badstraße;


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
