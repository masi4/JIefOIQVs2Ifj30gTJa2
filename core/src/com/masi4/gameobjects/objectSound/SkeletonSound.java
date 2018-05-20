package com.masi4.gameobjects.objectSound;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;
import com.masi4.gamehelpers.recourceHandlers.AssetLoader;
import com.masi4.gamehelpers.recourceHandlers.SoundManager;
import com.masi4.gameobjects.objects.Skeleton;

import static com.masi4.myGame.GameMain.random;

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
