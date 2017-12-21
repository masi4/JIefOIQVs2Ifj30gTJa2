package com.masi4.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.masi4.gamehelpers.AssetLoader;
import com.masi4.myGame.GameMainClass;

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
    private GameMainClass gameCtrl;

    Label.LabelStyle labelActive;
    Label.LabelStyle labelInactive;
    Label.LabelStyle systemLabel;

    public OptionsScreen(GameMainClass gameCtrl)
    {
        AssetLoader.load_Fonts();
        this.gameCtrl = gameCtrl;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage = new Stage(new FillViewport(SCREEN_WIDTH/2,SCREEN_HEIGHT/2));
        batch = new SpriteBatch();
        labelActive = new Label.LabelStyle();
        labelInactive = new Label.LabelStyle();
        systemLabel = new Label.LabelStyle();
        load_lableStyles();
    }

    @Override
    public void show()
    {
        Gdx.input.setInputProcessor(stage);
        Table mainTable = new Table();
        mainTable.setFillParent(true);

        Label[] systemLabels = {
                new Label("НАЗAД",systemLabel),
        };
        Label[] labels = {

                new Label("Звук",labelActive),
                new Label("Контроллер",labelActive)

        };
        final Label[][] options = {
                {new Label("ВКЛ",labelActive), new Label("ВЫКЛ",labelActive)},
                {new Label("ВИДИМЫЙ", labelActive),new Label("СКРЫТЫЙ", labelActive)}

        };
        load_properties(options);
       // mainTable.left().top().pad(10,10,0,0);

        mainTable.add(labels[0]).left().pad(15,15,10,0).expandX();
        mainTable.add(options[0][0]).right().padRight(15);mainTable.add(options[0][1]).left().padRight(15);
        mainTable.row();
        mainTable.add(labels[1]).left().padLeft(15).padBottom(10);
        mainTable.add(options[1][0]).right().padRight(15);mainTable.add(options[1][1]).left().padRight(15);
        mainTable.row();

        mainTable.add(systemLabels[0]).left().bottom().padLeft(15).padBottom(10).expandY();

        for (final Label[] LL: options ) {
            for (final Label L: LL ) {
                L.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        if(L.getStyle() == labelInactive){
                            for (Label l: LL) {
                                if(l.getStyle()==labelActive){
                                    l.setStyle(labelInactive);
                                }
                            }
                            L.setStyle(labelActive);
                        }
                    }
                });
            }
        }
        systemLabels[0].addListener(new ClickListener(){
             @Override
             public void clicked(InputEvent event, float x, float y) {
                 gameCtrl.setScreen(new MainMenuScreen(gameCtrl)); // Экран настроек
                 dispose();
             }
        });
        stage.addActor(mainTable);
    }
    private void load_properties(Label[][] options)
    {
        options[0][0].setStyle(labelActive);
        options[0][1].setStyle(labelInactive);
        options[1][0].setStyle(labelActive);
        options[1][1].setStyle(labelInactive);
    }
    private void load_lableStyles()
    {
        labelActive.font = AssetLoader.default18;
        labelActive.fontColor = Color.WHITE;
        labelInactive.font = AssetLoader.default18;
        labelInactive.fontColor = Color.GRAY;
        systemLabel.font = AssetLoader.default22;
        systemLabel.fontColor = Color.WHITE;
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
        stage.dispose();

    }
}
