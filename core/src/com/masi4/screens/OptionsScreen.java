package com.masi4.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;

import static com.masi4.myGame.GameMainClass.SCREEN_HEIGHT;
import static com.masi4.myGame.GameMainClass.SCREEN_WIDTH;

/**
 * Created by U1wknUzeU6 on 20.12.2017.
 */

public class OptionsScreen implements Screen
{
    private OrthographicCamera camera;
    SpriteBatch batch;
    protected Stage stage;
    public OptionsScreen()
    {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage = new Stage(new FillViewport(SCREEN_WIDTH/4,SCREEN_HEIGHT/4));
        batch = new SpriteBatch();
    }

    @Override
    public void show()
    {
        Table mainTable = new Table();
        mainTable.setFillParent(true);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont(Gdx.files.internal("fonts/ESKEYB.fnt"));
        labelStyle.fontColor = Color.WHITE;

        Label[] optMenuLabels = {new Label("123",labelStyle)};
        mainTable.add(optMenuLabels[0]);


        stage.addActor(mainTable);
    }


    @Override
    public void render(float delta)
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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

    }
}
