package com.masi4.myGame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.masi4.myGame.GameMainClass;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Game";
		config.width = GameMainClass.SCREEN_WIDTH;
		config.height = GameMainClass.SCREEN_HEIGHT;
		new LwjglApplication(new GameMainClass(), config);
	}
}
