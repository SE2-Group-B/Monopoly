package se2.groupb.monopoly;


import static se2.groupb.monopoly.screens.GameScreenAdapter.drawImageButton;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;

import se2.groupb.monopoly.network.ClientFoundation;
import se2.groupb.monopoly.network.ServerFoundation;
import se2.groupb.monopoly.screens.GameScreenAdapter;
import se2.groupb.monopoly.screens.InputBackProcessor;
import se2.groupb.monopoly.screens.MainMenuScreen;
import se2.groupb.monopoly.screens.SensorScreen;


public class Monopoly extends Game {

//    //Buttons
//    private ImageButton buyButton;
//    private ImageButton diceButton;
//    private ImageButton cheatButton;
//    private ImageButton nextButton;
//    private LogicalGameField logicalGameField;
//    private int buttonSizeX;
//    private int buttonSizeY;
//    private float buttonsize;
//    private float yPosInitialButtons;
//    private float yPosOffsetButtons;
//    private float xPosButtons;
//
//    //Player
//    private Player player1;
//    private Player player2;
//    private Player player3;
//    private Player player4;
//
//    //Andy
////    private OrthographicCamera camera;
////    private ModelBatch modelBatch;
////    private Environment environment;
//    private CreateGameField gameField;
//
//    //Marko
//    private Pot moneyPot;
//    private DiceRoll diceRoll;
//    private int currentPlayerId;
//    //private int playerCount;
//    private String screenOutput;
//    private Texture dice1;
//    private Texture dice2;
//    private PlayerOperation playerOperation;
//    private ArrayList<Player> playerList;
//
//    //Alen
//    private BitmapFont moneyfont;
//    private int player1mon, player2mon, player3mon, player4mon;
//    private ArrayList<Integer> sum;
//    private ArrayList<String> placement;
//    private ArrayList<Property> player1Propertylist;
//    private ArrayList<Property> player2Propertylist;
//    private ArrayList<Property> player3Propertylist;
//    private ArrayList<Property> player4Propertylist;
//
//    //Chris
//    private Stage stage;
    private ClientFoundation client;
//
//    //Vivi
//    private Deck ereigniskartenDeck;
//    private Deck gemeinschaftskartenDeck;
//    private Texture kartenHintergrund;
//    private boolean showCard;
//    private Timer timerCard;
//
    public SpriteBatch batch;

    public Monopoly(){
//       //Init Buttons
//       buttonSizeX = Gdx.graphics.getWidth() / 3;
//       buttonSizeY = (int) (Gdx.graphics.getHeight() / (4.545454 * 2));
//       buttonsize = (float) (Gdx.graphics.getWidth() / 3D);
//       xPosButtons = (float) (Gdx.graphics.getWidth() / 2D - buttonSizeX / 2D);
//       yPosInitialButtons = (float) (Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4D);
//       yPosOffsetButtons = (float) (-Gdx.graphics.getWidth() / 8D);
//
//       //init Player
//       //initServerPlayer();
//       initOfflinePlayer();
//
//       //init Andy
//       gameField = new CreateGameField(this);
//
//       //init Marko
//       currentPlayerId = 1;
//       screenOutput = "";
//       dice1 = new Texture("images/Dice/dice_0.png");
//       dice2 = new Texture("images/Dice/dice_0.png");
//       moneyPot = new Pot();
//       diceRoll = new DiceRoll();
//       playerOperation = new PlayerOperation(initPlayerList());
//       playerList = new ArrayList<>();
//
//       //init Alen
//       moneyfont = new BitmapFont();
//       player1mon = 0;
//       player2mon = 0;
//       player3mon = 0;
//       player4mon = 0;
//       sum = new ArrayList<>();
//       placement = new ArrayList<>();
//       player1Propertylist = new ArrayList<>();
//       player2Propertylist = new ArrayList<>();
//       player3Propertylist = new ArrayList<>();
//       player4Propertylist = new ArrayList<>();
//
//       //init Chris
//
//
//       //Init Vivi
//       logicalGameField = new LogicalGameField();
//       ereigniskartenDeck = new Deck();
//       gemeinschaftskartenDeck = new Deck();
//       ereigniskartenDeck.initializeEreigniskartenStapel();
//       gemeinschaftskartenDeck.initializeGemeinschaftskartenStapel();
//       ereigniskartenDeck.shuffle();
//       gemeinschaftskartenDeck.shuffle();
//       kartenHintergrund = new Texture("images/KartenImages/Karte1.png");
//       showCard = false;
//       timerCard = new Timer();
   }



    /**
     * for later, when online game can be finished
     * do: add server from host
     * when player won close clients and server and return to main menu
     * addServer(ServerFoundation)
     * closeServer()
     */
    /*private ServerFoundation server;*/



    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new MainMenuScreen(this));
