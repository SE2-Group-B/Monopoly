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

    public DiceRoll(){
        /**
         * not used
         */
    }
    public DiceRoll(Player player){
        this.player = player;
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

    public void reportCheat(Player p){
        if(Gdx.input.justTouched() && !reported){
            if (cheatActivated) {
                player.changeMoney(-200);
                p.changeMoney(100);
            } else {
                p.changeMoney(-100);
            }
            reported = true;
        }
    }

    public Texture setDice(int value) {
        String path;
        switch (value) {
            case 1:
                path = "images/Dice/dice_1.png";
                break;
            case 2:
                path = "images/Dice/dice_2.png";
                break;
            case 3:
                path = "images/Dice/dice_3.png";
                break;
            case 4:
                path = "images/Dice/dice_4.png";
                break;
            case 5:
                path = "images/Dice/dice_5.png";
                break;
            case 6:
                path = "images/Dice/dice_6.png";
                break;
            default:
                path = "images/Dice/dice_0.png";
                break;
        }
        return new Texture(path);
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