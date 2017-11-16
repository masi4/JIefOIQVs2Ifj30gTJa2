package com.masi4.screens;

/*
 * Created by OIEFIJM on 23.10.2017.
 *
 * Экран главного меню
 */

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;

import com.masi4.myGame.GameMainClass;

import static com.masi4.myGame.GameMainClass.SCREEN_HEIGHT;
import static com.masi4.myGame.GameMainClass.SCREEN_WIDTH;

public class MainMenuScreen implements Screen {
    private OrthographicCamera camera;
    private GameMainClass gameCtrl;
    protected Stage stage;
    Texture buttonsTxr;
    SpriteBatch batch;
    public MainMenuScreen(GameMainClass gameCtrl){
        Gdx.app.log("GameScreen", "Игровое меню запущено");
        this.gameCtrl = gameCtrl;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage = new Stage(new FillViewport(SCREEN_WIDTH/4,SCREEN_HEIGHT/4));
        batch = new SpriteBatch();
        loadTextures();
    }
    Texture[] menuBg;
    private void loadTextures(){
        menuBg = new Texture[5];
        for(int i = 0; i <5;i++){
            menuBg[i] =  new Texture(Gdx.files.internal("gameplay/level_0/bg_0"+i+".png"));
        }
        buttonsTxr = new Texture(Gdx.files.internal("menuButtons-256x96.png"));

    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top();

        TextureRegion[] playButtonTxrReg ={new TextureRegion(buttonsTxr,0,0,128,32),new TextureRegion(buttonsTxr,128,0,128,32)};
        ImageButton playButton = new ImageButton(new TextureRegionDrawable(playButtonTxrReg[0]),new TextureRegionDrawable(playButtonTxrReg[1]));
        TextureRegion[] optionsButtonTxrReg = {new TextureRegion(buttonsTxr,0,32,128,32),new TextureRegion(buttonsTxr,128,32,128,32)};
        ImageButton optionsButton = new ImageButton(new TextureRegionDrawable(optionsButtonTxrReg[0]),new TextureRegionDrawable(optionsButtonTxrReg[1]));
        TextureRegion[] exitButtonTxrReg = {new TextureRegion(buttonsTxr,0,64,128,32),new TextureRegion(buttonsTxr,128,64,128,32)};
        ImageButton exitButton = new ImageButton(new TextureRegionDrawable(exitButtonTxrReg[0]),new TextureRegionDrawable(exitButtonTxrReg[1]));

        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                gameCtrl.setScreen(new GameplayScreen());
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
            batch.draw(menuBg[i], 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
        batch.end();
       // Gdx.gl.glClearColor(255, 0, 0, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
