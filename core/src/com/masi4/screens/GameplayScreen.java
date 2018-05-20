package com.masi4.screens;

/**
 * Created by OIEFIJM on 23.10.2017.
 *
 * Экран непосредственно игрового процесса
 */

import com.badlogic.gdx.Input;
import com.masi4.GUI.GUI;
import com.masi4.GUI.PlayerDeathScreen;
import com.masi4.gamehelpers.recourceHandlers.AssetLoader;
import com.masi4.gamehelpers.recourceHandlers.SoundManager;
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
    private GameWorld gameworld;
    private LevelNames levelName;
    private GameRenderer renderer;
    private GUI gui;
    private PlayerDeathScreen playerDeathScreen;
    private float runTime;

    public GameplayScreen(LevelNames levelName)
    {
        this.levelName = levelName;

        AssetLoader.load_Level(levelName);
        AssetLoader.load_Player();
        AssetLoader.load_PlayerDefaultAttack();
        AssetLoader.load_Fonts();
        AssetLoader.load_Controller();
        AssetLoader.load_GUI_Buttons();

        // возможно создать дочерние классы от GameWorld. Для каждого levelName свой.
        gameworld = new GameWorld(levelName);
        switch (levelName)
        {
            case LEVEL_1:
            {
                AssetLoader.load_SkeletonBasis();

                renderer = new Level1_Renderer(gameworld, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                gameworld.addSkeletonListener((Level1_Renderer) renderer);
                // TODO: сделать, чтобы в PlayerDeathScreen не создавался новый GameplayScreen
                if (!AssetLoader.level1_ambience.isPlaying()) {
                    // TODO: рандомить только те моменты, в которых нет пиковых шумов
                    SoundManager.playMusic(AssetLoader.level1_ambience, true);
                    AssetLoader.level1_ambience.setPosition(random.nextInt(731));
                }
            }
        }

        gui = new GUI(gameworld.getPlayer());
        playerDeathScreen = new PlayerDeathScreen(this);
        gameworld.addSkeletonListener(this);
    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        // TODO: сделать. чтобы на ESC не выбрасывало в главное меню, если открыт инвентарь. Вместо этого закрыть инвентарь.
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
        {
            game.setScreen(new MainMenuScreen());
            dispose();
        }
        runTime += delta;

        gameworld.update(delta);
        renderer.render(runTime);
        gui.render(delta);
        //TODO: перенести сюда проверку player.isDead()
        if(gameworld.getPlayer().isDead())
        {
            playerDeathScreen.render(delta);
        }

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
                AssetLoader.dispose_SkeletonBasis();
                AssetLoader.dispose_SkeletonSwordAttack_Texture();

                // dispose all remaining skeletons (mobs)
            }
        }

        gui.dispose();
    }

    public LevelNames getLevelName() { return levelName; }

    public void reloadLevel(LevelNames levelName)
    {
        switch (levelName)
        {
            case LEVEL_1:
                gameworld = new GameWorld(levelName);
                renderer = new Level1_Renderer(gameworld, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                gameworld.addSkeletonListener((Level1_Renderer) renderer);
                // TODO: сделать, чтобы в PlayerDeathScreen не создавался новый GameplayScreen
                if (!AssetLoader.level1_ambience.isPlaying()) {
                    // TODO: рандомить только те моменты, в которых нет пиковых шумов
                    SoundManager.playMusic(AssetLoader.level1_ambience, true);
                    AssetLoader.level1_ambience.setPosition(random.nextInt(731));

                }
            break;
        }

        gameworld.addSkeletonListener(this);
        gui = new GUI(gameworld.getPlayer());
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

