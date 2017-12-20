package com.masi4.gameworld;

/**
 * Created by OIEFIJM on 19.12.2017.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import static com.masi4.myGame.GameMainClass.*;
import static com.masi4.gamehelpers.GameTextureRegions.*;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.masi4.gamehelpers.AssetLoader;
import com.masi4.gameobjects.Player;


public class Level0_Renderer extends GameRenderer
{
    // Objects
    private Player player;

    // Assets
    private TextureRegion player_default; // Впоследствии использовать Animation
    private TextureRegion
            level_BG1,
            level_BG2,
            level_BG3,
            level_BG4,
            level_grassBack,
            level_grassBackLoop,
            level_floor,
            level_grassForeLoop;

    // Misc
    private boolean cameraAttached;
    private float proportion = 0.42f;  // часть экрана, начиная с которой камера привязывается к персонажу
    private float staticBG_X;  // смещение фонов, которые двигаются с персонажем

    //
    // Методы
    //

    private void initGameObjects()
    {
        player = world.getPlayer();
    }

    private void initAssets()
    {
        level_BG1 = AssetLoader.level_BG1;
        level_BG2 = AssetLoader.level_BG2;
        level_BG3 = AssetLoader.level_BG3;
        level_BG4 = AssetLoader.level_BG4;
        level_grassBack = AssetLoader.level_grassBack;
        level_grassBackLoop = AssetLoader.level_grassBackLoop;
        level_floor = AssetLoader.level_floor;
        level_grassForeLoop = AssetLoader.level_grassForeLoop;

        player_default = AssetLoader.player_default;
    }

    public Level0_Renderer(GameWorld world, int gameWidth, int gameHeight)
    {
        super(world, gameWidth, gameHeight);
        initGameObjects();
        initAssets();
        cameraAttached = false;
    }

    public void render()
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // привязка камеры к персонажу
        // TODO: убрать рывки и рывки бэкграунда при прыжках
        if (player.getX() > SCREEN_WIDTH * proportion &&
                player.getX() < world.getLevelWidth() - SCREEN_WIDTH * (1 - proportion))
        {
            cameraAttached = true;
            camera.translate(player.getSpeedX() * player.getDelta(), 0);
        }
        else if (cameraAttached && player.getX() <= SCREEN_WIDTH * proportion)  // Вошел обратно в начало уровня
        {
            cameraAttached = false;
            camera.position.set(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 0);
        }
        else if (cameraAttached)  // Дошёл до конца уровня
        {
            cameraAttached = false;
            camera.position.set(world.getLevelWidth() - SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 0);
        }
        camera.update();
        batcher.setProjectionMatrix(camera.combined);

        batcher.begin();
            // TODO: параллакс
            // фоны двигаются с персонажем
            if (cameraAttached)
            {
                staticBG_X = player.getX() - SCREEN_WIDTH * proportion;
            }
            else
            {
                if (player.getX() < SCREEN_WIDTH * proportion)  // персонаж в начале уровня
                {
                    staticBG_X = 0;
                }
                else  // в конце
                {
                    staticBG_X = world.getLevelWidth() - SCREEN_WIDTH;
                }
            }
            batcher.draw(level_BG1, staticBG_X, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
            batcher.draw(level_BG2, staticBG_X, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
            batcher.draw(level_BG3, staticBG_X, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
            batcher.draw(level_BG4, staticBG_X, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

            batcher.draw(level_grassBack, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
            for (int i = 1; i <= 3; i++)
            {
                batcher.draw(level_grassBackLoop, 256 * i, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
            }
            batcher.draw(level_floor, 0, 25, world.getLevelWidth(), world.getLevelFloorHeight());

            for (int i = 0; i < 4; i++)
            {
                batcher.draw(level_grassForeLoop, 256 * i, -185, SCREEN_WIDTH, SCREEN_HEIGHT);
            }

            batcher.draw(player_default, player.getX(), player.getY(), player.getWidth(), player.getHeight());
        batcher.end();





    }
}
