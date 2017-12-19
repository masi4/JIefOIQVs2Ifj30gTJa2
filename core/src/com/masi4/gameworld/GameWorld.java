package com.masi4.gameworld;

/**
 * Created by OIEFIJM on 23.10.2017.
 *
 * Данный класс отвечает за обновление игровых объектов
 */

import static com.masi4.gamehelpers.GameTextureRegions.*;

import com.masi4.gameobjects.LevelNames;
import com.masi4.gameobjects.Player;
import com.masi4.gameobjects.Level;

public class GameWorld
{
    private Player player;
    private Level level;


    public GameWorld(LevelNames levelName)
    {
        level = new Level(levelName);
        player = new Player(player_default_Width, player_default_Height);
        player.setCoords(0, level.getFloorHeight());
    }

    public void update(float delta)
    {
        player.update_position(delta);

        // Обработка столкновений
        if ( player.getY() <= level.getFloorHeight())   // сделать циклом для всех levelobjects
        {
            player.handleDownCollision(level.getFloorHeight());
        }
        if (player.getY() + player.getHeight() >= level.getHeight())
        {
            player.handleUpCollision(level.getHeight());
        }
        if (player.getX() <= 0)
        {
            player.handleLeftCollision(0);
        }
        if (player.getX() + player.getWidth() >= level.getWidth())
        {
            player.handleRightCollision(level.getWidth());
        }


    }

    public Player getPlayer()
    {
        return player;
    }

}
