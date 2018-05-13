package com.masi4.Abilities.PlayerAbilities;

/**
 * Created by OIEFIJM on 25.02.2018.
 */

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.masi4.gameobjects.Player;


/**
 *  Default sword attack for player
 */
public class MeleeSwordAttack implements PlayerAbility
{
    public static float castTime = 0.23f;
    private float elapsedTime;
    private boolean isCasting;

    public MeleeSwordAttack()
    {
        elapsedTime = 0;
        isCasting = false;
    }

    @Override
    public void execute(Player player)
    {
        if (!isCasting) {
            isCasting = true;
        }
    }

    // Только для атак вроде ближнего боя сработает приём "обновлять, только если кастуется"
    // Для какого-нибудь фаербола будет спавнится самостоятельный объект, который будет
    // иметь свою отрисовку в рендерере.
    @Override
    public void update(float delta)
    {
        elapsedTime += delta;
        if (elapsedTime > castTime)
        {
            elapsedTime = 0;
            isCasting = false;
        }
    }

    @Override
    public boolean isCasting() {
        return isCasting;
    }

    @Override
    public float getElapsedTime() {
        return elapsedTime;
    }
}
