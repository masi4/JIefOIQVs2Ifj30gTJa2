package com.masi4.UI.gameInventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.masi4.UI.gameInventory.model.Inventory;
import com.masi4.UI.gameInventory.model.objects.healing_potion;
import com.masi4.gamehelpers.AssetLoader;
import com.masi4.screens.GameplayScreen;

import static com.masi4.myGame.GameMain.*;

/**
 * Created by U1wknUzeU6 on 23.04.2018.
 */

public class InventoryScreen implements Screen
{
    private Inventory inventory;
    private Stage stage;
    private Table container;
    private InventoryView inventoryView;
    private StatsView statsView;
    private OrthographicCamera camera;
    public static final int STAGE_WIDTH = SCREEN_WIDTH/2;
    public static final int STAGE_HEIGHT = SCREEN_HEIGHT/2;
    private GameplayScreen gameplayScreen;

    public InventoryScreen( GameplayScreen gameplayScreen)
    {
        this.gameplayScreen = gameplayScreen;
        AssetLoader.load_Inventory();
        AssetLoader.load_Fonts();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

    }

    private void CreateTable()
    {
        container=new Table();
        container.setFillParent(true);
        container.left().top();

        inventoryView = new InventoryView();
        for(int i = 0; i< inventory.slots.size; i++) {
            inventoryView.add(new SlotView(inventory.slots.get(i))).width(34).height(34).space(5);
            if((i+1)%6==0)// по 6 айтемов в строке
                inventoryView.row();
        }

        statsView = new StatsView();

        container.add(statsView).top().left().width(140); /////////////////
        container.add(inventoryView).right();
    }

    @Override
    public void show()
    {
        inventory = new Inventory();

        stage = new Stage(new FillViewport(STAGE_WIDTH,STAGE_HEIGHT));

        Gdx.input.setInputProcessor(stage);

        inventory.slots.get(0).Set(new healing_potion(),33);
        inventory.slots.get(1).Set(new healing_potion(),64);
        CreateTable();
        stage.addActor(container);
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor( 0, 0, 0, 1 );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK))
        {
            game.setScreen(gameplayScreen);
        }
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
    public void hide() {

    }

    @Override
    public void dispose()
    {
        stage.dispose();
    }
}
