package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

import se2.groupb.monopoly.Bahnhof;
import se2.groupb.monopoly.Grundstueck;
import se2.groupb.monopoly.Karte;
import se2.groupb.monopoly.Kartenstapel;
import se2.groupb.monopoly.Monopoly;
import se2.groupb.monopoly.Spielfigur;
import se2.groupb.monopoly.Strasse;
import se2.groupb.monopoly.Zahlfeld;

public class MonopolyScreen extends GameScreenAdapter {
    Texture img;
    Music music;
    private BitmapFont menuFont;
    private BitmapFont money;

    private SpriteBatch batch1;
    private Monopoly monopoly;

    private Grundstueck[] spielfeld;

    private Texture kartenHintergrund;

    public MonopolyScreen(Monopoly monopoly) {
        super(monopoly);
    }

    public void create() {
        batch1 = new SpriteBatch();
        money = new BitmapFont();
        money.setColor(Color.BLACK);
    }

    @Override
    public void show() {
        img = new Texture("images/badlogic.jpg");
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/OkLetsGOOO.mp3"));
        music.play();
        music.setVolume(100);

        //Spielfiguren
        ArrayList<Grundstueck> grundstueckeRot=new ArrayList<>();
        ArrayList<Grundstueck> grundstueckeGelb=new ArrayList<>();
        ArrayList<Grundstueck> grundstueckeGruen=new ArrayList<>();
        ArrayList<Grundstueck> grundstueckeBlau=new ArrayList<>();
        Spielfigur rot = new Spielfigur(1, "Rot", 2000, grundstueckeRot, 0, Color.RED);
        Spielfigur gelb = new Spielfigur(2, "Gelb", 2000, grundstueckeGelb, 0, Color.YELLOW);
        Spielfigur gruen = new Spielfigur(3, "Grün", 2000, grundstueckeGruen, 0, Color.GREEN);
        Spielfigur blau = new Spielfigur(4, "Blau", 2000, grundstueckeBlau, 0, Color.BLUE);



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

        /*textButton = new TextButton("Hallo", skin);
        textButton.setPosition(Gdx.graphics.getWidth() - 540f, Gdx.graphics.getHeight() - 180f);
        textButton.setSize(220, 140);*/

        monopoly.batch.begin();

        create();

        // Tap screen to go to main menu
        menuFont.draw(monopoly.batch, "Start",  xPosButtons, yPosInitialButtons);
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) || Gdx.input.isTouched()){
            // switch Screen
            monopoly.setScreen(new MainMenuScreen(monopoly));
        }
        batch1.begin();
        money.getData().setScale(4,4);
        money.draw(batch1, String.valueOf(200), Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 12,200);
        batch1.end();
        

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
