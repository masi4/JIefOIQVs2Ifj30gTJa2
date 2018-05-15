package com.masi4.UI.gameInventory.model.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.masi4.UI.gameInventory.model.Inventory;
import com.masi4.UI.gameInventory.model.Slot;
import com.masi4.gamehelpers.GamePreferences;

import java.util.ArrayList;

public class InventoryDataManager
{
    private Json json;

    public InventoryDataManager()
    {
        json = new Json();
    }

    public void Save(Array<Slot> slots)
    {
         GamePreferences.InventoryData.putString("data", json.toJson(slots));
         GamePreferences.InventoryData.flush();
    }

    public Array<Slot> Load()
    {
        String raw = GamePreferences.InventoryData.getString("data");

        if(raw == null) return null;

        return json.fromJson(Array.class, raw);
    }

}
