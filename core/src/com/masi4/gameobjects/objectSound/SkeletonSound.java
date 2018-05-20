package com.masi4.gameobjects.objectSound;

import com.masi4.gamehelpers.resourceHandlers.AssetLoader;
import com.masi4.gamehelpers.resourceHandlers.SoundManager;

// TODO: сделатль не static
public class SkeletonSound
{
    public static long footStep()
    {
        return SoundManager.playRandom(AssetLoader.skeleton_footstep_sounds);
    }

    public static long takeDamage()
    {
        return SoundManager.playRandom(AssetLoader.skeleton_take_damage_sounds);
    }

    public static long dead()
    {
        return SoundManager.playRandom(AssetLoader.skeleton_dead_sounds);
    }

}
