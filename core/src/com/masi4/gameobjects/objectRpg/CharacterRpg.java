package com.masi4.gameobjects.objectRpg;

/**
 * Created by OIEFIJM on 22.12.2017.
 *
 * Содержит "РПГ составляющую" персонажа, такую как: характеристики, хитбокс
 */

import com.badlogic.gdx.math.Rectangle;

public class CharacterRpg
{
    protected int maxHP;
    protected int currentHP;

    protected Stats stats;


    public CharacterRpg(Stats stats)
    {
        this.stats = stats;
        applyStats();
        currentHP = maxHP;
    }

    public void applyStats()
    {
        maxHP = Math.round(stats.getStamina() * 1.5f * (1 + stats.getDefence()));
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    // TODO: обработка смерти
    public void TakeDamage(int damage)
    {
        // При 100 защиты урон будет уменьшен в два раза
        currentHP -= Math.round(damage / (1 + 1.5 * Math.log(stats.getDefence() / 100f + 1)));
    }
}
