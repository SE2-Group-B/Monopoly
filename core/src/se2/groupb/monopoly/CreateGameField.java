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

    SpriteBatch spriteBatch;
    SpriteBatch spriteBatch2;
    private Environment environment;
    private OrthographicCamera camera;
    private ModelBatch modelBatch;
    private BitmapFont moneyfont;
    private Stage stage;
    private LogicalGameField gameField;
    private Field[] fields;
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private Deck ereigniskartenDeck = new Deck();
    private Deck gemeinschaftskartenDeck = new Deck();
    private Texture kartenHintergrund;
    public boolean showCard;
    private Timer timerCard = new Timer();
    public Vector3[] positions = new Vector3[40];
    private int buttonSizeX;
    private int buttonSizeY;
    private float buttonsize;
    private float yPosInitialButtons;
    private float yPosOffsetButtons;
    private float xPosButtons;

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
        this.stage = new Stage(new ScreenViewport());
        this.spriteBatch = new SpriteBatch();
        GameFieldUnits gf = new GameFieldUnits();
        gf.createField("monopoly");
        fields = gf.getFields();

        createPositions();
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1f));

        modelBatch = new ModelBatch();

        camera = new OrthographicCamera(100, 100);
        camera.rotate(90);
        camera.position.set(40f, 100f, -40f);  // 20,100,-20
        camera.lookAt(40f, 0f, -40f);  // 850f, 100f, -200f
        camera.zoom = 1;

        camera.far = 500000f;
        createModels();

        camera.update();
//        Gdx.input.setInputProcessor(stage);
        render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void show() {
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
        spriteBatch2 = new SpriteBatch();
        moneyfont = new BitmapFont();

        ScreenUtils.clear(0, 0, 0, 1);

        // Clear the stuff that is left over from the previous render cycle
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        modelBatch.begin(camera);
        spriteBatch.begin();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        // Let our ModelBatch take care of efficient rendering of our ModelInstance
        if (player1 != null && player2 != null) {
            modelBatch.render(player1.modInstance, environment);
            modelBatch.render(player2.modInstance, environment);
        }
        if (player3 != null) {
            modelBatch.render(player3.modInstance, environment);
        }
        if (player4 != null) {
            modelBatch.render(player4.modInstance, environment);
        }

        renderModels();

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
    public void dispose() {
        modelBatch.dispose();
        spriteBatch.dispose();
        moneyfont.dispose();
        stage.dispose();
        disposeModels();
    }

    @Override
    public void switchScreenDelayed(GameScreenAdapter screen, float delay) {
    }

    public void renderModels() {
        for (int i = 0; i < fields.length; i++) { // Don't forget to render the models
            modelBatch.render(fieldModInstance[i], environment);
        }
    }

    public void disposeModels() {
        for (int i = 0; i < fieldModel.length; i++) {
            fieldModel[i].dispose();
        }
    }

    public void drawDice(Texture d1, Texture d2) {
        spriteBatch.begin();
        spriteBatch.draw(d1, xPosButtons + 500, yPosInitialButtons - 400, 500, 500);
        spriteBatch.draw(d2, xPosButtons, yPosInitialButtons - 400, 500, 500);
        spriteBatch.end();
    }

    public void createModels() {
        transformModelsOnField(0, 10, 0, 0, 0, 0);
        transformModelsOnField(11, 19, leftX, leftZ, 270, 0);
        transformModelsOnField(20, 30, topX, topZ, 180, 0);
        transformModelsOnField(31, 39, rightX, rightZ, 90, 3);
    }

    public void transformModelsOnField(int start, int end, float v3X, float v3Z, int degrees,
                                       int offset) {
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
}