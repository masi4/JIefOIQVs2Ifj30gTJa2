package com.masi4.gameobjects;

/**
 * Created by OIEFIJM on 13.10.2017.
 *
 * Этот класс описывает игровой уровень
 */

import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;

public class Level {

    private int width;
    private int height;
    private int floorHeight;
    private int worldGravity;

    private ArrayList<Rectangle> levelObjects;  // (возможно) сделать отдельный класс. isRemovable true false

    public enum LevelNames {LEVEL_1}

    public Level(LevelNames name)
    {
        switch (name)
        {
            case LEVEL_1:
            {
                width = 3200;
                height = 480;
                floorHeight = 75;
                worldGravity = 1560;

                // Границы отдельно. прямоугольники с нулевой шириной высотой нельзя
/*
                levelObjects.add(new Rectangle(0f, 0f, 0f, height)); // левая граница уровня
                levelObjects.add(new Rectangle(0f, 0f, width, 0f)); // нижняя
                levelObjects.add(new Rectangle(0f, (float)height, (float)width, 0)); // верхняя
                levelObjects.add(new Rectangle((float)width, 0, 0, (float)height));  // правая
*/
                break;
            }
        }
    }

    public void update(float delta)
    {

    }

    public int getFloorHeight()
    {
        return floorHeight;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public int getWorldGravity() { return worldGravity; }

}
