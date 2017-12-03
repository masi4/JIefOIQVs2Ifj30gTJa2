package com.masi4.gameobjects;

/**
 * Created by OIEFIJM on 13.10.2017.
 *
 * Этот класс описывает игрового персонажа
 */

import com.badlogic.gdx.math.Vector2;

public class Player {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private int width;
    private int height;
    private float floorHeight;
    public Player(int width, int height)
    {
        this.width = width;
        this.height = height;
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, -1560);
    }

    public void update(float delta)
    {
        velocity.add(acceleration.cpy().scl(delta));
        position.add(velocity.cpy().scl(delta));    // cpy() - возвращает копию Vector2, scl() - умножает вектор на скаляр.

        if(position.y<floorHeight && jump){
            jump = false;
        }
        if(position.y<floorHeight){
            position.y = floorHeight;
        }


    }

    public void SetCoords(float x, float y)    // Координаты левого нижнего угла
    {
        floorHeight = y;
        position = new Vector2(x, y);
    }

    public void Move(Vector2 speedVec)    // TODO: джойстик, сделать движение вектором в джойстике, !!!вектор гравитации!!!
    {
        velocity = speedVec.cpy();
    }

    public boolean jump = false;    // Игрок находится в прыжке
    public void Jump()
    {
        if(!jump)
           velocity = new Vector2(velocity.x, 640);
        jump = true;
    }

    public void Stop()
    {
        velocity = new Vector2(0f, velocity.y);
    }

    public void Move(float speedX)
    {
        velocity.x = speedX;
    }

    public float GetX()
    {
        return position.x;
    }

    public float GetY()
    {
        return position.y;
    }

    public int GetWidth()
    {
        return width;
    }

    public int GetHeight()
    {
        return height;
    }

}
