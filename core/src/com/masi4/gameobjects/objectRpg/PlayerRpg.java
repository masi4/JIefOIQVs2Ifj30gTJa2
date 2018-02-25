package com.masi4.gameobjects.objectRpg;

import com.badlogic.gdx.math.Rectangle;
import com.masi4.gameobjectAbilities.player.*;

/**
 * Created by OIEFIJM on 22.12.2017.
 */

public class PlayerRpg extends CharacterRpg
{
    private int HP;

    public PlayerRpg(Rectangle hitbox, Stats stats)
    {
        super(hitbox, stats);
        abilities.add(new DefaultSwordAttack());
    }

    public PlayerRpg(Rectangle hitbox)
    {
        this(hitbox, new Stats(130, 30, 50, 0, 0, 0));
    }

    public void setHitboxCoords(float x, float y)
    {
        hitbox.setPosition(x, y);
    }



}
