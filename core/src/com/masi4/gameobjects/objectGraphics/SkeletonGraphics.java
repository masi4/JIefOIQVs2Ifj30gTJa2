package com.masi4.gameobjects.objectGraphics;

import com.masi4.Abilities.AbilityName;
import com.masi4.Directions;
import com.masi4.gameobjects.objects.Skeleton;

public class SkeletonGraphics extends CharacterGraphics
{
    // TODO: засунуть в CharacterGraphics
    public float elapsedWalkingTime;

    public SkeletonGraphics(int width, int height, int worldGravity, int initialJumpSpeed,
                            int horizontalVelocityGain, float maxHorizontalVelocity,
                            Directions turnedSide, boolean isOnGround)
    {
        super(width, height, worldGravity, initialJumpSpeed,
                horizontalVelocityGain, maxHorizontalVelocity,
                turnedSide, isOnGround);
        elapsedWalkingTime = 0;
    }

    // TODO: возможно организовать более правильным способом
    public void moveRight(Skeleton skeleton)
    {
        if (controlsDirection == Directions.LEFT)
            setVelocityX(-getVelocityX());
        else if (controlsDirection == Directions.NONE)
            setVelocityX(0);

        setAccelerationX(horizontalVelocityGain);
        controlsDirection = Directions.RIGHT;

        if (skeleton.getCastingAbilityName() != AbilityName.Player_MeleeSwordAttack)
            turnedSide = Directions.RIGHT;
    }

    public void moveLeft(Skeleton skeleton)
    {
        if (controlsDirection == Directions.RIGHT)
            setVelocityX(-getVelocityX());
        else if (controlsDirection == Directions.NONE)
            setVelocityX(0);

        setAccelerationX(-horizontalVelocityGain);
        controlsDirection = Directions.LEFT;
        if (skeleton.getCastingAbilityName() != AbilityName.Player_MeleeSwordAttack)
            turnedSide = Directions.LEFT;
    }
}
