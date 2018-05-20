package com.masi4.UI.gameInventory;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.masi4.gamehelpers.recourceHandlers.AssetLoader;

public class InventoryCloseButton extends ImageButton
{
    final static TextureRegionDrawable imageUp;
    final static TextureRegionDrawable imageDown;
    static
    {
        imageUp = new TextureRegionDrawable(AssetLoader.inventoryCloseButton_Up);
        imageDown = new TextureRegionDrawable(AssetLoader.inventoryCloseButton_Down);
    }

    public InventoryCloseButton()
    {
        super(imageUp, imageDown);
        SetStyle();
    }

    private void SetStyle()
    {
        this.getImage().setFillParent(true);
        this.setSize(95,95);
        this.getImageCell().size(100,100);
        pad(-10, 5, 0,0 );
    }

    private boolean switcher = false;
    public boolean IsJustPressed()
    {
        if(isPressed() && !switcher)
        {
            switcher = true;
            return true;
        }
        if(!isPressed() && switcher)
        {
            switcher = false;
        }
        return false;
    }
}
