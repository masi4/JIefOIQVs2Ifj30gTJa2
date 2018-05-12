package com.masi4.gameobjects.objectGraphics;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by OIEFIJM on 22.12.2017.
 *
 * Представляет собой математическую модель графики персонажа - координаты, скорость
 */

public class CharacterGraphics
{
    protected Vector2 position;
    protected Vector2 velocity;         // Вектор скорости
    protected Vector2 acceleration;     // Вектор ускорения
    protected int width;
    protected int height;

    protected float maxHorizontalVelocity;
    protected boolean inJump;    // находится ли персонаж в прыжке
    protected boolean isTurnedRight; // в какую сторону повернут

    public CharacterGraphics(int width, int height, float maxHorizontalVelocity, boolean isTurnedRight)
    {
        position = new Vector2();
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
        this.width = width;
        this.height = height;

        this.maxHorizontalVelocity = maxHorizontalVelocity;
        inJump = false;
        this.isTurnedRight = isTurnedRight;
    }

    public CharacterGraphics(int width, int height, int maxHorizontalVelocity)
    {
        // isTurnedRight = true
        this(width, height, maxHorizontalVelocity, true);
    }

    public void update_position(float delta)
    {
        velocity.y += acceleration.y * delta;
        velocity.x += acceleration.x * delta;
        if (Math.abs(velocity.x) > Math.abs(maxHorizontalVelocity))
            if (velocity.x > 0) velocity.x = maxHorizontalVelocity;
            else velocity.x = -maxHorizontalVelocity;

        position.y += velocity.y * delta;
        position.x += velocity.x * delta;
    }

    public void setCoords(float x, float y)    // Координаты левого нижнего угла
    {
        position = new Vector2(x, y);
    }

    public void setAcceleration (float accelX, float accelY)
    {
        acceleration.x = accelX;
        acceleration.y = accelY;
    }

    public void setAccelerationX (float accelX)
    {
        acceleration.x = accelX;
    }

    public void setAccelerationY (float accelY)
    {
        acceleration.y = accelY;
    }

    public void setVelocity(float velocityX, float velocityY)
    {
        velocity.y = velocityY;

        if (Math.abs(velocityX) > Math.abs(maxHorizontalVelocity))
            if (velocityX > 0) velocity.x = maxHorizontalVelocity;
            else velocity.x = -maxHorizontalVelocity;
        else
            velocity.x = velocityX;
    }

    public void setVelocityX(float velocityX)
    {
        if (Math.abs(velocityX) > Math.abs(maxHorizontalVelocity))
            if (velocityX > 0) velocity.x = maxHorizontalVelocity;
            else velocity.x = -maxHorizontalVelocity;
        else
            velocity.x = velocityX;
    }

    public void setVelocityY(float velocityY)
    {
        velocity.y = velocityY;
    }

    public void jump()
    {
        if(!inJump)
        {
            setAcceleration(0, -1560);
            setVelocityY(640);
            inJump = true;
        }
    }

    public void turnRight()
    {
        isTurnedRight = true;
    }

    public void turnLeft()
    {
        isTurnedRight = false;
    }

    public void handleDownCollision(float y)
    {
        if (acceleration.y < 0) setAccelerationY(0);
        if (velocity.y < 0) setVelocityY(0);
        position.y = y;
        if (inJump) inJump = false;
    }

    public void handleUpCollision(float y)
    {
        if (acceleration.y > 0) setAccelerationY(0);
        if (velocity.y > 0) setVelocityY(0);
        position.y = y - height;
    }

    public void handleLeftCollision(float x)
    {
        if (acceleration.x < 0) setAccelerationX(0);
        if (velocity.x < 0) setVelocityX(0);
        position.x = x;
    }

    public void handleRightCollision(float x)
    {
        if (acceleration.x > 0) setAccelerationX(0);
        if (velocity.x > 0) setVelocityX(0);
        position.x = x - width;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public float getX()
    {
        return position.x;
    }

    public float getY()
    {
        return position.y;
    }

    public float getVelocityX() { return velocity.x; }

    public float getVelocityY() { return velocity.y; }

    public float getMaxHorizontalVelocity() { return maxHorizontalVelocity; }

    public boolean isInJump() { return inJump; }

    public boolean isTurnedRight()
    {
        return isTurnedRight;
    }


}
