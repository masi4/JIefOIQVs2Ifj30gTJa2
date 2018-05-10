package com.masi4.UI.gameInventory.model.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.masi4.gamehelpers.AssetLoader;

/**
 * Created by U1wknUzeU6 on 01.05.2018.
 */

public class healing_potion implements _InventoryItem
{
    public void Use()
    {

    }

    @Override
    public boolean isUseable() {
        return true;
    }

    @Override
    public TextureRegion GetTexture() {
        return AssetLoader.Inventory_GetItemTexture(this);
    }
}
