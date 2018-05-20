package com.masi4.Abilities.Effects;

import com.masi4.gameobjects.Stats;

public class CommonDamageBuff implements Effect
{
    private Stats characterStats;
    private int oldDamage;
    private final float duration = 10f;
    private boolean isExecuted;
    private float elapsedTime;
    private boolean isFinishied;

    @Override
    public void apply(Stats stats)
    {
        characterStats = stats;
        oldDamage = stats.getDamage();
        characterStats.setDamage(Math.round(oldDamage * 1.15f));
        isExecuted = true;
    }

    @Override
    public void update(float delta)
    {
        if (isExecuted)
        {
            elapsedTime += delta;
            if (elapsedTime > duration)
            {
                characterStats.setDamage(oldDamage);
                isExecuted = false;
                elapsedTime = 0;
                isFinishied = true;
            }
        }
    }

    @Override
    public boolean isFinishied() {
        return isFinishied;
    }


}
