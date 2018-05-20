package com.masi4.GUI;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.masi4.UI.UI;
import com.masi4.Abilities.AbilityName;
import com.masi4.gameobjects.objects.Player;
import com.masi4.screens.MainMenuScreen;

import static com.masi4.myGame.GameMain.*;

/**
 *  Отвечает за управление
 */

public class GUI
{
    private UI ui;
    private StageLayer stageLayer;
    public static Player player;
    // Чтобы прыгнуть еще раз, нужно сначала опустить пипку джойстика пониже, а потом опять поднять
    private boolean jumpCtrl;
    private boolean jumpCtrl_W;
    private boolean jumpCtrl_SPACE;
    // TODO: разобраться, почему не работает
    // Чтобы атакoвать еще раз, нужно сначала отпустить кнопку атаки
    private boolean meleeSwordAttackCtrl;

    /** Определяет, является ли платформа, на которой запущено приложение Desktop, или нет **/
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
        this.player = player;
        stageLayer = new StageLayer();

        ui = new UI();
        jumpCtrl = true;
        jumpCtrl_W = true;
        jumpCtrl_SPACE = true;
        Gdx.input.setInputProcessor(stageLayer);
        Gdx.input.setCatchBackKey(true);
    }

    public void render(float delta)
    {
        // TODO: сделать событием (наверное)
        if (!player.isDead()) {
            stageLayer.render(delta);

            // region Touchpad controls
            if (!isDesktop) {
                //***********************
                // Touchscreen controls
                //***********************

                if (stageLayer.GetWalkingController().isTouched()) {
                    player.model.controlByJoystick(
                            player,
                            stageLayer.GetWalkingController().getKnobPercentX(),
                            stageLayer.GetWalkingController().getKnobPercentY(),
                            jumpCtrl
                    );
                    jumpCtrl = !(stageLayer.GetWalkingController().getKnobPercentY() > 0.4);
                } else
                    player.model.releaseMovementControls();

                if (stageLayer.GetAttackButton().isPressed()) {
                    player.executeAbility(AbilityName.Player_MeleeSwordAttack);
                }

                if (stageLayer.GetInventoryButton().IsJustPressed()) {
                    ui.ShowHideInventory();
                    SwitchInputProcessors();
                }

                // Кнопка в самом инвенторе
                if (ui.IsInventoryVisible()) {
                    if (ui.inventoryMain.GetCloseButton().IsJustPressed()) {
                        ui.ShowHideInventory();
                        SwitchInputProcessors();
                    }
                }

                if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
                    if (ui.IsInventoryVisible()) {
                        ui.ShowHideInventory();
                        SwitchInputProcessors();
                    } else {
                        game.setScreen(new MainMenuScreen());
                    }
                }

            }
            //endregion

            // region Keyboard controls
            else {
                // Передвижение
                if (Gdx.input.isKeyPressed(Input.Keys.D))
                    player.model.moveRight(player);

                if (Gdx.input.isKeyPressed(Input.Keys.A))
                    player.model.moveLeft(player);

                if (!Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.A))
                    player.model.releaseMovementControls();

                if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                    if (jumpCtrl_W) player.model.jump();
                    jumpCtrl_W = false;
                } else
                    jumpCtrl_W = true;

                if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    if (jumpCtrl_SPACE) player.model.jump();
                    jumpCtrl_SPACE = false;
                } else
                    jumpCtrl_SPACE = true;

                // Атака
                if (Gdx.input.isKeyPressed(Input.Keys.J)) {
                    player.executeAbility(AbilityName.Player_MeleeSwordAttack);
                }

                // Инвентарь
                if (Gdx.input.isKeyJustPressed(Input.Keys.U)) {
                    ui.ShowHideInventory();
                    SwitchInputProcessors();
                }
            }
            // endregion

            // Обработка UI объектов

            if (ui.IsInventoryVisible()) {
                ui.inventoryMain.render(delta);
            }
        }
        else
        {
            player.model.releaseMovementControls();
            player.model.setVelocityX(0);
        }
    }

    private void SwitchInputProcessors()
    {
        if(ui.IsInventoryVisible())
        {
            stageLayer.dispose();
            InputMultiplexer inputMultiplexer = new InputMultiplexer();
            inputMultiplexer.setProcessors(ui.inventoryMain.GetInputProcessor());
            Gdx.input.setInputProcessor(inputMultiplexer);
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
