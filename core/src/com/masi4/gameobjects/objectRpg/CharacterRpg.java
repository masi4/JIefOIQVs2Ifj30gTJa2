package com.masi4.gameobjects.objectRpg;

/**
 * Created by OIEFIJM on 22.12.2017.
 *
 * Содержит "РПГ составляющую" персонажа, такую как: характеристики, хитбокс
 *
 * TODO: ВАЖНО сделать шаблоном класса (от типа будет зависеть тип абилок в abilities (?))
 */

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectSet;
import com.masi4.Abilities.Effects.Effect;
import com.masi4.gameobjects.Stats;

public class CharacterRpg
{
    protected ObjectSet<Effect> effects;
    protected Stats stats;

    protected int maxHP;
    protected int currentHP;
    protected boolean isDead;

    public CharacterRpg(Stats stats)
    {
        effects = new ObjectSet<Effect>();
        this.stats = stats;
        applyStats();
        currentHP = maxHP;
        isDead = false;
    }

    public void applyStats()
    {
        maxHP = 1 + Math.round(stats.getStamina() * 1.5f * (1 + stats.getDefence() * 0.009f));
    }

    public void addEffect(Effect effect)
    {
        effects.add(effect);
        effect.apply(stats);
    }

    public void updateEffects(float delta)
    {
        for (Effect effect : effects) {
            effect.update(delta);
            if (effect.isFinishied())
                effects.remove(effect);
        }
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

    public int getMaxHP() {
        return maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public Stats getStats() { return stats; }

}
