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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.masi4.gamehelpers.GameTextureRegions.*;

import com.badlogic.gdx.utils.Array;
import com.masi4.gameobjects.LevelNames;

// TODO: Сделать дочерние классы для каждого level'a. А еще лучше использовать готовый Loader
public class AssetLoader
{

    public static Texture
            player_Texture,
            level_Texture,
            controller_Texture,
            MainMenu_Buttons;

    public static TextureRegion
            // персонаж (стоит)
            player_standing,

            // элементы уровня
            level_BG1,
            level_BG2,
            level_BG3,
            level_BG4,
            level_BG5,
            level_floor,   // TODO: Loop'ить
            level_grassBack,
            level_grassBackLoop,
            level_grassForeLoop,

            // джойстик
            controller_FrameActive,
            controller_CircleActive,
            controller_FrameInactive,
            controller_CircleInactive;

    public static BitmapFont
            default18,
            default22;

    public static Array<TextureRegion> player_default_frames;

    public static Animation<TextureRegion> player_default_animation;

    //
    // Методы
    //

    // может быть пригодится load по умолчанию
    public static void load() {}

    public static void load_Fonts()
    {
        default18 = new BitmapFont(Gdx.files.internal("fonts/default18.fnt"));
        default22 = new BitmapFont(Gdx.files.internal("fonts/default22.fnt"));
    }

    public static void dispose_Fonts()
    {
        default18.dispose();
        default22.dispose();
    }

    //#MAIN MENU
    public static Texture[] MainMenu_Bg;   // TODO: засунуть кнопки в один атлас, использовать bg из /level_0/
    public static void load_MainMenu()
    {
        MainMenu_Bg = new Texture[5];
        for(int i = 0; i < 5;i++)
        {
            MainMenu_Bg[i] =  new Texture(Gdx.files.internal("gameplay/main_menu_BG/bg_0"+i+".png"));
        }
        MainMenu_Buttons = new Texture(Gdx.files.internal("menuButtons-256x96.png"));
    }

    public static void dispose_MainMenu()
    {
        for (Texture bg: MainMenu_Bg)
        {
            bg.dispose();
        }
        MainMenu_Buttons.dispose();
    }

    // Джойстик
    public static void load_Controller()
    {
        controller_Texture = new Texture(Gdx.files.internal("controller.png"));
        controller_Texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        controller_FrameActive = new TextureRegion(controller_Texture, 0, 0, controller_frame_Width,
                controller_frame_Height);
        controller_CircleActive = new TextureRegion(controller_Texture, controller_frame_Width, 0,
                controller_circle_Width, controller_circle_Height);
        controller_FrameInactive = new TextureRegion(controller_Texture, 0, controller_frame_Height,
                controller_frame_Width, controller_frame_Height);
        controller_CircleInactive = new TextureRegion(controller_Texture, controller_frame_Width,
                controller_frame_Height, controller_circle_Width, controller_circle_Height);
    }

    public static void dispose_Controller()
    {
        controller_Texture.dispose();
    }


    // Player
    public static void load_Player()
    {
        player_Texture = new Texture(Gdx.files.internal("gameplay/player/player_default_BIG.png"));
        player_Texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        player_standing = new TextureRegion(player_Texture, player_default_frame0_X, player_default_frame_Y,
                player_default_frame_Width, player_default_frame_Height);

        player_default_frames = new Array<TextureRegion>(9);
        // анимация бега вправо
        player_default_frames.add(new TextureRegion(player_Texture, player_default_frame1_X, player_default_frame_Y,
                player_default_frame_Width, player_default_frame_Height));
        player_default_frames.add(new TextureRegion(player_Texture, player_default_frame2_X, player_default_frame_Y,
                player_default_frame_Width, player_default_frame_Height));
        player_default_frames.add(new TextureRegion(player_Texture, player_default_frame3_X, player_default_frame_Y,
                player_default_frame3_Width, player_default_frame_Height));
        player_default_frames.add(new TextureRegion(player_Texture, player_default_frame4_X, player_default_frame_Y,
                player_default_frame4_Width, player_default_frame_Height));
        player_default_frames.add(new TextureRegion(player_Texture, player_default_frame5_X, player_default_frame_Y,
                player_default_frame_Width, player_default_frame_Height));
        player_default_frames.add(new TextureRegion(player_Texture, player_default_frame6_X, player_default_frame_Y,
                player_default_frame_Width, player_default_frame_Height));
        player_default_frames.add(new TextureRegion(player_Texture, player_default_frame7_X, player_default_frame_Y,
                player_default_frame_Width, player_default_frame_Height));
        player_default_frames.add(new TextureRegion(player_Texture, player_default_frame8_X, player_default_frame_Y,
                player_default_frame_Width, player_default_frame_Height));
        player_default_frames.add(new TextureRegion(player_Texture, player_default_frame9_X, player_default_frame_Y,
                player_default_frame_Width, player_default_frame_Height));
        // TODO: скорость смены кадров в зависимости от скорости игрока
        player_default_animation = new Animation(0.06f, player_default_frames, Animation.PlayMode.LOOP);
    }

    public static void dispose_Player()
    {
        player_Texture.dispose();
    }

    // Level
    public static void load_Level(LevelNames levelName)
    {
        switch (levelName)
        {
            case TEST:
            {
                level_Texture = new Texture(Gdx.files.internal("gameplay/level_0/level0_atlas.png"));
                level_Texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
                level_BG1 = new TextureRegion(level_Texture, level_0_1_X, level_0_1_Y, level_0_1_Width, level_0_1_Height);
                level_BG2 = new TextureRegion(level_Texture, level_0_2_X, level_0_2_Y, level_0_2_Width, level_0_2_Height);
                level_BG3 = new TextureRegion(level_Texture, level_0_3_X, level_0_3_Y, level_0_3_Width, level_0_3_Height);
                level_BG4 = new TextureRegion(level_Texture, level_0_4_X, level_0_4_Y, level_0_4_Width, level_0_4_Height);
                level_grassBack = new TextureRegion(level_Texture, level_0_grassBack_X, level_0_grassBack_Y,
                        level_0_grassBack_Width, level_0_grassBack_Height);
                level_grassBackLoop = new TextureRegion(level_Texture, level_0_grassBackLoop_X, level_0_grassBackLoop_Y,
                        level_0_grassBackLoop_Width, level_0_grassBackLoop_Height);
                level_floor = new TextureRegion(level_Texture, level_0_floor_X, level_0_floor_Y,
                        level_0_floor_Width, level_0_floor_Height);
                level_grassForeLoop = new TextureRegion(level_Texture, level_0_grassForeLoop_X, level_0_grassForeLoop_Y,
                        level_0_grassForeLoop_Width, level_0_grassForeLoop_Height);

            }
        }
    }

    public static void dispose_Level()
    {
        level_Texture.dispose();
    }

    // dispose по умолчанию, запускается при закрытии приложения
    public static void dispose()
    {

    }

}
