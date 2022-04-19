package se2.groupb.monopoly;

import com.badlogic.gdx.Game;



public class Monopoly extends Game {

	public static Monopoly INSTANCE;

	public Monopoly() {
		if (INSTANCE == null) { INSTANCE = this; }
	}

	@Override
	public void create () {
		setScreen(new StartScreen());



	}

}
