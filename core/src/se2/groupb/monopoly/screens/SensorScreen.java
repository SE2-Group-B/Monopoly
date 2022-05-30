package se2.groupb.monopoly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import java.util.Random;
import se2.groupb.monopoly.Monopoly;


public class SensorScreen extends GameScreenAdapter {

    private boolean AccelerometerActive = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);

    private float xAccel;
    private float yAccel;
    private float zAccel;

    private boolean cheatActivated;
    private boolean shakeCheatActivated;
    private boolean onTurn;
    private boolean reported;

    private Random random;
    private int cheatDice;
    private int pachCount;

    private Texture rollDice;
    private Texture reportCheat;
    private Texture dice1;
    private Texture dice2;

    private int buttonSizeX;
    private int buttonSizeY;
    private float yPosInitialButtons;
    private float yPosOffsetButtons;
    private float xPosButtons;


    public SensorScreen(Monopoly monopoly) {
        super(monopoly);
    }

    @Override
    public void show() {

        InputBackProcessor inputProcessor = new InputBackProcessor(monopoly);
        inputProcessor.backToMainMenuProcessor();

        reportCheat = new Texture("images/MenuButtons/report_cheat.png");
        rollDice = new Texture("images/MenuButtons/roll.png");
        dice1 = new Texture("images/Dice/dice_0.png");
        dice2 = new Texture("images/Dice/dice_0.png");
        cheatActivated = false;
        shakeCheatActivated = false;
        reported = false;
        onTurn = true;
        random = new Random();
        cheatDice = 0;
        pachCount = 0;

        buttonSizeX = Gdx.graphics.getWidth() / 3;
        buttonSizeY = (int) (Gdx.graphics.getHeight() / (4.545454 * 2));

        xPosButtons = (float) (Gdx.graphics.getWidth() / 2D - buttonSizeX / 2D);
        yPosInitialButtons = (float) (Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4D);
        yPosOffsetButtons = (float) (-Gdx.graphics.getWidth() / 8D);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float userPosX = (float) Gdx.input.getX();
        float userPosY = (float) Gdx.graphics.getHeight() - Gdx.input.getY();

        monopoly.batch.begin();

        monopoly.batch.draw(rollDice, xPosButtons-500, yPosInitialButtons, buttonSizeX, buttonSizeY);
        monopoly.batch.draw(reportCheat, xPosButtons+500, yPosInitialButtons, buttonSizeX, buttonSizeY);

        if(Gdx.input.isKeyJustPressed(Input.Keys.VOLUME_UP)){
            cheatDice++;
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa :  "+cheatDice);
        }
        /**
         * Pressing the Roll Dice Button
         */
        if (isCorrectPosition(userPosX, userPosY, xPosButtons-500, yPosInitialButtons, buttonSizeX, buttonSizeY, 0 * yPosOffsetButtons)
                && Gdx.input.justTouched() && onTurn) {

            roll();
        }

        /**
         * Pressing the Report Cheat Button
         */
        if (isCorrectPosition(userPosX, userPosY, xPosButtons+500, yPosInitialButtons, buttonSizeX, buttonSizeY, 0 * yPosOffsetButtons)
                && Gdx.input.justTouched() && !reported) {

            if(cheatActivated){
                Gdx.gl.glClearColor(0, 1, 0, 1);
            }else{
                Gdx.gl.glClearColor(1, 0, 0, 1);
            }
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            reported = true;
        }
        drawDice(dice1, dice2);

        /**
         * Check if phone is shaking while pressing volume down
         */
        if (Gdx.input.isKeyPressed(Input.Keys.VOLUME_DOWN) && !shakeCheatActivated){
            if(AccelerometerActive){
                xAccel = Gdx.input.getAccelerometerX();
                yAccel = Gdx.input.getAccelerometerY();
                zAccel = Gdx.input.getAccelerometerZ();
                if(xAccel < -15 || xAccel > 15 || yAccel < -15 || yAccel > 15 || zAccel < -15 || zAccel > 15){
                    Gdx.gl.glClearColor(0, 0, 1, 1);
                    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                    cheatActivated = shakeCheatActivated = true;
                }
            }
        }
        monopoly.batch.end();
    }

    public void roll(){
        System.out.println("dddddddddddddddddd  d d  "+cheatDice);
        int firstDice = random.nextInt(6) + 1;
        int secondDice = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.VOLUME_DOWN) && cheatDice ==0) {
            secondDice = firstDice;
            cheatActivated = true;
        }else if(Gdx.input.isKeyPressed(Input.Keys.VOLUME_DOWN) && cheatDice !=0){
            if(cheatDice>=6){
                firstDice = secondDice = 6;
            }else {
                firstDice = secondDice = cheatDice;
                }
            cheatActivated = true;
        }
        else {
            secondDice = random.nextInt(6)+1;
        }
        onTurn = false;

        dice1 = setDice(firstDice);
        dice2 = setDice(secondDice);
        drawDice(dice1, dice2);

        if (firstDice == secondDice) {
            onTurn = true;
            pachCount++;
        }
        if(pachCount > 2){
            onTurn = false;
            Gdx.gl.glClearColor(1, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        }
        cheatDice =0;
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
                path="images/Dice/dice_0.png";
                break;
        }
        return new Texture(path);
    }

    private void drawDice(Texture d1, Texture d2) {
        monopoly.batch.draw(d1, Gdx.graphics.getWidth()/8, (Gdx.graphics.getHeight()/4)-500, 1000, 1000);
        monopoly.batch.draw(d2, Gdx.graphics.getWidth()/2, (Gdx.graphics.getHeight()/4)-500, 1000, 1000);
    }

    private void reset(){
        cheatActivated = shakeCheatActivated = reported = false;
        onTurn = true;
        dice1 = setDice(0);
        dice2 = setDice(0);
        drawDice(dice1, dice2);
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

    @Override
    public void switchScreenDelayed(GameScreenAdapter screen, float delay) {

    }

}
