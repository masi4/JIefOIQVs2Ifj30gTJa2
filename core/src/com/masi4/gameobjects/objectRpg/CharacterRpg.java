package com.masi4.gameobjects.objectRpg;

/**
 * Created by OIEFIJM on 22.12.2017.
 *
 * Содержит "РПГ составляющую" персонажа, такую как: характеристики, хитбокс
 *
 * TODO: ВАЖНО сделать шаблоном класса (от типа будет зависеть тип абилок в abilities (?))
 */

import com.masi4.gameobjects.Stats;

public class CharacterRpg
{
    boolean isDead;

    protected int maxHP;
    protected int currentHP;
    protected Stats stats;


    public CharacterRpg(Stats stats)
    {
        isDead = false;
        this.stats = stats;
        applyStats();
        currentHP = maxHP;
    }

    public void applyStats()
    {
        maxHP = Math.round(stats.getStamina() * 1.5f * (1 + stats.getDefence() * 0.009f));
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    // TODO: обработка смерти
    public void takeDamage(int damage)
    {
        // При 100 защиты урон будет уменьшен в два раза
        currentHP -= Math.round(damage / (1 + 1.5 * Math.log(stats.getDefence() / 100f + 1)));
        if (currentHP <= 0)
            isDead = true;
    }

    public void heal(int healing)
    {
        currentHP += healing;
        if (currentHP > maxHP)
            currentHP = maxHP;
    }

    public boolean isDead()
    {
        return isDead;
    }

}
