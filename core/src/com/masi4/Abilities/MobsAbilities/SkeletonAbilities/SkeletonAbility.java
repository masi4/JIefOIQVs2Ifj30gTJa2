package com.masi4.Abilities.MobsAbilities.SkeletonAbilities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.masi4.gameobjects.objects.Player;
import com.masi4.gameobjects.objects.Skeleton;

public interface SkeletonAbility
{
    void execute(Skeleton skeleton);

    /** Обновляет состояние хитбоксов и прочих элементов, которые создает способность **/
    void update(Skeleton skeleton, float delta);

    Array<Rectangle> getHitboxes();

    Rectangle getBoundingRec();

    /** @return True, если способность кастуется в данный момент, false иначе*/
    boolean isCasting();

    boolean isActing();

    float getCastTime();

    /** @return Время, прошедшее с момента начала произнесения **/
    float getElapsedTime();

    float getCooldown();

    /** @return Время, через которое способность будет готова **/
    float getCurrentCooldown();

    int dealDamage(Player player);
}
