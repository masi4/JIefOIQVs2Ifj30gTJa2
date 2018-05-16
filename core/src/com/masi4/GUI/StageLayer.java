package com.masi4.GUI;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.masi4.GUI.Elements.HealthBar;
import com.masi4.GUI.Elements.WalkingController;
import com.masi4.GUI.Elements.AttackButton;
import com.masi4.GUI.Elements.InventoryButton;
/**
    Отвечает за события.
 */

public class StageLayer extends Stage
{
    private Viewport viewport;

    private AttackButton attackButton;
    private InventoryButton inventoryButton;
    private WalkingController walkingControl;
    private HealthBar healthBar;
    private int controllerPointer;

    public StageLayer()
    {
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        attackButton = new com.masi4.GUI.Elements.AttackButton(viewport);
        inventoryButton = new com.masi4.GUI.Elements.InventoryButton(viewport);
        walkingControl = new WalkingController(viewport);
        healthBar = new HealthBar(viewport);

        if(Gdx.app.getType() != Application.ApplicationType.Desktop) {
        this.addActor(inventoryButton);
        this.addActor(attackButton);
        this.addActor(walkingControl);
        }
        this.addActor(healthBar);


        LoadEvents();
    }

    private void LoadEvents()
    {
        this.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                if ((x <= Gdx.graphics.getWidth() / 2 ) && !inventoryButton.isPressed())
                {
                    walkingControl.SetCenterPosition(x, y);
                    walkingControl.MakeActive();
                    controllerPointer = pointer;
                    if(!walkingControl.isTouched())
                        walkingControl.fire(event);
                }
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
                if (pointer == controllerPointer)
                {
                    walkingControl.MakeInactive();
                    walkingControl.ResetPosition();
                }
            }
        });
    }

    public AttackButton GetAttackButton(){ return attackButton; }

    public InventoryButton GetInventoryButton(){ return inventoryButton; }

    public WalkingController GetWalkingController() { return walkingControl; }

    public HealthBar getHealthBar() {
        return healthBar;
    }

    public void render(float delta)
    {
        healthBar.Update(delta);
        this.act(delta);
        this.draw();
    }
}
