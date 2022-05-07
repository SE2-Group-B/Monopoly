package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Timer;

import java.util.Random;

import javax.swing.text.View;


import se2.groupb.monopoly.Monopoly;
import sun.management.Sensor;
import sun.rmi.runtime.Log;

public class SensorScreen implements Screen {

    private Monopoly monopoly;
    private boolean gyroSensorActive=Gdx.input.isPeripheralAvailable(Input.Peripheral.Gyroscope);
    private boolean lightSensorActive = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);
    private float xGyro;
    private float yGyro;
    private float zGyro;
    private float xLight;
    private float yLight;
    private float zLight;
    private int toggleVibration;
    private Texture rollDice;
    private boolean cheatActivated;
    private boolean onTurn;
    private Random random;
    private Texture dice1;
    private Texture dice2;
    private int cheatDice;

    private int buttonSizeX;
    private int buttonSizeY;
    private float yPosInitialButtons;
    private float yPosOffsetButtons;
    private float xPosButtons;


    public SensorScreen(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    Stage stage;
    Batch batch;
    Camera camera;
    BitmapFont font;


    @Override
    public void show() {

        InputBackProcessor inputProcessor = new InputBackProcessor(monopoly);
        inputProcessor.backToMainMenuProcessor();

        rollDice = new Texture("images/play_button_inactive.png");
        cheatActivated = false;
        onTurn = true;

        random = new Random();

        buttonSizeX = Gdx.graphics.getWidth() / 3;
        buttonSizeY = (int) (Gdx.graphics.getHeight() / (4.545454 * 2));

        xPosButtons = (float) (Gdx.graphics.getWidth() / 2D - buttonSizeX / 2D);
        yPosInitialButtons = (float) (Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4D);
        yPosOffsetButtons = (float) (-Gdx.graphics.getWidth() / 8D);

        cheatDice = 0;

        dice1 = new Texture("images/Dice/dice_1.png");
        dice2 = new Texture("images/Dice/dice_1.png");
//        drawDice(dice1, dice2);
//        toggleVibration = -1;
//        Gdx.app.setLogLevel(Application.LOG_INFO);
//        if(gyroSensorActive){
//            xGyro = Gdx.input.getGyroscopeX();
//            Gdx.app.log("GyroX", String.valueOf(xGyro));
//
//            yGyro = Gdx.input.getGyroscopeY();
//            Gdx.app.log("GyroY", String.valueOf(yGyro));
//
//            zGyro = Gdx.input.getGyroscopeZ();
//            Gdx.app.log("GyroZ", String.valueOf(zGyro));
//        }
//        if(lightSensorActive){
//            xLight = Gdx.input.getAccelerometerX();
//            Gdx.app.log("LightX", String.valueOf(xLight));
//
//            yLight = Gdx.input.getAccelerometerY();
//            Gdx.app.log("LightY", String.valueOf(yLight));
//
//            zLight = Gdx.input.getAccelerometerZ();
//            Gdx.app.log("LightZ", String.valueOf(zLight));
//        }
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float userPosX = (float) Gdx.input.getX();
        float userPosY = (float) Gdx.graphics.getHeight() - Gdx.input.getY();
        float firstDice = 0;
        float secondDice = 0;
        monopoly.batch.begin();

        //Roll Dice Button
        monopoly.batch.draw(rollDice, xPosButtons, yPosInitialButtons, buttonSizeX, buttonSizeY);
        if (Gdx.input.isKeyJustPressed(Input.Keys.VOLUME_UP)) {
            cheatDice++;
            Gdx.app.log("Ist: ", String.valueOf(cheatDice));
        }
        /**
         * Pressing the Roll Dice Button
         */
        if (isCorrectPosition(userPosX, userPosY, xPosButtons, yPosInitialButtons, buttonSizeX, buttonSizeY, 0 * yPosOffsetButtons)
                && Gdx.input.justTouched() && onTurn) {


            firstDice = random.nextInt(6)+1;
            if (Gdx.input.isKeyPressed(Input.Keys.VOLUME_DOWN) && cheatDice ==0) {
                secondDice = firstDice;
                cheatActivated = true;
            }else if(Gdx.input.isKeyPressed(Input.Keys.VOLUME_DOWN) && cheatDice !=0){
                if(cheatDice>=6){
                    firstDice = 6;
                    secondDice = 6;
                }else {
                    firstDice = cheatDice;
                    secondDice = cheatDice;
                }
                cheatActivated = true;
            }
            else {
                secondDice = random.nextInt(6)+1;
                cheatActivated = false;
            }
            //onTurn = false;

            dice1 = setDice(firstDice);
            dice2 = setDice(secondDice);
            drawDice(dice1, dice2);

            if (firstDice == secondDice) {
                onTurn = true;
            }
            Gdx.app.log("Soll: ", String.valueOf(cheatDice));
            cheatDice =0;
        }
        drawDice(dice1, dice2);
//        if (Gdx.input.isKeyPressed(Input.Keys.VOLUME_DOWN)) {
//            toggleVibration=toggleVibration*-1;
//        }
//        if(toggleVibration > 0){
//            Gdx.input.vibrate(100);
//        }
        monopoly.batch.end();
    }

    private Texture setDice(float value) {
        String path;
        switch ((int) value){
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
        monopoly.batch.draw(d1, Gdx.graphics.getWidth()/8, (Gdx.graphics.getHeight()/4)-500, 1000, 1000);
        monopoly.batch.draw(d2, Gdx.graphics.getWidth()/2, (Gdx.graphics.getHeight()/4)-500, 1000, 1000);
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

    private static boolean isCorrectPosition(float userPosX, float userPosY, float xPosButton, float yPosButton, float buttonSizeX, float buttonSizeY, float yPosOffset) {
        return (userPosX > xPosButton && userPosX < xPosButton + buttonSizeX && userPosY > (yPosButton + yPosOffset) && userPosY < yPosButton + yPosOffset + buttonSizeY);
    }
}
