package com.masi4.gameobjects;

/**
 * Created by OIEFIJM on 13.10.2017.
 *
 * Этот класс описывает игрового персонажа
 */

import com.badlogic.gdx.math.Vector2;

public class Player
{
    private Vector2 position;
    private Vector2 velocity;         // Вектор скорости
    private Vector2 acceleration;     // Вектор ускорения
    private int width;
    private int height;
    private boolean isInJump;    // находится ли персонаж в прыжке

    // Публичные члены

    public Player(int width, int height)
    {
        this.width = width;
        this.height = height;
        isInJump = false;
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
    }

    public void update_position(float delta)
    {
        velocity.add(acceleration.cpy().scl(delta));
        position.add(velocity.cpy().scl(delta));    // cpy() - возвращает копию Vector2, scl() - умножает вектор на скаляр.
    }

    public void setCoords(float x, float y)    // Координаты левого нижнего угла
    {
        position = new Vector2(x, y);
    }

    public void setVelocity(Vector2 speedVec)
    {
        velocity = speedVec.cpy();
    }

    public void setVelocityX(float speedX)
    {
        velocity.x = speedX;
    }

    // акселерация только когда в воздухе
    public void jump()
    {
        if(!isInJump)
        {
            acceleration = new Vector2(0, -1560);
            velocity = new Vector2(velocity.x, 640);
            isInJump = true;
        }
    }

    // TODO: посмотреть, как сделано в птице
    public void handleDownCollision(float y)
    {
        setVelocity(new Vector2(0, 0));
        position.y = y;
        acceleration = new Vector2(0, 0);
        if (isInJump) isInJump = false;
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

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

}
