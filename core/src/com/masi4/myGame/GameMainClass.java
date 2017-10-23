package com.masi4.myGame;

import com.masi4.screens.GameplayScreen;    // <- обрати внимание (зделвно в германии ahAHA)))) )
import com.badlogic.gdx.Game;

public class GameMainClass extends Game {
    public static final int SCREEN_HEIGHT = 480;
    public static final int SCREEN_WIDTH = 800;
	
	@Override
	public void create () {

        this.setScreen(new GameplayScreen());

	}

	@Override
	public void render () {


	}
	
	@Override
	public void dispose () {


	}
}
