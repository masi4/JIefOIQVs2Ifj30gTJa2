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


/**
 * Created by U1wknUzeU6 on 01.05.2018.
 */

public class StatsView extends Table
{
    int MAX_HP = 200;
    int HP = 120;
    /*...*/

    Label.LabelStyle defaultStyle;
    public StatsView()//// TODO: 01.05.2018 Передавать плеера через параметры и узнавать его статы. Не могу сделать это сейчас, потому что не знаю, что ты изменил в своей ветке.
    {                 //// Так как инвентарь вызывается из GUI с этим не должно возникнуть проблем
        pad(10,30,10,10);

        defaultStyle = new Label.LabelStyle(AssetLoader.default18, Color.WHITE);
        Label healthLabel = new Label(GamePreferences.loc.format("Inventory_Health"),defaultStyle);

        ProgressBar.ProgressBarStyle healthBarStyle = new ProgressBar.ProgressBarStyle(new TextureRegionDrawable(AssetLoader.GameInventory_HealthBarBoundsTextureRegion),new TextureRegionDrawable(AssetLoader.GameInventory_HealthBarFillTextureRegion));
        healthBarStyle.knobBefore = new TextureRegionDrawable(AssetLoader.GameInventory_HealthBarFillTextureRegion);
        healthBarStyle.background.setLeftWidth(1);
        ProgressBar healthBar = new ProgressBar(0,1,0.01f,false,healthBarStyle);
        healthBar.setValue((float)HP/(float)MAX_HP);
        healthBar.setScale(0.5f);

        this.add(healthLabel);
        this.row();
        this.add(healthBar).width(100);
    }
}
