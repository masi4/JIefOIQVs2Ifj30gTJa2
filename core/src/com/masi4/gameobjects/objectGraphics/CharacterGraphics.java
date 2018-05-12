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

    protected boolean inJump;    // находится ли персонаж в прыжке
    protected boolean isTurnedRight; // в какую сторону повернут

    public CharacterGraphics(int width, int height, boolean isTurnedRight)
    {
        position = new Vector2();
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
        this.width = width;
        this.height = height;

        inJump = false;
        this.isTurnedRight = isTurnedRight;
    }

    public CharacterGraphics(int width, int height)
    {
        this(width, height, true);
    }

    public void update_position(float delta)
    {
        velocity.x += acceleration.x * delta;
        velocity.y += acceleration.y * delta;

        position.x += velocity.x * delta;
        position.y += velocity.y * delta;

    }

    public void setCoords(float x, float y)    // Координаты левого нижнего угла
    {
        position = new Vector2(x, y);
    }

    public void setVelocity(float velocityX, float velocityY)
    {
        velocity = new Vector2(velocityX, velocityY);
    }

    public void setVelocityX(float speedX)
    {
        velocity.x = speedX;
    }

    public void setVelocityY(float speedY)
    {
        velocity.y = speedY;
    }

    // акселерация только когда в воздухе
    public void jump()
    {
        if(!inJump)
        {
            acceleration = new Vector2(0, -1560);
            velocity = new Vector2(velocity.x, 640);
            inJump = true;
        }
    }

    public void handleDownCollision(float y)
    {
        position.y = y;
        if (inJump)
        {
            setVelocity(0, 0);
            acceleration = new Vector2(0, 0);
            inJump = false;
        }
    }

    public void handleUpCollision(float y)
    {
        velocity.y = 0f;
        position.y = y - height;
    }

    public void handleLeftCollision(float x)
    {
        velocity.x = 0f;
        position.x = 0;
    }

    public void handleRightCollision(float x)
    {
        velocity.x = 0f;
        position.x = x - width;
    }

    public float getX()
    {
        return position.x;
    }

    public float getY()
    {
        return position.y;
    }

    public float getSpeedX() { return velocity.x; }

    public boolean isInJump() { return inJump; }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public boolean isTurnedRight()
    {
        return isTurnedRight;
    }

    public void turnRight()
    {
        isTurnedRight = true;
    }

    public void turnLeft()
    {
        isTurnedRight = false;
    }
}
