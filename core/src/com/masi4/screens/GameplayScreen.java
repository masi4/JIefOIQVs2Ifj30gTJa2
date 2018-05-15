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
import com.masi4.gameobjects.objects.Skeleton;
import com.masi4.gameworld.GameWorld;
import com.masi4.gameworld.renderers.GameRenderer;
import com.masi4.gameworld.renderers.Level1_Renderer;
import com.masi4.gameobjects.Level.LevelNames;

import static com.masi4.myGame.GameMain.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.masi4.gameobjects.SkeletonListener;

public class GameplayScreen implements Screen, SkeletonListener
{
    private GameWorld world;
    private LevelNames levelName;
    private GameRenderer renderer;
    private GUI gui;
    private float runTime;

    public GameplayScreen(LevelNames levelName)
    {
        this.levelName = levelName;

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
            case LEVEL_1:
            {
                AssetLoader.load_SkeletonTextures();

                renderer = new Level1_Renderer(world, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                world.addSkeletonListener((Level1_Renderer) renderer);
            }
        }

        gui = new GUI(world.getPlayer()); //TODO: this(?) InventoryScreen сделать не screen

        world.addSkeletonListener(this);
    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
        {
            game.setScreen(new MainMenuScreen());
            dispose();
        }
        runTime += delta;

        world.update(delta);
        renderer.render(runTime);
        gui.render(delta);
        // Конфликтует с GUI рендером. TODO: Исправить UPD: Кнопка перекачевала в GUI
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
        AssetLoader.dispose_PlayerDefaultAttack();


        switch (levelName)
        {
            case LEVEL_1:
            {
                AssetLoader.dispose_Level1();
                AssetLoader.dispose_Skeleton_Textures();
                AssetLoader.dispose_SkeletonSwordAttack_Texture();

                // dispose all remaining skeletons (mobs)
            }
        }

        gui.dispose();
    }

    // TODO: перенести в рендерер
    @Override
    public void skeletonSpawned(Skeleton skeleton)
    {
        AssetLoader.load_NewSkeleton(skeleton);
        AssetLoader.load_NewSkeleton_MeleeSwordAttack(skeleton);
    }

    @Override
    public void skeletonDead(Skeleton skeleton)
    {
        AssetLoader.remove_Skeleton(skeleton);
        AssetLoader.remove_SkeletonMeleeSwordAttack(skeleton);
    }
}

