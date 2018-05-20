package com.masi4.UI.gameInventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.XmlReader;
import com.masi4.UI.gameInventory.model.objects._InventoryItem;
import com.masi4.gamehelpers.GamePreferences;

import java.io.IOException;


// Различные подгрузщики из файлов
public class __InventoryStatics
{

    public static int GetItemId(_InventoryItem item)
    {
        XmlReader reader = new XmlReader();
        XmlReader.Element root = null;
        FileHandle file = Gdx.files.internal("xml/Items.xml");

        try {
            root = reader.parse(file);
        }
        catch (IOException e) {}

        int id = Integer.parseInt(root.getChildByName(item.getClass().getSimpleName()).getAttribute("id"));
        return id;
    }
    // (Метод загрузки текстур остался в AssetLoader)

    public static String GetItemTitle(_InventoryItem item)
    {
        int id = GetItemId(item);
        String result = GamePreferences.loc.format("InventoryItem" + id + "_Title");
        return result;
    }

    public static String GetItemDescription(_InventoryItem item)
    {
        int id = GetItemId(item);
        String result = GamePreferences.loc.format("InventoryItem" + id + "_Description", item.GetStats());
        return result;
    }

    public static int GetRarity(_InventoryItem item)
    {
        XmlReader reader = new XmlReader();
        XmlReader.Element root = null;
        FileHandle file = Gdx.files.internal("xml/Items.xml");
        try {
            root = reader.parse(file);
        }
        catch (IOException e) { }

        int rarity = Integer.parseInt(root.getChildByName(item.getClass().getSimpleName()).getAttribute("rarity"));
        return rarity;
    }

    public static Color GetItemColor(_InventoryItem item)
    {
        switch (GetRarity(item))
        {
            case 0:
            {
                return Color.valueOf("ffffff");
            }
            case 1:
            {
                return Color.valueOf("3fe429");
            }
            case 2:
            {
                return Color.valueOf("0070dd");
            }
            case 3:
            {
                return Color.valueOf("a335ee");
            }
            case 4:
            {
                return Color.valueOf("ff8000");
            }
            default:
            {
                //Rarity не указана
                return Color.RED;
            }
        }

    }

}
