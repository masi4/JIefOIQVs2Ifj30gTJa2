package com.masi4.gameworld;

/**
 * Created by OIEFIJM on 19.12.2017.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import static com.masi4.myGame.GameMainClass.*;

import static com.masi4.gamehelpers.GameTextureRegions.*;
import com.masi4.gamehelpers.AssetLoader;
import com.masi4.gameobjects.Player;


public class Level0_Renderer extends GameRenderer
{
    // Objects
    private Player player;

    // Assets
    private Animation
            player_animation,
            player_startWalking_animation; // TODO: убрать подергивание, подкорректировать кадры, возможно убрать лишний кадр (8 ?)
    private TextureRegion
            player_standing,  // повернут вправо
            level_BG1,
            level_BG2,
            level_BG3,
            level_BG4,
            level_grassBack,
            level_floor;

    // 1) возможно сделать vector или list
    // 2) возможно будет работать и без массива, а возможно он отрисовывает в трех местах и типо мелькает
    private TextureRegion[] grassBackLoops, grassForeLoops;

    // Misc
    private boolean cameraAttached;
    private final float proportion = 0.42f;  // часть экрана, начиная с которой камера привязывается к персонажу
    private float attachedSegment;  // Длина отрезка, на котором камера прикрепляется к персонажу
    private float parallax;  // процентное смещение фонов
    // возможно внести в player
    private boolean playerTurnedRight;  // Повернут ли персонаж вправо

    //
    // Методы
    //

    private void initGameObjects()
    {
        player = world.getPlayer();
    }

    private void initAssets()
    {
        level_BG1 = AssetLoader.level_BG1;
        level_BG2 = AssetLoader.level_BG2;
        level_BG3 = AssetLoader.level_BG3;
        level_BG4 = AssetLoader.level_BG4;
        level_grassBack = AssetLoader.level_grassBack;
        level_floor = AssetLoader.level_floor;

        grassBackLoops = new TextureRegion[3];
        for (int i = 0; i < grassBackLoops.length; i++)
            grassBackLoops[i] = new TextureRegion(AssetLoader.level_grassBackLoop);

        grassForeLoops = new TextureRegion[4];
        for (int i = 0; i < grassForeLoops.length; i++)
            grassForeLoops[i] = new TextureRegion(AssetLoader.level_grassForeLoop);

        player_standing = AssetLoader.player_standing;
        player_startWalking_animation = AssetLoader.player_default_startsWalking_animation;
        player_animation = AssetLoader.player_default_animation;
    }

    public Level0_Renderer(GameWorld world, int gameWidth, int gameHeight)
    {
        super(world, gameWidth, gameHeight);
        cameraAttached = false;
        parallax = 0;
        attachedSegment = world.getLevelWidth() - SCREEN_WIDTH;
        playerTurnedRight = true;

        initGameObjects();
        initAssets();
    }
    private float elapsedTime = 0;
    public void render(float runTime)
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // привязка камеры к персонажу
        // TODO: убрать рывки
        if (player.getX() > SCREEN_WIDTH * proportion &&
                player.getX() < world.getLevelWidth() - SCREEN_WIDTH * (1 - proportion))
        {
            cameraAttached = true;
            camera.translate(player.getSpeedX() * player.getDelta(), 0);
            parallax = (player.getX() - proportion * SCREEN_WIDTH) / attachedSegment;
        }
        else if (cameraAttached && player.getX() <= SCREEN_WIDTH * proportion)  // Вошел обратно в начало уровня
        {
            cameraAttached = false;
            camera.position.set(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 0);
            parallax = 0;
        }
        else if (cameraAttached)  // Дошёл до конца уровня
        {
            cameraAttached = false;
            camera.position.set(world.getLevelWidth() - SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 0);
            parallax = 1;
        }
        camera.update();
        batcher.setProjectionMatrix(staticCam.combined);

        batcher.begin();
            //
            // Отрисовка фонов и GUI
            //

            // TODO: параллакс
            batcher.draw(level_BG1, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
            batcher.draw(level_BG2, -(SCREEN_WIDTH * 0.25f * parallax), 0, SCREEN_WIDTH * 1.25f, SCREEN_HEIGHT * 1.05f);
            batcher.draw(level_BG3, -(SCREEN_WIDTH * 0.5f * parallax), 0, SCREEN_WIDTH * 1.5f, SCREEN_HEIGHT);
            batcher.draw(level_BG4, -(SCREEN_WIDTH * parallax), 0, SCREEN_WIDTH * 2, SCREEN_HEIGHT * 1.1f);

            //
            // Отрисовка мира
            //
            batcher.setProjectionMatrix(camera.combined);
            // задняя трава
            batcher.draw(level_grassBack, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
            for (int i = 0; i < grassBackLoops.length; i++)
            {
                batcher.draw(grassBackLoops[i], SCREEN_WIDTH * (i + 1) , 0, SCREEN_WIDTH, SCREEN_HEIGHT);
            }
            // пол
            batcher.draw(level_floor, 0, 25, world.getLevelWidth(), world.getLevelFloorHeight());
            // передняя трава
            for (int i = 0; i < grassForeLoops.length; i++)
            {
                batcher.draw(grassForeLoops[i],  SCREEN_WIDTH * i, -185, SCREEN_WIDTH, SCREEN_HEIGHT);
            }

            if (player.getSpeedX() > 0)
            {
                elapsedTime += Gdx.graphics.getDeltaTime();
                if(!player_startWalking_animation.isAnimationFinished(elapsedTime)) {                  //Начало шага
                    batcher.draw((TextureRegion) player_startWalking_animation.getKeyFrame(elapsedTime), player.getX(), player.getY(), (float) player.getWidth(), (float) player.getHeight());
                }

                else {
                    batcher.draw((TextureRegion) player_animation.getKeyFrame(runTime), player.getX(), player.getY(), (float) player.getWidth(), (float) player.getHeight());
                }
                if (!playerTurnedRight) playerTurnedRight = true;
                else {}
            }
            else if (player.getSpeedX() < 0)
            {
                elapsedTime += Gdx.graphics.getDeltaTime();
                if(!player_startWalking_animation.isAnimationFinished(elapsedTime)) {                  //Начало шага
                    batcher.draw((TextureRegion) player_startWalking_animation.getKeyFrame(elapsedTime), player.getX() + player.getWidth(), player.getY(), -(float) player.getWidth(), (float) player.getHeight());
                }
                else{
                    batcher.draw((TextureRegion) player_animation.getKeyFrame(runTime), player.getX() + player.getWidth(),player.getY(), -(float) player.getWidth(), (float) player.getHeight());
                }
                if (playerTurnedRight) playerTurnedRight = false;
                else {}
            }
            else
                if (playerTurnedRight) {
                    batcher.draw(player_standing, player.getX(), player.getY(),  (float) player.getWidth(), (float) player.getHeight());
                    elapsedTime=0;
                }
                else {
                    batcher.draw(player_standing, player.getX() + player.getWidth(), player.getY(), -(float)player.getWidth(), (float)player.getHeight());
                    elapsedTime=0;
                }


        batcher.end();





    }
}
