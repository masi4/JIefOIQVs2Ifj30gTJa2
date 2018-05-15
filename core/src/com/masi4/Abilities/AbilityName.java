package com.masi4.Abilities;

public enum AbilityName
{
    NONE(0),
    // Player abilities
    Player_MeleeSwordAttack(101),
    Player_RushSwordAttack(102),

    // Skeleton abilities
    Skeleton_MeleeSwordAttack(1001);

    private int abilityCode;

    AbilityName(int abilityCode)
    {
        this.abilityCode = abilityCode;
    }
}
