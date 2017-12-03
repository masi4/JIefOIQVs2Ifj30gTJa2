package com.masi4.gameworld;

/**
 * Created by OIEFIJM on 23.10.2017.
 *
 * Данный класс отвечает за отрисовку игровых объектов
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.physics.box2d.World;
import com.masi4.GUI.WalkingControl;
import com.masi4.gamehelpers.AssetLoader;
import com.masi4.gameobjects.Player;
import static com.masi4.myGame.GameMainClass.*;
import static com.masi4.gamehelpers.GameTextureRegions.*;

public class GameRenderer {

    private int gameWidth;
    private int gameHeight;

    private GameWorld world;
    private OrthographicCamera camera;
    private SpriteBatch batcher;
    public GameRenderer(GameWorld world, int gameWidth, int gameHeight) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.world = world;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(camera.combined);

    }

    public void render() {
        Player player = world.GetPlayer();  // TODO: уберем это из цикла далее для улучшение производительности

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();

        batcher.draw(AssetLoader.level_test, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        batcher.draw(AssetLoader.player_test, player.GetX(), player.GetY(), player.GetWidth(), player.GetHeight());

        batcher.end();


    }
}
