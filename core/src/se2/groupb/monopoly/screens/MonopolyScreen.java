package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;


import java.util.ArrayList;

import se2.groupb.monopoly.Property;
import se2.groupb.monopoly.Monopoly;
import se2.groupb.monopoly.Player;

public class MonopolyScreen implements Screen {
    Texture img;
    Music music;
    private BitmapFont menuFont;
    private BitmapFont money;
    private Monopoly monopoly;

    private Texture kartenHintergrund;

    public MonopolyScreen(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    @Override
    public void show() {
        img = new Texture("images/badlogic.jpg");
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/OkLetsGOOO.mp3"));
        music.play();
        music.setVolume(100);

        //Spielfiguren
        ArrayList<Property> grundstueckeRot=new ArrayList<>();
        ArrayList<Property> grundstueckeGelb=new ArrayList<>();
        ArrayList<Property> grundstueckeGruen=new ArrayList<>();
        ArrayList<Property> grundstueckeBlau=new ArrayList<>();
        Player rot = new Player(1, "Rot", 2000, grundstueckeRot, 0, Color.RED);
        Player gelb = new Player(2, "Gelb", 2000, grundstueckeGelb, 0, Color.YELLOW);
        Player gruen = new Player(3, "Grün", 2000, grundstueckeGruen, 0, Color.GREEN);
        Player blau = new Player(4, "Blau", 2000, grundstueckeBlau, 0, Color.BLUE);



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

        money = new BitmapFont();
        money.getData().setScale(3.5f);

        monopoly.batch.begin();

        // Tap screen to go to main menu
        menuFont.draw(monopoly.batch, "START",  xPosButtons, yPosInitialButtons);
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) || Gdx.input.isTouched()){
            // switch Screen
            monopoly.setScreen(new MainMenuScreen(monopoly));
        }
        

        monopoly.batch.draw(img, 0, 0);

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

    }
}
