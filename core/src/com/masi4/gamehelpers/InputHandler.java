package com.masi4.gamehelpers;

/**
 * Created by OIEFIJM on 27.10.2017.
 *
 * Этот класс отвечает за обработку входных действий
 */

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Gdx;

import com.masi4.GUI.WalkingControl;
import com.masi4.gameobjects.Player;

public class InputHandler implements InputProcessor
{
    private Player player;
    public WalkingControl controller;
    public InputHandler(WalkingControl controller,Player player)
    {
        this.controller = controller;
        this.player = player;
    }

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
        if (screenX <= Gdx.graphics.getWidth() / 2)
        {
            controller.SetPosition(screenX,screenY);
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
        return false;
    }

    boolean jumpControl = false; // Чтобы нельзя было делать распрыжку
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        if(controller.touchpad.getKnobPercentY() > 0.5 && !jumpControl)
        {
            player.jump();
            jumpControl = true;
        }
        if(controller.touchpad.getKnobPercentY() < 0.5 && jumpControl)
        {
            jumpControl = false;
        }
        player.setVelocityX(300 * controller.touchpad.getKnobPercentX());

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
