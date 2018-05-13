package com.masi4.GUI;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.masi4.UI.UI;
import com.masi4.UI.gameInventory.InventoryMain;
import com.masi4.UI.gameInventory.InventoryScreen;
import com.masi4.UI.gameInventory.model.Inventory;
import com.masi4.gameobjects.Player;
import com.masi4.screens.GameplayScreen;
import static com.masi4.myGame.GameMain.*;

/**
 *  Отвечает за рендер. Проверяет состояние акторов.
 */

public class GUI
{
    private UI ui;
    private StageLayer stageLayer;
    private InventoryMain inventoryMain;

    private Player player;
    private boolean isInventoryOpened;

    /** Determines if the current running platform is Desktop **/
    private static boolean isDesktop;
    static
    {
        if (Gdx.app.getType() != Application.ApplicationType.Desktop) {
            isDesktop = false;
        }
        else
            isDesktop = true;
    }

    public GUI(Player player)
    {
        if (!isDesktop) stageLayer = new StageLayer();
        ui = new UI();
        this.player = player;
        isInventoryOpened = false;
        Gdx.input.setInputProcessor(stageLayer);
        Gdx.input.setCatchBackKey(true);
    }

    public void render(float delta) {
        //***********************
        // Touchscreen controls
        //***********************
        if (!isDesktop) {
            stageLayer.render(delta);

            if (stageLayer.GetWalkingController().isTouched()) {
                if (stageLayer.GetWalkingController().getKnobPercentY() > 0.5) {
                    player.graphics.jump();
                }
                if (!player.graphics.isAttacking()) {
                    player.graphics.setVelocityX(player.graphics.getMaxHorizontalVelocity() * stageLayer.GetWalkingController().getKnobPercentX());
                }
            }
            else
            {
                player.graphics.setVelocityX(0);
            }


            if (stageLayer.GetAttackButton().isPressed())
            {
                // TODO: реализовать атаку через абилити и вообще как надо.
                player.graphics.SetAttack(true);
            }

            if (stageLayer.GetInventoryButton().IsJustPressed())
            {
                ui.ShowHideInventory();
                SwitchInputProcessors();

                //game.setScreen(new InventoryScreen(gameplayScreen));
            }

            if(ui.IsInventoryVisible())
            {
                ui.inventoryMain.render(delta);
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.BACK))
            {
                if(ui.IsInventoryVisible())
                {
                    ui.ShowHideInventory();
                    SwitchInputProcessors();
                }
            }

        }
        //********************
        //  Keyboard controls
        //********************
        else
        {
            // Передвижение
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                if (player.graphics.getVelocityX() < 0)
                    player.graphics.setVelocityX(-player.graphics.getVelocityX());
                player.graphics.setAccelerationX(600);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                if (player.graphics.getVelocityX() > 0)
                    player.graphics.setVelocityX(-player.graphics.getVelocityX());
                player.graphics.setAccelerationX(-600);
            }

            if (!Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.A)) {
                player.graphics.setVelocityX(0);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                player.graphics.jump();
            }

            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                player.graphics.jump();
            }

            // Атака
            if (Gdx.input.isKeyPressed(Input.Keys.J)) {
                player.graphics.SetAttack(true);
            }

            // Инвентарь
            // TODO: сделать, чтобы выход из инвентаря работал
            if (Gdx.input.isKeyJustPressed(Input.Keys.U)) {
                if (!isInventoryOpened) {
                   // game.setScreen(new InventoryScreen(gameplayScreen));
                    isInventoryOpened = true;
                } else {
                   // game.setScreen(gameplayScreen);
                    isInventoryOpened = false;
                }
            }
        }
    }

    private void SwitchInputProcessors()
    {
        if(ui.IsInventoryVisible())
        {
            Gdx.input.setInputProcessor(ui.inventoryMain);
            return;
        }
        // ....
        else {
            stageLayer = new StageLayer();
            Gdx.input.setInputProcessor(stageLayer);
        }
    }

    // Может быть что нибудь здесь сделать (сохраниться (?) или освободить ресурсы)
    public void dispose()
    {

    }
}
