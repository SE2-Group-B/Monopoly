package se2.groupb.monopoly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
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
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.ScreenUtils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import se2.groupb.monopoly.screens.InputBackProcessor;


public class CreateGameField extends ScreenAdapter {

    Monopoly monopoly;
    SpriteBatch spriteBatch;
    SpriteBatch spriteBatch2;
    private Environment environment;
    private OrthographicCamera camera;
    private ModelBatch modelBatch;
    private BitmapFont moneyfont;

    private Field[] fields = new Field[40];

    private String buildingPath = "Spielfeld\\field.g3dj";



    private Texture rollDice = new Texture("images/MenuButtons/roll.png");
    private Texture BuyButton = new Texture("images/MenuButtons/buy_building.png");

    private Spielfigur spielfigur1;
    private Spielfigur spielfigur2;
    private Spielfigur spielfigur3;
    private Spielfigur spielfigur4;


   // private CameraInputController cameraController;
    public Vector3[] positions = new Vector3[40];

    ArrayList<Grundstueck> arrayList1 = new ArrayList();
    ArrayList<Grundstueck> arrayList2 = new ArrayList();
    ArrayList<Grundstueck> arrayList3 = new ArrayList();
    ArrayList<Grundstueck> arrayList4 = new ArrayList();


    private int buttonSizeX;
    private int buttonSizeY;
    private float yPosInitialButtons;
    private float yPosOffsetButtons;
    private float xPosButtons;

    private boolean cheatActivated;
    private boolean onTurn;

    private Random random = new Random();
    private int cheatDice;


    private Texture dice1;
    private Texture dice2;

    private int currentPos = 0;



    Model[] fieldModel = new Model[40];
    ModelInstance[] fieldModInstance = new ModelInstance[40]; // DON'T FORGET TO UPDATE ARRAY

    private void createFieldArray() {
        Model model = new G3dModelLoader(new JsonReader()).loadModel(Gdx.files.internal(buildingPath));
        //Bot Side of Board
        //Color.Cyan = Corner -> debugging
        fields[0] = new Field(model, Color.CYAN, Type.CORNER);
        fields[1] = new Field(model, Color.BROWN, Type.BUILDING);
        fields[2] = new Field(model, null, Type.SPECIAL);
        fields[3] = new Field(model, Color.BROWN, Type.BUILDING);
        fields[4] = new Field(model, null, Type.SPECIAL);
        fields[5] = new Field(model, null, Type.SPECIAL);
        fields[6] = new Field(model, Color.WHITE, Type.BUILDING);
        fields[7] = new Field(model, null, Type.SPECIAL);
        fields[8] = new Field(model, Color.WHITE, Type.BUILDING);
        fields[9] = new Field(model, Color.WHITE, Type.BUILDING);
        fields[10] = new Field(model, Color.CYAN, Type.CORNER);
        // Left Side of Board
        fields[11] = new Field(model, Color.PURPLE, Type.BUILDING);
        fields[12] = new Field(model, null, Type.SPECIAL);
        fields[13] = new Field(model, Color.PURPLE, Type.BUILDING);
        fields[14] = new Field(model, Color.PURPLE, Type.BUILDING);
        fields[15] = new Field(model, null, Type.SPECIAL);
        fields[16] = new Field(model, Color.ORANGE, Type.BUILDING);
        fields[17] = new Field(model, null, Type.SPECIAL);
        fields[18] = new Field(model, Color.ORANGE, Type.BUILDING);
        fields[19] = new Field(model, Color.ORANGE, Type.BUILDING);
        // Top Side of Board
        fields[20] = new Field(model, Color.CYAN, Type.CORNER);
        fields[21] = new Field(model, Color.RED, Type.BUILDING);
        fields[22] = new Field(model, null, Type.SPECIAL);
        fields[23] = new Field(model, Color.RED, Type.BUILDING);
        fields[24] = new Field(model, Color.RED, Type.BUILDING);
        fields[25] = new Field(model, null, Type.SPECIAL);
        fields[26] = new Field(model, Color.YELLOW, Type.BUILDING);
        fields[27] = new Field(model, Color.YELLOW, Type.BUILDING);
        fields[28] = new Field(model, null, Type.SPECIAL);
        fields[29] = new Field(model, Color.YELLOW, Type.BUILDING);
        fields[30] = new Field(model, Color.CYAN, Type.CORNER);
        // Right Side of Board
        fields[31] = new Field(model, Color.GREEN, Type.BUILDING);
        fields[32] = new Field(model, Color.GREEN, Type.BUILDING);
        fields[33] = new Field(model, null, Type.SPECIAL);
        fields[34] = new Field(model, Color.GREEN, Type.BUILDING);
        fields[35] = new Field(model, null, Type.SPECIAL);
        fields[36] = new Field(model, null, Type.SPECIAL);
        fields[37] = new Field(model, Color.BLUE, Type.BUILDING);
        fields[38] = new Field(model, null, Type.SPECIAL);
        fields[39] = new Field(model, Color.BLUE, Type.BUILDING);
    }
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
        spriteBatch = new SpriteBatch();
         buttonSizeX = Gdx.graphics.getWidth() / 3;
         buttonSizeY = (int) (Gdx.graphics.getHeight() / (4.545454 * 2));

