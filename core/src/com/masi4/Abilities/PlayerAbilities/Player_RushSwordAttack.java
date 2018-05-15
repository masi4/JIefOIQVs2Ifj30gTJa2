package com.masi4.Abilities.PlayerAbilities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.masi4.Abilities.AbilityInfo;
import com.masi4.gameobjects.objects.Player;
import com.masi4.gameobjects.objects.Skeleton;
import com.masi4.gameworld.GameWorld;

public class Player_RushSwordAttack implements PlayerAbility
{
    @Override
    public void execute(Player player) {

    }

    @Override
    public void update(Player player, float delta) {

    }

    @Override
    public Array<Rectangle> getHitboxes() {
        return null;
    }

    @Override
    public Rectangle getBoundingRec() {
        return null;
    }

    @Override
    public boolean isCasting() {
        return false;
    }

    @Override
    public boolean isActing() { return false; }

    @Override
    public float getCastTime() {
        return 0;
    }

    @Override
    public float getElapsedTime() {
        return 0;
    }

    @Override
    public float getCooldown() {
        return 0;
    }

    @Override
    public float getCurrentCooldown() {
        return 0;
    }

    @Override
    public int dealDamage(Skeleton skeleton) {
        return 0;
    }

    @Override
    public void subscribeAbility(GameWorld gameWorld) {

    }

}
