package se2.groupb.monopoly;

import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;

import se2.groupb.monopoly.screens.GameScreenAdapter;

public class CreateGameField extends GameScreenAdapter {

    private Monopoly monopoly;
    private SpriteBatch spriteBatch;
    private Environment environment;
    private OrthographicCamera camera;
    private ModelBatch modelBatch;
    private BitmapFont moneyfont;
    private Stage stage;
    private Field[] fields;
    private ArrayList<Player> players;
    private int playerCount;
    private Texture kartenHintergrund;
    public boolean showCard;
    private Timer timerCard;
    private PlayerOperation pO;
    private boolean p1 = false;
    private boolean p2 = false;
    private boolean p3 = false;
    private boolean p4 = false;


    // private CameraInputController cameraController;
    public Vector3[] positions = new Vector3[40];


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

    public CreateGameField(Monopoly monopoly, ArrayList<Player> list) {
        super(monopoly);
        this.stage = new Stage(new ScreenViewport());
        this.spriteBatch = new SpriteBatch();
        this.players = list;
        this.playerCount = players.size();
        GameFieldUnits gf = new GameFieldUnits();
        gf.createField("monopoly");
        fields = gf.getFields();
        pO = new PlayerOperation(players);

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

        camera.update();

//        cameraController = new CameraInputController(camera);
//        Gdx.input.setInputProcessor(cameraController);
    }

    public void checkIfPlayerIsAlone(Player player) {
        for (int i = 0; i < players.size(); i++) {
            if (player.getId() != players.get(i).getId() && player.getPosition() == players.get(i).getPosition()) {
                if(players.get(i).isNotAlone()) {
                    players.get(i).setNotAlone(false);
                } else players.get(i).setNotAlone(true);

                player.setNotAlone(true);
                players.get(i).setNotAlone(true);
            }

        }
        setMultiplePlayersOnField();
    }

    public void setMultiplePlayersOnField() {
        Vector3[] newPos = positions.clone();

        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).isNotAlone() && newPos != positions) {
                switch (i) {
                    case 0:
                        newPos[players.get(i).getPosition()].x -= 2;
                        newPos[players.get(i).getPosition()].z += 2;
                        players.get(i).move(newPos[players.get(i).getPosition()]);
//                        players.get(i).setNotAlone(false);

                        break;
                    case 1:
                        newPos[players.get(i).getPosition()].x += 2;
                        newPos[players.get(i).getPosition()].z += 2;
                        players.get(i).move(newPos[players.get(i).getPosition()]);
//                        players.get(i).setNotAlone(false);

                        break;
                    case 2:
                        newPos[players.get(i).getPosition()].x -= 2;
                        newPos[players.get(i).getPosition()].z -= 2;
                        players.get(i).move(newPos[players.get(i).getPosition()]);
//                        players.get(i).setNotAlone(false);

                        break;
                    case 3:
                        newPos[players.get(i).getPosition()].x += 2;
                        newPos[players.get(i).getPosition()].z -= 2;
                        players.get(i).move(newPos[players.get(i).getPosition()]);
//                        players.get(i).setNotAlone(false);

                        break;
                    default:
                        throw new IllegalArgumentException("ERROR");
                }
            }
            newPos = positions.clone();
        }

    }


    @Override
    public void render(float delta) {
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
        if (playerCount > 0) {
            modelBatch.render(players.get(0).modInstance, environment);
        }
        if (playerCount > 1) {
            modelBatch.render(players.get(1).modInstance, environment);
        }
        if (playerCount > 2) {
            modelBatch.render(players.get(2).modInstance, environment);
        }
        if (playerCount > 3) {
            modelBatch.render(players.get(3).modInstance, environment);
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

    public void changeColor(int field, Color color) {
        fieldModInstance[field].materials.get(0).set(new ColorAttribute(ColorAttribute.Diffuse, color));
        pO.setBought(false);
    }
}

