package com.masi4.gameobjectAbilities.PlayerAbilities;

/**
 * Created by OIEFIJM on 25.02.2018.
 */

import com.masi4.gameobjects.Player;

/**            +--------------------+
 *             | Player's abilities |
 * +-----------+--------------------+-----------------+
 * | Default_Sword_Attack - default attack for player |
 * +--------------------------------------------------+
 * |                                                  |
 * |                                                  |
 * |                                                  |
 * |                                                  |
 **/

public abstract class PlayerAbility
{
    public abstract void execute(Player player);
}
