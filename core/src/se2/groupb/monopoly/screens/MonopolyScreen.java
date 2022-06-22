package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;

import se2.groupb.monopoly.CreateGameField;
import se2.groupb.monopoly.Deck;
import se2.groupb.monopoly.DiceRoll;
import se2.groupb.monopoly.Monopoly;
import se2.groupb.monopoly.Player;
import se2.groupb.monopoly.PlayerOperation;
import se2.groupb.monopoly.Pot;
import se2.groupb.monopoly.Property;
import se2.groupb.monopoly.network.messages.NextTurnMessage;
import se2.groupb.monopoly.network.messages.PlayerInformation;

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

    //Marko
    private Pot moneyPot;
    private DiceRoll diceRoll;
//    private int currentPlayerId;
    private String screenOutput;
    private Texture dice1;
    private Texture dice2;
    private PlayerOperation playerOperation;
    private ArrayList<Player> playerList;

    //Andy
    private CreateGameField gameField;

    //Alen
    private BitmapFont moneyfont;
    private int player1mon, player2mon, player3mon, player4mon;
    private ArrayList<Integer> sum;
    private ArrayList<String> placement;
    private ArrayList<Property> player1Propertylist;
    private ArrayList<Property> player2Propertylist;
    private ArrayList<Property> player3Propertylist;
    private ArrayList<Property> player4Propertylist;

    //Vivi
    private Deck communityCards;
    private Deck eventCards;
    public boolean showCard;
    private Timer timerCard;

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

        //init Marko
        screenOutput = "";
        dice1 = new Texture("images/Dice/dice_0.png");
        dice2 = new Texture("images/Dice/dice_0.png");
        moneyPot = new Pot();

        playerList = new ArrayList<>();

        //init Alen
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

        //init Vivi
        timerCard = new Timer();

        if (monopoly.isOfflineGame()) {
            initOfflinePlayer();
        } else {
            initOnlinePlayer();
        }
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

        gameField = new CreateGameField(monopoly, playerList);
        playerOperation = new PlayerOperation(playerList);
        diceRoll = new DiceRoll(playerOperation.getCurrentPlayer());
        initCardDeck();


        diceButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (Gdx.input.justTouched() && diceRoll.getOnTurn() && clientIsCurrentPlayer()) {
                    System.out.println("SSSsssssss " + playerOperation.getCurrentPlayer().getId());
                    int dice = diceRoll.roll(playerOperation.getCurrentPlayer());
                    ArrayList<Texture> l = diceRoll.getDiceTextures();
                    dice1 = l.get(0);
                    dice2 = l.get(1);
                    playerOperation.getCurrentPlayer().move(dice);
                    playerOperation.setMoneyPotForOperation(moneyPot);
                    screenOutput = playerOperation.checkCurrentProperty(playerOperation.getCurrentPlayer());
                    playerOperation.getCurrentPlayer().move(gameField.positions[playerOperation.getCurrentPlayer().getPosition()]);
//                    gameField.checkIfPlayerIsAlone(monopoly.getClient().getPlayerOperation().getCurrentPlayer());
                }
                return true;
            }
        });

        nextButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (Gdx.input.justTouched() && clientIsCurrentPlayer()) {

                    if (!diceRoll.getOnTurn()) {

                        monopoly.getClient().getPlayer().setPlayer(playerOperation.getCurrentPlayer());

                        diceRoll.reset();
                        NextTurnMessage ntm = new NextTurnMessage();
                        ntm.setBankBalance(playerOperation.getCurrentPlayer().getBankBalance());
                        ntm.setId(playerOperation.getCurrentPlayer().getId());
                        ntm.setMyProperties(playerOperation.getCurrentPlayer().getMyProperties());
                        ntm.setPosition(playerOperation.getCurrentPlayer().getPosition());
                        ntm.setNumOfTrainstations(playerOperation.getCurrentPlayer().getNumOfTrainstaitions());
                        monopoly.getClient().getClient().sendTCP(ntm);
                        screenOutput = playerOperation.nextPlayer();
//                        screenOutput = playerOperation.nextPlayer(monopoly.getClient().getNextTurnMessage().getNextTurnPlayerID());
//                        monopoly.getClient().getClient().sendTCP(playerOperation.getCurrentPlayer().getBankBalance());
//                        monopoly.getClient().getClient().sendTCP(playerOperation.getCurrentPlayer().get);


//                        monopoly.getClient().getPlayer().setMessageType("NEXTTURN");
//                        PlayerInformation play = monopoly.getClient().getPlayer();
//                        monopoly.getClient().getClient().sendTCP(play.getPlayer());
                    } else {
                        screenOutput = "It's still " + playerOperation.getCurrentPlayer().getName() + "'s turn";
                    }
                }
                return true;
            }
        });

        cheatButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (!clientIsCurrentPlayer()) {
                    diceRoll.reportCheat();
                }
                return true;
            }
        });

        buyButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (Gdx.input.justTouched() && clientIsCurrentPlayer()) {
                    //winning();
                    screenOutput = playerOperation.buying();
                    if (playerOperation.isBought()) {
                        gameField.changeColor(playerOperation.getCurrentPlayer().getPosition(), playerOperation.getCurrentPlayer().getColor());
                    }
                }
                return true;
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
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameField.render(Gdx.graphics.getDeltaTime());

        moneyfont = new BitmapFont();
        moneyfont.setColor(Color.WHITE);
        moneyfont.getData().setScale(4, 4);

        stage.act(Gdx.graphics.getDeltaTime()); //Perform ui logic
        stage.draw(); //Draw the ui

        batch.begin();

        diceRoll.checkManualPachCount();
        diceRoll.checkForShakeCheat();
        drawDice(dice1, dice2);

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
        moneyfont.draw(batch, screenOutput, (float) (Gdx.graphics.getWidth() / 3.75), yPosInitialButtons + 250f);
        moneyfont.draw(batch, "Pot: " + moneyPot.getAmount(), 0, Gdx.graphics.getHeight() - 400f);

        if (playerOperation.getCardBoolean()) {
            batch.draw(playerOperation.getCardTexture(), (Gdx.graphics.getWidth() / 2) - 100, (Gdx.graphics.getHeight() / 3) - 200, 600, 750);
            timerCard.schedule(new Timer.Task() {
                @Override
                public void run() {
                    playerOperation.setCardBoolean(false);
                }
            }, 3);
            timerCard.stop();
        }
        if(monopoly.getClient().getNextTurnMessage() != null) {
            if (monopoly.getClient().getNextTurnMessage().getNextTurnPlayerID() != playerOperation.getCurrentPlayer().getId()) {
//                screenOutput = monopoly.getClient().getPlayerOperation().nextPlayer();
                for(Player player : playerList){
                    if(monopoly.getClient().getNextTurnMessage().getId() == player.getId()){
                        player.setPosition(monopoly.getClient().getNextTurnMessage().getPosition());
                        player.setNumOfTrainstaitions(monopoly.getClient().getNextTurnMessage().getNumOfTrainstations());
                        player.setBankBalance(monopoly.getClient().getNextTurnMessage().getBankBalance());
                        player.setMyProperties(monopoly.getClient().getNextTurnMessage().getMyProperties());
                    }
                }
//                playerOperation ;

//                playerOperation.getCurrentPlayer().setId(monopoly.getClient().getNextTurnMessage().getNextTurnPlayerID());
            }
        }
        batch.end();
    }

    @Override
    public void switchScreenDelayed(GameScreenAdapter screen, float delay) {
        /**
         *not used
         */
    }

    @Override
    public void dispose() {
        batch.dispose();
        moneyfont.dispose();
        stage.dispose();
        dice1.dispose();
        dice2.dispose();
    }

    public void initOfflinePlayer() {
        player1 = new Player(1, "Blue", 2000, player1Propertylist, 0, Color.BLUE);
        player1.createSpielfigur();
        player2 = new Player(2, "Red", 2000, player2Propertylist, 0, Color.RED);
        player2.createSpielfigur();
        player3 = new Player(3, "Yellow", 2000, player3Propertylist, 0, Color.YELLOW);
        player3.createSpielfigur();
        player4 = new Player(4, "Green", 2000, player4Propertylist, 0, Color.GREEN);
        player4.createSpielfigur();
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);
    }

    public void initOnlinePlayer() {
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

    public void drawDice(Texture d1, Texture d2) {
        batch.draw(d1, xPosButtons + 500, yPosInitialButtons - 400, 500, 500);
        batch.draw(d2, xPosButtons, yPosInitialButtons - 400, 500, 500);
    }

    private void initCardDeck() {
        communityCards = new Deck();
        communityCards.initializeGemeinschaftskartenStapel();
        communityCards.shuffle();
        eventCards = new Deck();
        eventCards.initializeEreigniskartenStapel();
        eventCards.shuffle();
        playerOperation.setCommunityCards(communityCards);
        playerOperation.setEventCards(eventCards);
    }

    public boolean clientIsCurrentPlayer() {
        System.out.println("client ID: " + monopoly.getClient().getPlayer().getPlayer().getId());
        System.out.println("PlayerOP ID: " + playerOperation.getCurrentPlayer().getId());
//        return playerOperation.getCurrentPlayer().getId() == monopoly.getClient().getPlayer().getPlayer().getId();
        if(monopoly.getClient().getNextTurnMessage() == null){
            return monopoly.getClient().getPlayer().getCurrentPlayerID() == monopoly.getClient().getPlayer().getPlayer().getId();
        }else{
            return monopoly.getClient().getNextTurnMessage().getNextTurnPlayerID() == monopoly.getClient().getPlayer().getPlayer().getId();

        }

    }

}
