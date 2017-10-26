package com.masi4.gameworld;

/**
 * Created by OIEFIJM on 23.10.2017.
 *
 * Данный класс отвечает за отрисовку игровых объектов
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.masi4.myGame.GameMainClass;
import com.sun.org.apache.xpath.internal.operations.Or;

public class GameRenderer {

    private GameWorld world;
    private OrthographicCamera camera;

    public GameRenderer(GameWorld world)
    {
        this.world = world;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameMainClass.SCREEN_WIDTH, GameMainClass.SCREEN_HEIGHT);
    }

    public void render()
    {
        Gdx.gl.glClearColor(0, 255, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

}
