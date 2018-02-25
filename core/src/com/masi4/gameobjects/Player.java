package com.masi4.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.masi4.objectsRpg.PlayerRpg;
import com.masi4.objectsGraphic.PlayerGraphics;

/**
 * Created by OIEFIJM on 22.12.2017.
 */

public class Player
{
    public PlayerGraphics graphics;
    public PlayerRpg rpg;

    public Player(PlayerGraphics graphics, PlayerRpg rpg)
    {
        this.graphics = graphics;
        this.rpg = rpg;
    }

    public Player(int width, int height)
    {
        graphics = new PlayerGraphics(width, height);
        Rectangle hitbox = new Rectangle();
        hitbox.setWidth(width);
        hitbox.setHeight(height);
        rpg = new PlayerRpg(hitbox);
    }

    public void update_position(float delta)
    {
        graphics.update_position(delta);
        rpg.setHitboxCoords(graphics.getX(), graphics.getY());
    }

    public void setCoords(float x, float y)
    {
        graphics.setCoords(x, y);

    }
}
