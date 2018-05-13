package com.masi4.Abilities;

public enum AbilityName
{
    NULL_ABILITY(0),
    Player_MeleeSwordAttack(101);

    private int abilityCode;

    AbilityName(int abilityCode)
    {
        this.abilityCode = abilityCode;
    }
}
