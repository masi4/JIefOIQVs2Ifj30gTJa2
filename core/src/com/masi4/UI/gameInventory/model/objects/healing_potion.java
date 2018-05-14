package com.masi4.UI.gameInventory.model.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.masi4.gamehelpers.AssetLoader;

import static com.masi4.UI.UI.player;

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
        Gdx.app.log("healing_potion", "Use()");
    }

    @Override
    public void Split()
    {
        Gdx.app.log("healing_potion", "Split()");
    }

    @Override
    public void Throw()
    {

        Gdx.app.log("healing_potion", "Throw()");
    }
}
