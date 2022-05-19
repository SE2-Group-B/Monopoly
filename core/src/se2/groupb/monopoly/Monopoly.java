package se2.groupb.monopoly;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import se2.groupb.monopoly.screens.MainMenuScreen;
import se2.groupb.monopoly.screens.SensorScreen;


public class Monopoly extends Game {
    public SpriteBatch batch;
    GameLogic logicArray;
    Property[] arr;
    SensorScreen sensors;
    int[] diceRoll;



    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new MainMenuScreen(this));

    }

//	public static Monopoly INSTANCE;
//
//	public Monopoly() {
//		if (INSTANCE == null) { INSTANCE = this; }
//	}


    @Override
    public void render() {
        super.render();

    }

    @Override
    public void dispose() {
        batch.dispose();

    }

}
