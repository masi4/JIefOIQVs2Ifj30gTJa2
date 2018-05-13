package com.masi4.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ObjectMap;
import com.masi4.Abilities.AbilityName;
import com.masi4.Abilities.PlayerAbilities.PlayerAbility;
import com.masi4.Directions;
import com.masi4.gameobjects.objectRpg.PlayerRpg;
import com.masi4.gameobjects.objectGraphics.PlayerGraphics;
import com.masi4.gameobjects.objectRpg.Stats;

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
        int maxHorizontalVelocity = Math.round(300 * (1 + stats.getBonusSpeedProcent() * 0.01f));
        // Initial jump speed = 640, Horizontal velocity gain = 600
        graphics = new PlayerGraphics(width, height, worldGravity, 640, 600, maxHorizontalVelocity, Directions.RIGHT, isOnGround);
        rpg = new PlayerRpg(stats);
    }

    public Player(int width, int height, int worldGravity, boolean isOnGround)
    {
        // 1) stamina
        // 2) damage
        // 3) intellect
        // 4) haste
        // 5) defence
        // 6) blocking
        // 7) bonusSpeedProcent
        this(width, height, worldGravity, isOnGround, new Stats(60, 30, 50, 0, 0, 0, 0));
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
            entry.value.update(delta);
        }
    }

    // Из соображений, что в момент времени может кастоваться только одна способность
    /**
     * Возвращает название способности, которая кастуется в данный момент
     * @return Название способности
     */
    public AbilityName getCastingAbility()
    {
        for (ObjectMap.Entry<AbilityName, PlayerAbility> entry : rpg.getAbilities()) {
            if (entry.value.isCasting())
                return entry.key;
        }
        return AbilityName.NULL_ABILITY;
    }

    /**
     * Returns elapsed time of current casting ability
     * @return Elapsed time; 0 if nothing is being casted,
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




}
