package se2.groupb.monopoly;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.JsonReader;


public class CreateGameField extends ScreenAdapter  {

    private Environment environment;
    private OrthographicCamera camera;
    private ModelBatch modelBatch;
    private Field[] fields = new Field[40];

    private String buildingPath = "Spielfeld\\untitled.g3dj";

//    private

    private CameraInputController cameraController;

//    private ModelInstance il1;
//    private ModelInstance il2;


    Model[] modelLeft = new Model[39];
    ModelInstance[] modInstanceLeft = new ModelInstance[10];

    private void createFieldArray() {
        Model model = new G3dModelLoader(new JsonReader()).loadModel(Gdx.files.internal(buildingPath));
        //Botton Side of Board
        fields[0] = new Field(model, null, Type.CORNER);
        fields[1] = new Field(model, Color.BROWN, Type.BUILDING);
        fields[2] = new Field(model, null, Type.SPECIAL);
        fields[3] = new Field(model, Color.BROWN, Type.BUILDING);
        fields[4] = new Field(model, null, Type.SPECIAL);
        fields[5] = new Field(model, null, Type.SPECIAL);
        fields[6] = new Field(model, Color.WHITE, Type.BUILDING);
        fields[7] = new Field(model, null, Type.SPECIAL);
        fields[8] = new Field(model, Color.WHITE, Type.BUILDING);
        fields[9] = new Field(model, Color.WHITE, Type.BUILDING);
        // Left Side of Board
        fields[10] = new Field(model, null, Type.CORNER);
        fields[11] = new Field(model, Color.PURPLE, Type.BUILDING);
        fields[12] = new Field(model, null, Type.SPECIAL);
        fields[13] = new Field(model, Color.PURPLE, Type.BUILDING);
        fields[14] = new Field(model, Color.PURPLE, Type.BUILDING);
        fields[15] = new Field(model, null, Type.SPECIAL);
        fields[16] = new Field(model, Color.YELLOW, Type.BUILDING);
        fields[17] = new Field(model, null, Type.SPECIAL);
        fields[18] = new Field(model, Color.YELLOW, Type.BUILDING);
        fields[19] = new Field(model, Color.YELLOW, Type.BUILDING);
        // Upper Side of Board
        fields[20] = new Field(model, null, Type.CORNER);
        fields[21] = new Field(model, Color.RED, Type.BUILDING);
        fields[22] = new Field(model, null, Type.SPECIAL);
        fields[23] = new Field(model, Color.RED, Type.BUILDING);
        fields[24] = new Field(model, Color.RED, Type.BUILDING);
        fields[25] = new Field(model, null, Type.SPECIAL);
        fields[26] = new Field(model, Color.YELLOW, Type.BUILDING);
        fields[27] = new Field(model, Color.YELLOW, Type.BUILDING);
        fields[28] = new Field(model, null, Type.SPECIAL);
        fields[29] = new Field(model, Color.YELLOW, Type.BUILDING);
        // Right Side of Board
        fields[30] = new Field(model, null, Type.CORNER);
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


    public CreateGameField() {

//        Gdx.app.setLogLevel(Application.LOG_DEBUG);
//        Gdx.app.debug("GDSAFA", "Hello");
        createFieldArray();
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        modelBatch = new ModelBatch();

        // Create a perspective camera with some sensible defaults
        //camera = new OrthographicCamera(30,30*((float)Gdx.graphics.getWidth()/ Gdx.graphics.getHeight()));
        camera = new OrthographicCamera(100, 100);
        camera.position.set(850,150,-800);
//        camera.position.set(Gdx.graphics.getHeight() / 2 - 700,Gdx.graphics.getWidth() / 2, 5);
//        camera.position.set(500f,500f,200f);
        camera.lookAt(850f, 100f, -800f);
        camera.zoom = 30;
//        float effectiveViewportWidth = camera.viewportWidth * camera.zoom;
//        float effectiveViewportHeight = camera.viewportHeight * camera.zoom;


//        camera.near = 0f;
//        camera.far = 500f;
        createModels();
        camera.update();



        // Import and instantiate our model (called "myModel.g3dj")
//        ModelBuilder modelBuilder = new ModelBuilder();

//        ml1 = new G3dModelLoader(new JsonReader()).loadModel(Gdx.files.internal("Spielfeld\\untitled.g3dj"));
//        ml2 = new G3dModelLoader(new JsonReader()).loadModel(Gdx.files.internal("Spielfeld\\untitled.g3dj"));
//        il1 = new ModelInstance(ml1);
//        il1.transform.translate(new Vector3(1000,0,0));
//        il2 = new ModelInstance(ml2);
//        il2.transform.translate(new Vector3(1000,0,200));

//        cameraController = new CameraInputController(camera);
//        Gdx.input.setInputProcessor(cameraController);
    }



    @Override
    public void render(float delta) {
//        cameraController.update();

        // Clear the stuff that is left over from the previous render cycle
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        // Let our ModelBatch take care of efficient rendering of our ModelInstance
        modelBatch.begin(camera);
//        modelBatch.render(il1, environment);
//        modelBatch.render(il2, environment);
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
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    public void createModels() {
                Gdx.app.setLogLevel(Application.LOG_DEBUG);

//        for (int i = 0; i < modelLeft.length; i++) {
//            modInstanceLeft[i] = new ModelInstance(fields[i].getModel());
//            modInstanceLeft[i].materials.get(1).set(new ColorAttribute(ColorAttribute.Diffuse, Color.BLUE));
//            modInstanceLeft[i].transform.translate(new Vector3(0, 0, i*200));
//            modInstanceLeft[i].transform.rotate(new Vector3(0,1,0), 90);
//        }

        Vector3 vector3 = new Vector3(0, 0, 0);
        Vector3 vector3Botton = new Vector3(0,1,0);
        for (int i = 0; i < 10; i++) {
            modInstanceLeft[i] = new ModelInstance(fields[i].getModel());
            modInstanceLeft[i].materials.get(1).set(new ColorAttribute(ColorAttribute.Diffuse, fields[i].getFieldColors()));
            vector3.x = i*200;
            modInstanceLeft[i].transform.translate(vector3);

            modInstanceLeft[i].transform.rotate(vector3Botton, 90);
        }
    }


    public void renderModels() {
//        ModelInstance[] modInstanceLeft
        for (int i = 0; i < modInstanceLeft.length; i++) {
            modelBatch.render(modInstanceLeft[i], environment);
        }
    }

    public void disposeModels() {
//        Model[] modelLeft
        for (int i = 0; i < modelLeft.length; i++) {
            modelLeft[i].dispose();
        }
    }

}