package com.masi4.gameobjects.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.masi4.Abilities.AbilityName;
import com.masi4.Abilities.PlayerAbilities.PlayerAbility;
import com.masi4.gamehelpers.Directions;
import com.masi4.gameobjects.Stats;
import com.masi4.gameobjects.objectRpg.PlayerRpg;
import com.masi4.gameobjects.objectGraphics.PlayerGraphics;
import com.masi4.gameworld.GameWorld;

import static com.masi4.gamehelpers.GameCharactersDefaults.*;

/**
 * Created by OIEFIJM on 22.12.2017.
 *
 * Класс, представляющий собой игрока
 */

public class Player
{
    public final PlayerGraphics graphics;
    private PlayerRpg rpg;

    // Статы добавлять из расчета талантов etc.
    public Player(int width, int height, int worldGravity, boolean isOnGround, Stats stats)
    {
        int maxHorizontalVelocity = Math.round(PLAYER_DEFAULT_MAX_HORIZONTAL_VELOCITY * (1 + stats.getBonusSpeedProcent() * 0.01f));
        // Initial jump speed = 640, Horizontal velocity gain = 600
        graphics = new PlayerGraphics(width, height, worldGravity, PLAYER_DEFAULT_INITIAL_JUMP_SPEED,
                PLAYER_DEFAULT_HORIZONTAL_VELOCITY_GAIN, maxHorizontalVelocity, Directions.RIGHT, isOnGround);
        rpg = new PlayerRpg(stats);
    }

    public Player(int width, int height, int worldGravity, boolean isOnGround)
    {
        this(width, height, worldGravity, isOnGround, new Stats(
                PLAYER_DEFAULT_STAMINA,
                PLAYER_DEFAULT_DAMAGE,
                PLAYER_DEFAULT_INTELLECT,
                PLAYER_DEFAULT_HASTE,
                PLAYER_DEFAULT_DEFENCE,
                PLAYER_DEFAULT_BLOCKING,
                PLAYER_DEFAULT_BONUSSPEED
        ));
    }

    public void setCoords(float x, float y)
    {
        graphics.setPosition(x, y);
    }

    public void executeAbility(AbilityName abilityName)
    {
        if (rpg.getAbilities().containsKey(abilityName))
            rpg.getAbilities().get(abilityName).execute(this);
    }

    public void updateAbilities(float delta)
    {
        for (ObjectMap.Entry<AbilityName, PlayerAbility> entry : rpg.getAbilities()) {
            entry.value.update(this, delta);
        }
    }

    // Из соображений, что в момент времени может кастоваться только одна способность
    /**
     * Возвращает название способности, которая кастуется в данный момент
     * @return Название способности
     */
    public AbilityName getCastingAbilityName()
    {
        for (ObjectMap.Entry<AbilityName, PlayerAbility> entry : rpg.getAbilities()) {
            if (entry.value.isCasting())
                return entry.key;
        }
        return AbilityName.NONE;
    }

    public int dealDamage(AbilityName abilityName, Skeleton skeleton)
    {
        if (rpg.getAbilities().containsKey(abilityName))
            return rpg.getAbilities().get(abilityName).dealDamage(skeleton);
        else return 0;
    }

    /**
     * Returns elapsed time of current casting ability
     * @return Elapsed time; 0 if nothing is being casted
     */
    public float getCastingElapsedTime()
    {
        for (ObjectMap.Entry<AbilityName, PlayerAbility> entry : rpg.getAbilities()) {
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
        for (ObjectMap.Entry<AbilityName, PlayerAbility> entry : rpg.getAbilities()) {
            if (entry.value.isCasting())
                return entry.value.getHitboxes();
        }
        return null;
    }

    // TODO: заменить на Acting не только название, но и реализацию. А вообще придумать нормальную систему
    /** @return Bounding rectangle of hitboxes of casting ability; null if nothing is being casted **/
    public Rectangle getActingBoundingRec() {
        for (ObjectMap.Entry<AbilityName, PlayerAbility> entry : rpg.getAbilities()) {
            if (entry.value.isCasting())
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

    public void takeDamage(int damage)
    {
        rpg.takeDamage(damage);
    }

    public void heal(int healing)
    {
        rpg.heal(healing);
    }

    public boolean isDead()
    {
        return rpg.isDead();
    }

    // TODO: либо придумать другой способ обработки того, продамажен враг, или нет, либо сделать gameworld реализующим интерфейс создателя событий, либо написать нормальный EventManager
    public void subscribeAbilities(GameWorld gameWorld)
    {
        rpg.subscribeAbilities(gameWorld);
    }

}
