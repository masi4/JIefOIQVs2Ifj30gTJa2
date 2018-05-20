package com.masi4.Abilities.Effects;

import com.masi4.gameobjects.Stats;

public interface Effect
{
    void apply(Stats stats);
    void update (float delta);
    boolean isFinishied();
}
