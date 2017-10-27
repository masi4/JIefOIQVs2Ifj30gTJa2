package com.masi4.gameobjects;

/**
 * Created by OIEFIJM on 13.10.2017.
 *
 * Этот класс описывает игровой уровень
 */

public class Level {

    private int width;
    private int height;
    private int floorHeight;

    public Level(int width, int height, int floorHeight)
    {
        this.width = width;
        this.height = height;
        this.floorHeight = floorHeight;
    }

    public Level(LevelNames name)
    {
        switch (name)
        {
            case TEST:
            {
                width = 800;
                height = 480;
                floorHeight = 100;

                break;
            }
        }
    }

    public void update(float delta)
    {

    }

    public int GetFloorHeight()
    {
        return floorHeight;
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
