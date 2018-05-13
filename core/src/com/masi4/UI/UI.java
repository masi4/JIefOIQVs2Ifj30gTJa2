package com.masi4.UI;

import com.badlogic.gdx.Gdx;
import com.masi4.UI.gameInventory.InventoryMain;
import com.masi4.gameobjects.Player;

public class UI
{
    public static Player player;

    public InventoryMain inventoryMain;

    public UI(Player player)
    {
        this.player = player;
    }

    private void ShowInventory()
    {
        inventoryMain = new InventoryMain();
    }

    private void HideInventory()
    {
        //inventoryMain.dispose();
        inventoryMain.Hide();
        inventoryMain = null;
    }

    public void ShowHideInventory()
    {
        if(inventoryMain == null || !inventoryMain.IsDisplayed)
            ShowInventory();
        else
            HideInventory();
    }

    public boolean IsInventoryVisible()
    {
        if(inventoryMain == null || !inventoryMain.IsDisplayed)
        {
            return false;
        }
        else
        {
            if(inventoryMain.IsDisplayed)// Инвентарь прогружен
                return true;
            else
                return false;
        }
    }

}
