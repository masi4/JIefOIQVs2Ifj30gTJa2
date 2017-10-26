package com.masi4.myGame;


import com.masi4.screens.GameplayScreen;    // <- обрати внимание (зделвно в германии ahAHA)))) )
import com.masi4.screens.MainMenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;


public class GameMainClass extends Game {
    public static final int SCREEN_HEIGHT = 480;
    public static final int SCREEN_WIDTH = 800;


	@Override
	public void create () {
		Gdx.app.log("GameMainClass", "Игра запущена");
        this.setScreen(new MainMenuScreen(this));

	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {

	}
}
