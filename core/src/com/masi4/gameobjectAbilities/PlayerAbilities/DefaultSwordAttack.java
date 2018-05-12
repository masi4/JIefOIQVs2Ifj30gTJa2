package com.masi4.gameobjectAbilities.PlayerAbilities;

/**
 * Created by OIEFIJM on 25.02.2018.
 */

import com.masi4.gameobjects.Player;


/**
 *  Default sword attack for player
 */
public class DefaultSwordAttack implements PlayerAbility
{
    public static float castTime = 0.25f;
    private boolean isCasting;

    public DefaultSwordAttack()
    {
        isCasting = false;
    }

    public void execute(Player player)
    {
        isCasting = true;

        isCasting = false;
    }

    public boolean isCasting()
    {

        return isCasting;
    }
}
