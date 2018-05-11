package com.masi4.gameobjects.objectRpg;

/**
 * Created by OIEFIJM on 25.02.2018.
 */

public class Stats
{
    public enum RecourceTypes { MANA, ENERGY };

    private int stamina;
    private int damage;
    private int intellect;

    private int haste;
    private int defence;
    private int blocking;

    public Stats(int stamina, int damage, int intellect, int haste, int defence, int blocking)
    {
        this.damage = damage;
        this.stamina = stamina;
        this.intellect = intellect;
        this.haste = haste;
        this.defence = defence;
        this.blocking = blocking;
    }

    public int getStamina() {
        return stamina;
    }

    public int getDamage() {
        return damage;
    }

    public int getIntellect() {
        return intellect;
    }

    public int getHaste() {
        return haste;
    }

    public int getDefence() {
        return defence;
    }

    public int getBlocking() {
        return blocking;
    }

}