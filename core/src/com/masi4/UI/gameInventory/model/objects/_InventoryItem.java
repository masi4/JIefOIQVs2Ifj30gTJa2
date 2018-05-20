package com.masi4.UI.gameInventory.model.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by U1wknUzeU6 on 23.04.2018.
 */
//TODO: Обрабатывать кулдауны
public interface _InventoryItem
{
    TextureRegion GetTexture();

    Object[] GetStats();
}
