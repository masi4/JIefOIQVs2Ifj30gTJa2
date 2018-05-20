package com.masi4.GUI.Elements;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.masi4.gamehelpers.recourceHandlers.AssetLoader;

/**
 * Created by U1wknUzeU6 on 09.05.2018.
 */

public class InventoryButton extends ImageButton
{
    Viewport viewport;
    public InventoryButton(Viewport viewport)
    {
        super(new TextureRegionDrawable(AssetLoader.inventoryButton_Up),new TextureRegionDrawable(AssetLoader.inventoryButton_Down));
        this.getImage().setFillParent(true);
        this.setSize(100,100);
        this.viewport = viewport;
        ResetPosition();
    }

    private void ResetPosition()
    {
        this.setPosition(20,viewport.getWorldHeight()-120);
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
