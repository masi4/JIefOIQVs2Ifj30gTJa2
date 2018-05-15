package com.masi4.gameobjects.objectRpg;

import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.OrderedMap;
import com.masi4.Abilities.AbilityName;
import com.masi4.Abilities.PlayerAbilities.Player_MeleeSwordAttack;
import com.masi4.Abilities.PlayerAbilities.PlayerAbility;
import com.masi4.gameobjects.Stats;
import com.masi4.gameworld.GameWorld;

/**
 * Created by OIEFIJM on 22.12.2017.
 *
 * Содержит РПГ составляющую игрока
 *
 * TODO: Шмотки падают с боссов, с мобцов падает опыт (возможно и шмот, с небольшим шансом)
 * TODO: Применять бонусы от предметов при их надевании (а не только в конструкторе)
 */

public class PlayerRpg extends CharacterRpg
{
    private ObjectMap<AbilityName, PlayerAbility> abilities;

    public PlayerRpg(Stats stats)
    {
        super(stats);
        abilities = new OrderedMap<AbilityName, PlayerAbility>();
        abilities.put(AbilityName.Player_MeleeSwordAttack, new Player_MeleeSwordAttack(stats));
    }

    public ObjectMap<AbilityName, PlayerAbility> getAbilities() {
        return abilities;
    }

    public void subscribeAbilities(GameWorld gameWorld)
    {
        abilities.get(AbilityName.Player_MeleeSwordAttack).subscribeAbility(gameWorld);
    }
}
