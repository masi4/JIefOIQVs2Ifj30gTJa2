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
import com.masi4.gameobjects.objectGraphics.PlayerGraphics;

public class InputHandler implements InputProcessor
{
    private PlayerGraphics playerGraphics;
    public WalkingControl controller;
    private AttackButton attackButton;

    public InputHandler(WalkingControl controller, AttackButton attackButton, PlayerGraphics playerGraphics)
    {
        this.controller = controller;
        this.attackButton = attackButton;
        this.playerGraphics = playerGraphics;
    }

    // TODO: Обработка кнопки назад
    @Override
    public boolean keyDown(int keycode)
    {
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
        if (screenX <= Gdx.graphics.getWidth() / 2)
        {
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
            if (!playerGraphics.isInJump())
                playerGraphics.setVelocityX(0);
        }
        else if(attackButton.IsPressed())
        {
            playerGraphics.SetAttack(true);
        }
        return false;
    }

    boolean jumpControl = false; // Чтобы нельзя было делать распрыжку
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        if(controller.getKnobPercentY() > 0.5 && !jumpControl)
        {
            playerGraphics.jump();
            jumpControl = true;
        }
        if(controller.getKnobPercentY() < 0.5 && jumpControl)
        {
            jumpControl = false;
        }
        playerGraphics.setVelocityX(300 * controller.getKnobPercentX());

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
