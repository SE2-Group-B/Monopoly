package se2.groupb.monopoly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Random;

public class DiceRoll {

    private Random random = new Random();

    private boolean accelerometerActive = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);

    private boolean cheatActivated;
    private boolean shakeCheatActivated;
    private boolean onTurn;
    private boolean reported;
    private boolean keyVolumeUp;

    private int cheatDice;
    private int pachCount;
    private int firstDice;
    private int secondDice;

    private Texture dice1;
    private Texture dice2;

    private Player player;

    public DiceRoll() {
        this.onTurn = true;
    }

    public int roll(Player player) {
        this.player = player;
        firstDice = random.nextInt(6) + 1;
        secondDice = 0;
        chechForPach();
        this.dice1 = setDice(firstDice);
        this.dice2 = setDice(secondDice);
        cheatDice = 0;
        return firstDice + secondDice;
    }

    public ArrayList<Texture> getDiceTextures() {
        ArrayList<Texture> list = new ArrayList<>();
        list.add(this.dice1);
        list.add(this.dice2);
        return list;
    }

    private void chechForPach() {
        if (Gdx.input.isKeyPressed(Input.Keys.VOLUME_DOWN) && cheatDice == 0) {
            secondDice = firstDice;
            cheatActivated = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.VOLUME_DOWN) && cheatDice != 0) {
            if (cheatDice >= 6) {
                cheatDice = 6;
            }
            firstDice = secondDice = cheatDice;
            cheatActivated = true;
        } else {
            secondDice = random.nextInt(6) + 1;
        }
        onTurn = false;
        if (firstDice == secondDice) {
            onTurn = true;
            pachCount++;
            player.setPrison(false);
        }
        if (pachCount > 2) {
            onTurn = false;
            player.goToJail();
        }
    }

    public void checkForShakeCheat() {
        if (Gdx.input.isKeyPressed(Input.Keys.VOLUME_DOWN) && !shakeCheatActivated && accelerometerActive) {
            float xAccel = Gdx.input.getAccelerometerX();
            float yAccel = Gdx.input.getAccelerometerY();
            float zAccel = Gdx.input.getAccelerometerZ();
            if (xAccel < -15 || xAccel > 15 || yAccel < -15 || yAccel > 15 || zAccel < -15 || zAccel > 15) {
                player.changeMoney(100);
                cheatActivated = shakeCheatActivated = true;
            }
        }
    }

    public void reportCheat() {
        if (Gdx.input.justTouched() && !reported) {
            if (cheatActivated) {
                player.changeMoney(100);
            } else {
                player.changeMoney(-100);
            }
            reported = true;
        }
    }

    public Texture setDice(int value) {
        return new Texture("images/Dice/dice_"+value+".png");
    }

    public void checkManualPachCount() {
        if (Gdx.input.isKeyPressed(Input.Keys.VOLUME_UP)) {
            if (!keyVolumeUp) {
                keyVolumeUp = true;
                cheatDice++;
            }
        } else {
            keyVolumeUp = false;
        }
    }

    public void reset() {
        cheatActivated = shakeCheatActivated = reported = false;
        onTurn = true;
        cheatDice = 0;
        pachCount = 0;
    }

    public boolean getOnTurn() {
        return this.onTurn;
    }
}