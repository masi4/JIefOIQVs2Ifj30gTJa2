package com.masi4.gameobjects.objectSound;

import com.masi4.gamehelpers.helpers.Directions;
import com.masi4.gamehelpers.resourceHandlers.AssetLoader;
import com.masi4.gamehelpers.resourceHandlers.SoundManager;
import com.masi4.gameobjects.objects.Player;

public class PlayerSound
{
    /** Time gap between playing footstep sound **/
    private float footstepGap;
    private float footstepElapsed;
    /** To play footstep sound on grounding **/
    private boolean isGrounded;

    public PlayerSound()
    {
        footstepGap = 0.225f;
        footstepElapsed = 0;
        isGrounded = true;
    }

    public void update(Player player, float delta)
    {
        // playing footstep on grounding
        if (player.model.isOnGround()) {
            if (!isGrounded) {
                PlayerSound.footStep();
                isGrounded = true;
            }
        }
        else isGrounded = false;

        // Изменение промежутка между шагами в зависимости от скорости игрока. 0.225f - при скорости 300
        // 0.001 - чтобы не было слишком большого числа и ошибки деления на ноль
        footstepGap = 300f / (Math.abs(player.model.getVelocityX()) > 0.001f ? Math.abs(player.model.getVelocityX()) : 0.001f) * 0.225f;

        if (player.model.isOnGround() && player.model.getControlsDirection() != Directions.NONE) {
            footstepElapsed += delta;
            if (footstepElapsed > footstepGap) {
                PlayerSound.footStep();
                footstepElapsed = 0;
            }
        }
        else
            footstepElapsed = 0;
    }

    public static long footStep()
    {
        return SoundManager.playRandom(AssetLoader.player_footstep_sound, 0.7f);
    }

    // TODO: придумать звук для получения урона
    public static long takeDamage()
    {
        return SoundManager.playRandom(AssetLoader.player_take_damage_sound);
    }
}
