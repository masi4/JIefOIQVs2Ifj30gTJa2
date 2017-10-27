package com.masi4.gamehelpers;

/**
 * Created by OIEFIJM on 27.10.2017.
 *
 * Этот класс загружает ассеты
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.masi4.gamehelpers.GameTextureRegions.*;

public class AssetLoader {

    public static Texture player_Texture, level_Texture;
    public static TextureRegion player_test, level_test;

    public static void load()
    {
        player_Texture = new Texture(Gdx.files.internal("gameplay/player_test_image_Texture.png"));
        player_Texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        level_Texture = new Texture(Gdx.files.internal("gameplay/level_test_background_Texture.png"));
        level_Texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        player_test = new TextureRegion(player_Texture, player_test_X, player_test_Y, player_test_Width, player_test_Height);
        level_test = new TextureRegion(level_Texture, test_level_BG_X, test_level_BG_Y, test_level_BG_Width, test_level_BG_Height);

    }

    public static void dispose()
    {
        player_Texture.dispose();
        level_Texture.dispose();
    }

}
