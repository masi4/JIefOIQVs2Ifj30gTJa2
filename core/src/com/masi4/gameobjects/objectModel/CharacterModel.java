package com.masi4.gameobjects.objectModel;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.masi4.gamehelpers.helpers.Directions;
import com.masi4.gameobjects.objectSound.PlayerSound;

/**
 * Created by OIEFIJM on 22.12.2017.
 *
 * Представляет собой математическую модель графики персонажа - координаты, скорость
 */

public class CharacterModel
{
    protected Vector2 position;
    protected Vector2 velocity;         // Вектор скорости
    protected Vector2 acceleration;     // Вектор ускорения
    protected int width;
    protected int height;
    protected Rectangle hitbox; // Важно: сначала выполнять дешевые проверки (rec.overlaps), затем дорогие (intersector)

    /** Начальная скорость по вертикали при прыжке **/
    protected int initialJumpSpeed;
    /** Модуль вертикального ускорения, возникающего при прыжке и действующего вниз**/
    protected int worldGravity;
    /** Максимальная скорость по X **/
    protected float maxHorizontalVelocity;
    /** Как быстро набирает максимальную скорость по X**/
    protected int horizontalVelocityGain;
    /** В какую сторону повернут **/
    protected Directions turnedSide;
    /** Находится ли персонаж на земле **/
    protected boolean isOnGround; // TODO: Сделать какую-то универсальную проверку на случай, если его будет отбрасывать. Пока что на это влияет только прыжок.
    /** Управляют ли персонажем в эту сторону, или он двигается потому что его, например, оттолкнули **/
    protected Directions controlsDirection;

    public CharacterModel(int width, int height, int worldGravity, int initialJumpSpeed, int horizontalVelocityGain,
                          float maxHorizontalVelocity, Directions turnedSide, boolean isOnGround)
    {
        position = new Vector2(0, 0);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
        this.width = width;
        this.height = height;
        hitbox = new Rectangle(position.x, position.y, width, height);

        this.worldGravity = worldGravity;
        this.initialJumpSpeed = initialJumpSpeed;
        this.horizontalVelocityGain = horizontalVelocityGain;
        this.maxHorizontalVelocity = maxHorizontalVelocity;
        this.turnedSide = turnedSide;
        this.isOnGround = isOnGround;
        controlsDirection = Directions.NONE;
    }

    public void update_position(float delta)
    {
        // ускорение
        if (!isOnGround) setAccelerationY(-worldGravity);

        // скорость
        velocity.x += acceleration.x * delta;
        if (Math.abs(velocity.x) > Math.abs(maxHorizontalVelocity))
            if (velocity.x > 0) velocity.x = maxHorizontalVelocity;
            else velocity.x = -maxHorizontalVelocity;
        velocity.y += acceleration.y * delta;

        if (isOnGround() &&  controlsDirection == Directions.NONE)
            setVelocityX(0);

        // координаты
        setPosition(position.x + velocity.x * delta, position.y + velocity.y * delta);
    }

    public void setPosition(float x, float y)    // Координаты левого нижнего угла
    {
        position = new Vector2(x, y);
        hitbox.x = position.x;
        hitbox.y = position.y;
    }

    public void setPositionX(float x)
    {
        position.x = x;
        hitbox.x = x;
    }

    public void setPositionY(float y)
    {
        position.y = y;
        hitbox.y = y;
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
        if(isOnGround)
        {
            setVelocityY(initialJumpSpeed);
            setAcceleration(0, -worldGravity);
            isOnGround = false;
        }
    }

    protected void moveRight()
    {
        if (controlsDirection == Directions.LEFT)
            setVelocityX(-getVelocityX());
        else if (controlsDirection == Directions.NONE)
            setVelocityX(0);

        setAccelerationX(horizontalVelocityGain);
        turnedSide = Directions.RIGHT;
        controlsDirection = Directions.RIGHT;
    }

    protected void moveLeft()
    {
        if (controlsDirection == Directions.RIGHT)
            setVelocityX(-getVelocityX());
        else if (controlsDirection == Directions.NONE)
            setVelocityX(0);

        setAccelerationX(-horizontalVelocityGain);
        turnedSide = Directions.LEFT;
        controlsDirection = Directions.LEFT;
    }

    public void releaseMovementControls()
    {
        // разделить для вынужденного ускорения (если его отбро (очень потом)
        setAccelerationX(0);
        controlsDirection = Directions.NONE;
    }

    public void handleDownCollision(float y)
    {
        if (acceleration.y < 0) setAccelerationY(0);
        if (velocity.y < 0) setVelocityY(0);
        setPositionY(y);
        if (!isOnGround) isOnGround = true;
    }

    public void handleUpCollision(float y)
    {
        if (acceleration.y > 0) setAccelerationY(0);
        if (velocity.y > 0) setVelocityY(0);
        setPositionY(y - height);
    }

    public void handleLeftCollision(float x)
    {
        if (acceleration.x < 0) setAccelerationX(0);
        if (velocity.x < 0) setVelocityX(0);
        setPositionX(x);
    }

    public void handleRightCollision(float x)
    {
        if (acceleration.x > 0) setAccelerationX(0);
        if (velocity.x > 0) setVelocityX(0);
        setPositionX(x - width);
    }

    public Rectangle getHitbox()
    {
        return hitbox;
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

    public boolean isOnGround() { return isOnGround; }

    public Directions getTurnedSide()
    {
        return turnedSide;
    }

    public Directions getControlsDirection() {
        return controlsDirection;
    }
}
