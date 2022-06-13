package se2.groupb.monopoly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import se2.groupb.monopoly.screens.GameScreenAdapter;
import se2.groupb.monopoly.screens.InputBackProcessor;
import se2.groupb.monopoly.screens.WinningScreen;


public class CreateGameField extends GameScreenAdapter {

    Monopoly monopoly;
    SpriteBatch spriteBatch;
    SpriteBatch spriteBatch2;
    private Environment environment;
    private OrthographicCamera camera;
    private ModelBatch modelBatch;
    private BitmapFont moneyfont;
    private Property[] logicalGameField;

    private Stage stage;
    private ImageButton buyButton;
    private ImageButton diceButton;
    private ImageButton cheatButton;
    private ImageButton nextButton;
    private LogicalGameField gameField;


    private Field[] fields = new Field[40];

    //private String buildingPath = "Spielfeld\\field.g3dj";


    private Texture rollDice = new Texture("images/MenuButtons/roll.png");
    private Texture reportCheat = new Texture("images/MenuButtons/report_cheat.png");
    private Texture BuyButton = new Texture("images/MenuButtons/buy_building.png");


    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private Pot moneyPot = new Pot();
//    private DiceRoll diceRoll = new DiceRoll();
    private ArrayList<Player> players = new ArrayList();
    public int player1mon = 0, player2mon = 0, player3mon = 0, player4mon = 0;
    //private int[] sums = new int[4];
    //private String[] placement = new String[4];
    public ArrayList<Integer> sum = new ArrayList<>();
    public ArrayList<String> placement = new ArrayList<>(4);

    private Deck ereigniskartenDeck = new Deck();
    private Deck gemeinschaftskartenDeck = new Deck();
    private Texture kartenHintergrund;
    public boolean showCard;
    private Timer timerCard = new Timer();


    // private CameraInputController cameraController;
    public Vector3[] positions = new Vector3[40];


    ArrayList<Property> arrayList = new ArrayList();
    ArrayList<Property> arrayList2 = new ArrayList();
    ArrayList<Property> arrayList3 = new ArrayList();
    ArrayList<Property> arrayList4 = new ArrayList();


    private int buttonSizeX;
    private int buttonSizeY;
    private float buttonsize;
    private float yPosInitialButtons;
    private float yPosOffsetButtons;
    private float xPosButtons;

