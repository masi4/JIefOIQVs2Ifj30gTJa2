package com.masi4.GUI.Elements;
/**
 * Created by U1wknUzeU6 on 01.12.2017.
 */
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.masi4.gamehelpers.resourceHandlers.AssetLoader;

import static com.masi4.gamehelpers.GameTextureRegions.*;

public class WalkingController extends Touchpad
{
    private Viewport viewport;

    public WalkingController(Viewport viewport) {
        super(10, new TouchpadStyle(AssetLoader.controllerSkin.getDrawable("frameAc"),AssetLoader.controllerSkin.getDrawable("circleAc")));
        MakeInactive();
        ResetPosition();
        this.viewport = viewport;

    }

    public void MakeActive()
    {
        getStyle().background = AssetLoader.controllerSkin.getDrawable("frameAc");;
        getStyle().knob = AssetLoader.controllerSkin.getDrawable("circleAc");
    }

    public void MakeInactive()
    {
        getStyle().background = AssetLoader.controllerSkin.getDrawable("frameInac");
        getStyle().knob = AssetLoader.controllerSkin.getDrawable("circleInac");;
    }

    public void SetCenterPosition(float x, float y)
    {
        this.setPosition(x-getWidth()/2,y-getHeight()/2);
    }

    public void ResetPosition()
    {
        setBounds(0, 0, controller_frame_Width,controller_frame_Height);
        this.setSize(300,300);
    }

}
