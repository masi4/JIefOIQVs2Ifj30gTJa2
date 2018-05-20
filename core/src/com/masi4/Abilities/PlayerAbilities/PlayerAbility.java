package com.masi4.Abilities.PlayerAbilities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.masi4.gameobjects.SkeletonListener;
import com.masi4.gameobjects.objects.Player;
import com.masi4.gameobjects.objects.Skeleton;
import com.masi4.gameworld.GameWorld;

/**
 * Created by OIEFIJM on 25.02.2018.
 * TODO: заменить на шаблон интерфейса (?)
 */
public interface PlayerAbility
{
    // TODO: не нужен здесь именно Player. Достаточно CharacterRpg, так что переделать в один интерфейс.
    void execute(Player player);

    /** Обновляет состояние хитбоксов и прочих элементов, которые создает способность **/
    void update(Player player, float delta);

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

    int dealDamage(Skeleton skeleton);

    void subscribeAbility(GameWorld gameWorld);
}
