package com.masi4.UI.gameInventory.model;

import com.badlogic.gdx.utils.Array;
import com.masi4.UI.gameInventory.model.objects.Test_Item;

/**
 * Created by U1wknUzeU6 on 23.04.2018.
 */

public class Inventory {

    public Array<Slot> slots;

    public Inventory()
    {
        slots = new Array<Slot>();

        for(int i =0;i<40;i++){
            slots.add(new Slot());
        }
    }

}