    private boolean AccelerometerActive = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);

    private float xAccel;
    private float yAccel;
    private float zAccel;

    private boolean cheatActivated;
    private boolean shakeCheatActivated;
    private boolean onTurn;
    private boolean reported;
    private boolean keyVolumeUp;

    private Random random = new Random();
    private int cheatDice;
    private int pachCount;
    private int currentPlayerId;
    private int playerCount;
    private String screenOutput;
    private int roundCount;

    private Texture dice1;
    private Texture dice2;

    private int currentPos = 0;

    // variables for fieldoffsets
    public float leftX = 9.535f;
    public float leftZ = 68f;
    public float topX = 71.05f;
    public float topZ = 30f;
    public float rightX = 40f;
    public float rightZ = 3.25f;

    Model[] fieldModel = new Model[40];
    ModelInstance[] fieldModInstance = new ModelInstance[40];

    private void createBotPositions() {
        float[] botPos = {0, -6.5f, -13f, -19.5f, -26f, -32.5f, -39f, -45.5f, -52f, -58.5f, -65f};
        for (int i = 0; i < 11; i++) {
            positions[i] = new Vector3(0f, 3.5f, botPos[i]);
        }
    }

    private void createLeftPositions() {
        float[] leftPos = {9.522501f, 16.022501f, 22.522501f, 29.022501f, 35.5225f, 42.0225f, 48.5225f, 55.0225f, 61.5225f};
        int count = 0;
        for (int i = 11; i < 20; i++) {
            positions[i] = new Vector3(leftPos[count], 3.5f, -68f);
            count++;
        }
    }

    private void createTopPositions() {
        float[] topPos = {-65f, -58.5f, -52f, -45.5f, -39f, -32.5f, -26f, -19.5f, -13f, -6.5f, 0f};
        int count = 0;
        for (int i = 20; i < 31; i++) {
            positions[i] = new Vector3(71.05f, 3.5f, topPos[count]);
            count++;
        }
    }

    private void createRightPositions() {
        float[] rightPos = {61.5f, 55.0f, 48.5f, 42.0f, 35.5f, 29.0f, 22.5f, 16.0f, 9.5f};
        int count = 0;
        for (int i = 31; i < 40; i++) {
            positions[i] = new Vector3(rightPos[count], 3.5f, 3.25f);
            count++;
        }
    }

    private void createPositions() {
        createBotPositions();
        createLeftPositions();
        createTopPositions();
        createRightPositions();
    }


    public CreateGameField(Monopoly monopoly) {
        super(monopoly);
        spriteBatch = new SpriteBatch();
        buttonSizeX = Gdx.graphics.getWidth() / 3;
        buttonSizeY = (int) (Gdx.graphics.getHeight() / (4.545454 * 2));

        GameFieldUnits gf = new GameFieldUnits();
        gf.createField("monopoly");
        fields = gf.getFields();

        buttonsize = (float) (Gdx.graphics.getWidth()/3D);
        xPosButtons = (float) (Gdx.graphics.getWidth() / 2D - buttonSizeX / 2D);
        yPosInitialButtons = (float) (Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4D);
        yPosOffsetButtons = (float) (-Gdx.graphics.getWidth() / 8D);

        dice1 = new Texture("images/Dice/dice_0.png");
        dice2 = new Texture("images/Dice/dice_0.png");

        ereigniskartenDeck.initializeEreigniskartenStapel();
        ereigniskartenDeck.shuffle();
        gemeinschaftskartenDeck.initializeGemeinschaftskartenStapel();
        gemeinschaftskartenDeck.shuffle();
        kartenHintergrund = new Texture("images/KartenImages/Karte1.png");
        showCard = false;
        keyVolumeUp = false;


        onTurn = true;
        cheatActivated = reported = shakeCheatActivated = false;
        cheatDice = pachCount = 0;
        currentPlayerId = 1;
        playerCount = 4;
        screenOutput = "";

        gameField = new LogicalGameField();


//        Gdx.app.setLogLevel(Application.LOG_DEBUG);
//        Gdx.app.debug("GDSAFA", "Hello");
        createPositions();
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1f));

        modelBatch = new ModelBatch();


        camera = new OrthographicCamera(100, 100);
        camera.rotate(90);
        camera.position.set(40f, 100f, -40f);  // 20,100,-20
//         camera.rotate(new Vector3(3, -45, 3), -50); //changed the view of se Camera
//        camera.position.set(Gdx.graphics.getHeight() / 2 - 700,Gdx.graphics.getWidth() / 2, 5);
//        camera.position.set(500f,500f,200f);
        camera.lookAt(40f, 0f, -40f);  // 850f, 100f, -200f
        camera.zoom = 1;


//        camera.near = -10000f;
        camera.far = 500000f;
        createModels();


//        if(!monopoly.getClient().getOtherPlayers().isEmpty()){
//            player1 = monopoly.getClient().getPlayer().getPlayer();
//            player1.createSpielfigur();
//            if (monopoly.getClient().getOtherPlayers().size() == 1){
//                player2 = monopoly.getClient().getOtherPlayers().get(0).getPlayer();
//                player2.createSpielfigur();
//            }else if (monopoly.getClient().getOtherPlayers().size() == 2){
//                player2 = monopoly.getClient().getOtherPlayers().get(0).getPlayer();
//                player2.createSpielfigur();
//                player3 = monopoly.getClient().getOtherPlayers().get(1).getPlayer();
//                player3.createSpielfigur();
//            }else if (monopoly.getClient().getOtherPlayers().size() == 3){
//                player2 = monopoly.getClient().getOtherPlayers().get(0).getPlayer();
//                player2.createSpielfigur();
//                player3 = monopoly.getClient().getOtherPlayers().get(1).getPlayer();
//                player3.createSpielfigur();
//                player4 = monopoly.getClient().getOtherPlayers().get(2).getPlayer();
//                player4.createSpielfigur();
//            }
//        }
        player1 = new Player(1, "Blue", 2000, arrayList, 0, Color.BLUE);
        player1.createSpielfigur();
        player2 = new Player(2, "Red", 2000, arrayList2, 0, Color.RED);
        player2.createSpielfigur();
        player3 = new Player(3, "Yellow", 2000, arrayList3, 0, Color.YELLOW);
        player3.createSpielfigur();
        player4 = new Player(4, "Green", 2000, arrayList4, 0, Color.GREEN);
        player4.createSpielfigur();

        camera.update();

