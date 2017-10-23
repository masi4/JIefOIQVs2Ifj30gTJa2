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

    public Level(int number)    // Считывать информацию об уровне откуда нибудь
    {

    }

    public Level(int width, int height, int floorHeight)
    {
        this.width = width;
        this.height = height;
        this.floorHeight = floorHeight;
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
