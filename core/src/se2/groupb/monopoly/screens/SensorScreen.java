package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Timer;

import javax.swing.text.View;


import se2.groupb.monopoly.Monopoly;
import sun.management.Sensor;
import sun.rmi.runtime.Log;

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
    private int toggleVibration;

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
        toggleVibration = -1;
        Gdx.app.setLogLevel(Application.LOG_INFO);
        if(gyroSensorActive){
            xGyro = Gdx.input.getGyroscopeX();
            Gdx.app.log("GyroX", String.valueOf(xGyro));

            yGyro = Gdx.input.getGyroscopeY();
            Gdx.app.log("GyroY", String.valueOf(yGyro));

            zGyro = Gdx.input.getGyroscopeZ();
            Gdx.app.log("GyroZ", String.valueOf(zGyro));
        }
        if(lightSensorActive){
            xLight = Gdx.input.getAccelerometerX();
            Gdx.app.log("LightX", String.valueOf(xLight));

            yLight = Gdx.input.getAccelerometerY();
            Gdx.app.log("LightY", String.valueOf(yLight));

            zLight = Gdx.input.getAccelerometerZ();
            Gdx.app.log("LightZ", String.valueOf(zLight));
        }
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        monopoly.batch.begin();
        if (Gdx.input.isKeyPressed(Input.Keys.VOLUME_DOWN)) {
            toggleVibration=toggleVibration*-1;
        }
        if(toggleVibration > 0){
            Gdx.input.vibrate(100);
        }
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
