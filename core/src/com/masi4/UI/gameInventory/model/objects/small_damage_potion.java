package com.masi4.UI.gameInventory.model.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.masi4.gamehelpers.AssetLoader;

public class small_damage_potion implements _InventoryItem, _Useable
{
    final int DAMAGE = 10;
    final float COOLDOWN = 0.01f;

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public TextureRegion GetTexture() {
        return AssetLoader.Inventory_GetItemTexture(this);
    }

    @Override
    public Object[] GetStats() {
        return new Object[]{
                DAMAGE, COOLDOWN
        };
    }

    @Override
    public void Use() {
        //Player.IncDamage(DAMAGE);
    }
}
