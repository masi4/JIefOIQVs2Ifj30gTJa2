package com.masi4.gameobjects.objectGraphics;

/**
 * Created by OIEFIJM on 13.10.2017.
 *
 * Представляет собой математическую модель графики игрока
 */



public class PlayerGraphics extends CharacterGraphics
{
    private float delta;


    public PlayerGraphics(int width, int height)
    {
        super(width, height);
    }

    public void update_position(float delta)
    {
        this.delta = delta;
        super.update_position(delta);
    }

    public float getDelta() { return delta; }

}
