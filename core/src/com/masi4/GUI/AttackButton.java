package com.masi4.GUI;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.masi4.gamehelpers.AssetLoader;

/**
 * Created by U1wknUzeU6 on 02.04.2018.
 */

public class AttackButton
{
    private Viewport viewport;
    public Stage stage;
    private int viewportWidth = 960;
    private int viewportHeight = 540;

    private ImageButton attackButton;
    private ImageButton.ImageButtonStyle attackButtonStyle;

    private Skin attackButtonSkin;

    public AttackButton()
    {
        attackButtonSkin = new Skin();
        attackButtonSkin.add("buttonUp", AssetLoader.attackButton_Up);
        attackButtonSkin.add("buttonDown", AssetLoader.attackButton_Down);
        attackButtonStyle = new ImageButton.ImageButtonStyle();
        attackButtonStyle.up = attackButtonSkin.getDrawable("buttonUp");
        attackButtonStyle.down = attackButtonSkin.getDrawable("buttonDown");

        attackButton = new ImageButton(attackButtonStyle);
        attackButton.setSize(100,100);
        ResetPosition();

        viewport = new FitViewport(viewportWidth,viewportHeight);
        stage = new Stage(viewport);
        stage.addActor(attackButton);
    }

    public void ResetPosition()
    {
        attackButton.setPosition(viewportWidth-200, 100);
    }

    public void render(float delta){
        stage.act(delta);
        stage.draw();

    }

    public boolean IsPressed()
    {
        return attackButton.isPressed();
    }
}
