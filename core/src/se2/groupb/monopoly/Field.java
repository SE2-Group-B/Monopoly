package se2.groupb.monopoly;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.JsonReader;

import org.mockito.Mock;

import sun.rmi.runtime.Log;


public class Field extends ScreenAdapter  {

    private Environment environment;
    private OrthographicCamera camera;
    private CameraInputController cameraController;
    private ModelBatch modelBatch;
    private Model model;
    private ModelInstance instance;




    public Field() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Gdx.app.debug("GDSAFA", "Hello");
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        modelBatch = new ModelBatch();

        // Create a perspective camera with some sensible defaults
        camera = new OrthographicCamera(30,30*((float)Gdx.graphics.getWidth()/ Gdx.graphics.getHeight()));
        camera.position.set(0,250,0);
        camera.lookAt(0, 0, 0);
        camera.zoom = -50;
        float effectiveViewportWidth = camera.viewportWidth * camera.zoom;
        float effectiveViewportHeight = camera.viewportHeight * camera.zoom;


        camera.near = 0f;
        camera.far = 500f;
        camera.update();

        // Import and instantiate our model (called "myModel.g3dj")
        ModelBuilder modelBuilder = new ModelBuilder();
        model = new G3dModelLoader(new JsonReader()).loadModel(Gdx.files.internal("Spielfeld\\untitled.g3dj"));
        instance = new ModelInstance(model);

        cameraController = new CameraInputController(camera);
        Gdx.input.setInputProcessor(cameraController);
    }



    @Override
    public void render(float delta) {
        cameraController.update();

        // Clear the stuff that is left over from the previous render cycle
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        // Let our ModelBatch take care of efficient rendering of our ModelInstance
        modelBatch.begin(camera);
        modelBatch.render(instance, environment);
        modelBatch.end();
    }

    @Override
    public void dispose() {
        modelBatch.dispose();
        model.dispose();
    }



    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }
}