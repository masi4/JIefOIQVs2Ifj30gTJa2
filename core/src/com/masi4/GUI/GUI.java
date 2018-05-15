package com.masi4.GUI;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.masi4.UI.gameInventory.InventoryScreen;
import com.masi4.Abilities.AbilityName;
import com.masi4.gameobjects.objects.Player;
import com.masi4.screens.GameplayScreen;
import static com.masi4.myGame.GameMain.*;

/**
 *  Отвечает за управление
 */

public class GUI
{
    private StageLayer stageLayer;
    private Player player;
    private GameplayScreen gameplayScreen;
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

    public GUI(Player player, GameplayScreen gameplayScreen)
    {
        if (!isDesktop) stageLayer = new StageLayer();
        this.player = player;
        this.gameplayScreen = gameplayScreen;
        jumpCtrl = true;
        jumpCtrl_W = true;
        jumpCtrl_SPACE = true;

        Gdx.input.setInputProcessor(stageLayer);
        Gdx.input.setCatchBackKey(true);
    }

    public void render(float delta)
    {
        if (!isDesktop) {
            //***********************
            // Touchscreen controls
            //***********************
            stageLayer.render(delta);

            if (stageLayer.GetWalkingController().isTouched())
            {
                player.graphics.controlByJoystick(
                        player,
                        stageLayer.GetWalkingController().getKnobPercentX(),
                        stageLayer.GetWalkingController().getKnobPercentY(),
                        jumpCtrl
                );
                jumpCtrl = !(stageLayer.GetWalkingController().getKnobPercentY() > 0.4);
            }
            else
                player.graphics.releaseMovementControls();

            if (stageLayer.GetAttackButton().isPressed()) {
                    player.executeAbility(AbilityName.Player_MeleeSwordAttack);
            }

            if (stageLayer.GetInventoryButton().isPressed())
                game.setScreen(new InventoryScreen(gameplayScreen));
        }
        else {
            //********************
            //  Keyboard controls
            //********************

            // Передвижение
           if (Gdx.input.isKeyPressed(Input.Keys.D))
                player.graphics.moveRight(player);

            if (Gdx.input.isKeyPressed(Input.Keys.A))
                player.graphics.moveLeft(player);

            if (!Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.A))
                player.graphics.releaseMovementControls();

            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                if (jumpCtrl_W) player.graphics.jump();
                    jumpCtrl_W = false;
            }
            else
               jumpCtrl_W = true;

            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                if (jumpCtrl_SPACE) player.graphics.jump();
                    jumpCtrl_SPACE = false;
            }
            else
               jumpCtrl_SPACE = true;

            // Атака
            if (Gdx.input.isKeyPressed(Input.Keys.J)) {
                    player.executeAbility(AbilityName.Player_MeleeSwordAttack);
            }
        }
    }

    // Может быть что нибудь здесь сделать (сохраниться (?) или освободить ресурсы)
    public void dispose()
    {

    }
}
