package com.masi4.gamehelpers;

/**
 * Created by OIEFIJM on 27.10.2017.
 *
 * Этот класс загружает ассеты
 *
 * u1wkn TODO: засунуть кнопки атаки, джойстика и т.д. в один атлас
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.masi4.gamehelpers.GameTextureRegions.*;
import static com.masi4.gameobjects.Level.LevelNames;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.XmlReader;
import com.masi4.UI.gameInventory.model.objects._InventoryItem;
import com.masi4.gameobjects.objects.Skeleton;

// TODO: Использовать готовый Loader из libgdx
public class AssetLoader
{
    private static Texture
            // Игрок
            player_Texture,
            player_default_sword_attack_Texture,
            // Скелет
            skeleton_WalkingTexture,
            skeleton_dead_Texture,
            skeleton_sword_attack_Texture,
            // Уровень
            level1_Texture,
            level_torch_Texture,
            level_cave_Texture,
            // Кнопки
            controller_Texture,
            attackButton_Texture,
            inventoryButton_Texture,
            MainMenu_Buttons,
            // Предметы и инвентарь
            items_Texture,
            GameInventory_HealthBarTexture,
            GameInventory_InventoryTexture,
            GameInventory_SlotTexture;

    public static Texture[] MainMenu_Bg;

    public static TextureRegion
            // персонаж (стоит)
            player_standing,
            // элементы уровня
            level_BG1,
            level_BG2,
            level_BG3,
            level_BG4,
            level_floor,
            level_grassBack,
            level_grassBackLoop,
            level_grassForeLoop,
            level_cave,
            // джойстик
            controller_FrameActive,
            controller_CircleActive,
            controller_FrameInactive0,
            controller_CircleInactive0,
            controller_FrameInactive1,
            controller_CircleInactive1,
            // кнопка атаки
            attackButton_Up,
            attackButton_Down,
            // кнопка инвентаря
            inventoryButton_Up,
            inventoryButton_Down,
            // инвентарь
            GameInventory_InventoryTextureRegion,
            GameInventory_HealthBarBoundsTextureRegion,
            GameInventory_HealthBarFillTextureRegion,
            GameInventory_SlotTextureRegion;

    public static TextureRegion[]
            // главное меню
            playButtonTxrReg,
            optionsButtonTxrReg,
            exitButtonTxrReg;

    public static BitmapFont
            default18,
            default12,
            default22;

    // Кадры для анимаций
    private static Array<TextureRegion>
            // игрок
            player_default_walking_frames,
            player_default_start_walking_frames,
            player_default_attack_frames,
            // скелетон
            skeleton_walking_frames,
            skeleton_dead_frames,
            skeleton_attack_frames,
            // факел
            torch_frames;

    public static Animation<TextureRegion>
            // игрок
            player_default_animation,
            player_default_start_walking_animation,
            player_default_attack_animation,
            // факел
            torch_animation;

    // Анимации скелетов
    public static ObjectMap<Skeleton, Animation>
            skeleton_walking_animations = new ObjectMap<Skeleton, Animation>(),
            skeleton_melee_sword_attack_animations = new ObjectMap<Skeleton, Animation>();

    // Скелеты стоят
    public static ObjectMap<Skeleton, TextureRegion>
        skeletons_standing = new ObjectMap<Skeleton, TextureRegion>(),
        skeletons_remains = new ObjectMap<Skeleton, TextureRegion>();


    public static Skin
            controllerSkin;

    //
    // Методы
    //

    // load по умолчанию
    public static void load() {
        load_Fonts();
    }


    public static void load_Fonts()
    {
        default12 = new BitmapFont(Gdx.files.internal("fonts/default12.fnt"));
        default18 = new BitmapFont(Gdx.files.internal("fonts/default18.fnt"));
        default22 = new BitmapFont(Gdx.files.internal("fonts/default22.fnt"));
    }

    public static void dispose_Fonts()
    {
        default12.dispose();
        default18.dispose();
        default22.dispose();
    }

    // Главное меню
    public static void load_MainMenu()
    {
        MainMenu_Bg = new Texture[5];
        for(int i = 0; i < 5;i++)
        {
            MainMenu_Bg[i] =  new Texture(Gdx.files.internal("gameplay/main_menu_BG/bg_0"+i+".png"));
        }
        MainMenu_Buttons = new Texture(Gdx.files.internal("menuButtons.png"));
        int m = GamePreferences.Options.getInteger("Language"); // Для локализации
        playButtonTxrReg = new TextureRegion[]
                {
                        new TextureRegion(MainMenu_Buttons, 0,96 * m , 138, 32),
                        new TextureRegion(MainMenu_Buttons, 138, 96 * m, 138, 32)
                };
        optionsButtonTxrReg = new TextureRegion[]
                {
                        new TextureRegion(MainMenu_Buttons, 0, 96 * m + 32, 138, 32),
                        new TextureRegion(MainMenu_Buttons, 138, 96 * m + 32, 138, 32)
                };
        exitButtonTxrReg = new TextureRegion[]
                {
                        new TextureRegion(MainMenu_Buttons, 0, 96 * m + 64, 138, 32),
                        new TextureRegion(MainMenu_Buttons, 138, 96 * m + 64, 138, 32)
                };
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

        controller_FrameActive = new TextureRegion(controller_Texture, 0, 0, controller_frame_Width, controller_frame_Height);
        controller_CircleActive = new TextureRegion(controller_Texture,  controller_frame_Width, 0, controller_circle_Width, controller_circle_Height);
        controller_FrameInactive1 = new TextureRegion(controller_Texture, 0,  controller_frame_Height, controller_frame_Width, controller_frame_Height);
        controller_CircleInactive1 = new TextureRegion(controller_Texture, controller_frame_Width, controller_frame_Height, controller_circle_Width, controller_circle_Height);
        controller_FrameInactive0 = new TextureRegion(controller_Texture, 0,  2*controller_frame_Height, controller_frame_Width, controller_frame_Height);
        controller_CircleInactive0 = new TextureRegion(controller_Texture, controller_frame_Width, 2*controller_frame_Height, controller_circle_Width, controller_circle_Height);

        controllerSkin = new Skin();
        controllerSkin .add("frameAc", AssetLoader.controller_FrameActive);
        controllerSkin .add("circleAc", AssetLoader.controller_CircleActive);
        if(GamePreferences.Options.getInteger("Controller")==0)
        {
            controllerSkin .add("frameInac", AssetLoader.controller_FrameInactive0);
            controllerSkin .add("circleInac", AssetLoader.controller_CircleInactive0);
        }
        else
        {
            controllerSkin .add("frameInac", AssetLoader.controller_FrameInactive1);
            controllerSkin .add("circleInac", AssetLoader.controller_CircleInactive1);
        }
    }

    // Кнопка атаки
    public static void load_GUI_Buttons()
    {
        attackButton_Texture = new Texture(Gdx.files.internal("attackButton.png"));
        attackButton_Down = new TextureRegion(attackButton_Texture,0,0, GUI_Button_Width, GUI_Button_Height);
        attackButton_Up = new TextureRegion(attackButton_Texture,0, GUI_Button_Height, GUI_Button_Width, GUI_Button_Height);
        inventoryButton_Texture = new Texture(Gdx.files.internal("inventoryButton.png"));
        inventoryButton_Down = new TextureRegion(inventoryButton_Texture,0,0, GUI_Button_Width, GUI_Button_Height);
        inventoryButton_Up = new TextureRegion(inventoryButton_Texture,0, GUI_Button_Height, GUI_Button_Width, GUI_Button_Height);
    }

    public static void dispose_Controller()
    {
        controller_Texture.dispose();
    }

    //
    // Игрок
    //

    public static void load_Player()
    {
        player_Texture = new Texture(Gdx.files.internal("gameplay/player/player_walking.png"));
        player_Texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        player_standing = new TextureRegion(player_Texture, 0, player_default_frame_Y,
                player_default_frame_Width, player_default_frame_Height);

        int[] frames_count = new int[] {8,  2}; // {бег, начало бега}
        player_default_walking_frames = new Array<TextureRegion>(frames_count[0]);
        player_default_start_walking_frames = new Array<TextureRegion>(frames_count[1]);

        // анимация бега вправо
        for(int i = 2; i <= frames_count[0]; i++)
        {
            player_default_walking_frames.add(new TextureRegion(player_Texture, player_default_frame_Width * i,
                    player_default_frame_Y, player_default_frame_Width, player_default_frame_Height));
        }
        for(int i = 0; i <= frames_count[1];i++)
        {
            player_default_start_walking_frames.add(new TextureRegion(player_Texture, player_default_frame_Width * i,
                    player_default_frame_Y, player_default_frame_Width, player_default_frame_Height));
        }

        player_default_animation = new Animation(0.06f, player_default_walking_frames, Animation.PlayMode.LOOP);
        player_default_start_walking_animation = new Animation(0.06f, player_default_start_walking_frames, Animation.PlayMode.NORMAL);
    }

    // Default Sword Attack
    public static void load_PlayerDefaultAttack()
    {
        player_default_sword_attack_Texture = new Texture(Gdx.files.internal("gameplay/player/player_attack_1.png"));
        player_default_attack_frames = new Array<TextureRegion>(5);
        for (int i = 0; i < 5; i++)
        {
            player_default_attack_frames.add(new TextureRegion(player_default_sword_attack_Texture,
                    player_attack_frame_Width * i, player_attack_frame_Y, player_attack_frame_Width,
                    player_attack_frame_Height));
        }

        // default cast time, actual cast time depends on current cast time of that ability,
        // which depends on player's stats
        float frameTime = 0.15f / player_default_attack_frames.size;
        player_default_attack_animation =
                new Animation(frameTime, player_default_attack_frames, Animation.PlayMode.NORMAL);
    }

    public static void dispose_Player()
    {
        player_Texture.dispose();
    }


    public static void dispose_PlayerDefaultAttack()
    {
        player_default_sword_attack_Texture.dispose();
    }

    //
    // Скелетон
    //

    public static void load_SkeletonTextures()
    {
        skeleton_WalkingTexture = new Texture(Gdx.files.internal("gameplay/npc/skeleton/skeleton_shag_128.png"));
        skeleton_WalkingTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        skeleton_sword_attack_Texture = new Texture(Gdx.files.internal("gameplay/npc/skeleton/skeleton_attack_128.png"));
        skeleton_sword_attack_Texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        skeleton_dead_Texture = new Texture(Gdx.files.internal("gameplay/npc/skeleton/skeleton_umer_128.png"));
        skeleton_dead_Texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
    }

    public static void load_NewSkeleton(Skeleton skeleton) {
        skeletons_standing.put(
                skeleton,
                new TextureRegion(skeleton_WalkingTexture, 0, 0, skeleton_frame_Width, skeleton_frame_Height)
        );
        skeletons_remains.put(
                skeleton,
                new TextureRegion(skeleton_dead_Texture, skeleton_frame_Width * 7, 0, skeleton_frame_Width, skeleton_frame_Height)
        );

        skeleton_walking_frames = new Array<TextureRegion>(11);

        for (int i = 0; i < 11; i++)
            skeleton_walking_frames.add(new TextureRegion(skeleton_WalkingTexture, skeleton_frame_Width * i,
                    0, skeleton_frame_Width, skeleton_frame_Height));

        skeleton_walking_animations.put(
                skeleton,
                new Animation(0.06f, skeleton_walking_frames, Animation.PlayMode.LOOP)
        );
    }

    public static void load_NewSkeletonRemains(SkeletonDeath deathPoint)
    {
        // umer
        skeleton_dead_frames = new Array<TextureRegion>(8);

        for (int i = 0; i < 8; i++ )
            skeleton_dead_frames.add(new TextureRegion(skeleton_dead_Texture, skeleton_frame_Width * i,
                    0, skeleton_frame_Width, skeleton_frame_Height));

        deathPoint.skeletonDeathAnimation =  new Animation(0.1f, skeleton_dead_frames, Animation.PlayMode.NORMAL);
        deathPoint.skeletonDeathRemains = skeleton_dead_frames.get(7);
    }

    public static void load_NewSkeleton_MeleeSwordAttack(Skeleton skeleton)
    {
        skeleton_attack_frames = new Array<TextureRegion>(13);

        for (int i = 0; i < 13; i++)
            skeleton_attack_frames.add(new TextureRegion(skeleton_sword_attack_Texture,
                    skeleton_frame_Width * i, 0, skeleton_frame_Width, skeleton_frame_Height));

        float frameTime = 0.2f / skeleton_attack_frames.size;
        skeleton_melee_sword_attack_animations.put(
                skeleton,
                new Animation(frameTime, skeleton_attack_frames, Animation.PlayMode.NORMAL)
        );
    }

    public static void remove_Skeleton(Skeleton skeleton)
    {
        skeletons_standing.remove(skeleton);
        skeleton_walking_animations.remove(skeleton);
    }

    public static void remove_SkeletonMeleeSwordAttack(Skeleton skeleton)
    {
        skeleton_melee_sword_attack_animations.remove(skeleton);
    }

    public static void dispose_Skeleton_Textures()
    {
        skeleton_WalkingTexture.dispose();
        skeleton_dead_Texture.dispose();
    }

    public static void dispose_SkeletonSwordAttack_Texture()
    {
        skeleton_sword_attack_Texture.dispose();
    }

    //
    // Игровые уровни
    //
    public static void load_Level(LevelNames levelName)
    {
        switch (levelName)
        {
            case LEVEL_1:
            {
                level1_Texture = new Texture(Gdx.files.internal("gameplay/level_0/level0_atlas.png"));
                level1_Texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
                level_BG1 = new TextureRegion(level1_Texture, level_0_1_X, level_0_1_Y, level_0_1_Width, level_0_1_Height);
                level_BG2 = new TextureRegion(level1_Texture, level_0_2_X, level_0_2_Y, level_0_2_Width, level_0_2_Height);
                level_BG3 = new TextureRegion(level1_Texture, level_0_3_X, level_0_3_Y, level_0_3_Width, level_0_3_Height);
                level_BG4 = new TextureRegion(level1_Texture, level_0_4_X, level_0_4_Y, level_0_4_Width, level_0_4_Height);
                level_grassBack = new TextureRegion(level1_Texture, level_0_grassBack_X, level_0_grassBack_Y,
                        level_0_grassBack_Width, level_0_grassBack_Height);
                level_grassBackLoop = new TextureRegion(level1_Texture, level_0_grassBackLoop_X, level_0_grassBackLoop_Y,
                        level_0_grassBackLoop_Width, level_0_grassBackLoop_Height);
                level_floor = new TextureRegion(level1_Texture, level_0_floor_X, level_0_floor_Y,
                        level_0_floor_Width, level_0_floor_Height);
                level_grassForeLoop = new TextureRegion(level1_Texture, level_0_grassForeLoop_X, level_0_grassForeLoop_Y,
                        level_0_grassForeLoop_Width, level_0_grassForeLoop_Height);

                load_Torch();
                load_Cave();
            }
        }
    }

    private static void load_Torch()
    {
        level_torch_Texture = new Texture(Gdx.files.internal("gameplay/level_0/torch_atlas.png")); //TODO: Поместить в атлас
        level_torch_Texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        int frames_count = 11;
        torch_frames = new Array<TextureRegion>(frames_count);
        for(int i = 0; i<=frames_count;i++)
        {
            torch_frames.add(new TextureRegion(level_torch_Texture,torch_width*i,0,torch_width,torch_height));
        }
        torch_animation = new Animation(0.09f, torch_frames,Animation.PlayMode.LOOP);
    }

    private static void load_Cave()
    {
        level_cave_Texture = new Texture(Gdx.files.internal("gameplay/level_0/cave.png")); //TODO: Поместить в атлас

        level_cave = new TextureRegion(level_cave_Texture);
    }
    // Инвентарь
    public static void load_Inventory()
    {
        GameInventory_InventoryTexture = new Texture(Gdx.files.internal("inventoryDefaultSkin.png"));
        GameInventory_InventoryTextureRegion = new TextureRegion(GameInventory_InventoryTexture,0,0,52,52);
        GameInventory_SlotTexture = new Texture(Gdx.files.internal("slotDefaultSkin.png"));
        GameInventory_SlotTextureRegion = new TextureRegion(GameInventory_InventoryTexture,0,0,52,52);
        items_Texture = new Texture(Gdx.files.internal("Items_atlas.png"));
        GameInventory_HealthBarTexture = new Texture(Gdx.files.internal("healthbar.png"));
        GameInventory_HealthBarBoundsTextureRegion = new TextureRegion(GameInventory_HealthBarTexture,0,0,90,10);
        GameInventory_HealthBarFillTextureRegion = new TextureRegion(GameInventory_HealthBarTexture,0,10,1,8);
    }

    public static TextureRegion Inventory_GetItemTexture(_InventoryItem item) {
        XmlReader reader = new XmlReader();
        XmlReader.Element root = null;
        FileHandle file = Gdx.files.internal("xml/Items.xml");
        root = reader.parse(file);

        int id = Integer.parseInt(root.getChildByName(item.getClass().getSimpleName()).getAttribute("id"));

        int x = (id%(items_Texture.getWidth()/itemWidth))*itemWidth;
        int y = (id/(items_Texture.getHeight()/itemHeight))*itemHeight;
        return new TextureRegion(items_Texture,x,y,itemWidth,itemHeight);
    }


    public static void dispose_Level1()
    {
        level1_Texture.dispose();
    }


    // dispose по умолчанию, запускается при закрытии приложения
    public static void dispose()
    {

    }

}
