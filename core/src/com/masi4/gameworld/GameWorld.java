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

public class GameWorld {

    private Player player;
    private Level level;

    public GameWorld()
    {
        // TODO: информацию об уровнях, размерах персонажа куда нибудь вынести

        level = new Level(LevelNames.TEST);
        player = new Player(player_test_Width, player_test_Height);
        player.SetCoords(0, level.GetFloorHeight());
    }

    public void update(float delta)
    {
        player.update(delta);
    }

    public Player GetPlayer()
    {
        return player;
    }

}
