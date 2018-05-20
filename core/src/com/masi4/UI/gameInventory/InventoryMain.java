package com.masi4.UI.gameInventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.masi4.UI.gameInventory.model.Inventory;
import com.masi4.gamehelpers.resourceHandlers.AssetLoader;

public class InventoryMain extends Stage
{
    static final Viewport InventoryViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    static ContextMenu contextMenu;

    private InventoryWindow inventoryWindow;

    private Inventory inventory;

    private InventoryCloseButton inventoryCloseButton;

    public boolean IsDisplayed = false; // Показывается ли в данный момент инвентарь

    private Stage contextMenuStage; // Для контекстного меню

    public InventoryMain()
    {
        AssetLoader.load_Inventory();
        AssetLoader.load_Fonts();

        inventory = new Inventory();

        inventoryCloseButton = new InventoryCloseButton();

        inventoryWindow = new InventoryWindow(inventory, inventoryCloseButton);

        CreateContextMenu();

        this.addActor(inventoryWindow);

        addListener(new ClickListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                if(contextMenu.isTrueClick)
                    contextMenu.setPosition(x,y );
                else {
                    contextMenu.Hide();
                }

                contextMenu.isTrueClick = false;

                return super.touchDown(event, x, y, pointer, button);
            }
        });

        IsDisplayed = true;
    }

    private void CreateContextMenu()
    {
        contextMenu = new ContextMenu(this);
        contextMenu.setPosition(0, 0);
        contextMenu.setZIndex(100);

        contextMenuStage = new Stage();
        contextMenuStage.addActor(contextMenu);
    }

    public void render(float delta)
    {
        contextMenuStage.act(delta);

        this.act(delta);

        this.draw();

        contextMenuStage.draw();

        inventoryWindow.UpdateStatsView(delta);
    }

    public void Hide()
    {
        inventory.Save();
        this.dispose();
        IsDisplayed = false;
    }

    public InventoryCloseButton GetCloseButton()
    {
        return inventoryCloseButton;
    }

    public Array<InputProcessor> GetInputProcessor()
    {
        Array<InputProcessor> array = new Array<InputProcessor>();
        array.addAll(contextMenuStage,this);
        return array;
    }

    public InventoryWindow GetInventoryWindow() {
        return inventoryWindow;
    }

    public Inventory GetInventory() {
        return inventory;
    }
}
