package com.masi4.screens;

/**
 * Created by OIEFIJM on 23.10.2017.
 *
 * Экран главного меню
 */


// TODO: управление в меню по стрелочкам
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;

import com.masi4.gamehelpers.AssetLoader;
import com.masi4.gameobjects.Level.LevelNames;

import static com.masi4.myGame.GameMain.*;

public class MainMenuScreen implements Screen
{
    private OrthographicCamera camera;
    protected Stage stage;
    SpriteBatch batch;

    public MainMenuScreen()
    {
        AssetLoader.load_MainMenu();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage = new Stage(new FillViewport(SCREEN_WIDTH/3.8f,SCREEN_HEIGHT/3.8f));
        batch = new SpriteBatch();

    }

    @Override
    public void show()
    {
        Gdx.input.setInputProcessor(stage);

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.center();
        mainTable.padLeft(30);

        ImageButton playButton = new ImageButton(new TextureRegionDrawable(AssetLoader.playButtonTxrReg[0]),new TextureRegionDrawable(AssetLoader.playButtonTxrReg[1]));
        ImageButton optionsButton = new ImageButton(new TextureRegionDrawable(AssetLoader.optionsButtonTxrReg[0]),new TextureRegionDrawable(AssetLoader.optionsButtonTxrReg[1]));
        ImageButton exitButton = new ImageButton(new TextureRegionDrawable(AssetLoader.exitButtonTxrReg[0]),new TextureRegionDrawable(AssetLoader.exitButtonTxrReg[1]));

        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(new GameplayScreen(LevelNames.LEVEL_1)); // Экран выбора уровня
                dispose();
            }
        });
        optionsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new OptionsScreen()); // Экран настроек
                dispose();
            }
        });
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                Gdx.app.exit();
            }
        });

        mainTable.left();
        mainTable.add(playButton); mainTable.row();
        mainTable.add(optionsButton); mainTable.row();
        mainTable.add(exitButton);

        stage.addActor(mainTable);
    }


    @Override
    public void render(float delta)
    {
        batch.begin();
        for(int i = 0; i <5;i++) {
            batch.draw(AssetLoader.MainMenu_Bg[i], 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
        batch.end();

        camera.update();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        AssetLoader.dispose_MainMenu();
        stage.dispose();
    }
}
