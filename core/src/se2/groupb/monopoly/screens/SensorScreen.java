package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import javax.swing.text.View;


import se2.groupb.monopoly.Monopoly;
import sun.management.Sensor;

public class SensorScreen implements Screen {

    Monopoly monopoly;
    boolean gyroSensorActive=Gdx.input.isPeripheralAvailable(Input.Peripheral.Gyroscope);
    boolean lightSensorActive = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);
    private float xGyro;
    private float yGyro;
    private float zGyro;
    private float xLight;
    private float yLight;
    private float zLight;


    public SensorScreen(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    Stage stage;
    Batch batch;
    Camera camera;
    BitmapFont font;


    @Override
    public void show() {
        stage = new Stage();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //batch.setProjectionMatrix(camera.projection);
        monopoly.batch.begin();
        if(gyroSensorActive){
            xGyro = Gdx.input.getGyroscopeX();
            yGyro = Gdx.input.getGyroscopeY();
            zGyro = Gdx.input.getGyroscopeZ();
        }
        if(lightSensorActive){
            xLight = Gdx.input.getAccelerometerX();
            yLight = Gdx.input.getAccelerometerY();
            zLight = Gdx.input.getAccelerometerZ();
        }
            //font.draw(batch, "OK, Let's GOO", 10,10);
        monopoly.batch.end();

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

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
