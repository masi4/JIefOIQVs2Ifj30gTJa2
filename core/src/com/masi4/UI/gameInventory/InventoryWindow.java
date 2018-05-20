package com.masi4.UI.gameInventory;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.masi4.UI.gameInventory.model.Inventory;
import com.masi4.gamehelpers.recourceHandlers.AssetLoader;
import com.masi4.gamehelpers.helpers.BackgroundColor;

import static com.masi4.UI.gameInventory.InventoryMain.InventoryViewport;

public class InventoryWindow extends Window
{
    protected static final float INVENTORY_WIDTH = InventoryViewport.getWorldWidth()-20;
    protected static final float INVENTORY_HEIGHT = InventoryViewport.getWorldHeight()-20;

    private InventoryView inventoryView;
    private StatsView statsView;

    private InventoryCloseButton inventoryCloseButton;

    private Inventory inventory;

    private Table leftContainer;

    private static float bgOpacity = 0.7f; // 0 ... 1

    private static final Window.WindowStyle windownStyle;
    static
    {
        BackgroundColor bc = new BackgroundColor("UI/Inventory/inventoryDefaultSkin.png");
        bc.setColor(0, 0, 0, 255 * bgOpacity);

        windownStyle = new Window.WindowStyle(
                AssetLoader.default18,
                Color.WHITE,
                bc);
    }

    public InventoryWindow(Inventory inventory, InventoryCloseButton inventoryCloseButton)
    {
        super("",windownStyle);
        SetStyle();

        this.inventory = inventory;
        this.inventoryCloseButton = inventoryCloseButton;

        CreateTable();
        this.top();
    }

    private void SetStyle()
    {
        this.pad(10);
        this.setBounds(10, 10, INVENTORY_WIDTH, INVENTORY_HEIGHT );
        this.align(Align.left);
    }

    private void CreateTable()
    {
        leftContainer = new Table();

        inventoryView = new InventoryView(inventory);

        statsView = new StatsView();
        statsView.setWidth(inventoryView.getWidth()-100);

        statsView.left();
        inventoryView.right();

        leftContainer.add(inventoryCloseButton)
                .left()
                .top();
        leftContainer.row();
        leftContainer.add(statsView)
                .left()
                .top();

        leftContainer.align(Align.left);

        this.add(leftContainer).left().top().width(INVENTORY_WIDTH - inventoryView.getWidth());
        this.add(inventoryView).right().space(0, 0,0 ,10 );
        /*
        this.add(inventoryCloseButton);
        this.row();
        this.add(statsView).left().top().width(INVENTORY_WIDTH - inventoryView.getWidth());
        this.add(inventoryView).right().space(0, 0,0 ,10 );
        this.pack();*/

    }

    public InventoryView GetInventoryView() {
        return inventoryView;
    }

    public void UpdateStatsView(float delta)
    {
        statsView.UpdateHealthBar(delta);
    }
}
