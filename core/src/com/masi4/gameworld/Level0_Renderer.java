package com.masi4.gameworld;

/**
 * Created by OIEFIJM on 19.12.2017.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import static com.masi4.myGame.GameMainClass.*;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.masi4.gamehelpers.AssetLoader;
import com.masi4.gamehelpers.GameTextureRegions;
import com.masi4.gameobjects.Player;

public class Level0_Renderer extends GameRenderer
{
    // DEBUG TODO: закоментить перед релизом
    ShapeRenderer shapeRenderer = new ShapeRenderer();

    // Objects
    private Player player;

    // Assets
    private Animation
            player_animation,
            player_startWalking_animation,
            player_attack_animation,
            level_torch_animanion; // TODO: typo
    private TextureRegion
            player_standing,  // стоит на месте
            level_BG1,
            level_BG2,
            level_BG3,
            level_BG4,
            level_grassBack,
            level_floor,
            level_cave;
    private TextureRegion[] grassBackLoops, grassForeLoops;

    /** Время, прошедшее со старта анимации ходьбы **/
    private float elapsedWalkingTime;
    /** Время, прошедшее со старта анимации атаки **/
    private float elapsedAttackTime;

    /** Прикреплена ли камера к игроку **/
    private boolean cameraAttached;
    /** Длина отрезка, на котором камера прикрепляется к игроку **/
    private float attachedSegment;
    /** Процентное смещение фонов **/
    private float backgroundsOffset;
    /** часть экрана, начиная с которой камера привязывается к игроку **/
    // не перемещать внутрь функции (функция зыполняется 60 раз в секунду в методе render)
    private final float proportion = 0.42f;

    // ***********************************************
    //                Инициализация
    // ***********************************************

    public Level0_Renderer(GameWorld world, int gameWidth, int gameHeight)
    {
        super(world, gameWidth, gameHeight);
        cameraAttached = false;
        backgroundsOffset = 0;
        attachedSegment = world.getLevelWidth() - SCREEN_WIDTH;

        elapsedWalkingTime = 0;
        elapsedAttackTime = 0;

        initGameObjects();
        initAssets();
    }

    /**
     * Инициализация игровых объектов (игрок, мобы etc.)
     */
    private void initGameObjects()
    {
        player = world.getPlayer();
    }

    /**
     * Инициализация asset'ов
     */
    private void initAssets()
    {
        initWorldAssets();
        initPlayerAssets();
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
        level_torch_animanion = AssetLoader.torch_animation;
        level_cave = AssetLoader.level_cave;

        grassBackLoops = new TextureRegion[3];
        for (int i = 0; i < grassBackLoops.length; i++)
            grassBackLoops[i] = new TextureRegion(AssetLoader.level_grassBackLoop);

        grassForeLoops = new TextureRegion[4];
        for (int i = 0; i < grassForeLoops.length; i++)
            grassForeLoops[i] = new TextureRegion(AssetLoader.level_grassForeLoop);
    }

    /**
     * Инициализация asset'ов игрока
     */
    private void initPlayerAssets()
    {
        player_standing = AssetLoader.player_standing;
        player_startWalking_animation = AssetLoader.player_default_startsWalking_animation;
        player_animation = AssetLoader.player_default_animation;
        player_attack_animation = AssetLoader.player_attack_animation;
    }

    // ***********************************************
    //                    Отрисовка
    // ***********************************************

    public void render(float runTime)
    {
        // Чистим вилкой
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
            drawLevel();

            // Отрисовка игрока
            drawPlayer(runTime);


        batcher.end();
    }

    /**
     *  Привязка камеры к персонажу и вычисление смещения фонов
     */
    private void AttachCameraAndCalculateParallax()
    {
        // Если игрок не в начале и не в конце уровня
        if (player.graphics.getX() > SCREEN_WIDTH * proportion &&
                player.graphics.getX() < world.getLevelWidth() - SCREEN_WIDTH * (1 - proportion))
        {
            cameraAttached = true;
            camera.translate(player.graphics.getSpeedX() * player.graphics.getDelta(), 0);
            backgroundsOffset = (player.graphics.getX() - proportion * SCREEN_WIDTH) / attachedSegment;
        }
        // Если игрок в начале уровня
        else if (cameraAttached && player.graphics.getX() <= SCREEN_WIDTH * proportion)
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
        batcher.draw(level_BG2, -(SCREEN_WIDTH * 0.25f * backgroundsOffset), 0, SCREEN_WIDTH * 1.25f, SCREEN_HEIGHT * 1.05f);
        batcher.draw(level_BG3, -(SCREEN_WIDTH * 0.5f * backgroundsOffset), 0, SCREEN_WIDTH * 1.5f, SCREEN_HEIGHT);
        batcher.draw(level_BG4, -(SCREEN_WIDTH * backgroundsOffset), 0, SCREEN_WIDTH * 2, SCREEN_HEIGHT * 1.1f);
    }

    /**
     * Отрисовка (декоративных) графических элементов уровня
     */
    private void drawLevel()
    {
        // задняя трава
        batcher.draw(level_grassBack, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        for (int i = 0; i < grassBackLoops.length; i++)
        {
            batcher.draw(grassBackLoops[i], SCREEN_WIDTH * (i + 1) , 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        }

        // пол
        batcher.draw(level_floor, 0, 25, world.getLevelWidth(), world.getLevelFloorHeight());

        // пещера (скорее нора (скорее дыра))
        batcher.draw(level_cave, world.getLevelWidth()-950,world.getLevelFloorHeight()-2, level_cave.getRegionWidth()*3, level_cave.getRegionHeight()*3);
        batcher.draw((TextureRegion) level_torch_animanion.getKeyFrame(runTime), world.getLevelWidth()-1000,world.getLevelFloorHeight()+30, GameTextureRegions.torch_width*2, GameTextureRegions.torch_height*2);
        batcher.draw((TextureRegion) level_torch_animanion.getKeyFrame(runTime+10), world.getLevelWidth()-800,world.getLevelFloorHeight()+30, GameTextureRegions.torch_width*2, GameTextureRegions.torch_height*2);

        // передняя трава
        for (int i = 0; i < grassForeLoops.length; i++)
        {
            batcher.draw(grassForeLoops[i],  SCREEN_WIDTH * i, -185, SCREEN_WIDTH, SCREEN_HEIGHT);
        }
    }

    /**
     * Отрисовка игрока
     */
    private void drawPlayer(float runTime)
    {
        //
        // Атака
        //
        if (player_attack_animation.isAnimationFinished(elapsedAttackTime))
        {
            player.graphics.SetAttack(false);
            elapsedAttackTime = 0;
        }
        else if(player.graphics.isAttacking())
        {
            elapsedAttackTime += Gdx.graphics.getDeltaTime();
            if (player.graphics.isTurnedRight())
            {
                batcher.draw((TextureRegion) player_attack_animation.getKeyFrame(elapsedAttackTime),
                        player.graphics.getX(), player.graphics.getY(), 174, 128);
            }
            else
            {
                batcher.draw((TextureRegion) player_attack_animation.getKeyFrame(elapsedAttackTime),
                        player.graphics.getX() + 174/2, player.graphics.getY(), -174, 128);
            }
        }

        //
        // Ходьба
        //

        // TODO: если остановился, elapsedTime = 0. Переименовать в elapsedWalkingTime
        // Если идет вправо
        if (player.graphics.getSpeedX() > 0)
        {
            elapsedWalkingTime += Gdx.graphics.getDeltaTime();
            // начало шага
            if (!player_startWalking_animation.isAnimationFinished(elapsedWalkingTime))
            {
                batcher.draw((TextureRegion) player_startWalking_animation.getKeyFrame(elapsedWalkingTime), player.graphics.getX(),
                        player.graphics.getY(), (float) player.graphics.getWidth(), (float) player.graphics.getHeight());
            }
            else
            {
                batcher.draw((TextureRegion) player_animation.getKeyFrame(runTime), player.graphics.getX(),
                        player.graphics.getY(), (float) player.graphics.getWidth(), (float) player.graphics.getHeight());
            }
            if (!player.graphics.isTurnedRight())
                player.graphics.turnRight();
            else {}
        }

        // Если идет влево
        else if (player.graphics.getSpeedX() < 0)
        {
            elapsedWalkingTime += Gdx.graphics.getDeltaTime();
            // начало шага
            if (!player_startWalking_animation.isAnimationFinished(elapsedWalkingTime))
            {
                batcher.draw((TextureRegion) player_startWalking_animation.getKeyFrame(elapsedWalkingTime),
                        player.graphics.getX() + player.graphics.getWidth(), player.graphics.getY(),
                        -(float) player.graphics.getWidth(), (float) player.graphics.getHeight());
            }
            else
            {
                batcher.draw((TextureRegion) player_animation.getKeyFrame(runTime),
                        player.graphics.getX() + player.graphics.getWidth(), player.graphics.getY(),
                        -(float) player.graphics.getWidth(), (float) player.graphics.getHeight());
            }
            if (player.graphics.isTurnedRight()) player.graphics.turnLeft();
            else {}
        }

        // Если стоит на месте
        else if (player.graphics.getSpeedX() == 0 && !player.graphics.isAttacking())
        {
            if (player.graphics.isTurnedRight())
            {
                batcher.draw(player_standing, player.graphics.getX(), player.graphics.getY(),
                        (float) player.graphics.getWidth(), (float) player.graphics.getHeight());
                elapsedWalkingTime = 0;
            }
            else
            {
                batcher.draw(player_standing, player.graphics.getX() + player.graphics.getWidth(),
                        player.graphics.getY(), -(float) player.graphics.getWidth(),
                        (float) player.graphics.getHeight());
                elapsedWalkingTime = 0;
            }
        }

        // DEBUG: Отрисовка хитбокса игрока
        if (DEBUG)
        {
            Rectangle hitbox = player.rpg.getHitbox();

            batcher.end();
            camera.update();
            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

                shapeRenderer.setColor(Color.RED);
                shapeRenderer.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);

            shapeRenderer.end();
            batcher.begin();
        }


    }
}
