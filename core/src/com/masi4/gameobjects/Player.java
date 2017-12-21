package com.masi4.gameobjects;

/**
 * Created by OIEFIJM on 13.10.2017.
 *
 * Этот класс описывает игрового персонажа
 */

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import org.w3c.dom.css.Rect;

public class Player
{
    private Vector2 position;
    private Vector2 velocity;         // Вектор скорости
    private Vector2 acceleration;     // Вектор ускорения
    private float delta;
    private int width;
    private int height;
    // Важно: сначала выполнять дешевые проверки, затем дорогие (intersector)
    private Rectangle boundingRec;   // Определение коллизий TODO: переписать handleCollisions через этот квадрат
    private boolean inJump;    // находится ли персонаж в прыжке

    // Публичные члены

    public Player(int width, int height)
    {
        this.width = width;
        this.height = height;
        boundingRec = new Rectangle();
        boundingRec.setSize(width, height);
        inJump = false;
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
    }

    public void update_position(float delta)
    {
        this.delta = delta;
        velocity.add(acceleration.cpy().scl(delta));    // TODO: прибавление вручную, без cpy()
        position.add(velocity.cpy().scl(delta));
        boundingRec.setPosition(position.x, position.y);
    }

    public void setCoords(float x, float y)    // Координаты левого нижнего угла
    {
        position = new Vector2(x, y);
        boundingRec.setPosition(x, y);
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

    public float getDelta() { return delta; }

    public Rectangle getBoundingRec() { return boundingRec; }

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
