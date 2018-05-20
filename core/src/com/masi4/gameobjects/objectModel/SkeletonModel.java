package com.masi4.gameobjects.objectModel;

import com.masi4.Abilities.AbilityName;
import com.masi4.gamehelpers.helpers.Directions;
import com.masi4.gameobjects.objects.Skeleton;

public class SkeletonModel extends CharacterModel
{
    // TODO: засунуть в CharacterModel
    public float elapsedWalkingTime;

    public SkeletonModel(int width, int height, int worldGravity, int initialJumpSpeed,
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
