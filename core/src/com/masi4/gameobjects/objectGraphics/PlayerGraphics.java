package com.masi4.gameobjects.objectGraphics;

/**
 * Created by OIEFIJM on 13.10.2017.
 *
 * Представляет собой математическую модель графики игрока
 */

public class PlayerGraphics extends CharacterGraphics
{
    private float delta;
    private boolean isAttacking;

    public PlayerGraphics(int width, int height, int maxHorizontalVelocity)
    {
        super(width, height, maxHorizontalVelocity);
        isAttacking = false;
    }

    public void update_position(float delta)
    {
        this.delta = delta;
        super.update_position(delta);
    }

    // TODO: убрать остановку персонажа при атаке
    // Сохраняет скорость которая была до активации атаки, а применяет ее после того, как атака
    // завершится. Связано с тем, что скорость персонажа зависит от ПЕРЕМЕЩЕНИЯ кноба. Исправить!
    private float bufferVelocity = 0;
    public void SetAttack(boolean isAttacking)
    {
        this.isAttacking = isAttacking;
        if (isAttacking)
        {
            bufferVelocity = this.velocity.x;
            setVelocityX(0);
        }
        else
        {
            setVelocityX(bufferVelocity);
            bufferVelocity = 0;
        }
    }

    public boolean isAttacking()
    {
        return isAttacking;
    }

    public float getDelta()
    {
        return delta;
    }

}
