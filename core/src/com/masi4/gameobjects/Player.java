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
    private int width;
    private int height;

    public Player(int width, int height)
    {
        this.width = width;
        this.height = height;
        velocity = new Vector2(0, 0);
    }

    public void update(float delta)
    {
        position.add(velocity.cpy().scl(delta));    // cpy() - возвращает копию Vector2, scl() - умножает вектор на скаляр.
    }

    public void SetCoords(float x, float y)    // Координаты центра прямоугольника с шириной widht и высотой height
    {
        position = new Vector2(x, y);
    }

    public void SetSpeed(float speedX, float speedY)
    {
        velocity = new Vector2(speedX, speedY);
    }

    public float GetX()
    {
        return position.x;
    }

    public float GetY()
    {
        return position.y;
    }

    public int GetWidht()
    {
        return width;
    }

    public int GetHeight()
    {
        return height;
    }

}
