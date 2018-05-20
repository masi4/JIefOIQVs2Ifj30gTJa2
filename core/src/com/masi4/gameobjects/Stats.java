package com.masi4.gameobjects;

/**
 * Created by OIEFIJM on 25.02.2018.
 */

public class Stats
{
    private int stamina;
    private int damage;
    private int intellect;

    private int haste;
    private int defence;
    private int blocking;

    private int bonusSpeedProcent;

    public Stats(int stamina, int damage, int intellect, int haste, int defence, int blocking, int bonusSpeedProcent)
    {
        this.damage = damage;
        this.stamina = stamina;
        this.intellect = intellect;
        this.haste = haste;
        this.defence = defence;
        this.blocking = blocking;
        this.bonusSpeedProcent = bonusSpeedProcent;
    }

    public void setStamina(int stamina) {
        if (stamina >= 0)
            this.stamina = stamina;
    }

    public void setDamage(int damage) {
        if (damage >= 0)
            this.damage = damage;
    }

    public void setIntellect(int intellect) {
        if (intellect >= 0)
            this.intellect = intellect;
    }

    public void setHaste(int haste) {
            this.haste = haste;
    }

    public void setDefence(int defence) {
            this.defence = defence;
    }

    public void setBlocking(int blocking) {
            this.blocking = blocking;
    }

    public void setBonusSpeedProcent(int bonusSpeedProcent) {
            this.bonusSpeedProcent = bonusSpeedProcent;
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

    public int getBonusSpeedProcent() { return bonusSpeedProcent; }

}
