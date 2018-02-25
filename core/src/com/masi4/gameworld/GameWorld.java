package com.masi4.gameworld;

/**
 * Created by OIEFIJM on 23.10.2017.
 *
 * Данный класс отвечает за обновление игровых объектов
 */

import static com.masi4.gamehelpers.GameTextureRegions.*;

import com.masi4.gameobjects.Player;
import com.masi4.objectsGraphic.PlayerGraphics;
import com.masi4.gameobjects.Level;

public class GameWorld
{
    private Player player;
    private Level level;

    public GameWorld(Level.LevelNames levelName)
    {
        level = new Level(levelName);
        player = new Player(player_default_frame_Width, player_default_frame_Height); //TODO: сделать ширину игрока не зависящей от ширины кадра
        player.setCoords(0, level.getFloorHeight());
    }

    public void update(float delta)
    {
        // TODO: доделать развитие архитектуры
        player.update_position(delta);

        // Обработка столкновений
        if ( player.graphics.getY() < level.getFloorHeight())   // сделать циклом для всех levelobjects
        {
            player.graphics.handleDownCollision(level.getFloorHeight());
        }
        if (player.graphics.getY() + player.graphics.getHeight() >= level.getHeight())
        {
            player.graphics.handleUpCollision(level.getHeight());
        }
        if (player.graphics.getX() <= 0)
        {
            player.graphics.handleLeftCollision(0);
        }
        if (player.graphics.getX() + player.graphics.getWidth() >= level.getWidth())
        {
            player.graphics.handleRightCollision(level.getWidth());
        }


    }

    public PlayerGraphics getPlayerGraphics() { return player.graphics; }

    public int getLevelWidth() { return level.getWidth(); }

    public int getLevelFloorHeight() { return level.getFloorHeight(); }
}