         xPosButtons = (float) (Gdx.graphics.getWidth() / 2D - buttonSizeX / 2D);
         yPosInitialButtons = (float) (Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4D);
         yPosOffsetButtons = (float) (-Gdx.graphics.getWidth() / 8D);

        dice1 = new Texture("images/Dice/dice_0.png");
        dice2 = new Texture("images/Dice/dice_0.png");

//        Gdx.app.setLogLevel(Application.LOG_DEBUG);
//        Gdx.app.debug("GDSAFA", "Hello");
        createFieldArray();
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


        spielfigur1 = new Spielfigur(1, "Blue", 2000, arrayList1, 0, Color.BLUE);
        spielfigur1.createSpielfigur();
        spielfigur2 = new Spielfigur(2, "Red", 2000, arrayList2, 0, Color.RED);
        spielfigur2.createSpielfigur();
        spielfigur3 = new Spielfigur(3, "Yellow", 2000, arrayList3, 0, Color.YELLOW);
        spielfigur3.createSpielfigur();
        spielfigur4 = new Spielfigur(4, "Green", 2000, arrayList4, 0, Color.GREEN);
        spielfigur4.createSpielfigur();


        camera.update();

//        cameraController = new CameraInputController(camera);
//        Gdx.input.setInputProcessor(cameraController);
    }


    @Override
    public void render(float delta) {

        float userPosX = (float) Gdx.input.getX();
        float userPosY = (float) Gdx.graphics.getHeight() - Gdx.input.getY();
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

        // Let our ModelBatch take care of efficient rendering of our ModelInstance


        modelBatch.render(spielfigur1.modInstance, environment);
        modelBatch.render(spielfigur2.modInstance, environment);
        modelBatch.render(spielfigur3.modInstance, environment);
        modelBatch.render(spielfigur4.modInstance, environment);


        spriteBatch.draw(rollDice, xPosButtons+100, yPosInitialButtons - 500, buttonSizeX, buttonSizeY);
        if (isCorrectPosition(userPosX, userPosY, xPosButtons+100, yPosInitialButtons-500, buttonSizeX, buttonSizeY, 0 * yPosOffsetButtons)
                && Gdx.input.justTouched()) {
            if(count <10) {
                int pos = roll();
                currentPos += pos;
                currentPos %= 40;
//            float posA = positions[currentPos].x;
//            positions[currentPos].x = posA + 4;
                spielfigur1.move(positions[currentPos]);
                count++;
            }else{
                //Some end-event
            }
        }

        renderModels();
        drawDice(dice1, dice2);

        moneyfont.setColor(Color.WHITE);
        moneyfont.getData().setScale(4,4);
        moneyfont.draw(spriteBatch, spielfigur1.getName() + ": " + String.valueOf(spielfigur1.getKontostand()),Gdx.graphics.getWidth()-Gdx.graphics.getWidth(),Gdx.graphics.getHeight()-100);
        moneyfont.draw(spriteBatch, spielfigur2.getName() + ": " +String.valueOf(spielfigur2.getKontostand()),Gdx.graphics.getWidth()-Gdx.graphics.getWidth(),Gdx.graphics.getHeight()-150);
        moneyfont.draw(spriteBatch, spielfigur3.getName()+ ": " + String.valueOf(spielfigur3.getKontostand()),Gdx.graphics.getWidth()-Gdx.graphics.getWidth(),Gdx.graphics.getHeight()-200);
        moneyfont.draw(spriteBatch, spielfigur4.getName() + ": " +String.valueOf(spielfigur4.getKontostand()),Gdx.graphics.getWidth()-Gdx.graphics.getWidth(),Gdx.graphics.getHeight()-250);

        spriteBatch.draw(BuyButton, Gdx.graphics.getWidth()-Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-400, buttonSizeX/2, buttonSizeY/2);
        if (isCorrectPosition(userPosX, userPosY, Gdx.graphics.getWidth()-Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-400, buttonSizeX/2, buttonSizeY/2, 0 * yPosOffsetButtons)
                && Gdx.input.justTouched()) {
            int pos = spielfigur1.getPosition();

            //fields[pos]
            //spielfigur1.setMeineGrundstuecke(arrayList1.add());
        }

        spriteBatch.end();
        modelBatch.end();
    }

    @Override
    public void dispose() {
        modelBatch.dispose();
//        ml1.dispose();
//        ml2.dispose();
        disposeModels();
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

            //spielfigur.move(new Vector3(0f,0f,-6.5f));

    public void createModels() {


        Vector3 vector3 = new Vector3(0, 0, 0);
        Vector3 boardPosition = new Vector3(0f, 0, 0);
        Vector3 vector3Rotate = new Vector3(0, 1, 0);
        // Rotate 0 degrees = left side
        // Rotate 90 degrees = bot side
        // Rotate 180 degrees = right side
        // Rotate 270 degrees = top side
        float distanceWidth = 6.5f;
        vector3.set(boardPosition.add(vector3));
        for (int i = 0; i < fields.length; i++) {

            if (i <= 10) { // left side
                vector3.x = 0f;
                fieldModInstance[i] = new ModelInstance(fields[i].getModel());
                fieldModInstance[i].materials.get(1).set(new ColorAttribute(ColorAttribute.Diffuse, fields[i].getFieldColors()));
                vector3.z = -((i) * distanceWidth); // how far up/down - i-5 is more up
                fieldModInstance[i].transform.translate(vector3);
                fieldModInstance[i].transform.rotate(vector3Rotate, 0);
//                instantiatePositions(i, vector3);
            }
            if (i >= 11 && i < 20) { // top side
                //vector3.x = 50;
                vector3.z = -68f; // here
                fieldModInstance[i] = new ModelInstance(fields[i].getModel());
                fieldModInstance[i].materials.get(1).set(new ColorAttribute(ColorAttribute.Diffuse, fields[i].getFieldColors()));
                vector3.x = ((i - 9.535f) * distanceWidth) ; // more <- || less ->
                fieldModInstance[i].transform.translate(vector3);
                fieldModInstance[i].transform.rotate(vector3Rotate, 270);
//                instantiatePositions(i, vector3);
            }
            if (i >= 20 && i <= 30) { // right side
                //vector3.x = 100;
                vector3.x = 71.05f; // right/left
                fieldModInstance[i] = new ModelInstance(fields[i].getModel());
                fieldModInstance[i].materials.get(1).set(new ColorAttribute(ColorAttribute.Diffuse, fields[i].getFieldColors()));
                vector3.z = ((i - 30f) * distanceWidth); // how far up/down - i-5 is more up
                fieldModInstance[i].transform.translate(vector3);
                fieldModInstance[i].transform.rotate(vector3Rotate, 180);
//                instantiatePositions(i, vector3);

            }
            if (i > 30 && i < fields.length) { // bot Side
                fieldModInstance[i] = new ModelInstance(fields[i].getModel());
                fieldModInstance[i].materials.get(1).set(new ColorAttribute(ColorAttribute.Diffuse, fields[i].getFieldColors()));
                vector3.x = -((i - 40f) * distanceWidth) + 3f; // more -> || less <-
                vector3.z = 3.25f;
                fieldModInstance[i].transform.translate(vector3);
                fieldModInstance[i].transform.rotate(vector3Rotate, 90);
//                instantiatePositions(i, vector3);
            }
        }
    }

//    private void instantiatePositions(int i, Vector3 vector) {
//        positions[i] = vector;
//    }

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


    public int roll(){
        int firstDice = random.nextInt(6) + 1;
        int secondDice = random.nextInt(6)+1;
//        if (Gdx.input.isKeyPressed(Input.Keys.VOLUME_DOWN) && cheatDice ==0) {
//            secondDice = firstDice;
//            cheatActivated = true;
//        }else if(Gdx.input.isKeyPressed(Input.Keys.VOLUME_DOWN) && cheatDice !=0){
//            if(cheatDice>=6){
//                firstDice = secondDice = 6;
//            }else {
//                firstDice = secondDice = cheatDice;
////            }
//            cheatActivated = true;
//        }
//        else {
//            secondDice = random.nextInt(6)+1;
//        }
//        onTurn = false;

        dice1 = setDice(firstDice);
        dice2 = setDice(secondDice);
        drawDice(dice1, dice2);

//        if (firstDice == secondDice) {
//            onTurn = true;
//        }
//        cheatDice =0;
//        return new int[] {firstDice, secondDice};
        return firstDice+secondDice;
    }

    private Texture setDice(int value) {
        String path;
        switch (value){
            case 1:
                path="images/Dice/dice_1.png";
                break;
            case 2:
                path="images/Dice/dice_2.png";
                break;
            case 3:
                path="images/Dice/dice_3.png";
                break;
            case 4:
                path="images/Dice/dice_4.png";
                break;
            case 5:
                path="images/Dice/dice_5.png";
                break;
            case 6:
                path="images/Dice/dice_6.png";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + (int) value);
        }
        return new Texture(path);
    }

    private void drawDice(Texture d1, Texture d2) {
        spriteBatch.draw(d1, xPosButtons+500 , yPosInitialButtons-400, 500, 500);
        spriteBatch.draw(d2, xPosButtons, yPosInitialButtons-400, 500, 500);
    }


    private static boolean isCorrectPosition(float userPosX, float userPosY, float xPosButton, float yPosButton, float buttonSizeX, float buttonSizeY, float yPosOffset) {
        return (userPosX > xPosButton && userPosX < xPosButton + buttonSizeX && userPosY > (yPosButton + yPosOffset) && userPosY < yPosButton + yPosOffset + buttonSizeY);
    }
}