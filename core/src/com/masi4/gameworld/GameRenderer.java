package com.masi4.gameworld;

/**
 * Created by OIEFIJM on 23.10.2017.
 *
 * Данный класс отвечает за отрисовку игровых объектов
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.masi4.Directions;
import com.masi4.gamehelpers.AssetLoader;
import com.masi4.gameobjects.Player;

import static com.masi4.gamehelpers.GameTextureRegions.player_attack_frame_Height;
import static com.masi4.gamehelpers.GameTextureRegions.player_attack_frame_Width;
import static com.masi4.gamehelpers.GameTextureRegions.player_default_frame_Height;
import static com.masi4.gamehelpers.GameTextureRegions.player_default_frame_Width;
import static com.masi4.myGame.GameMain.*;

public abstract class GameRenderer
{
    protected Player player;

    protected int gameWidth;
    protected int gameHeight;

    protected GameWorld world;
    protected OrthographicCamera camera;
    protected OrthographicCamera staticCam;
    protected SpriteBatch batcher;

    // DEBUG TODO: закоментить перед релизом
    protected ShapeRenderer shapeRenderer = new ShapeRenderer();

    private Animation
            player_animation,
            player_start_walking_animation,
            player_attack_animation;

    private TextureRegion
            player_standing;  // стоит на месте

    /** Время, прошедшее со старта анимации ходьбы **/
    private float elapsedWalkingTime;

    //
    // Методы
    //

    public GameRenderer(GameWorld world, int gameWidth, int gameHeight)
    {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.world = world;
        player = world.getPlayer();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
        staticCam = new OrthographicCamera();
        staticCam.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(camera.combined);

        initPlayerAssets();

        elapsedWalkingTime = 0;
    }

    /**
     * Инициализация asset'ов игрока
     */
    private void initPlayerAssets()
    {
        player_standing = AssetLoader.player_standing;
        player_start_walking_animation = AssetLoader.player_default_startsWalking_animation;
        player_animation = AssetLoader.player_default_animation;
        player_attack_animation = AssetLoader.player_default_attack_animation;
    }

    abstract public void render(float runTime);

    /**
     * Отрисовка игрока
     */
    protected void drawPlayer(float runTime)
    {
        // При отрисовке игрока учитывается следующий факт: размер кадра анимации, как правило,
        // отличается от размера хитбокса игрока. Поэтому кадр сдвигается так, чтобы его середина
        // и середина хитбокса совпадали.
        //
        // Вообще говоря, это нужно учитывать при отрисовке любых персонажей.

        switch (player.getCastingAbility())
        {
            //
            // Атака
            //
            case Player_MeleeSwordAttack:
                if (player.graphics.getTurnedSide() == Directions.RIGHT)
                {
                    batcher.draw((TextureRegion) player_attack_animation.getKeyFrame(player.getCastingElapsedTime()),
                            player.graphics.getX() - (player_attack_frame_Width - player.graphics.getWidth()) / 2,
                            player.graphics.getY(), player_attack_frame_Width, player_attack_frame_Height);
                }
                else
                {
                    batcher.draw((TextureRegion) player_attack_animation.getKeyFrame(player.getCastingElapsedTime()),
                            player.graphics.getX() + 174/2 - (player_attack_frame_Width - player.graphics.getWidth()) / 2,
                            player.graphics.getY(), -player_attack_frame_Width, player_attack_frame_Height);
                }
                break;

            //
            // Ходьба / стоит на месте
            // TODO: анимация ходьбы, только если нажаты кнопки A или D или джойстик
            case NULL_ABILITY:
                // Если идет вправо
                if (player.graphics.getVelocityX() > 0 && player.graphics.getControlsDirection() == Directions.RIGHT)
                {
                    elapsedWalkingTime += Gdx.graphics.getDeltaTime();
                    // начало шага
                    if (!player_start_walking_animation.isAnimationFinished(elapsedWalkingTime))
                    {
                        batcher.draw((TextureRegion) player_start_walking_animation.getKeyFrame(elapsedWalkingTime),
                                player.graphics.getX() - (player_default_frame_Width - player.graphics.getWidth()) / 2,
                                player.graphics.getY(), player_default_frame_Width, player_default_frame_Height);
                    }
                    else
                    {
                        batcher.draw((TextureRegion) player_animation.getKeyFrame(runTime),
                                player.graphics.getX() - (player_default_frame_Width - player.graphics.getWidth()) / 2,
                                player.graphics.getY(), player_default_frame_Width, player_default_frame_Height);
                    }
                }

                // Если идет влево
                else if (player.graphics.getVelocityX() < 0 && player.graphics.getControlsDirection() == Directions.LEFT)
                {
                    elapsedWalkingTime += Gdx.graphics.getDeltaTime();
                    // начало шага
                    if (!player_start_walking_animation.isAnimationFinished(elapsedWalkingTime))
                    {
                        batcher.draw((TextureRegion) player_start_walking_animation.getKeyFrame(elapsedWalkingTime),
                                player.graphics.getX() + player_default_frame_Width - (player_default_frame_Width - player.graphics.getWidth()) / 2,
                                player.graphics.getY(), -player_default_frame_Width, player_default_frame_Height);
                    }
                    else
                    {
                        batcher.draw((TextureRegion) player_animation.getKeyFrame(runTime),
                                player.graphics.getX() + player_default_frame_Width - (player_default_frame_Width - player.graphics.getWidth()) / 2,
                                player.graphics.getY(), -player_default_frame_Width, player_default_frame_Height);
                    }
                }

                // Если стоит на месте
                else
                {
                    if (player.graphics.getTurnedSide() == Directions.RIGHT)
                    {
                        batcher.draw(player_standing,
                                player.graphics.getX() - (player_default_frame_Width - player.graphics.getWidth()) / 2,
                                player.graphics.getY(), player_default_frame_Width, player_default_frame_Height);

                        elapsedWalkingTime = 0;
                    }
                    else
                    {
                        batcher.draw(player_standing,
                                player.graphics.getX() + player_default_frame_Width - (player_default_frame_Width - player.graphics.getWidth()) / 2,
                                player.graphics.getY(), -player_default_frame_Width, player_default_frame_Height);

                        elapsedWalkingTime = 0;
                    }
                }
                break;
        }


        // DEBUG: Отрисовка хитбокса игрока
        if (DEBUG)
        {
            Rectangle hitbox = player.graphics.getHitbox();

            batcher.end();
            camera.update();
            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

            shapeRenderer.setColor(Color.GREEN);
            shapeRenderer.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);

            shapeRenderer.end();
            batcher.begin();
        }


    }

}
