package com.masi4.gameobjects.objectGraphics;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by OIEFIJM on 22.12.2017.
 */

public class CharacterGraphics
{
    protected Vector2 position;
    protected Vector2 velocity;         // Вектор скорости
    protected Vector2 acceleration;     // Вектор ускорения
    protected int width;
    protected int height;
    protected boolean inJump;    // находится ли персонаж в прыжке

    public CharacterGraphics(int width, int height)
    {
        this.width = width;
        this.height = height;
        inJump = false;
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
    }

    public void update_position(float delta)
    {
        velocity.add(acceleration.cpy().scl(delta));    // TODO: прибавление вручную, без cpy()
        position.add(velocity.cpy().scl(delta));
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

    // TODO: посмотреть, как сделано в птице
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
}
