package com.masi4.gameworld;

/**
 * Created by OIEFIJM on 19.12.2017.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import static com.masi4.myGame.GameMainClass.*;
import static com.masi4.gamehelpers.GameTextureRegions.*;

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
            level_grass,
            level_floor;

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
        level_grass = AssetLoader.level_grass;
        level_floor = AssetLoader.level_floor;

        player_default = AssetLoader.player_default;
    }

    public Level0_Renderer(GameWorld world, int gameWidth, int gameHeight)
    {
        super(world, gameWidth, gameHeight);
        initGameObjects();
        initAssets();
    }

    public void render()
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();

        batcher.draw(level_BG1, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        batcher.draw(level_BG2, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        batcher.draw(level_BG3, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        batcher.draw(level_BG4, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        batcher.draw(level_grass, 0, 0, level_0_grass_Width * 2, level_0_grass_Height * 3);
        batcher.draw(level_floor, 0, 0, level_0_grass_Width * 2, world.getLevelFloorHeight());

        // возможно:
        // персонаж доходит где то до половины и камера привязывается к персонажу
        // уходит в начало и камера отвязывается
        // то же самое с концом
        batcher.draw(player_default, player.getX(), player.getY(), player.getWidth(), player.getHeight());

        batcher.end();
    }
}
