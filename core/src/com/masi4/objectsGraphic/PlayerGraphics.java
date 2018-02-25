package com.masi4.objectsGraphic;

/**
 * Created by OIEFIJM on 13.10.2017.
 *
 * Этот класс описывает игрового персонажа
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
