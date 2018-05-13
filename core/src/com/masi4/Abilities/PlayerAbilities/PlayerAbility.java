package com.masi4.Abilities.PlayerAbilities;

import com.masi4.gameobjects.Player;

/**
 * Created by OIEFIJM on 25.02.2018.
 *
 */
public interface PlayerAbility
{
    void execute(Player player);
    void update(float delta);

    /** @return true, если способность кастуется в данный момент, false иначе*/
    boolean isCasting();
    float getElapsedTime();
}
