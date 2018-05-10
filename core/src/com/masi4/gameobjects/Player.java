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
        graphics = new PlayerGraphics(width, height);
        Rectangle hitbox = new Rectangle(graphics.getX(), graphics.getY(), width, height);
        Progress razvitie = new Progress("Default_Sword_Attack");
        rpg = new PlayerRpg(hitbox, stats, razvitie);
    }

    public Player(int width, int height)
    {
        this(width, height, new Stats(130, 30, 50, 0, 0, 0));
    }

    public void setCoords(float x, float y)
    {
        graphics.setCoords(x, y);
        rpg.setHitboxCoords(x, y);
    }
}
