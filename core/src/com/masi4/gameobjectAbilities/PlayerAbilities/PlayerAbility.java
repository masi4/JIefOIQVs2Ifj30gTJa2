package com.masi4.gameobjectAbilities.PlayerAbilities;

import com.masi4.gameobjects.Player;

/**
 * Created by OIEFIJM on 25.02.2018.
 *
 */
public interface PlayerAbility
{
    void execute(Player player);
    boolean isCasting();
}
