package com.masi4.gamehelpers;

/**
 * Created by OIEFIJM on 27.10.2017.
 *
 * Этот класс отвечает за обработку входных действий
 */

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.masi4.GUI.WalkingControl;
import com.masi4.gameobjects.Player;
import com.masi4.screens.GameplayScreen;

public class InputHandler implements InputProcessor{

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
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (screenX >= Gdx.graphics.getWidth() / 2)
        {

        }
        else
        {

            controller.SetPosition(screenX,screenY);
            controller.MakeActive();
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        controller.MakeInactive();
        controller.ResetPosition();
        player.Stop();
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        player.Move(300*controller.touchpad.getKnobPercentX(), 0);
        //if(controller.touchpad.getKnobPercentY()>0.5) {player.Jump()}   // TODO: наприсать в классе player метод Jump
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
