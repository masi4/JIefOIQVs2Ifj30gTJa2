package com.masi4.screens;

/*
 * Created by OIEFIJM on 23.10.2017.
 *
 * Экран главного меню
 */

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;

import com.masi4.gamehelpers.AssetLoader;
import com.masi4.myGame.GameMainClass;
import com.masi4.gameobjects.LevelNames;

import static com.masi4.myGame.GameMainClass.SCREEN_HEIGHT;
import static com.masi4.myGame.GameMainClass.SCREEN_WIDTH;

public class MainMenuScreen implements Screen
{
    private OrthographicCamera camera;
    private GameMainClass gameCtrl;
    protected Stage stage;
    SpriteBatch batch;

    public MainMenuScreen(GameMainClass gameCtrl)
    {
        AssetLoader.load_MainMenu();

        this.gameCtrl = gameCtrl;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage = new Stage(new FillViewport(SCREEN_WIDTH/4,SCREEN_HEIGHT/4));
        batch = new SpriteBatch();

    }

    @Override
    public void show()
    {
        Gdx.input.setInputProcessor(stage);

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top();

        TextureRegion[] playButtonTxrReg ={new TextureRegion(AssetLoader.MainMenu_Buttons,0,0,128,32),new TextureRegion(AssetLoader.MainMenu_Buttons,128,0,128,32)};
        ImageButton playButton = new ImageButton(new TextureRegionDrawable(playButtonTxrReg[0]),new TextureRegionDrawable(playButtonTxrReg[1]));
        TextureRegion[] optionsButtonTxrReg = {new TextureRegion(AssetLoader.MainMenu_Buttons,0,32,128,32),new TextureRegion(AssetLoader.MainMenu_Buttons,128,32,128,32)};
        ImageButton optionsButton = new ImageButton(new TextureRegionDrawable(optionsButtonTxrReg[0]),new TextureRegionDrawable(optionsButtonTxrReg[1]));
        TextureRegion[] exitButtonTxrReg = {new TextureRegion(AssetLoader.MainMenu_Buttons,0,64,128,32),new TextureRegion(AssetLoader.MainMenu_Buttons,128,64,128,32)};
        ImageButton exitButton = new ImageButton(new TextureRegionDrawable(exitButtonTxrReg[0]),new TextureRegionDrawable(exitButtonTxrReg[1]));

        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                gameCtrl.setScreen(new GameplayScreen(LevelNames.TEST)); // Экран выбора уровня
                dispose();
            }
        });
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        mainTable.add(playButton); mainTable.row();
        mainTable.add(optionsButton);  mainTable.row();
        mainTable.add(exitButton);

        stage.addActor(mainTable);
    }


    @Override
    public void render(float delta) {
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
    }
}
