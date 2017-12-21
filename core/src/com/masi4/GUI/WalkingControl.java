package com.masi4.GUI;
/**
 * Created by U1wknUzeU6 on 01.12.2017.
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.masi4.gamehelpers.AssetLoader;
import com.masi4.gamehelpers.GamePreferences;

import static com.masi4.gamehelpers.GameTextureRegions.*;

public class WalkingControl
{
    private Viewport viewport;
    public Stage stage;
    public Touchpad touchpad; // публичные поля = плохо
    public Touchpad.TouchpadStyle touchpadStyle;  // публичные поля = плохо

    private Skin touchpadSkin;
    private Drawable touchFrameAc;
    private Drawable touchCicleAc;
    private Drawable touchFrameInac;
    private Drawable touchCicleInac;
    private int viewportWidth = 960;
    private int viewportHeight = 540;

    public WalkingControl() {
        touchpadSkin = new Skin();
        touchpadSkin.add("frameAc", AssetLoader.controller_FrameActive);
        touchpadSkin.add("circleAc", AssetLoader.controller_CircleActive);
        if(GamePreferences.Options.getInteger("Controller")==0) {
            touchpadSkin.add("frameInac", AssetLoader.controller_FrameInactive0);
            touchpadSkin.add("circleInac", AssetLoader.controller_CircleInactive0);
        }
        else{
            touchpadSkin.add("frameInac", AssetLoader.controller_FrameInactive1);
            touchpadSkin.add("circleInac", AssetLoader.controller_CircleInactive1);
        }
        touchpadStyle = new Touchpad.TouchpadStyle();
        touchFrameAc = touchpadSkin.getDrawable("frameAc");
        touchCicleAc = touchpadSkin.getDrawable("circleAc");
        touchFrameInac = touchpadSkin.getDrawable("frameInac");
        touchCicleInac = touchpadSkin.getDrawable("circleInac");
        MakeInactive();
        touchpad = new Touchpad(10, touchpadStyle);
        ResetPosition();
        viewport = new FitViewport(viewportWidth,viewportHeight);
        stage = new Stage(viewport);
        stage.addActor(touchpad);

    }
    public void MakeActive(){
        touchpadStyle.background = touchFrameAc;
        touchpadStyle.knob = touchCicleAc;
    }
    public void MakeInactive(){
        touchpadStyle.background = touchFrameInac;
        touchpadStyle.knob = touchCicleInac;
    }
    public void SetPosition(int screenX, int screenY){
        screenX = screenX*viewportWidth/Gdx.graphics.getWidth();
        screenY = screenY*viewportHeight/Gdx.graphics.getHeight();

        touchpad.setBounds(screenX-controller_frame_Width/2,viewportHeight-screenY-controller_frame_Height/2,
                controller_frame_Width, controller_frame_Height);

    }
    public void ResetPosition(){
        touchpad.setBounds(0, 0, controller_frame_Width,controller_frame_Height);
    }
    public void Opacity(){

    }
    public void render(float delta){
         stage.act(delta);
         stage.draw();

    }

}
