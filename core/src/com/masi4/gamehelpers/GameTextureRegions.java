package com.masi4.gamehelpers;

/**
 * Created by OIEFIJM on 27.10.2017.
 *
 * В этом классе содержатся информация об участках атласов
 * (X, Y) - координаты левого верхнего пикселя конкретного участка на атласе
 *  Width, Height - высота и ширина
 *  // u1wkn! ВАЖНО TODO: 1) выровнять ровно по пикселям атлас с анимациями (для корректного расположения хитбоксов) 2) сгруппировать атласы в один большой 3) сгруппировать файлы с атласами по папкам
 */

public class GameTextureRegions
{
    //
    // /assets/
    //

    // /gameplay/player/player_default - атлас с default анимацией ходьбы игрока
    public static final int player_default_frame_Y = 0;
    /** Ширина кадра дефолтной анимации ходьбы и стоящего на месте игрока **/
    public static final int player_default_frame_Width = 64;
    public static final int player_default_frame_Height = 96;

    // /gameplay/npc/skeleton - размер фрейма скелета
    public static final int skeleton_frame_Width = 128;
    public static final int skeleton_frame_Height = 128;

    // /gameplay/player/player_attack1 - атлас с default анимацией атаки игрока
    public static final int player_attack_frame_Y = 0;
    public static final int player_attack_frame_Width = 174;
    public static final int player_attack_frame_Height = 128;

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

    // /gameplay/level_0/torch_atlas_png - атлас с факелом
    public static final int torch_width = 80;
    public static final int torch_height = 77;

    // controller - атлас с джойстиком
    public static final int controller_frame_Width = 242;
    public static final int controller_frame_Height = 242;
    public static final int controller_circle_Width = 70;
    public static final int controller_circle_Height = 70;

    // attackButton - атлас с кнопкой атаки
    public static final int GUI_Button_Width = 90;
    public static final int GUI_Button_Height = 90;

    // inventory
    public static final int itemWidth = 32;
    public static final int itemHeight = 32;
}
