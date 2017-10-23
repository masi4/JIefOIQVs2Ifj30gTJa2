package com.masi4.gameworld;

/**
 * Created by OIEFIJM on 23.10.2017.
 *
 * Данный класс отвечает за обновление игровых объектов
 */

import com.masi4.gameobjects.Player;
import com.masi4.gameobjects.Level;

public class GameWorld {

    private Player player;
    private Level level;

    public GameWorld()
    {
        level = new Level(1600, 480, 110);      // TODO: информацию об уровнях, размерах персонажа куда нибудь вынести
        player = new Player(82, 100);
        player.SetCoords(player.GetWidht()/2, level.GetFloorHeight() + player.GetHeight()/2);
    }

    public void update(float delta)
    {

    }

}
