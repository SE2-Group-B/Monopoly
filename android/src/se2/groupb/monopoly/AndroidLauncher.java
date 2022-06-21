package se2.groupb.monopoly;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;


public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.hideStatusBar=true;
		config.useWakelock=true; //handy soll an bleiben!
		config.useGyroscope = true;
		config.useAccelerometer = true;
		initialize(new Monopoly(), config);
	}
}
