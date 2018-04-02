package com.masi4.gamehelpers;

/**
 * Created by OIEFIJM on 27.10.2017.
 *
 * Этот класс отвечает за обработку входных действий
 */

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Gdx;

import com.masi4.GUI.AttackButton;
import com.masi4.GUI.WalkingControl;
import com.masi4.gameobjects.Player;

import java.awt.event.KeyEvent;

public class InputHandler implements InputProcessor
{
    private Player player;
    private WalkingControl controller;
    private AttackButton attackButton;
    public InputHandler(WalkingControl controller,AttackButton attackButton,Player player)
    {
        this.controller = controller;
        this.attackButton = attackButton;
        this.player = player;
    }

    // TODO: Обработка кнопки назад
    @Override
    public boolean keyDown(int keycode) {

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        if (screenX <= Gdx.graphics.getWidth() / 2) {
            controller.SetPosition(screenX, screenY);
            controller.MakeActive();
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        if (screenX <= Gdx.graphics.getWidth() / 2)
        {
            controller.MakeInactive();
            controller.ResetPosition();
            if (!player.isInJump())
                player.setVelocityX(0);
        }
        else
        if(attackButton.IsPressed())
        {
            player.SetAttack(true);
        }
        return false;
    }

    boolean jumpControl = false; // Чтобы нельзя было делать распрыжку
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        if(controller.getKnobPercentY() > 0.5 && !jumpControl)
        {
            player.jump();
            jumpControl = true;
        }
        if(controller.getKnobPercentY() < 0.5 && jumpControl)
        {
            jumpControl = false;
        }
        if(!player.IsAttack())
        {
            player.setVelocityX(300 * controller.getKnobPercentX());
        }


        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
