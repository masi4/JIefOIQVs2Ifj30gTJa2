package com.masi4.UI.gameInventory.model.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.masi4.Abilities.Effects.CommonDamageBuff;
import com.masi4.gamehelpers.resourceHandlers.AssetLoader;
import static com.masi4.GUI.GUI.player;

public class small_damage_potion implements _InventoryItem, _Useable
{
    final int DAMAGE_PROCEnT = 15;
    final float COOLDOWN = 0.01f;

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public TextureRegion GetTexture() {
        return AssetLoader.Inventory_GetItemTexture(this);
    }

    @Override
    public Object[] GetStats() {
        return new Object[]{
                DAMAGE_PROCEnT, COOLDOWN
        };
    }

    @Override
    public void Use() {
        player.addEffect(new CommonDamageBuff());
    }
}
