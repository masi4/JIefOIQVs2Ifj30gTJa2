package com.masi4.screens;

/**
 * Created by OIEFIJM on 23.10.2017.
 *
 * Экран непосредственно игрового процесса
 */

import com.masi4.gamehelpers.InputHandler;
import com.masi4.gameworld.GameWorld;
import com.masi4.gameworld.GameRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class GameplayScreen implements Screen {

    private GameWorld world;
    private com.masi4.gameworld.GameRenderer renderer;

    public GameplayScreen()
    {
        world = new GameWorld();
        renderer = new com.masi4.gameworld.GameRenderer(world, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.input.setInputProcessor(new InputHandler(world.GetPlayer()));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        world.update(delta);
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

