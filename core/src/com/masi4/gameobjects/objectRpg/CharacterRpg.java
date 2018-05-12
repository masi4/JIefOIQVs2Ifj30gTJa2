package com.masi4.gameobjects.objectRpg;

/**
 * Created by OIEFIJM on 22.12.2017.
 *
 * Содержит "РПГ составляющую" персонажа, такую как: характеристики, хитбокс
 */

import com.badlogic.gdx.math.Rectangle;

public class CharacterRpg
{
    protected Stats stats;
    protected Rectangle hitbox; // Важно: сначала выполнять дешевые проверки (rec.overlaps), затем дорогие (intersector)

    public CharacterRpg(Rectangle hitbox, Stats stats)
    {
        this.hitbox = hitbox;
        this.stats = stats;
    }

    public void setHitboxCoords(float x, float y)
    {
        hitbox.setPosition(x, y);
    }

    public Rectangle getHitbox()
    {
        return hitbox;
    }

}
