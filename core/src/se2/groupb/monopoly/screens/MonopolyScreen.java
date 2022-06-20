package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;

import se2.groupb.monopoly.Monopoly;
import se2.groupb.monopoly.Player;
import se2.groupb.monopoly.Property;

public class MonopolyScreen extends GameScreenAdapter {
    //Buttons
    private ImageButton buyButton;
    private ImageButton diceButton;
    private ImageButton cheatButton;
    private ImageButton nextButton;
    private int buttonSizeX;
    private int buttonSizeY;
    private float buttonsize;
    private float yPosInitialButtons;
    private float yPosOffsetButtons;
    private float xPosButtons;

    //Players
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private ArrayList<Player> playerList;

    //Alen
    private BitmapFont moneyfont;
    private int player1mon, player2mon, player3mon, player4mon;
    private ArrayList<Integer> sum;
    private ArrayList<String> placement;
    private ArrayList<Property> player1Propertylist;
    private ArrayList<Property> player2Propertylist;
    private ArrayList<Property> player3Propertylist;
    private ArrayList<Property> player4Propertylist;

    private SpriteBatch batch;
    private Stage stage;

    public MonopolyScreen(Monopoly monopoly) {
        super(monopoly);

        //InitButtons
        buttonSizeX = Gdx.graphics.getWidth() / 3;
        buttonSizeY = (int) (Gdx.graphics.getHeight() / (4.545454 * 2));
        buttonsize = (float) (Gdx.graphics.getWidth() / 3D);
        xPosButtons = (float) (Gdx.graphics.getWidth() / 2D - buttonSizeX / 2D);
        yPosInitialButtons = (float) (Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4D);
        yPosOffsetButtons = (float) (-Gdx.graphics.getWidth() / 8D);

        //initAlen
        moneyfont = new BitmapFont();
        player1mon = 0;
        player2mon = 0;
        player3mon = 0;
        player4mon = 0;
        sum = new ArrayList<>();
        placement = new ArrayList<>();
        player1Propertylist = new ArrayList<>();
        player2Propertylist = new ArrayList<>();
        player3Propertylist = new ArrayList<>();
        player4Propertylist = new ArrayList<>();
        playerList = new ArrayList();

        if (monopoly.isOfflineGame()) {
            //initOfflinePlayers();
        } else {
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
    }

    public void create() {

    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport());

        Gdx.input.setInputProcessor(stage);
        buyButton = drawImageButton("images/MenuButtons/buy_building.png", 180, yPosInitialButtons - 45, buttonsize / 2);
        diceButton = drawImageButton("images/MenuButtons/roll.png", xPosButtons + 500, yPosInitialButtons - 500, buttonsize);
        cheatButton = drawImageButton("images/MenuButtons/report_cheat.png", xPosButtons + 500, yPosInitialButtons - 700, buttonsize);
        nextButton = drawImageButton("images/MenuButtons/nextbutton.png", Gdx.graphics.getWidth() - 90, 50, buttonsize / 5);

        diceButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {

                return true;
            }
        });

        nextButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {

                return true;
            }
        });

        cheatButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {

                return true;
            }
        });

        buyButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(Gdx.input.justTouched()){
                    //winning();
                    //screenOutput = playerOperation.buying();
                }return true;
            }
        });

        stage.addActor(buyButton);
        stage.addActor(diceButton);
        stage.addActor(cheatButton);
        stage.addActor(nextButton);

        InputBackProcessor inputProcessor = new InputBackProcessor(monopoly);
        InputMultiplexer inputMultiplexer = new InputMultiplexer(inputProcessor.backDoesNothingProcessor(), stage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta) {
        moneyfont = new BitmapFont();
        moneyfont.setColor(Color.WHITE);
        moneyfont.getData().setScale(4, 4);

        stage.act(Gdx.graphics.getDeltaTime()); //Perform ui logic
        stage.draw(); //Draw the ui

        if (player1 != null && player2 != null) {
            moneyfont.draw(batch, player1.getName() + ": " + player1.getBankBalance(), 0, Gdx.graphics.getHeight() - 100);
            moneyfont.draw(batch, player2.getName() + ": " + player2.getBankBalance(), 0, Gdx.graphics.getHeight() - 150);
        }
        if (player3 != null) {
            moneyfont.draw(batch, player3.getName() + ": " + player3.getBankBalance(), 0, Gdx.graphics.getHeight() - 200);
        }
        if (player4 != null) {
            moneyfont.draw(batch, player4.getName() + ": " + player4.getBankBalance(), 0, Gdx.graphics.getHeight() - 250);
        }
    }

    @Override
    public void switchScreenDelayed(GameScreenAdapter screen, float delay) {

    }
}
