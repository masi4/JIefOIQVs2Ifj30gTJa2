package com.masi4.UI.gameInventory.model.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.masi4.gamehelpers.recourceHandlers.AssetLoader;

import static com.masi4.GUI.GUI.player;

/**
 * Created by U1wknUzeU6 on 01.05.2018.
 */

public class healing_potion implements _InventoryItem, _Useable
{
    @Override
    public TextureRegion GetTexture()
    {
        return AssetLoader.Inventory_GetItemTexture(this);
    }

    @Override
    public void Use()
    {
        player.heal(100);
    }

}
