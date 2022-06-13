package se2.groupb.monopoly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class DiceRoll {

    private Random random = new Random();

    private boolean cheatActivated;
    private boolean shakeCheatActivated;
    private boolean onTurn;
    private boolean reported;
    private boolean keyVolumeUp;

    private int cheatDice;
    private int pachCount;
    private int firstDice;
    private int secondDice;

    private Player player;


    public int roll() {
        firstDice = random.nextInt(6) + 1;
        secondDice = 0;
        chechForPach();
//        dice1 = setDice(firstDice);
//        dice2 = setDice(secondDice);
//        drawDice(dice1, dice2);
        cheatDice = 0;
        return firstDice + secondDice;
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
//            player.move(positions[player.getPosition()]);
        }
    }

    private Texture setDice(int value) {
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
}