package com.masi4.UI.gameInventory.model.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.masi4.gamehelpers.recourceHandlers.AssetLoader;

/**
 * Created by U1wknUzeU6 on 23.04.2018.
 */

public class Test_Item implements _InventoryItem
{

    //СТАТЫ//

    int HEALTH_POINTS;
    int ARMOR_POINTS;
    //...

    //     //

    @Override
    public TextureRegion GetTexture()
    {
        return AssetLoader.Inventory_GetItemTexture(this); //// TODO: 30.04.2018 че то до меня туго дохолит как сделать это в родительском классе.
    }

}
