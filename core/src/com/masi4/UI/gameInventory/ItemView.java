package com.masi4.UI.gameInventory;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.masi4.UI.gameInventory.model.objects._InventoryItem;

/**
 * Created by U1wknUzeU6 on 30.04.2018.
 */

public class ItemView extends ImageButton
{
    public ItemView(_InventoryItem item)
    {
        super(new TextureRegionDrawable(item.GetTexture()), new TextureRegionDrawable(item.GetTexture()));
        this.getImage().setFillParent(true);
    }
}
