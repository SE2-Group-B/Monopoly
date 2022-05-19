package se2.groupb.monopoly;

import com.badlogic.gdx.Gdx;
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

import java.util.ArrayList;
import java.util.Random;


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

    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;


   // private CameraInputController cameraController;
    public Vector3[] positions = new Vector3[40];


    ArrayList<Property> arrayList = new ArrayList();

    ArrayList<Property> arrayList1 = new ArrayList();
    ArrayList<Property> arrayList2 = new ArrayList();
    ArrayList<Property> arrayList3 = new ArrayList();
    ArrayList<Property> arrayList4 = new ArrayList();


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

        Property[] logicalGameField =createLogicalGameField();

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


        modelBatch.render(player1.modInstance, environment);
        modelBatch.render(player2.modInstance, environment);
        modelBatch.render(player3.modInstance, environment);
        modelBatch.render(player4.modInstance, environment);


        spriteBatch.draw(rollDice, xPosButtons+100, yPosInitialButtons - 500, buttonSizeX, buttonSizeY);
        if (isCorrectPosition(userPosX, userPosY, xPosButtons+100, yPosInitialButtons-500, buttonSizeX, buttonSizeY, 0 * yPosOffsetButtons)
                && Gdx.input.justTouched()) {
            if(count <10) {
                int pos = roll();
                currentPos += pos;
                currentPos %= 40;
//            float posA = positions[currentPos].x;
//            positions[currentPos].x = posA + 4;

            player1.move(positions[currentPos]);

                player1.move(positions[currentPos]);
                count++;
            }else{
                //Some end-event
            }

        }

        renderModels();
        drawDice(dice1, dice2);

        moneyfont.setColor(Color.WHITE);
        moneyfont.getData().setScale(4,4);
        moneyfont.draw(spriteBatch, player1.getName() + ": " + String.valueOf(player1.getBankBalance()),Gdx.graphics.getWidth()-Gdx.graphics.getWidth(),Gdx.graphics.getHeight()-100);
        moneyfont.draw(spriteBatch, player2.getName() + ": " +String.valueOf(player2.getBankBalance()),Gdx.graphics.getWidth()-Gdx.graphics.getWidth(),Gdx.graphics.getHeight()-150);
        moneyfont.draw(spriteBatch, player3.getName()+ ": " + String.valueOf(player3.getBankBalance()),Gdx.graphics.getWidth()-Gdx.graphics.getWidth(),Gdx.graphics.getHeight()-200);
        moneyfont.draw(spriteBatch, player4.getName() + ": " +String.valueOf(player4.getBankBalance()),Gdx.graphics.getWidth()-Gdx.graphics.getWidth(),Gdx.graphics.getHeight()-250);

        spriteBatch.draw(BuyButton, Gdx.graphics.getWidth()-Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-400, buttonSizeX/2, buttonSizeY/2);
        if (isCorrectPosition(userPosX, userPosY, Gdx.graphics.getWidth()-Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-400, buttonSizeX/2, buttonSizeY/2, 0 * yPosOffsetButtons)
                && Gdx.input.justTouched()) {
            int pos = player1.getPosition();

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

    public Property[] createLogicalGameField(){
        Property[] spielfeld = new Property[40];
        spielfeld[0] = new Property("Los");
        spielfeld[1] = new Street("Badstraße", 40,false, 0, 0, 10,  50);
        spielfeld[2]=new Property("Gemeinschaftsfeld");
        spielfeld[3]=new Street("Turmstraße", 80,false, 0, 0, 20,  50);
        spielfeld[4]=new PenaltyField("Einkommenssteuer", 200);
        spielfeld[5]=new Trainstation("Südbahnhof", false, 50);
        spielfeld[6]=new Street("Chausseestraße", 120,false, 0, 0, 30,  50);
        spielfeld[7]=new Property("Ereignisfeld");
        spielfeld[8]=new Street("Elisenstraße", 120,false, 0, 0, 30,  50);
        spielfeld[9]=new Street("Poststraße", 160,false, 0, 0, 35,  50);
        spielfeld[10]=new Property("Gefängnis");
        spielfeld[11]=new Street("Seestraße", 200,false, 0, 0, 60,  100);
        spielfeld[12]=new Property("Ereignisfeld");
        spielfeld[13]=new Street("Hafenstraße", 200,false, 0, 0, 70,  100);
        spielfeld[14]=new Street("Neue Straße", 240,false, 0, 0, 80,  100);
        spielfeld[15]=new Trainstation("Westbahnhof", false, 50);
        spielfeld[16]=new Street("Münchner Straße", 280,false, 0, 0, 85,  100);
        spielfeld[17]=new Property("Gemeinschaftsfeld");
        spielfeld[18]=new Street("Wiener Straße", 280,false, 0, 0, 90,  100);
        spielfeld[19]=new Street("Berliner Straße", 320,false, 0, 0, 95,  100);
        spielfeld[20]=new Property("Sofa");
        spielfeld[21]=new Street("Theater Straße", 360,false, 0, 0, 100,  150);
        spielfeld[22]=new Property("Ereignisfeld");
        spielfeld[23]=new Street("Museumsstraße", 360,false, 0, 0, 110,  150);
        spielfeld[24]=new Street("Opernplatz", 400,false, 0, 0, 115,  150);
        spielfeld[25]=new Trainstation("Nordbahnhof", false, 50);
        spielfeld[26]=new Street("Lessingstraße", 440,false, 0, 0, 120,  150);
        spielfeld[27]=new Street("Schillerstraße", 440,false, 0, 0, 122,  150);
        spielfeld[28]=new Property("Gemeinschaftsfeld");
        spielfeld[29]=new Street("Goethestraße", 480,false, 0, 0, 130,  150);
        spielfeld[30]=new Property("Gehe ins Gefängnis");
        spielfeld[31]=new Street("Rathausplatz", 520,false, 0, 0, 150,  200);
        spielfeld[32]=new Street("Hauptstraße", 520,false, 0, 0, 155,  200);
        spielfeld[33]=new Property("Gemeinschaftsfeld");
        spielfeld[34]=new Street("Bahnhofstraße", 560,false, 0, 0, 160,  200);
        spielfeld[35]=new Trainstation("Hauptbahnhof", false, 50);
        spielfeld[36]=new Property("Ereignisfeld");
        spielfeld[37]=new Street("Parkstraße", 650,false, 0, 0, 250,  200);
        spielfeld[38]= new PenaltyField("Zusatzsteuer", 200);
        spielfeld[39]=new Street("Schlossallee", 800,false, 0, 0, 350,  200);
        return spielfeld;
    }

}