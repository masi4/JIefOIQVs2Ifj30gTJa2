package com.masi4.UI.gameInventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.masi4.UI.gameInventory.model.Inventory;

public class InventoryMain extends Stage
{
    protected static final Viewport InventoryViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    private InventoryWindow inventoryWindow;
    private Inventory inventory;
    public boolean IsDisplayed = false;

    public InventoryMain()
    {
        inventory = new Inventory();
        inventoryWindow = new InventoryWindow(inventory);
        this.addActor(inventoryWindow);
        IsDisplayed = true;
    }

    public void render(float delta)
    {
        this.act(delta);
        this.draw();
    }

    public void Hide()
    {
        this.dispose();
        IsDisplayed = false;
    }

}
