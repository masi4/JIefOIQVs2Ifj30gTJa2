package com.masi4.gameobjects.objectRpg;

import com.badlogic.gdx.utils.OrderedMap;
import com.masi4.Abilities.AbilityName;
import com.masi4.Abilities.PlayerAbilities.MeleeSwordAttack;
import com.masi4.Abilities.PlayerAbilities.PlayerAbility;

/**
 * Created by OIEFIJM on 22.12.2017.
 *
 * Содержит РПГ составляющую игрока
 *
 * TODO: Шмотки падают с боссов, с мобцов падает опыт (возможно и шмот, с небольшим шансом)
 * TODO: Применять бонусы от предметов при их надевании (а не только в конструкторе player'a)
 */

public class PlayerRpg extends CharacterRpg
{
    private OrderedMap<AbilityName, PlayerAbility> abilities;

    public PlayerRpg(Stats stats)
    {
        super(stats);
        abilities = new OrderedMap<AbilityName, PlayerAbility>();
        abilities.put(AbilityName.Player_MeleeSwordAttack, new MeleeSwordAttack());
    }

    public OrderedMap<AbilityName, PlayerAbility> getAbilities() {
        return abilities;
    }
}
