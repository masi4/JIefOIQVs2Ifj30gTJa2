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

    public static Texture player_Texture, level_Texture, controller_Texture;
    public static TextureRegion player_test, level_test, controller_FrameAc, controller_CircleAc, controller_FrameInac, controller_CircleInac;

    public static void load()
    {
        player_Texture = new Texture(Gdx.files.internal("gameplay/player_test_image_Texture.png"));
        player_Texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        level_Texture = new Texture(Gdx.files.internal("gameplay/level_test_background_Texture.png"));
        level_Texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        controller_Texture = new Texture(Gdx.files.internal("controller.png"));
        controller_Texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        player_test = new TextureRegion(player_Texture, player_test_X, player_test_Y, player_test_Width, player_test_Height);
        level_test = new TextureRegion(level_Texture, test_level_BG_X, test_level_BG_Y, test_level_BG_Width, test_level_BG_Height);
        controller_FrameAc = new TextureRegion(controller_Texture, 0, 0, controller_frame_Width, controller_frame_Height);
        controller_CircleAc = new TextureRegion(controller_Texture,  controller_frame_Width, 0, controller_circle_Width, controller_circle_Height);
        controller_FrameInac = new TextureRegion(controller_Texture, 0,  controller_frame_Height, controller_frame_Width, controller_frame_Height);
        controller_CircleInac = new TextureRegion(controller_Texture, controller_frame_Width, controller_frame_Height, controller_circle_Width, controller_circle_Height);
    }

    //#MAIN MENU
    public static Texture[] MainMenu_Bg;
    public static Texture  MainMenu_Buttons;

    public static void load_MainMenu(){
        MainMenu_Bg = new Texture[5];
        for(int i = 0; i <5;i++){
            MainMenu_Bg[i] =  new Texture(Gdx.files.internal("gameplay/main_menu_BG/bg_0"+i+".png"));
        }
        MainMenu_Buttons = new Texture(Gdx.files.internal("menuButtons-256x96.png"));
    }



    public static void dispose()
    {
        player_Texture.dispose();
        level_Texture.dispose();
        controller_Texture.dispose();
        MainMenu_Buttons.dispose();
    }

}
