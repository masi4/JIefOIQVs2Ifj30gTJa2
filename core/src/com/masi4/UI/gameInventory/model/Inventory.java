package com.masi4.UI.gameInventory.model;

import com.badlogic.gdx.utils.Array;
import com.masi4.UI.gameInventory.model.data.InventoryDataManager;
import com.masi4.UI.gameInventory.model.objects.Test_Item;
import com.masi4.UI.gameInventory.model.objects._InventoryItem;
import com.masi4.UI.gameInventory.model.objects.healing_potion;

/**
 * Created by U1wknUzeU6 on 23.04.2018.
 */

public class Inventory {

    public Array<Slot> slots;

    InventoryDataManager dataManager;

    public Inventory()
    {
        dataManager = new InventoryDataManager();
        Load();
    }

    public Slot GetFirstEmptySlot()
    {
        for(int i = 0; i < slots.size; i++)
        {
            if(slots.get(i).isEmpty())
            {
                return slots.get(i);
            }
        }
        return null;
    }

    /// Здесь написать связь с хранилищем объектов
    public void Save()
    {
        dataManager.Save(slots);
    }

    private void Load()
    {
        slots = dataManager.Load();
        if(isEmpty())
            LoadDeafult();
    }

    private boolean isEmpty()
    {
        if(slots != null)
        {
            boolean state = false;
            for (int i = 0; i < slots.size;i++)
                if(!slots.get(i).isEmpty())
                    state = true;
            if(!state)
                return true;
            else
                return false;
        }
        else
        {
            return true;
        }

    }

    private void LoadDeafult()
    {
        slots.get(0).Set(new healing_potion(), 64);
    }
}
