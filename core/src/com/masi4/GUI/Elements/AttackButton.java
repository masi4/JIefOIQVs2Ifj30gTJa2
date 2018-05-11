package com.masi4.GUI.Elements;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.masi4.gamehelpers.AssetLoader;

/**
 * Created by U1wknUzeU6 on 02.04.2018.
 */

public class AttackButton extends ImageButton
{
    Viewport viewport;
    public AttackButton(Viewport viewport)
    {
        super(new TextureRegionDrawable(AssetLoader.attackButton_Up),new TextureRegionDrawable(AssetLoader.attackButton_Down));
        this.getImage().setFillParent(true);
        this.setSize(150,150);
        this.viewport = viewport;
        ResetPosition();

    }

    private void ResetPosition()
    {
        this.setPosition(viewport.getWorldWidth()-270, 80);
    }
}
