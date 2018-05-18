package com.masi4.UI.gameInventory.model.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.masi4.UI.gameInventory.model.Slot;
import com.masi4.gamehelpers.AssetLoader;

import static com.masi4.GUI.GUI.player;

/**
 * Created by U1wknUzeU6 on 01.05.2018.
 */

public class healing_potion implements _InventoryItem, _Useable
{
    final int HEALING = 100;
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public TextureRegion GetTexture()
    {
        return AssetLoader.Inventory_GetItemTexture(this);
    }

    @Override
    public Object[] GetStats()
    {
        return new Object[]{
                HEALING
        };
    }

    @Override
    public void Use()
    {
        player.heal(HEALING);
    }


}
