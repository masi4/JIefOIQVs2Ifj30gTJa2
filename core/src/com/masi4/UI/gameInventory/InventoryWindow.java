package com.masi4.UI.gameInventory;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.masi4.UI.gameInventory.model.Inventory;
import com.masi4.gamehelpers.AssetLoader;
import com.masi4.gamehelpers.BackgroundColor;

import static com.masi4.UI.gameInventory.InventoryMain.InventoryViewport;

public class InventoryWindow extends Window
{
    private static final Window.WindowStyle windownStyle;
    private InventoryView inventoryView;
    private StatsView statsView;
    private Inventory inventory;
    static
    {
        BackgroundColor bc = new BackgroundColor("UI/Inventory/inventoryDefaultSkin.png");
        bc.setColor(0, 0, 0, 255);

        windownStyle = new Window.WindowStyle(
                AssetLoader.default18,
                Color.WHITE,
                bc);
    }

    public InventoryWindow(Inventory inventory)
    {
        super("",windownStyle);
        this.setBounds(10, 10, InventoryViewport.getWorldWidth()-20,InventoryViewport.getWorldHeight()-20);
        this.pad(10);
        this.inventory = inventory;
        AssetLoader.load_Inventory();
        AssetLoader.load_Fonts();
        CreateTable();
    }

    private void CreateTable()
    {
        inventoryView = new InventoryView();
        for(int i = 0; i< inventory.slots.size; i++)
        {
            SlotView slotView = new SlotView(inventory.slots.get(i));
            inventoryView.add(slotView)
                    .width(slotView.getWidth())
                    .height(slotView.getHeight())
                    .space(InventoryViewport.getWorldHeight()/50);

            if((i+1)%6==0)// по 6 айтемов в строке
                inventoryView.row();
        }
        inventoryView.right();
        this.add(inventoryView);
    }


}
