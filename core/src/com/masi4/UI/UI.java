package com.masi4.UI;

import com.masi4.UI.gameInventory.InventoryMain;
import com.masi4.gameobjects.objects.Player;

public class UI
{

    public InventoryMain inventoryMain;

    public UI()
    {
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
        if (inventoryMain == null || !inventoryMain.IsDisplayed)
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
