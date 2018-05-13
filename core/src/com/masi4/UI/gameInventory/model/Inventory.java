package com.masi4.UI.gameInventory.model;

import com.badlogic.gdx.utils.Array;
import com.masi4.UI.gameInventory.model.objects.Test_Item;
import com.masi4.UI.gameInventory.model.objects._InventoryItem;
import com.masi4.UI.gameInventory.model.objects.healing_potion;

/**
 * Created by U1wknUzeU6 on 23.04.2018.
 */

public class Inventory {

    public Array<Slot> slots;

    public Inventory()
    {
        slots = new Array<Slot>();

        for(int i =0;i<64;i++){
            slots.add(new Slot());
        }

        slots.get(0).Set(new healing_potion(), 33);
        slots.get(1).Set(new healing_potion(), 64);
    }
    /// Здесь написать связь с хранилищем объектов

}
