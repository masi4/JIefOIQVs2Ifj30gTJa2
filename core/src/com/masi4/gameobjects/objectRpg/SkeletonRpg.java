package com.masi4.gameobjects.objectRpg;

import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.OrderedMap;
import com.masi4.Abilities.AbilityName;
import com.masi4.Abilities.MobsAbilities.SkeletonAbilities.SkeletonAbility;
import com.masi4.Abilities.MobsAbilities.SkeletonAbilities.Skeleton_MeleeSwordAttack;
import com.masi4.gameobjects.Stats;
import com.masi4.gameobjects.objectGraphics.SkeletonGraphics;

public class SkeletonRpg extends CharacterRpg
{
    private ObjectMap<AbilityName, SkeletonAbility> abilities;

    public SkeletonRpg(SkeletonGraphics graphics, Stats stats)
    {
        super(stats);
        abilities = new OrderedMap<AbilityName, SkeletonAbility>();
        abilities.put(AbilityName.Skeleton_MeleeSwordAttack , new Skeleton_MeleeSwordAttack(graphics, stats));
    }

    public ObjectMap<AbilityName, SkeletonAbility> getAbilities() {
        return abilities;
    }
}
