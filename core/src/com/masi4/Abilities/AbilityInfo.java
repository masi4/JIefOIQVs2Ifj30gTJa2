package com.masi4.Abilities;

public interface AbilityInfo
{
    /** @return True, если способность кастуется в данный момент, false иначе*/
    boolean isCasting();

    boolean isActing();

    float getCastTime();

    /** @return Время, прошедшее с момента начала произнесения **/
    float getElapsedTime();

    float getCooldown();

    /** @return Время, через которое способность будет готова **/
    float getCurrentCooldown();

    float getDamage();
}
