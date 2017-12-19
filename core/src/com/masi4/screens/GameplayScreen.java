package com.masi4.screens;

/**
 * Created by OIEFIJM on 23.10.2017.
 *
 * Экран непосредственно игрового процесса
 */

import com.masi4.GUI.WalkingControl;
import com.masi4.gamehelpers.AssetLoader;
import com.masi4.gamehelpers.InputHandler;
import com.masi4.gameworld.GameWorld;
import com.masi4.gameworld.GameRenderer;
import com.masi4.gameworld.Level0_Renderer;
import com.masi4.gameobjects.LevelNames;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class GameplayScreen implements Screen
{
    private LevelNames levelName;
    private GameWorld world;
    private GameRenderer renderer;
    public WalkingControl controller;  // публичные поля = плохо

    public GameplayScreen(LevelNames levelName)
    {
        this.levelName = levelName;
        AssetLoader.load_Level(levelName);
        AssetLoader.load_Player();
        AssetLoader.load_Controller();

        // возможно создать дочерние классы от GameWorld. Для каждого levelName свой.
        world = new GameWorld(levelName);
        switch (levelName)
        {
            case TEST:
            {
                renderer = new Level0_Renderer(world, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            }
        }

        controller = new WalkingControl();
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(new InputHandler(controller, world.getPlayer()));
        inputMultiplexer.addProcessor(controller.stage);
        Gdx.input.setInputProcessor(inputMultiplexer);

    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        world.update(delta);
        renderer.render();
        controller.render(delta);
    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void dispose()
    {
        AssetLoader.dispose_Controller();
        AssetLoader.dispose_Player();
        AssetLoader.dispose_Level();
    }
}

