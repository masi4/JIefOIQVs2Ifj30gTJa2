package com.masi4.myGame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.masi4.myGame.GameMain;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Game";
		config.width = GameMain.SCREEN_WIDTH;
		config.height = GameMain.SCREEN_HEIGHT;
		new LwjglApplication(new GameMain(), config);
	}
}
