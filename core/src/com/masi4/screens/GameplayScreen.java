package com.masi4.screens;

/**
 * Created by OIEFIJM on 23.10.2017.
 *
 * Экран непосредственно игрового процесса
 */

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input;
import com.masi4.GUI.AttackButton;
import com.masi4.GUI.WalkingControl;
import com.masi4.gamehelpers.AssetLoader;
import com.masi4.gamehelpers.InputHandler;
import com.masi4.gameworld.GameWorld;
import com.masi4.gameworld.GameRenderer;
import com.masi4.gameworld.Level0_Renderer;
import com.masi4.myGame.GameMainClass;
import com.masi4.gameobjects.Level.LevelNames;

public class GameplayScreen implements Screen
{
    private GameMainClass gameCtrl;
    private InputMultiplexer inputMultiplexer;
    private GameWorld world;
    private GameRenderer renderer;

    private WalkingControl controller;
    private AttackButton attackButton;
    private float runTime;

    public GameplayScreen(GameMainClass gameCtrl, LevelNames levelName)
    {
        runTime = 0;
        this.gameCtrl = gameCtrl;
        AssetLoader.load_Level(levelName);
        AssetLoader.load_Player();
        AssetLoader.load_PlayerAttack();
        AssetLoader.load_Controller();
        AssetLoader.load_AttackButton();
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
        attackButton = new AttackButton();
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(new InputHandler(controller, attackButton, world.getPlayer().graphics));
        inputMultiplexer.addProcessor(controller.stage);
        inputMultiplexer.addProcessor(attackButton.stage);
        Gdx.input.setInputProcessor(inputMultiplexer);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK))
        {
            gameCtrl.setScreen(new MainMenuScreen(gameCtrl));
            dispose();
        }
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);
        controller.render(delta);
        attackButton.render(delta);
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
        inputMultiplexer.clear();
    }
}

