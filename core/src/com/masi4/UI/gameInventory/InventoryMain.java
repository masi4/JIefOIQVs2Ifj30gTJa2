package com.masi4.UI.gameInventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.masi4.UI.gameInventory.model.Inventory;
import com.masi4.gamehelpers.AssetLoader;
import com.masi4.gameobjects.Player;

public class InventoryMain extends Stage
{
    static final Viewport InventoryViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    static ContextMenu contextMenu;
    private InventoryWindow inventoryWindow;
    private Inventory inventory;
    private InventoryCloseButton inventoryCloseButton;
    public boolean IsDisplayed = false; // Показывается ли в данный момент инвентарь
    private Stage layout; // Для контекстного меню

    public InventoryMain()
    {
        AssetLoader.load_Inventory();
        AssetLoader.load_Fonts();
        CreateContextMenu();

        inventory = new Inventory();

        inventoryCloseButton = new InventoryCloseButton();

        inventoryWindow = new InventoryWindow(inventory, inventoryCloseButton);

        this.addActor(inventoryWindow);

        addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                if(contextMenu.isTrueClick)
                    contextMenu.setPosition(x,y );
                else
                    contextMenu.Hide();

                contextMenu.isTrueClick = false;

                return super.touchDown(event, x, y, pointer, button);
            }});

        IsDisplayed = true;
    }

    private void CreateContextMenu()
    {
        contextMenu = new ContextMenu();
        contextMenu.setPosition(0, 0);
        contextMenu.setZIndex(100);

        layout = new Stage();
        layout.addActor(contextMenu);
    }

    public void render(float delta)
    {
        this.act(delta);
        this.draw();
        layout.act(delta);
        layout.draw();
    }

    public void Hide()
    {
        this.dispose();
        IsDisplayed = false;
    }

    public InventoryCloseButton GetCloseButton()
    {
        return inventoryCloseButton;
    }
}
