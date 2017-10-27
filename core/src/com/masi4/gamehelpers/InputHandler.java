package com.masi4.gamehelpers;

/**
 * Created by OIEFIJM on 27.10.2017.
 *
 * Этот класс отвечает за обработку входных действий
 */

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Gdx;

import com.masi4.gameobjects.Player;

public class InputHandler implements InputProcessor{

    private Player player;

    public InputHandler(Player player)
    {
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
            player.Move(200, 0);
        }
        else
        {
            player.Move(-200, 0);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        player.Stop();
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
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
