package com.masi4.objectsRpg;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by OIEFIJM on 22.12.2017.
 */

public class PlayerRpg extends CharacterRpg
{
    private final int defaultMaxHP = 230;
    private final int defaultMaxMana = 170;

    public PlayerRpg(Rectangle hitbox, int maxHP, int maxMana)
    {
        super(hitbox, maxHP, maxMana);
    }

    public PlayerRpg(Rectangle hitbox)
    {
        super(hitbox, 230, 170);
    }

    public PlayerRpg(int width, int height, Vector2 position)
    {
        super(new Rectangle(position.x, position.y, width, height), 230, 170);
    }

    public void setHitboxCoords(float x, float y)
    {
        hitbox.setPosition(x, y);
    }

}
