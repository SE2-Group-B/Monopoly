package se2.groupb.monopoly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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

import java.util.ArrayList;
import java.util.Random;


public class CreateGameField extends ScreenAdapter {

    Monopoly monopoly;
    SpriteBatch spriteBatch;
    private Environment environment;
    private OrthographicCamera camera;
    private ModelBatch modelBatch;

    private Field[] fields = new Field[40];

    private String buildingPath = "Spielfeld\\field.g3dj";



    private Texture rollDice = new Texture("images/MenuButtons/roll.png");

    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;


    private CameraInputController cameraController;
    public Vector3[] positions = new Vector3[40];

    ArrayList<Property> arrayList = new ArrayList();


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
        //Left Side of Board
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
        // Top Side of Board
        fields[11] = new Field(model, Color.PURPLE, Type.BUILDING);
        fields[12] = new Field(model, null, Type.SPECIAL);
        fields[13] = new Field(model, Color.PURPLE, Type.BUILDING);
        fields[14] = new Field(model, Color.PURPLE, Type.BUILDING);
        fields[15] = new Field(model, null, Type.SPECIAL);
        fields[16] = new Field(model, Color.YELLOW, Type.BUILDING);
        fields[17] = new Field(model, null, Type.SPECIAL);
        fields[18] = new Field(model, Color.YELLOW, Type.BUILDING);
        fields[19] = new Field(model, Color.YELLOW, Type.BUILDING);
        // Right Side of Board
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
        // Bot Side of Board
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
    private void createPositions() {
        positions[0] = new Vector3(0f, 3.5f, 0f);
        positions[1] = new Vector3(0f, 3.5f, -6.5f);
        positions[2] = new Vector3(0f, 3.5f, -13f);
        positions[3] = new Vector3(0f, 3.5f, -19.5f);
        positions[4] = new Vector3(0f, 3.5f, -26f);
        positions[5] = new Vector3(0f, 3.5f, -32.5f);
        positions[6] = new Vector3(0f, 3.5f, -39f);
        positions[7] = new Vector3(0f, 3.5f, -45.5f);
        positions[8] = new Vector3(0f, 3.5f, -52f);
        positions[9] = new Vector3(0f, 3.5f, -58.5f);
        positions[10] = new Vector3(0f, 3.5f, -65f);
        positions[11] = new Vector3(9.522501f, 3.5f, -68f);
        positions[12] = new Vector3(16.022501f, 3.5f, -68f);
        positions[13] = new Vector3(22.522501f, 3.5f, -68f);
        positions[14] = new Vector3(29.022501f, 3.5f, -68f);
        positions[15] = new Vector3(35.5225f, 3.5f, -68f);
        positions[16] = new Vector3(42.0225f, 3.5f, -68f);
        positions[17] = new Vector3(48.5225f, 3.5f, -68f);
        positions[18] = new Vector3(55.0225f, 3.5f, -68f);
        positions[19] = new Vector3(61.5225f, 3.5f, -68f);
        positions[20] = new Vector3(71.05f, 3.5f, -65.0f);
        positions[21] = new Vector3(71.05f, 3.5f, -58.5f);
        positions[22] = new Vector3(71.05f, 3.5f, -52.0f);
        positions[23] = new Vector3(71.05f, 3.5f, -45.5f);
        positions[24] = new Vector3(71.05f, 3.5f, -39.0f);
        positions[25] = new Vector3(71.05f, 3.5f, -32.5f);
        positions[26] = new Vector3(71.05f, 3.5f, -26.0f);
        positions[27] = new Vector3(71.05f, 3.5f, -19.5f);
        positions[28] = new Vector3(71.05f, 3.5f, -13.0f);
        positions[29] = new Vector3(71.05f, 3.5f, -6.5f);
        positions[30] = new Vector3(71.05f, 3.5f, 0f);
        positions[31] = new Vector3(61.5f, 3.5f, 3.25f);
        positions[32] = new Vector3(55.0f, 3.5f, 3.25f);
        positions[33] = new Vector3(48.5f, 3.5f, 3.25f);
        positions[34] = new Vector3(42.0f, 3.5f, 3.25f);
        positions[35] = new Vector3(35.5f, 3.5f, 3.25f);
        positions[36] = new Vector3(29.0f, 3.5f, 3.25f);
        positions[37] = new Vector3(22.5f, 3.5f, 3.25f);
        positions[38] = new Vector3(16.0f, 3.5f, 3.25f);
        positions[39] = new Vector3(9.5f, 3.5f, 3.25f);




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


        player1 = new Player(1, "Blue", 2000, arrayList, 0, Color.BLUE);
        player1.createSpielfigur();
        player2 = new Player(2, "Red", 2000, arrayList, 0, Color.RED);
        player2.createSpielfigur();
        player3 = new Player(3, "Yellow", 2000, arrayList, 0, Color.YELLOW);
        player3.createSpielfigur();
        player4 = new Player(4, "Green", 2000, arrayList, 0, Color.GREEN);
        player4.createSpielfigur();


        camera.update();

        cameraController = new CameraInputController(camera);
        Gdx.input.setInputProcessor(cameraController);
    }


    @Override
    public void render(float delta) {

        float userPosX = (float) Gdx.input.getX();
        float userPosY = (float) Gdx.graphics.getHeight() - Gdx.input.getY();

        ScreenUtils.clear(0, 0, 0, 1);
        cameraController.update();

        // Clear the stuff that is left over from the previous render cycle
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        modelBatch.begin(camera);
        spriteBatch.begin();
        // Let our ModelBatch take care of efficient rendering of our ModelInstance


        modelBatch.render(player1.modInstance, environment);
        modelBatch.render(player2.modInstance, environment);
        modelBatch.render(player3.modInstance, environment);
        modelBatch.render(player4.modInstance, environment);


        spriteBatch.draw(rollDice, xPosButtons+100, yPosInitialButtons - 500, buttonSizeX, buttonSizeY);
        if (isCorrectPosition(userPosX, userPosY, xPosButtons+100, yPosInitialButtons-500, buttonSizeX, buttonSizeY, 0 * yPosOffsetButtons)
                && Gdx.input.justTouched()) {
            int pos = roll();
            for (int i = 0; i < positions.length; i++) {
                System.out.println(i + " " + positions[i]);
            }
            currentPos+=pos;
            currentPos %= 40;
//            float posA = positions[currentPos].x;
//            positions[currentPos].x = posA + 4;
            player1.move(positions[currentPos]);

        }


        renderModels();
        drawDice(dice1, dice2);

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
            System.out.println(positions[i].toString());
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