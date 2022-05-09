package se2.groupb.monopoly;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import se2.groupb.monopoly.screens.MainMenuScreen;
import se2.groupb.monopoly.screens.MonopolyScreen;
import se2.groupb.monopoly.screens.SensorScreen;


public class Monopoly extends Game {
    public SpriteBatch batch;
    GameLogic logicArray;
    Grundstueck[] arr;
    SensorScreen sensors;
    int[] diceRoll;



    @Override
    public void create() {
        logicArray = new GameLogic();
        batch = new SpriteBatch();
        arr = logicArray.getLogicArray();
//        diceRoll = sensors.roll();
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
