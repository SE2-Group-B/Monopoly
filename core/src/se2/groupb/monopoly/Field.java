package se2.groupb.monopoly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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




public class Field extends ScreenAdapter  {

    private Environment environment;
    private OrthographicCamera camera;
    private ModelBatch modelBatch;


    private CameraInputController cameraController;
//    private Model ml1;
//    private Model ml2;
//    private ModelInstance il1;
//    private ModelInstance il2;


    Model[] modelLeft = new Model[9];
    ModelInstance[] modInstanceLeft = new ModelInstance[9];


    public Field() {
//        Gdx.app.setLogLevel(Application.LOG_DEBUG);
//        Gdx.app.debug("GDSAFA", "Hello");
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        modelBatch = new ModelBatch();

        // Create a perspective camera with some sensible defaults
        camera = new OrthographicCamera(30,30*((float)Gdx.graphics.getWidth()/ Gdx.graphics.getHeight()));
        camera.position.set(0,250,0);
        camera.lookAt(0, 0, 0);
        camera.zoom = -100;
//        float effectiveViewportWidth = camera.viewportWidth * camera.zoom;
//        float effectiveViewportHeight = camera.viewportHeight * camera.zoom;


        camera.near = 0f;
        camera.far = 500f;
        camera.update();

        createModels();

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
//        Model[] modelLeft, ModelInstance[] modInstanceLeft

        for (int i = 0; i < modelLeft.length; i++) {
            modelLeft[i] = new Model();
            modInstanceLeft[i] = new ModelInstance(modelLeft[i]);
        }
        for (int i = 0; i < modelLeft.length ; i++) {
            modelLeft[i] = new G3dModelLoader(new JsonReader()).loadModel(Gdx.files.internal("Spielfeld\\untitled.g3dj"));
            modInstanceLeft[i].transform.translate(new Vector3(0,0,0));// if work add 200 to z

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