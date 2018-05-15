package com.masi4.GUI.Elements;

import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.masi4.gamehelpers.AssetLoader;
import com.masi4.gamehelpers.BackgroundColor;

public class HealthBar extends ProgressBar
{
    private Viewport viewport;

    private static final ProgressBarStyle healthBarStyle;
    static
    {
        healthBarStyle = new ProgressBarStyle(
                new TextureRegionDrawable(AssetLoader.GUI_HealthBar_BoundsTextureRegion),
                new TextureRegionDrawable(AssetLoader.GUI_HealthBar_KnobTextureRegion)
        );
        healthBarStyle.knobBefore = new TextureRegionDrawable(AssetLoader.GUI_HealthBar_KnobTextureRegion);
        healthBarStyle.knobAfter = new TextureRegionDrawable(AssetLoader.GUI_HealthBar_FillTextureRegion);

        healthBarStyle.background.setMinHeight(healthBarStyle.background.getMinHeight() * 0);
        healthBarStyle.knob.setMinHeight(healthBarStyle.knob.getMinHeight() * 4);
        healthBarStyle.knobBefore.setMinHeight(healthBarStyle.knobBefore.getMinHeight() * 4);
        healthBarStyle.knobAfter.setMinHeight(healthBarStyle.knobAfter.getMinHeight() * 4);

    }

    public HealthBar(Viewport viewport)
    {
        super(0,1,0.01f,false,healthBarStyle);
        this.viewport = viewport;
        this.setValue(0.4f);
        LoadStyle();
    }

    private void LoadStyle()
    {
        setWidth(500);
        this.setPosition(viewport.getWorldWidth()-this.getWidth()-50, viewport.getWorldHeight()-50);
    }
}
