package com.masi4.UI.gameInventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.masi4.gamehelpers.AssetLoader;
import com.masi4.gamehelpers.GamePreferences;

import static com.masi4.GUI.GUI.player;
import static com.masi4.UI.gameInventory.InventoryWindow.INVENTORY_WIDTH;


/**
 * Created by U1wknUzeU6 on 01.05.2018.
 */

public class StatsView extends Table
{
    private float defaultScale = 3f;
    int MAX_HP;
    int HP;
    /*...*/
    ProgressBar healthBar;
    Label.LabelStyle defaultStyle;

    public StatsView()
    {
        LoadStats();
        SetStyle();
        Create();
    }

    private void SetStyle()
    {
        pad(10,30,10,10);

    }

    private void Create()
    {
        defaultStyle = new Label.LabelStyle(AssetLoader.default18, Color.WHITE);
        Label healthLabel = new Label(GamePreferences.loc.format("Inventory_Health"),defaultStyle);

        ProgressBar.ProgressBarStyle healthBarStyle = new ProgressBar.ProgressBarStyle(
                new TextureRegionDrawable(AssetLoader.GameInventory_HealthBarBoundsTextureRegion),
                new TextureRegionDrawable(AssetLoader.GameInventory_HealthBarFillTextureRegion));
        healthBarStyle.knobBefore = new TextureRegionDrawable(AssetLoader.GameInventory_HealthBarFillTextureRegion);
        healthBarStyle.background.setLeftWidth(3);
        healthBarStyle.background.setRightWidth(6);

        healthBarStyle.background.setMinHeight(healthBarStyle.background.getMinHeight() * defaultScale);
        healthBarStyle.knobBefore.setMinHeight(healthBarStyle.knobBefore.getMinHeight() * defaultScale);
        healthBarStyle.knob.setMinHeight(healthBarStyle.knob.getMinHeight() * defaultScale);

        healthBar = new ProgressBar(0,1,0.01f,false,healthBarStyle);
        healthBar.setValue((float)HP/(float)MAX_HP);
        healthBar.setScale(defaultScale);
        healthLabel.setFontScale(defaultScale);


        this.add(healthLabel);
        this.row();
        this.add(healthBar).width(INVENTORY_WIDTH/3);

        this.pack();
    }

    void LoadStats()
    {
        MAX_HP =  player.getMaxHP();
        HP = player.getCurrentHP();
    }

    public void UpdateHealthBar(float delta)
    {
        healthBar.setValue((float)player.getCurrentHP()/(float)player.getMaxHP());
        healthBar.act(delta);
    }
}
