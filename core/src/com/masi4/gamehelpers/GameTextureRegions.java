package com.masi4.gamehelpers;

/**
 * Created by OIEFIJM on 27.10.2017.
 *
 * В этом классе содержатся информация об участках атласов
 * (X, Y) - координаты левого верхнего пикселя конкретного участка на атласе
 *  Width, Height - высота и ширина
 */

public class GameTextureRegions
{
    //
    // /assets/
    //

    // /gameplay/player/player_default - атлас с default анимацией персонажа

    public static final int player_default_frame0_X = 0;  // стоит
    public static final int player_default_frame1_X = 48;
    public static final int player_default_frame2_X = 92;
    public static final int player_default_frame3_X = 140;  // кадр прыжка
    public static final int player_default_frame4_X = 196;
    public static final int player_default_frame5_X = 252;
    public static final int player_default_frame6_X = 306;
    public static final int player_default_frame7_X = 354;
    public static final int player_default_frame8_X = 401;
    public static final int player_default_frame9_X = 450;
    public static final int player_default_frame_Y = 0;
    public static final int player_default_frame_Width = 64;
    public static final int player_default_frame_Height = 96;

    // /gameplay/player/player_attack1 - атлас с первой частью анимации атаки персонажа
    public static final int player_attack1_frame1_X = 0;
    public static final int player_attack1_frame2_X = 174;
    public static final int player_attack1_frame3_X = 348;
    public static final int player_attack1_frame4_X = 522;
    public static final int player_attack1_frame5_X = 696;
    public static final int player_attack1_frame_Y = 0;
    public static final int player_attack1_frame_Width = 174;
    public static final int player_attack1_frame_Height = 128;
    // TODO: померить смещение ноги относительно левого края и головы
    public static final int player_attack_X_offset = 50;

    // /gameplay/level_0/level0_atlas - атлас с фонами 0-го уровня
    public static final int level_0_1_X = 0;
    public static final int level_0_1_Y = 0;
    public static final int level_0_1_Width = 256;
    public static final int level_0_1_Height = 144;

    public static final int level_0_2_X = 256;
    public static final int level_0_2_Y = 0;
    public static final int level_0_2_Width = 256;
    public static final int level_0_2_Height = 144;

    public static final int level_0_3_X = 512;
    public static final int level_0_3_Y = 0;
    public static final int level_0_3_Width = 256;
    public static final int level_0_3_Height = 144;

    public static final int level_0_4_X = 768;
    public static final int level_0_4_Y = 0;
    public static final int level_0_4_Width = 256;
    public static final int level_0_4_Height = 144;

    public static final int level_0_grassBack_X = 0;
    public static final int level_0_grassBack_Y = 144;
    public static final int level_0_grassBack_Width = 256;
    public static final int level_0_grassBack_Height = 144;

    public static final int level_0_grassBackLoop_X = 256;
    public static final int level_0_grassBackLoop_Y = 144;
    public static final int level_0_grassBackLoop_Width = 256;
    public static final int level_0_grassBackLoop_Height = 144;

    public static final int level_0_floor_X = 0;
    public static final int level_0_floor_Y = 288;
    public static final int level_0_floor_Width = 256;
    public static final int level_0_floor_Height = 18;

    public static final int level_0_grassForeLoop_X = 512;
    public static final int level_0_grassForeLoop_Y = 144;
    public static final int level_0_grassForeLoop_Width = 256;
    public static final int level_0_grassForeLoop_Height = 144;

  // /controller - атлас с джойстиком
    public static final int controller_frame_Width = 242;
    public static final int controller_frame_Height = 242;
    public static final int controller_circle_Width = 70;
    public static final int controller_circle_Height = 70;



}
