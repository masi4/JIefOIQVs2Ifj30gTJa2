package com.masi4.myGame;

import com.masi4.gamehelpers.GamePreferences;
import com.masi4.screens.MainMenuScreen;
import com.badlogic.gdx.Game;
import com.masi4.gamehelpers.AssetLoader;

public class GameMainClass extends Game
{


	@Override
	public void create ()
	{
		AssetLoader.load();
		if(GamePreferences.Options.getInteger("Language")==0)
			GamePreferences.SwitchToEn();
		if(GamePreferences.Options.getInteger("Language")==1)
			GamePreferences.SwitchToRu();
        this.setScreen(new MainMenuScreen());
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {

		super.dispose();
		AssetLoader.dispose();
	}
}
