package com.masi4.gameworld.renderers;

/**
 * Created by OIEFIJM on 19.12.2017.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import static com.masi4.myGame.GameMain.*;

import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;
import com.masi4.gamehelpers.recourceHandlers.AssetLoader;
import com.masi4.gamehelpers.GameTextureRegions;
import com.masi4.gameworld.SkeletonDeath;
import com.masi4.gameobjects.SkeletonListener;
import com.masi4.gameobjects.objects.Skeleton;
import com.masi4.gameworld.GameWorld;

public class Level1_Renderer extends GameRenderer implements SkeletonListener
{
    // Assets
    private Animation
            level_torch_animation;

    private TextureRegion
            level_BG1,
            level_BG2,
            level_BG3,
            level_BG4,
            level_grassBack,
            level_floor,
            level_cave;
    private TextureRegion[] grassBackLoops, grassForeLoops;

    /** Прикреплена ли камера к игроку **/
    private boolean cameraAttached;
    /** Длина отрезка, на котором камера прикрепляется к игроку **/
    private float attachedSegment;
    /** Процентное смещение фонов **/
    private float backgroundsOffset;
    /** Часть экрана, начиная с которой камера привязывается к игроку **/
    // не перемещать внутрь функции (функция зыполняется 60 раз в секунду в методе render)
    private final float proportion = 0.42f;
    /** Визуальная высота пола первого уровня **/
    private final int drawingFloorHeight = 90;

    // ***********************************************
    //                Инициализация
    // ***********************************************

    public Level1_Renderer(GameWorld world, int gameWidth, int gameHeight)
    {
        super(world, gameWidth, gameHeight);
        cameraAttached = false;
        backgroundsOffset = 0;
        attachedSegment = world.getLevelWidth() - SCREEN_WIDTH;

        initGameObjects();
        initWorldAssets();

        playerHp = AssetLoader.default18;
        skeletonHps = new ObjectMap<Skeleton, BitmapFont>();

        skeletonDeathPoints = new ObjectSet<SkeletonDeath>();
    }

    /**
     * Инициализация игровых объектов (мобы etc.)
     */
    private void initGameObjects()
    {
        initSkeletonsAssets();
    }

    /**
     * Инициализация asset'ов мира
     */
    private void initWorldAssets()
    {
        level_BG1 = AssetLoader.level_BG1;
        level_BG2 = AssetLoader.level_BG2;
        level_BG3 = AssetLoader.level_BG3;
        level_BG4 = AssetLoader.level_BG4;
        level_grassBack = AssetLoader.level_grassBack;
        level_floor = AssetLoader.level_floor;
        level_torch_animation = AssetLoader.torch_animation;
        level_cave = AssetLoader.level_cave;

        grassBackLoops = new TextureRegion[3];
        for (int i = 0; i < grassBackLoops.length; i++)
            grassBackLoops[i] = new TextureRegion(AssetLoader.level_grassBackLoop);

        grassForeLoops = new TextureRegion[4];
        for (int i = 0; i < grassForeLoops.length; i++)
            grassForeLoops[i] = new TextureRegion(AssetLoader.level_grassForeLoop);
    }

    // ***********************************************
    //                    Отрисовка
    // ***********************************************

    public void render(float runTime)
    {
        // Открепляем/прикрепляем камеру к персонажу и вычисляем смещение фонов ("параллакс")
        AttachCameraAndCalculateParallax();

        batcher.begin();

        // Прикрепляемся к неподвижной камере
        staticCam.update();
        batcher.setProjectionMatrix(staticCam.combined);

            // Отрисовка фонов уровня
            drawBackgrounds();


        // Прикрепляемся к камере, следующей за игроком
        camera.update();
        batcher.setProjectionMatrix(camera.combined);

            // Отрисовка уровня
            drawLevel(runTime);

            // Отрисовка игрока
            drawPlayer(runTime);

            drawSkeletons(runTime);


        batcher.end();
    }

    /**
     *  Привязка камеры к персонажу и вычисление смещения фонов
     */
    private void AttachCameraAndCalculateParallax()
    {
        // Если игрок не в начале и не в конце уровня
        if (player.model.getX() > SCREEN_WIDTH * proportion &&
                player.model.getX() < world.getLevelWidth() - SCREEN_WIDTH * (1 - proportion))
        {
            cameraAttached = true;
            camera.translate(player.model.getVelocityX() * Gdx.graphics.getDeltaTime(), 0);
            backgroundsOffset = (player.model.getX() - proportion * SCREEN_WIDTH) / attachedSegment;
        }
        // Если игрок в начале уровня
        else if (cameraAttached && player.model.getX() <= SCREEN_WIDTH * proportion)
        {
            cameraAttached = false;
            camera.position.set(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 0);
            backgroundsOffset = 0;
        }
        // Если игрок в конце уровня
        else if (cameraAttached)
        {
            cameraAttached = false;
            camera.position.set(world.getLevelWidth() - SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 0);
            backgroundsOffset = 1;
        }
    }

    /**
     *  Отрисовка фонов
     */
    private void drawBackgrounds()
    {
        batcher.draw(level_BG1, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        batcher.draw(level_BG2, -(SCREEN_WIDTH * 0.25f * backgroundsOffset), 0, SCREEN_WIDTH * 1.25f, SCREEN_HEIGHT * 1.1f);
        batcher.draw(level_BG3, -(SCREEN_WIDTH * 0.5f * backgroundsOffset), 0, SCREEN_WIDTH * 1.5f, SCREEN_HEIGHT * 1.1f);
        batcher.draw(level_BG4, -(SCREEN_WIDTH * backgroundsOffset), 0, SCREEN_WIDTH * 2, SCREEN_HEIGHT * 1.2f);
    }

    /**
     * Отрисовка (декоративных) графических элементов уровня
     */
    private void drawLevel(float runTime)
    {
        // задняя трава
        batcher.draw(level_grassBack, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        for (int i = 0; i < grassBackLoops.length; i++)
        {
            batcher.draw(grassBackLoops[i], SCREEN_WIDTH * (i + 1) , 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        }

        // пол
        batcher.draw(level_floor, 0, 25, world.getLevelWidth(), drawingFloorHeight);

        // пещера (скорее нора (скорее дыра))
        batcher.draw(level_cave, world.getLevelWidth() - 950, drawingFloorHeight - 2,
                level_cave.getRegionWidth() * 3, level_cave.getRegionHeight() * 3);
        batcher.draw((TextureRegion) level_torch_animation.getKeyFrame(runTime),
                world.getLevelWidth() - 1000,drawingFloorHeight + 30,
                GameTextureRegions.torch_width * 2, GameTextureRegions.torch_height * 2);
        batcher.draw((TextureRegion) level_torch_animation.getKeyFrame(runTime + 10),
                world.getLevelWidth() - 800,drawingFloorHeight + 30,
                GameTextureRegions.torch_width * 2, GameTextureRegions.torch_height * 2);

        // передняя трава
        for (int i = 0; i < grassForeLoops.length; i++)
        {
            batcher.draw(grassForeLoops[i],  SCREEN_WIDTH * i, -185, SCREEN_WIDTH, SCREEN_HEIGHT);
        }
    }

    @Override
    public void skeletonSpawned(Skeleton skeleton) {
        skeletonHps.put(skeleton, AssetLoader.default18);
    }

    @Override
    public void skeletonDead(Skeleton skeleton) {
        SkeletonDeath death = new SkeletonDeath(skeleton);
        skeletonDeathPoints.add(death);
        AssetLoader.load_NewSkeletonRemains(death);
    }

}
