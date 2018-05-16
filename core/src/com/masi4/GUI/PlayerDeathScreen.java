package com.masi4.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.masi4.gamehelpers.AssetLoader;
import com.masi4.gamehelpers.BackgroundColor;
import com.masi4.gamehelpers.GamePreferences;
import com.masi4.gameobjects.Level;
import com.masi4.screens.GameplayScreen;

import static com.masi4.myGame.GameMain.game;

public class PlayerDeathScreen extends Stage
{
    BackgroundColor bc;
    private Image background;
    private Animation<BackgroundColor> colorAnimation;
    private Label text;
    private Label button;
    private float elapsedTime = 0;
    boolean switcher = false;

    public PlayerDeathScreen()
    {
        LoadBackground();
        LoadAnimation();
        LoadText();
    }

    private void LoadBackground()
    {
        background = new Image();
        bc = new BackgroundColor("UI/Inventory/inventoryDefaultSkin.png");
        background.setDrawable(bc);
        background.setFillParent(true);

        this.addActor(background);
    }

    private void LoadAnimation()
    {
        Array<BackgroundColor> colors = new Array<BackgroundColor>(150);
        for(int i = 0; i < 355; i++)
        {
            BackgroundColor color = new BackgroundColor("UI/Inventory/inventoryDefaultSkin.png");
            color.setColor(29,29 ,29 ,i );
            colors.add(color);
        }
        colorAnimation = new Animation<BackgroundColor>(0.01f,colors,Animation.PlayMode.NORMAL);
    }

    private void LoadText()
    {
        VerticalGroup group = new VerticalGroup();
        group.setFillParent(true);
        group.center();

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = AssetLoader.default22;
        text = new Label(GamePreferences.loc.format("PlayerDeathScreen_Text"),labelStyle);
        text.setFontScale(10);

        group.addActor(text);

        button = new Label(GamePreferences.loc.format("PlayerDeathScreen_Button"),labelStyle);
        button.setFontScale(4);
        button.setVisible(false);
        button.setName("button");
        button.addListener(new ClickListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                MainAction();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        group.addActor(button);

        this.addActor(group);
    }

    private void MainAction()
    {
        game.setScreen(new GameplayScreen(Level.LevelNames.LEVEL_1));
        dispose();
    }

    public void render(float delta)
    {
        if(!switcher) // ПРОСТО ПРЕКРАСНО...
        {
            Gdx.input.setInputProcessor(this);
            switcher = true;
        }

        elapsedTime+=delta;

        ((Image)this.getActors().get(0))
                .setDrawable(colorAnimation.getKeyFrame(elapsedTime));

        if(colorAnimation.isAnimationFinished(elapsedTime))
            ((VerticalGroup)this.getActors().get(1)).findActor("button").setVisible(true);

        this.act(delta);
        this.draw();

    }
}
