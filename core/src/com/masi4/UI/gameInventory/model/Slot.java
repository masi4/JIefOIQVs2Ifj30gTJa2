package com.masi4.UI.gameInventory.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.masi4.UI.gameInventory.model.objects._InventoryItem;
import com.masi4.gamehelpers.AssetLoader;

/**
 * Created by U1wknUzeU6 on 23.04.2018.
 */

public class Slot {
    private int count;
    private _InventoryItem item;

    public Slot()
    {
        item = null;
        count = 0;
    }

    public Slot(_InventoryItem item, int count)
    {
        this.item = item;
        this.count = count;
    }

    public void Set(_InventoryItem item, int count)
    {
        this.item = item;
        this.count = count;
    }

    public void Remove()
    {
        item = null;
        count = 0;
    }

    public void Reduce(int value)
    {
        count -= value;
        if(count <0)
            count = 0;
        if(count == 0)
            item = null;
    }

    public _InventoryItem GetItem()
    {
        return item;
    }

    public int GetCount()
    {
        return count;
    }

    public boolean isEmpty(){
        if(item == null) return true;
        else return false;
    }

}
