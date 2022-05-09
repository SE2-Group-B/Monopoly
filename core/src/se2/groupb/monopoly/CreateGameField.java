package se2.groupb.monopoly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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


public class CreateGameField extends ScreenAdapter {

    Monopoly monopoly;
    private Environment environment;
    private OrthographicCamera camera;
    private ModelBatch modelBatch;

    private Field[] fields = new Field[40];

    private String buildingPath = "Spielfeld\\field.g3dj";

    private Spielfigur spielfigur;

    private CameraInputController cameraController;
    public Vector3[] positions = new Vector3[40];

    ArrayList<Grundstueck> arrayList = new ArrayList();


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


    public CreateGameField(Monopoly monopoly) {

//        Gdx.app.setLogLevel(Application.LOG_DEBUG);
//        Gdx.app.debug("GDSAFA", "Hello");
        createFieldArray();
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1f));


        modelBatch = new ModelBatch();


        camera = new OrthographicCamera(100, 100);
        camera.position.set(30f, 100f, -35f);  // 20,100,-20
//         camera.rotate(new Vector3(3, -45, 3), -50); //changed the view of se Camera
//        camera.position.set(Gdx.graphics.getHeight() / 2 - 700,Gdx.graphics.getWidth() / 2, 5);
//        camera.position.set(500f,500f,200f);
        camera.lookAt(30f, 0f, -35f);  // 850f, 100f, -200f
        camera.zoom = 1;


//        camera.near = -10000f;
        camera.far = 500000f;
        createModels();
        spielfigur = new Spielfigur(1, "Bernd", 0, arrayList, 3, Color.BLUE);
        spielfigur.createSpielfigur();
        camera.update();


        cameraController = new CameraInputController(camera);
        Gdx.input.setInputProcessor(cameraController);
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        cameraController.update();

        // Clear the stuff that is left over from the previous render cycle
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        // Let our ModelBatch take care of efficient rendering of our ModelInstance
        modelBatch.begin(camera);

        modelBatch.render(spielfigur.modInstance, environment);

        renderModels();

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
                instantiatePositions(i, vector3);
            }
            if (i >= 11 && i < 20) { // top side
                //vector3.x = 50;
                vector3.z = -68f; // here
                fieldModInstance[i] = new ModelInstance(fields[i].getModel());
                fieldModInstance[i].materials.get(1).set(new ColorAttribute(ColorAttribute.Diffuse, fields[i].getFieldColors()));
                vector3.x = ((i - 9.535f) * distanceWidth) ; // more <- || less ->
                fieldModInstance[i].transform.translate(vector3);
                fieldModInstance[i].transform.rotate(vector3Rotate, 270);
                instantiatePositions(i, vector3);
            }
            if (i >= 20 && i <= 30) { // right side
                //vector3.x = 100;
                vector3.x = 71.05f; // right/left
                fieldModInstance[i] = new ModelInstance(fields[i].getModel());
                fieldModInstance[i].materials.get(1).set(new ColorAttribute(ColorAttribute.Diffuse, fields[i].getFieldColors()));
                vector3.z = ((i - 30f) * distanceWidth); // how far up/down - i-5 is more up
                fieldModInstance[i].transform.translate(vector3);
                fieldModInstance[i].transform.rotate(vector3Rotate, 180);
                instantiatePositions(i, vector3);

            }
            if (i > 30 && i < fields.length) { // bot Side
                fieldModInstance[i] = new ModelInstance(fields[i].getModel());
                fieldModInstance[i].materials.get(1).set(new ColorAttribute(ColorAttribute.Diffuse, fields[i].getFieldColors()));
                vector3.x = -((i - 40f) * distanceWidth) + 3f; // more -> || less <-
                vector3.z = 3.25f;
                fieldModInstance[i].transform.translate(vector3);
                fieldModInstance[i].transform.rotate(vector3Rotate, 90);
                instantiatePositions(i, vector3);
            }
            System.out.println(positions[i].toString());
        }
    }

    private void instantiatePositions(int i, Vector3 vector) {
        positions[i] = vector;
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

}