package com.masi4.screens;

/**
 * Created by OIEFIJM on 23.10.2017.
 *
 * Экран непосредственно игрового процесса
 */

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.masi4.GUI.WalkingControl;
import com.masi4.gamehelpers.InputHandler;
import com.masi4.gameworld.GameWorld;
import com.masi4.gameworld.GameRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class GameplayScreen implements Screen {
    private GameWorld world;
    private com.masi4.gameworld.GameRenderer renderer;
    public WalkingControl controller;
    public GameplayScreen()
    {
        world = new GameWorld();
        renderer = new com.masi4.gameworld.GameRenderer(world, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        controller = new WalkingControl();

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(new InputHandler(controller,world.GetPlayer()));
        inputMultiplexer.addProcessor(controller.stage);

        Gdx.input.setInputProcessor(inputMultiplexer);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        world.update(delta);
        renderer.render();
        controller.render(delta);
        //Gdx.app.log("GameScreen", "JUMP: "+world.GetPlayer().jump);
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

