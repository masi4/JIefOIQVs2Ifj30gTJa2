package com.masi4.gameobjects.objectRpg;

import com.badlogic.gdx.math.Rectangle;

import java.util.HashMap;
import com.masi4.gameobjectAbilities.PlayerAbilities.DefaultSwordAttack;
import com.masi4.gameobjectAbilities.PlayerAbilities.PlayerAbility;
import com.masi4.gameobjects.Player;

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
    // TODO: max hp (на основе статов), current hp
    private int maxHP;
    private int currentHP;

    private Progress progress;

    private HashMap<String, PlayerAbility> abilities;

    public PlayerRpg(Rectangle hitbox, Stats stats, Progress progress)
    {
        super(hitbox, stats);
        this.progress = progress;
        abilities = new HashMap<String, PlayerAbility>();
        abilities.put("Default_Sword_Attack", new DefaultSwordAttack());
    }

    public void setHitboxCoords(float x, float y)
    {
        hitbox.setPosition(x, y);
    }

    public void executeDefaultAttack(Player player, String abilityName)
    {
        abilities.get(progress.getCurrentDefaultAbility()).execute(player);
    }

    public int getMaxHP(){ return maxHP; }
    public int getCurrentHP(){ return currentHP; }

}
