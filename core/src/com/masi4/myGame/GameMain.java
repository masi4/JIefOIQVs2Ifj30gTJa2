package com.masi4.myGame;

import com.badlogic.gdx.ApplicationListener;

/**
 * Created by U1wknUzeU6 on 10.05.2018.
 */

public class GameMain implements ApplicationListener
{
    public static final GameMainClass game = new GameMainClass();

    @Override
    public void create() {
        game.create();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        game.render();
    }

    @Override
    public void pause() {
        game.pause();
    }

    @Override
    public void resume() {
        game.resume();
    }

    @Override
    public void dispose() {
        game.dispose();
    }
}
