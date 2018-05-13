package com.masi4.gameobjects.objectGraphics;

import com.masi4.Directions;

/**
 * Created by OIEFIJM on 13.10.2017.
 *
 * Представляет собой математическую модель графики игрока
 */

public class PlayerGraphics extends CharacterGraphics
{
    private float delta;

    public PlayerGraphics(int width, int height, int worldGravity, int initialJumpSpeed, int horizontalVelocityGain,
                          float maxHorizontalVelocity, Directions turnedSide, boolean isOnGround)
    {
        super(width, height, worldGravity, initialJumpSpeed, horizontalVelocityGain, maxHorizontalVelocity, turnedSide, isOnGround);
    }

    public void update_position(float delta)
    {
        this.delta = delta;
        super.update_position(delta);
    }

    public void controlByJoystick(float knobPercentX, float knobPercentY, boolean allowedToJump)
    {
        if (knobPercentY > 0.4 && allowedToJump)
            jump();

        setVelocityX(getMaxHorizontalVelocity() * knobPercentX);

        if (knobPercentX > 0)
            controlsDirection = Directions.RIGHT;
        else if (knobPercentX < 0)
            controlsDirection = Directions.LEFT;
        else
            controlsDirection = Directions.NONE;
    }

    // Возможно просто заменить единственное место использования на Gdx.graphics.getDelta()
    public float getDelta()
    {
        return delta;
    }

}
