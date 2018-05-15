package com.masi4.gameobjects.objectGraphics;

import com.masi4.Abilities.AbilityName;
import com.masi4.Directions;
import com.masi4.gameobjects.objects.Player;

/**
 * Created by OIEFIJM on 13.10.2017.
 *
 * Представляет собой математическую модель графики игрока
 */

public class PlayerGraphics extends CharacterGraphics
{
    public PlayerGraphics(int width, int height, int worldGravity, int initialJumpSpeed, int horizontalVelocityGain,
                          float maxHorizontalVelocity, Directions turnedSide, boolean isOnGround)
    {
        super(width, height, worldGravity, initialJumpSpeed, horizontalVelocityGain, maxHorizontalVelocity, turnedSide, isOnGround);
    }

    public void update_position(float delta)
    {
        super.update_position(delta);
    }

    public void controlByJoystick(Player player, float knobPercentX, float knobPercentY, boolean allowedToJump)
    {
        if (!player.isDead())
        {
            if (knobPercentY > 0.4 && allowedToJump)
                jump();

            setVelocityX(getMaxHorizontalVelocity() * knobPercentX);

            if (knobPercentX > 0) {
                controlsDirection = Directions.RIGHT;
                if (player.getCastingAbilityName() != AbilityName.Player_MeleeSwordAttack)
                    turnedSide = Directions.RIGHT;
            } else if (knobPercentX < 0) {
                controlsDirection = Directions.LEFT;
                if (player.getCastingAbilityName() != AbilityName.Player_MeleeSwordAttack)
                    turnedSide = Directions.LEFT;
            } else {
                controlsDirection = Directions.NONE;
            }
        }
    }

    // TODO: возможно организовать более правильным способом
    public void moveRight(Player player)
    {
        if (controlsDirection == Directions.LEFT)
            setVelocityX(-getVelocityX());
        else if (controlsDirection == Directions.NONE)
            setVelocityX(0);

        setAccelerationX(horizontalVelocityGain);
        controlsDirection = Directions.RIGHT;

        if (player.getCastingAbilityName() != AbilityName.Player_MeleeSwordAttack)
            turnedSide = Directions.RIGHT;
    }

    public void moveLeft(Player player)
    {
        if (controlsDirection == Directions.RIGHT)
            setVelocityX(-getVelocityX());
        else if (controlsDirection == Directions.NONE)
            setVelocityX(0);

        setAccelerationX(-horizontalVelocityGain);
        controlsDirection = Directions.LEFT;
        if (player.getCastingAbilityName() != AbilityName.Player_MeleeSwordAttack)
            turnedSide = Directions.LEFT;
    }

}