//        stage = new Stage(new ScreenViewport());
//
//        Gdx.input.setInputProcessor(stage);
//        buyButton = drawImageButton("images/MenuButtons/buy_building.png", 180, yPosInitialButtons - 45, buttonsize / 2);
//        diceButton = drawImageButton("images/MenuButtons/roll.png", xPosButtons + 500, yPosInitialButtons - 500, buttonsize);
//        cheatButton = drawImageButton("images/MenuButtons/report_cheat.png", xPosButtons + 500, yPosInitialButtons - 700, buttonsize);
//        nextButton = drawImageButton("images/MenuButtons/nextbutton.png", Gdx.graphics.getWidth() - 90, 50, buttonsize / 5);
//
//        playerOperation = new PlayerOperation(initPlayerList());
//        playerOperation.setPlayerCount(4);
//
//        diceButton.addListener(new EventListener() {
//            @Override
//            public boolean handle(Event event) {
//                if (Gdx.input.justTouched() && diceRoll.getOnTurn()) {
//                    int dice = diceRoll.roll(playerOperation.getCurrentPlayer());
//                    ArrayList<Texture> l = diceRoll.getDiceTextures();
//                    dice1 = l.get(0);
//                    dice2 = l.get(1);
//                    playerOperation.getCurrentPlayer().move(dice);
////                checkIfPlayerIsAlone(getCurrentPlayer());
//                    playerOperation.setMoneyPotForOperation(moneyPot);
//                    screenOutput = playerOperation.checkCurrentProperty(playerOperation.getCurrentPlayer());
//                    playerOperation.getCurrentPlayer().move(gameField.positions[playerOperation.getCurrentPlayer().getPosition()]);
//                }
//                return true;
//            }
//        });
//
//        nextButton.addListener(new EventListener() {
//            @Override
//            public boolean handle(Event event) {
//                if(Gdx.input.justTouched()){
//                    if (!diceRoll.getOnTurn()) {
//                        screenOutput = playerOperation.nextPlayer();
//                        diceRoll.reset();
//                    }else{
//                        screenOutput = "It's still " + playerOperation.getCurrentPlayer().getName() + "'s turn";
//                    }
//                }
//                return true;
//            }
//        });
//
//        cheatButton.addListener(new EventListener() {
//            @Override
//            public boolean handle(Event event) {
//                diceRoll.reportCheat();
//                return true;
//            }
//        });
//
//        buyButton.addListener(new EventListener() {
//            @Override
//            public boolean handle(Event event) {
//                if(Gdx.input.justTouched()){
//                    //winning();
//                    screenOutput = playerOperation.buying();
//                }return true;
//            }
//        });
//
//        stage.addActor(buyButton);
//        stage.addActor(diceButton);
//        stage.addActor(cheatButton);
//        stage.addActor(nextButton);
//
//        InputBackProcessor inputProcessor = new InputBackProcessor(this);
//        InputMultiplexer inputMultiplexer = new InputMultiplexer(inputProcessor.backDoesNothingProcessor(), stage);
//        Gdx.input.setInputProcessor(inputMultiplexer);
    }


    @Override
    public void render() {
        super.render();

//
//        moneyfont = new BitmapFont();
//        moneyfont.setColor(Color.WHITE);
//        moneyfont.getData().setScale(4, 4);
//
//        batch.begin();
//
//        diceRoll.checkManualPachCount();
//        diceRoll.checkForShakeCheat();
//        gameField.drawDice(dice1, dice2);
//
//        if (player1 != null && player2 != null) {
//            moneyfont.draw(batch, player1.getName() + ": " + player1.getBankBalance(), 0, Gdx.graphics.getHeight() - 100);
//            moneyfont.draw(batch, player2.getName() + ": " + player2.getBankBalance(), 0, Gdx.graphics.getHeight() - 150);
//        }
//        if (player3 != null) {
//            moneyfont.draw(batch, player3.getName() + ": " + player3.getBankBalance(), 0, Gdx.graphics.getHeight() - 200);
//        }
//        if (player4 != null) {
//            moneyfont.draw(batch, player4.getName() + ": " + player4.getBankBalance(), 0, Gdx.graphics.getHeight() - 250);
//        }
//        moneyfont.draw(batch, screenOutput, (float) (Gdx.graphics.getWidth() / 3.75), yPosInitialButtons + 250);
//        moneyfont.draw(batch, "Pot: " + moneyPot.getAmount(), 0, Gdx.graphics.getHeight() - 400);
//
//        batch.end();
    }

    @Override
    public void dispose() {
//        batch.dispose();
//        moneyfont.dispose();
//        stage.dispose();
    }

    public void addClient(ClientFoundation client){
        this.client = client;
    }

    public ClientFoundation getClient(){
        return this.client;
    }

    public void removeClient(){
        this.client = null;
    }
}
