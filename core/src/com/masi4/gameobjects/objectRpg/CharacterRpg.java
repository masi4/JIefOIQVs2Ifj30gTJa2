package com.masi4.gameobjects.objectRpg;

/**
 * Created by OIEFIJM on 22.12.2017.
 */

import java.util.ArrayList;
import com.badlogic.gdx.math.Rectangle;
import com.masi4.gameobjectAbilities.Ability;

public class CharacterRpg
{
    protected Stats stats;

    protected ArrayList<Ability> abilities;

    protected Rectangle hitbox; // Важно: сначала выполнять дешевые проверки, затем дорогие (intersector)

    public CharacterRpg(Rectangle hitbox, Stats stats)
    {
        this.hitbox = hitbox;
        this.stats = stats;
        this.abilities = new ArrayList<Ability>();
    }

}