//        cameraController = new CameraInputController(camera);
//        Gdx.input.setInputProcessor(cameraController);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());

        Gdx.input.setInputProcessor(stage);
        buyButton = drawImageButton("images/MenuButtons/buy_building.png", 180, yPosInitialButtons-45,buttonsize/2);
        diceButton = drawImageButton("images/MenuButtons/roll.png", xPosButtons + 500, yPosInitialButtons - 500, buttonsize);
        cheatButton = drawImageButton("images/MenuButtons/report_cheat.png",xPosButtons + 500, yPosInitialButtons - 700, buttonsize);
        nextButton = drawImageButton("images/MenuButtons/nextbutton.png", Gdx.graphics.getWidth()-90,50, buttonsize/5);


        diceButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                    if(Gdx.input.justTouched()) {
                        int dice = roll();
                        getCurrentPlayer().move(dice);
//                checkIfPlayerIsAlone(getCurrentPlayer());
                        //getCurrentPlayer().setPosition((getCurrentPlayer().getPosition() + dice) % 40);
                        getCurrentPlayer().move(positions[getCurrentPlayer().getPosition()]);
                        checkCurrentProperty();
                        if (!onTurn) {
                            nextPlayer();
                            roundCount++;
                        }
                    }
                    return true;
            }
        });

        nextButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(Gdx.input.justTouched()){
                    /** Here do something Andy*/
                }
                return true;
            }
        });

        cheatButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(Gdx.input.justTouched()){
                    if (cheatActivated) {
                        getCurrentPlayer().changeMoney(-200);
                        player2.changeMoney(100);
                    } else {
                        player2.changeMoney(-100);
                    }
                    reported = true;
                    return true;
                }
                return false;
            }
        });

        buyButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(Gdx.input.justTouched()){
                   winning();
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



    public void checkIfPlayerIsAlone(Player player) {
        ArrayList<Player> playersToPosition = new ArrayList<>();
        if (player.getPosition() == player1.getPosition() && player != player1) {
            playersToPosition.add(player1);
        }
        if (player.getPosition() == player2.getPosition() && player != player2) {
            playersToPosition.add(player2);
        }
        if (player.getPosition() == player3.getPosition() && player != player3) {
            playersToPosition.add(player3);
        }
        if (player.getPosition() == player4.getPosition() && player != player4) {
            playersToPosition.add(player4);
        }
        setMultiplePlayersOnField(playersToPosition);
    }

    public void setMultiplePlayersOnField(ArrayList<Player> playersToPosition) {


//        if (players.size() > 0) {
//            for (Player player : players) {
//                if (currentPlayerId != player.getId()) {
//                    if (currentPlayerId < 10) {
//                        positions[player.getPosition()].x -= 1;
//                        player.move(positions[player.getPosition()]);
//                    }
//                }
//            }
//        }
    }

    @Override
    public void render(float delta) {

        final float userPosX = (float) Gdx.input.getX();
        final float userPosY = (float) Gdx.graphics.getHeight() - Gdx.input.getY();
        int count = 0;

        spriteBatch2 = new SpriteBatch();
        moneyfont = new BitmapFont();

        ScreenUtils.clear(0, 0, 0, 1);
        // cameraController.update();

        // Clear the stuff that is left over from the previous render cycle
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        modelBatch.begin(camera);
        spriteBatch.begin();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();


        // Let our ModelBatch take care of efficient rendering of our ModelInstance


        modelBatch.render(player1.modInstance, environment);
        modelBatch.render(player2.modInstance, environment);
        modelBatch.render(player3.modInstance, environment);
        modelBatch.render(player4.modInstance, environment);

        /**
         * Set pach Cheat
         */
        if(Gdx.input.isKeyPressed(Input.Keys.VOLUME_UP)){
            if(!keyVolumeUp){
                keyVolumeUp = true;
                cheatDice++;
            }
        }else{
            keyVolumeUp = false;
        }


        renderModels();
        drawDice(dice1, dice2);

        /**
         * Fonts for the Accounts of players
         */
        moneyfont.setColor(Color.WHITE);
        moneyfont.getData().setScale(4, 4);
        moneyfont.draw(spriteBatch, player1.getName() + ": " + player1.getBankBalance(), 0, Gdx.graphics.getHeight() - 100);
        moneyfont.draw(spriteBatch, player2.getName() + ": " + player2.getBankBalance(), 0, Gdx.graphics.getHeight() - 150);
        moneyfont.draw(spriteBatch, player3.getName() + ": " + player3.getBankBalance(), 0, Gdx.graphics.getHeight() - 200);
        moneyfont.draw(spriteBatch, player4.getName() + ": " + player4.getBankBalance(), 0, Gdx.graphics.getHeight() - 250);
        moneyfont.draw(spriteBatch, screenOutput, (float) (Gdx.graphics.getWidth() / 3.75), yPosInitialButtons + 250);
        moneyfont.draw(spriteBatch, "Rounds: " + roundCount, (float) (Gdx.graphics.getWidth()*0.9),yPosInitialButtons + 250);
        moneyfont.draw(spriteBatch, "Pot: " + moneyPot.getAmount(), 0, Gdx.graphics.getHeight() - 400);

            /**
             * Check if phone is shaking while pressing volume down
             */
            if (Gdx.input.isKeyPressed(Input.Keys.VOLUME_DOWN) && !shakeCheatActivated) {
                if (AccelerometerActive) {
                    xAccel = Gdx.input.getAccelerometerX();
                    yAccel = Gdx.input.getAccelerometerY();
                    zAccel = Gdx.input.getAccelerometerZ();
                    if (xAccel < -15 || xAccel > 15 || yAccel < -15 || yAccel > 15 || zAccel < -15 || zAccel > 15) {
                        getCurrentPlayer().changeMoney(100);
                        cheatActivated = shakeCheatActivated = true;
                    }
                }
            }



        /**
         * Check showCard is true and draw the card
         */
        if (showCard) {
            spriteBatch.draw(kartenHintergrund, (Gdx.graphics.getWidth() / 2) - 100, (Gdx.graphics.getHeight() / 3) - 200, 600, 750);
            timerCard.schedule(new Timer.Task() {
                @Override
                public void run() {
                    showCard = false;
                }
            }, 5);
            timerCard.stop();
        }
        spriteBatch.end();
        modelBatch.end();
    }

        @Override
        public void dispose () {
            modelBatch.dispose();
            spriteBatch.dispose();
            moneyfont.dispose();
            stage.dispose();

//        ml1.dispose();
//        ml2.dispose();
            disposeModels();
        }

    @Override
    public void switchScreenDelayed(GameScreenAdapter screen, float delay) {

    }
    //spielfigur.move(new Vector3(0f,0f,-6.5f));


        public void renderModels () {
            for (int i = 0; i < fields.length; i++) { // Don't forget to render the models
                modelBatch.render(fieldModInstance[i], environment);

            }
        }

        public void disposeModels () {
            for (int i = 0; i < fieldModel.length; i++) {
                fieldModel[i].dispose();
            }
        }


        public int roll () {
            int firstDice = random.nextInt(6) + 1;
            int secondDice = 0;
            if (Gdx.input.isKeyPressed(Input.Keys.VOLUME_DOWN) && cheatDice == 0) {
                secondDice = firstDice;
                cheatActivated = true;
            } else if (Gdx.input.isKeyPressed(Input.Keys.VOLUME_DOWN) && cheatDice != 0) {
                if (cheatDice >= 6) {
                    firstDice = secondDice = 6;
                } else {
                    firstDice = secondDice = cheatDice;
                }
                cheatActivated = true;
            } else {
                secondDice = random.nextInt(6) + 1;
            }
            onTurn = false;
            dice1 = setDice(firstDice);
            dice2 = setDice(secondDice);
            drawDice(dice1, dice2);

            if (firstDice == secondDice) {
                onTurn = true;
                pachCount++;
                getCurrentPlayer().setPrison(false);
            }
            if (pachCount > 2) {
                onTurn = false;
                getCurrentPlayer().goToJail();
                getCurrentPlayer().move(positions[getCurrentPlayer().getPosition()]);
            }
            cheatDice = 0;
            return firstDice + secondDice;
        }

        private Texture setDice ( int value){
            String path;
            switch (value) {
                case 1:
                    path = "images/Dice/dice_1.png";
                    break;
                case 2:
                    path = "images/Dice/dice_2.png";
                    break;
                case 3:
                    path = "images/Dice/dice_3.png";
                    break;
                case 4:
                    path = "images/Dice/dice_4.png";
                    break;
                case 5:
                    path = "images/Dice/dice_5.png";
                    break;
                case 6:
                    path = "images/Dice/dice_6.png";
                    break;
                default:
                    path = "images/Dice/dice_0.png";
                    break;
            }
            return new Texture(path);
        }

        private void drawDice (Texture d1, Texture d2){
            spriteBatch.draw(d1, xPosButtons + 500, yPosInitialButtons - 400, 500, 500);
            spriteBatch.draw(d2, xPosButtons, yPosInitialButtons - 400, 500, 500);
        }

        /**
         * Method for output of the field on the Screen
         */
        private void checkCurrentProperty () {
            int playerPosition = getCurrentPlayer().getPosition();
            String propertyType = getPropertyType(playerPosition);
            String output = "";
            switch (propertyType) {
                case "Street":
                    output = "Spieler " + getCurrentPlayer().getName() + " befindet sich auf " + gameField.getGameField()[getCurrentPlayer().getPosition()].getName();
                    if (isSomeonesProperty(playerPosition) && (getCurrentPlayer().getId() != getPropertyOwner(playerPosition).getId())) {
                        Street s = (Street) gameField.getGameField()[playerPosition];
                        output = getCurrentPlayer().payToOtherPlayer(getPropertyOwner(playerPosition), s.getRent());
                    }
                    break;
                case "Trainstation":
                    output = "Spieler " + getCurrentPlayer().getName() + " befindet sich auf " + gameField.getGameField()[getCurrentPlayer().getPosition()].getName();
                    if (isSomeonesProperty(playerPosition) && (getCurrentPlayer().getId() != getPropertyOwner(playerPosition).getId())) {
                        Trainstation t = (Trainstation) gameField.getGameField()[playerPosition];
                        output = getCurrentPlayer().payToOtherPlayer(getPropertyOwner(playerPosition), t.getRent() * getPropertyOwner(playerPosition).getNumOfTrainstaitions());
                    }
                    break;
                case "PenaltyField":
                    PenaltyField p = (PenaltyField) gameField.getGameField()[playerPosition];
                    output = moneyPot.donateToPot(getCurrentPlayer(), p.getPenalty());
                    break;
                case "Property":
                    Property prop = gameField.getGameField()[playerPosition];
                    output = checkSoleProperty(prop);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + propertyType);
            }
            screenOutput = output;
        }

        private String screenOutputCheck () {
            String playerName = getCurrentPlayer().getName();
            String playerPosition = gameField.getGameField()[getCurrentPlayer().getPosition()].getName();
            return playerName + " befindet sich bei " + playerPosition;
        }

        /**
         * Method for controll of special fields as well as their methods
         * @param property to be controlled
         * @return additional Output text for the screen for special fields
         */
        private String checkSoleProperty (Property property){
            String output = "Spieler " + getCurrentPlayer().getName();
            switch (property.getName()) {
                case "Los":
                    getCurrentPlayer().changeMoney(200);
                    output += " ist direkt auf Los gekommen und zieht 400€ ein.";
                    break;
                case "Gemeinschaftsfeld":
                    output += " ist auf einem Gemeinschaftsfeld.";
                    kartenHintergrund = getCurrentPlayer().drawCard(gemeinschaftskartenDeck);
                    showCard = true;

                    break;
                case "Ereignisfeld":
                    output += " ist auf einem Ereignisfeld.";
                    kartenHintergrund = getCurrentPlayer().drawCard(ereigniskartenDeck);
                    showCard = true;
                    break;
                case "Gefängnis":
                    if(getCurrentPlayer().getPrison()){
                        int häfn = 3-getCurrentPlayer().getPrisonCount();
                        output += " sitzt noch für " + häfn + " Runden im Geföngnis";
                    }else{
                        output += " ist nur zu Besuch im Gefägnis.";
                    }
                    break;
                case "Sofa":
                    output = moneyPot.winPot(getCurrentPlayer());
                    break;
                case "Gehe ins Gefängnis":
                    getCurrentPlayer().move(positions[10]);
                    getCurrentPlayer().goToJail();
                    output += " sitzt jetzt im Gefägnis.";
                    break;
            }
            return output;
        }

        /**
         * method for the prison
         */
        private void checkPrison () {
            int playerPosition = getCurrentPlayer().getPosition();
            if (playerPosition == 30) {
                getCurrentPlayer().setPrison(true);
            }
        }

        private boolean isSomeonesProperty ( int position){
            return gameField.getGameField()[position].getOwnerId() != 0;
        }

        private Player getPropertyOwner ( int pos){
            return getPlayerById(gameField.getGameField()[pos].getOwnerId());
        }

        private void nextPlayer () {
            if (currentPlayerId == playerCount) {
                currentPlayerId = 1;
            } else {
                currentPlayerId++;
            }
            //screenOutput = "";
            reset();
        }

    /**
     * Change Method with Server
     */
        private Player getCurrentPlayer () {
            return getPlayerById(currentPlayerId);
        }

        private Player getPlayerById ( int id){
            Player p;
            switch (id) {
                case 1:
                    p = player1;
                    break;
                case 2:
                    p = player2;
                    break;
                case 3:
                    p = player3;
                    break;
                case 4:
                    p = player4;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + currentPlayerId);
            }
            return p;
        }

        private void reset () {
            cheatActivated = shakeCheatActivated = reported = false;
            onTurn = true;
            cheatDice = 0;
            pachCount = 0;
        }

        public static boolean isCorrectPosition(float userPosX, float userPosY, float xPosButton,
                                                float yPosButton, float buttonSizeX, float buttonSizeY, float yPosOffset){
            return (userPosX > xPosButton && userPosX < xPosButton + buttonSizeX && userPosY > (yPosButton + yPosOffset) && userPosY < yPosButton + yPosOffset + buttonSizeY);
        }

        public String getPropertyType ( int n){
            return getLastSubString("" + gameField.getGameField()[n].getClass());
        }

        private String getLastSubString (String filename){
            String[] parts = filename.split("\\."); // String array, each element is text between dots
            return parts[parts.length - 1];
        }


        public void createModels () {
            transformModelsOnField(0, 10, 0, 0, 0, 0);
            transformModelsOnField(11, 19, leftX, leftZ, 270, 0);
            transformModelsOnField(20, 30, topX, topZ, 180, 0);
            transformModelsOnField(31, 39, rightX, rightZ, 90, 3);
        }


        public void transformModelsOnField ( int start, int end, float v3X, float v3Z, int degrees,
        int offset){
            Vector3 vector3 = new Vector3(0, 0, 0);
            Vector3 vector3Rotate = new Vector3(0, 1, 0);
            float distanceWidth = 6.5f;
            for (int i = start; i <= end; i++) {
                fieldModInstance[i] = new ModelInstance(fields[i].getModel());
                fieldModInstance[i].materials.get(1).set(new ColorAttribute(ColorAttribute.Diffuse, fields[i].getFieldColors()));
                if (i <= 10) { // bot side
                    vector3.z = -((i) * distanceWidth);
                }
                if (i >= 11 && i <= 19) { // left side
                    vector3.x = ((i - v3X) * distanceWidth);
                    vector3.z = -v3Z;
                }
                if (i >= 20 && i <= 30) { // top side
                    vector3.x = v3X;
                    vector3.z = ((i - v3Z) * distanceWidth);
                }
                if (i >= 31 && i < fields.length) { // right Side
                    vector3.x = -((i - v3X) * distanceWidth) + offset;
                    vector3.z = v3Z;
                }
                fieldModInstance[i].transform.translate(vector3);
                fieldModInstance[i].transform.rotate(vector3Rotate, degrees);
            }
        }

        /**
         * Winning Condition which changes to the final Screen of the game
         */
        public void winning () {
            int amount = 0;


            for (int i = 0; i < 40; i++) {
                if (gameField.getGameField()[i].getOwnerId() == 1) {
                    String propertyType = getPropertyType(i);
                    switch (propertyType) {
                        case "Street":
                            Street s = (Street) gameField.getGameField()[i];
                            amount = s.getPrice();
                            amount += s.getHousePrice();
                            amount += s.getHotel();
                            player1mon += amount;
                            amount = 0;
                            break;
                        case "Trainstation":
                            Trainstation t = (Trainstation) gameField.getGameField()[i];
                            amount = t.getPrice();
                            player1mon += amount;
                            amount = 0;
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + propertyType);
                    }
                }
            }
            player1mon += player1.getBankBalance();
            player2mon += player2.getBankBalance();
            player3mon += player3.getBankBalance();
            player4mon += player4.getBankBalance();

            sum.add(player1mon);
            sum.add(player2mon);
            sum.add(player3mon);
            sum.add(player4mon);
            Collections.sort(sum);
            //Arrays.sort(sum);

            for (int j = 0; j < 4; j++) {
                if (sum.get(j) == player1mon) {
                    placement.add(0, player1.getName());
                    //placement.set(j, player1.getName());
                } else if (sum.get(j) == player2mon) {
                    //placement.set(j, player2.getName());
                    placement.add(1,player2.getName());
                } else if (sum.get(j) == player3mon) {
                    //placement.set(j, player3.getName());
                    placement.add(2,player3.getName());
                } else if (sum.get(j) == player4mon) {
                    //placement.set(j, player4.getName());
                    placement.add(3,player4.getName());
                }
            }


            //monopoly.setSums(sums);
            //monopoly.setPlacement(placement);
            /** Debugging necessary*/
            monopoly.setScreen(new WinningScreen(monopoly, sum, placement));
        }

        public void buying() {
            int pos = getCurrentPlayer().getPosition();
            if (!isSomeonesProperty(pos)) {
                String propertyType = getPropertyType(pos);
                switch (propertyType) {
                    case "Street":
                        Street s = (Street) gameField.getGameField()[pos];
                        getCurrentPlayer().changeMoney(-s.getPrice());
                        gameField.getGameField()[pos].setOwnerId(getCurrentPlayer().getId());
                        break;
                    case "Trainstation":
                        Trainstation t = (Trainstation) gameField.getGameField()[pos];
                        getCurrentPlayer().changeMoney(-t.getPrice());
                        gameField.getGameField()[pos].setOwnerId(getCurrentPlayer().getId());
                        t.increaseRent();
                        break;
                    default:
                        if (gameField.getGameField()[pos].getOwnerId() == getCurrentPlayer().getId()) {
                            if (propertyType.equals("Street")) {
                                Street s1 = (Street) gameField.getGameField()[pos];
                                boolean bought = s1.buyhouse();
                                if (bought) {
                                    getCurrentPlayer().changeMoney(-s1.getHousePrice());
                                } else {
                                    break;
                                }
                            } else {
                                screenOutput = "Hier kannst du nichts zukaufen";
                            }
                        } else {
                            screenOutput = "Du kannst das nicht kaufen. Es gehört schon jemandem";
                        }
                }
            } else {
                screenOutput = "Du kannst das nicht kaufen. Es gehört schon jemandem";
            }
        }

    }

