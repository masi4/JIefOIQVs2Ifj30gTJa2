package com.masi4.objectsRpg;

/**
 * Created by OIEFIJM on 22.12.2017.
 */

import com.badlogic.gdx.math.Rectangle;

public class CharacterRpg
{
    protected int maxHP;
    protected int hp;
    protected int maxMana;
    protected int mana;
    protected Rectangle hitbox; // Важно: сначала выполнять дешевые проверки, затем дорогие (intersector)

    public CharacterRpg(Rectangle hitbox, int maxHP, int maxMana)
    {
        this.hitbox = hitbox;
        this.maxHP = maxHP;
        this.maxMana = maxMana;
    }

}
