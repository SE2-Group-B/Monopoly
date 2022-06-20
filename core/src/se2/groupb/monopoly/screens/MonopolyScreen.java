package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

import se2.groupb.monopoly.Monopoly;
import se2.groupb.monopoly.Player;
import se2.groupb.monopoly.Property;

public class MonopolyScreen extends GameScreenAdapter {
    Texture img;
    Music music;
    private BitmapFont menuFont;
    private BitmapFont money;

    private SpriteBatch batch1;
    private Monopoly monopoly;

    private Property[] spielfeld;

    private Texture kartenHintergrund;

    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private ArrayList<Player> playerList;

    public MonopolyScreen(Monopoly monopoly) {
        super(monopoly);




        playerList = new ArrayList();

        if (!monopoly.getClient().getOtherPlayers().isEmpty()) {
            player1 = monopoly.getClient().getPlayer().getPlayer();
            player1.createSpielfigur();
            playerList.add(player1);
            if (monopoly.getClient().getOtherPlayers().size() > 0) {
                player2 = monopoly.getClient().getOtherPlayers().get(0).getPlayer();
                player2.createSpielfigur();
                playerList.add(player2);
            }
            if (monopoly.getClient().getOtherPlayers().size() > 1) {
                player3 = monopoly.getClient().getOtherPlayers().get(1).getPlayer();
                player3.createSpielfigur();
                playerList.add(player3);
            }
            if (monopoly.getClient().getOtherPlayers().size() > 2) {
                player4 = monopoly.getClient().getOtherPlayers().get(2).getPlayer();
                player4.createSpielfigur();
                playerList.add(player4);
            }
        }

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
        ArrayList<Property> grundstueckeRot = new ArrayList<>();
        ArrayList<Property> grundstueckeGelb = new ArrayList<>();
        ArrayList<Property> grundstueckeGruen = new ArrayList<>();
        ArrayList<Property> grundstueckeBlau = new ArrayList<>();
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
        menuFont.setColor(0, 0, 0, 1);
        menuFont.getData().setScale(4, 4);

        /*textButton = new TextButton("Hallo", skin);
        textButton.setPosition(Gdx.graphics.getWidth() - 540f, Gdx.graphics.getHeight() - 180f);
        textButton.setSize(220, 140);*/

        monopoly.batch.begin();

        create();

        // Tap screen to go to main menu
        menuFont.draw(monopoly.batch, "Start", xPosButtons, yPosInitialButtons);
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) || Gdx.input.isTouched()) {
            // switch Screen
            monopoly.setScreen(new MainMenuScreen(monopoly));
        }
        batch1.begin();
        money.getData().setScale(4, 4);
        money.draw(batch1, String.valueOf(200), Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 12, 200);
        batch1.end();


        monopoly.batch.draw(img, 0, 0);

        monopoly.batch.draw(img, 0, 0);
        monopoly.batch.end();
    }

    @Override
    public void switchScreenDelayed(GameScreenAdapter screen, float delay) {

    }
}
