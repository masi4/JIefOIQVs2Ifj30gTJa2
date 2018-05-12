package com.masi4.gameworld;

/**
 * Created by OIEFIJM on 23.10.2017.
 *
 * Данный класс отвечает за обновление игровых объектов
 */

import static com.masi4.gamehelpers.GameTextureRegions.*;

import com.masi4.gameobjects.Player;
import com.masi4.gameobjects.objectGraphics.PlayerGraphics;
import com.masi4.gameobjects.Level;

public class GameWorld
{
    private Player player;
    private Level level;

    // Свойства игрока (размеры, шмот etc) возможно вынести в отдельный файл
    public GameWorld(Level.LevelNames levelName)
    {
        level = new Level(levelName);
        player = new Player(48, player_default_frame_Height); //TODO: сделать ширину игрока не зависящей от ширины кадра
        player.setCoords(0, level.getFloorHeight()); // TODO: надо спавнить игрока пониже. (edit: для этого нужно понизить уровень пола в классе Level)
    }

    public void update(float delta)
    {
        player.graphics.update_position(delta);

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

        player.rpg.setHitboxCoords(player.graphics.getX(), player.graphics.getY());

    }

    public Player getPlayer() { return player; }

    public int getLevelWidth() { return level.getWidth(); }

    public int getLevelFloorHeight() { return level.getFloorHeight(); }
}
