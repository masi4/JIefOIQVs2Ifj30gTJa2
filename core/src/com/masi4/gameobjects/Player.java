package com.masi4.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.masi4.gameobjects.objectRpg.PlayerRpg;
import com.masi4.gameobjects.objectGraphics.PlayerGraphics;
import com.masi4.gameobjects.objectRpg.Progress;
import com.masi4.gameobjects.objectRpg.Stats;

/**
 * Created by OIEFIJM on 22.12.2017.
 *
 * Класс, представляющий собой игрока
 */

public class Player
{
    public final PlayerGraphics graphics;
    public final PlayerRpg rpg;

    // Статы добавлять из расчета талантов etc.
    public Player(int width, int height, Stats stats)
    {
        int maxHorizontalVelocity = 300 * (1 + stats.getBonusSpeedProcent());
        graphics = new PlayerGraphics(width, height, maxHorizontalVelocity);

        Rectangle hitbox = new Rectangle(graphics.getX(), graphics.getY(), width, height);
        Progress progress = new Progress("Default_Sword_Attack");
        rpg = new PlayerRpg(hitbox, stats, progress);
    }

    public Player(int width, int height)
    {
        // 1) stamina
        // 2) damage
        // 3) intellect
        // 4) haste
        // 5) defence
        // 6) blocking
        // 7) bonusSpeedProcent
        this(width, height, new Stats(130, 30, 50, 0, 0, 0, 0));
    }

    public void setCoords(float x, float y)
    {
        graphics.setCoords(x, y);
        rpg.setHitboxCoords(x, y);
    }

}
