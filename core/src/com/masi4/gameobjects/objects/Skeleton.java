package com.masi4.gameobjects.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.masi4.Abilities.AbilityName;
import com.masi4.Abilities.MobsAbilities.SkeletonAbilities.SkeletonAbility;
import com.masi4.Directions;
import com.masi4.gameobjects.Stats;
import com.masi4.gameobjects.objectGraphics.SkeletonGraphics;
import com.masi4.gameobjects.objectRpg.SkeletonRpg;
import static com.masi4.gamehelpers.GameCharactersDefaults.*;

public class Skeleton
{
    public final SkeletonGraphics graphics;
    private SkeletonRpg rpg;

    public Skeleton (int width, int height, int worldGravity, boolean isOnGround,
                     Directions turnedSide, Stats stats)
    {
        int maxHorizontalVelocity = Math.round(SKELETON_DEFAULT_MAX_HORIZONTAL_VELOCITY * (1 + stats.getBonusSpeedProcent() * 0.01f));
        graphics = new SkeletonGraphics(
                width,
                height,
                worldGravity,
                SKELETON_DEFAULT_INITIAL_JUMP_SPEED,
                SKELETON_DEFAULT_HORIZONTAL_VELOCITY_GAIN,
                SKELETON_DEFAULT_MAX_HORIZONTAL_VELOCITY,
                turnedSide,
                isOnGround
        );
        rpg = new SkeletonRpg(graphics, stats);
    }

    public Skeleton (int width, int height, int worldGravity, boolean isOnGround, Directions turnedSide)
    {
        this(width, height, worldGravity, isOnGround, turnedSide, new Stats(
                SKELETON_DEFAULT_STAMINA,
                SKELETON_DEFAULT_DAMAGE,
                SKELETON_DEFAULT_INTELLECT,
                SKELETON_DEFAULT_HASTE,
                SKELETON_DEFAULT_DEFENCE,
                SKELETON_DEFAULT_BLOCKING,
                SKELETON_DEFAULT_BONUS_SPEED_PROCENT
        ));
    }


    public void setCoords(float x, float y)
    {
        graphics.setPosition(x, y);
    }

    public void takeDamage(int damage)
    {
        rpg.takeDamage(damage);
    }

    public void heal(int healing)
    {
        rpg.heal(healing);
    }

    public void executeAbility(AbilityName abilityName)
    {
        if (rpg.getAbilities().containsKey(abilityName))
            rpg.getAbilities().get(abilityName).execute(this);
    }

    public void updateAbilities(float delta)
    {
        for (ObjectMap.Entry<AbilityName, SkeletonAbility> entry : rpg.getAbilities()) {
            entry.value.update(this, delta);
        }
    }

    // Из ----------->соображений (SIC), что в момент времени может кастоваться только одна способность
    /**
     * Возвращает название способности, которая кастуется в данный момент
     * @return Название способности
     */
    public AbilityName getCastingAbilityName()
    {
        for (ObjectMap.Entry<AbilityName, SkeletonAbility> entry : rpg.getAbilities()) {
            if (entry.value.isCasting())
                return entry.key;
        }
        return AbilityName.NONE;
    }

    public AbilityName getActingAbilityName()
    {
        for (ObjectMap.Entry<AbilityName, SkeletonAbility> entry : rpg.getAbilities()) {
            if (entry.value.isActing())
                return entry.key;
        }
        return AbilityName.NONE;
    }

    // TODO: нормальная а хрю тектура
    public int dealDamage(AbilityName abilityName, Player player)
    {
        if (rpg.getAbilities().containsKey(abilityName))
            return rpg.getAbilities().get(abilityName).dealDamage(player);
        else return 0;
    }

    /**
     * Returns elapsed time of current casting ability
     * @return Elapsed time; 0 if nothing is being casted
     */
    public float getCastingElapsedTime()
    {
        for (ObjectMap.Entry<AbilityName, SkeletonAbility> entry : rpg.getAbilities()) {
            if (entry.value.isCasting())
            {
                return entry.value.getElapsedTime();
            }
        }
        return 0;
    }

    public float getAbilityCastTime(AbilityName abilityName)
    {
        if (rpg.getAbilities().containsKey(abilityName))
            return rpg.getAbilities().get(abilityName).getCastTime();
        else return 0;
    }

    public float getAbilityCurrentCooldown(AbilityName abilityName)
    {
        if (rpg.getAbilities().containsKey(abilityName))
            return rpg.getAbilities().get(abilityName).getCurrentCooldown();
        else return 0;
    }

    /** @return Hitboxes of casting ability; null if nothing is being casted **/
    public Array<Rectangle> getActingHitboxes() {
        for (ObjectMap.Entry<AbilityName, SkeletonAbility> entry : rpg.getAbilities()) {
            if (entry.value.isActing())
                return entry.value.getHitboxes();
        }
        return null;
    }

    /** @return Bounding rectangle of hitboxes of casting ability; null if nothing is being casted **/
    public Rectangle getActingBoundingRec() {
        for (ObjectMap.Entry<AbilityName, SkeletonAbility> entry : rpg.getAbilities()) {
            if (entry.value.isActing())
                return entry.value.getBoundingRec();
        }
        return null;
    }

    public int getMaxHP()
    {
        return rpg.getMaxHP();
    }

    public int getCurrentHP()
    {
        return rpg.getCurrentHP();
    }

    public boolean isDead()
    {
        return rpg.isDead();
    }


}
