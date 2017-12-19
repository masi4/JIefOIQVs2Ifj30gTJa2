package com.masi4.gameworld;

/**
 * Created by OIEFIJM on 23.10.2017.
 *
 * Данный класс отвечает за отрисовку игровых объектов
 */

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.masi4.myGame.GameMainClass.*;

public abstract class GameRenderer
{
    protected int gameWidth;
    protected int gameHeight;

    protected GameWorld world;
    protected OrthographicCamera camera;
    protected SpriteBatch batcher;

    //
    // Методы
    //

    public GameRenderer(GameWorld world, int gameWidth, int gameHeight)
    {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.world = world;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(camera.combined);
    }

    abstract public void render();


}
