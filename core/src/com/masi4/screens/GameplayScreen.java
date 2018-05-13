package com.masi4.screens;

/**
 * Created by OIEFIJM on 23.10.2017.
 *
 * Экран непосредственно игрового процесса
 */

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Input;
import com.masi4.GUI.GUI;
import com.masi4.gamehelpers.AssetLoader;
import com.masi4.gameworld.GameWorld;
import com.masi4.gameworld.GameRenderer;
import com.masi4.gameworld.Level0_Renderer;
import com.masi4.gameobjects.Level.LevelNames;

import static com.masi4.myGame.GameMain.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class GameplayScreen implements Screen
{
    private GameWorld world;
    private GameRenderer renderer;
    private GUI gui;
    private float runTime;

    public GameplayScreen(LevelNames levelName)
    {
        AssetLoader.load_Level(levelName);
        AssetLoader.load_Player();
        AssetLoader.load_PlayerDefaultAttack();
        if (Gdx.app.getType() != Application.ApplicationType.Desktop) {
            AssetLoader.load_Controller();
            AssetLoader.load_GUI_Buttons();
        }

        // возможно создать дочерние классы от GameWorld. Для каждого levelName свой.
        world = new GameWorld(levelName);
        switch (levelName)
        {
            case TEST:
            {
                renderer = new Level0_Renderer(world, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            }
        }

        gui = new GUI(world.getPlayer()); //TODO: this(?) InventoryScreen сделать не screen

    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);
        gui.render(delta);
        // Конфликтует с GUI рендером. TODO: Исправить
        /*if (Gdx.input.isKeyJustPressed(Input.Keys.BACK))
        {
            game.setScreen(new MainMenuScreen());
            dispose();
        }*/
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
        gui.dispose();
    }
}

