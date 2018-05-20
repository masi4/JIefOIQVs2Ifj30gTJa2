package com.masi4.gameworld.renderers;

/**
 * Created by OIEFIJM on 23.10.2017.
 *
 * Данный класс отвечает за отрисовку игровых объектов
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;
import com.masi4.Abilities.AbilityName;
import com.masi4.gamehelpers.helpers.Directions;
import com.masi4.gamehelpers.resourceHandlers.AssetLoader;
import com.masi4.gameworld.SkeletonDeath;
import com.masi4.gamehelpers.GameCharactersDefaults;
import com.masi4.gameobjects.objects.Player;
import com.masi4.gameobjects.objects.Skeleton;
import com.masi4.gameworld.GameWorld;

import static com.masi4.gamehelpers.GameTextureRegions.player_attack_frame_Height;
import static com.masi4.gamehelpers.GameTextureRegions.player_attack_frame_Width;
import static com.masi4.gamehelpers.GameTextureRegions.player_default_frame_Height;
import static com.masi4.gamehelpers.GameTextureRegions.player_default_frame_Width;
import static com.masi4.gamehelpers.GameTextureRegions.skeleton_frame_Height;
import static com.masi4.gamehelpers.GameTextureRegions.skeleton_frame_Width;
import static com.masi4.myGame.GameMain.*;

public abstract class GameRenderer
{
    // TODO: заменить на что то более красивое
    // Здоровье игрока и скелетов
    protected BitmapFont playerHp;
    protected ObjectMap<Skeleton, BitmapFont> skeletonHps;

    protected Player player;
    protected ObjectSet<Skeleton> skeletons;
    protected ObjectSet<SkeletonDeath> skeletonDeathPoints;

    protected int gameWidth;
    protected int gameHeight;

    protected GameWorld world;
    protected OrthographicCamera camera;
    protected OrthographicCamera staticCam;
    protected SpriteBatch batcher;

    // TODO: сделать нормальным образом
    protected float skeletonWidthMult = GameCharactersDefaults.SKELETON_DEFAULT_WIDTH / 30f;
    protected float skeletonHeightMult = GameCharactersDefaults.SKELETON_DEFAULT_HEIGHT / 60f;

    // DEBUG TODO: закоментить перед релизом
    protected ShapeRenderer shapeRenderer = new ShapeRenderer();

    private Animation
            // Игрок
            player_walking_animation,
            player_start_walking_animation,
            player_melee_sword_attack_animation;

    private TextureRegion
            player_standing;  // стоит на месте

    protected ObjectMap<Skeleton, Animation>
            // Скелетоны
            skeleton_walking_animations,
            skeleton_start_walking_animation,
            skeleton_melee_sword_attack_animations;

    protected ObjectMap<Skeleton, TextureRegion>
            skeletons_standing;

    /** Время, прошедшее со старта анимации ходьбы **/
    private float playerElapsedWalkingTime;

    //
    // Методы
    //

    public GameRenderer(GameWorld world, int gameWidth, int gameHeight)
    {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.world = world;
        player = world.getPlayer();
        skeletons = world.getSkeletons();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
        staticCam = new OrthographicCamera();
        staticCam.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(camera.combined);

        initPlayerAssets();

        playerElapsedWalkingTime = 0;
    }

    /**
     * Инициализация asset'ов игрока
     */
    protected void initPlayerAssets()
    {
        player_standing = AssetLoader.player_standing;
        player_start_walking_animation = AssetLoader.player_default_start_walking_animation;
        player_walking_animation = AssetLoader.player_default_animation;
        player_melee_sword_attack_animation = AssetLoader.player_default_attack_animation;
    }

    protected void initSkeletonsAssets()
    {
        skeletons_standing = AssetLoader.skeletons_standing;
        skeleton_walking_animations = AssetLoader.skeleton_walking_animations;
        skeleton_start_walking_animation = AssetLoader.skeleton_start_walking_animation;
        skeleton_melee_sword_attack_animations = AssetLoader.skeleton_melee_sword_attack_animations;
    }

    abstract public void render(float runTime);

    /**
     * Отрисовка игрока
     */
    //TODO: Может лучше хранить рендер каждого персонажа в классе этого персонажа?
    protected void drawPlayer(float runTime)
    {
        if (!player.isDead())
        {
            playerHp.draw(batcher, Integer.toString(player.getCurrentHP()),
                    // (playerWidth - playerHpTextWidth) / 2
                    player.model.getX() + (player.model.getWidth() - playerHp.getSpaceWidth() * Integer.toString(player.getCurrentHP()).length()) / 2f,
                    player.model.getY() + player.model.getHeight() + 21
            );

            // TODO: Скорость анимации ходьбы зависит от скорости персонажа
        /*
        if (Math.abs(player.model.getVelocityX()) > 30) {
            player_start_walking_animation.setFrameDuration(
                    300f / Math.abs(player.model.getVelocityX()) * 0.06f
            );

            player_walking_animation.setFrameDuration(
                    300f / Math.abs(player.model.getVelocityX()) * 0.06f
            );
        }
        else {
            player_start_walking_animation.setFrameDuration(
                    300f / 30 * 0.06f
            );

            player_walking_animation.setFrameDuration(
                    300f / 30 * 0.06f
            );
        }
        */

            // Скорость анимации атаки зависит от времени каста данной абилки
            player_melee_sword_attack_animation.setFrameDuration(
                    player.getAbilityCastTime(AbilityName.Player_MeleeSwordAttack) / player_melee_sword_attack_animation.getKeyFrames().length
            );

            // При отрисовке игрока учитывается следующий факт: размер кадра анимации, как правило,
            // отличается от размера хитбокса игрока. Поэтому кадр сдвигается так, чтобы его середина
            // и середина хитбокса совпадали.
            //
            // Вообще говоря, это нужно учитывать при отрисовке любых персонажей.

            switch (player.getCastingAbilityName())
            {
                //
                // Атака
                //
                case Player_MeleeSwordAttack:
                    if (player.model.getTurnedSide() == Directions.RIGHT)
                    {
                        batcher.draw((TextureRegion) player_melee_sword_attack_animation.getKeyFrame(player.getCastingElapsedTime()),
                                player.model.getX() - (player_attack_frame_Width - player.model.getWidth()) / 2f,
                                player.model.getY(), player_attack_frame_Width, player_attack_frame_Height);
                    }
                    else
                    {
                        batcher.draw((TextureRegion) player_melee_sword_attack_animation.getKeyFrame(player.getCastingElapsedTime()),
                                player.model.getX() + player_attack_frame_Width - (player_attack_frame_Width - player.model.getWidth()) / 2f,
                                player.model.getY(), -player_attack_frame_Width, player_attack_frame_Height);
                    }
                    break;

                //
                // Ходьба / стоит на месте
                // TODO: анимация ходьбы, только если нажаты кнопки A или D или джойстик
                case NONE:
                    // Если идет вправо
                    if (player.model.getControlsDirection() == Directions.RIGHT)
                    {
                        playerElapsedWalkingTime += Gdx.graphics.getDeltaTime();
                        // начало шага
                        if (!player_start_walking_animation.isAnimationFinished(playerElapsedWalkingTime))
                        {
                            batcher.draw((TextureRegion) player_start_walking_animation.getKeyFrame(playerElapsedWalkingTime),
                                    player.model.getX() - (player_default_frame_Width - player.model.getWidth()) / 2f,
                                    player.model.getY(), player_default_frame_Width, player_default_frame_Height);
                        }
                        else
                        {
                            batcher.draw((TextureRegion) player_walking_animation.getKeyFrame(runTime),
                                    player.model.getX() - (player_default_frame_Width - player.model.getWidth()) / 2f,
                                    player.model.getY(), player_default_frame_Width, player_default_frame_Height);
                        }
                    }

                    // Если идет влево
                    else if (player.model.getControlsDirection() == Directions.LEFT)
                    {
                        playerElapsedWalkingTime += Gdx.graphics.getDeltaTime();
                        // начало шага
                        if (!player_start_walking_animation.isAnimationFinished(playerElapsedWalkingTime))
                        {
                            batcher.draw((TextureRegion) player_start_walking_animation.getKeyFrame(playerElapsedWalkingTime),
                                    player.model.getX() + player_default_frame_Width - (player_default_frame_Width - player.model.getWidth()) / 2,
                                    player.model.getY(), -player_default_frame_Width, player_default_frame_Height);
                        }
                        else
                        {
                            batcher.draw((TextureRegion) player_walking_animation.getKeyFrame(runTime),
                                    player.model.getX() + player_default_frame_Width - (player_default_frame_Width - player.model.getWidth()) / 2,
                                    player.model.getY(), -player_default_frame_Width, player_default_frame_Height);
                        }
                    }

                    // Если стоит на месте
                    else
                    {
                        playerElapsedWalkingTime = 0;
                        ///////// Можно вставить заревершенную анимацию player_start_walking
                        if (player.model.getTurnedSide() == Directions.RIGHT)
                            batcher.draw(
                                    player_standing,
                                    player.model.getX() - (player_default_frame_Width - player.model.getWidth()) / 2,
                                    player.model.getY(), player_default_frame_Width, player_default_frame_Height);

                        else
                            batcher.draw(player_standing,
                                    player.model.getX() + player_default_frame_Width - (player_default_frame_Width - player.model.getWidth()) / 2,
                                    player.model.getY(), -player_default_frame_Width, player_default_frame_Height);


                    }
                    break;

            }

            // DEBUG: Отрисовка хитбоксов игрока и его атаки
            if (DEBUG)
            {
                batcher.end();
                //***************//

                camera.update();
                shapeRenderer.setProjectionMatrix(camera.combined);
                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

                // Хитбокс игрока
                shapeRenderer.setColor(Color.GREEN);
                shapeRenderer.rect(
                        player.model.getHitbox().x,
                        player.model.getHitbox().y,
                        player.model.getHitbox().width,
                        player.model.getHitbox().height
                );

                // Края хитбоксов атаки
                shapeRenderer.setColor(1, 0, 0, 0);
                if (player.getCastingAbilityName() == AbilityName.Player_MeleeSwordAttack) {
                    for (Rectangle hitbox : player.getActingHitboxes())
                        if (hitbox != null) {
                            shapeRenderer.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);

                            shapeRenderer.rect(
                                    player.getActingBoundingRec().x,
                                    player.getActingBoundingRec().y,
                                    player.getActingBoundingRec().width,
                                    player.getActingBoundingRec().height
                            );
                        }
                }

                shapeRenderer.end();

                // Хитбокс атаки игрока
                Gdx.gl.glEnable(GL30.GL_BLEND);
                Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

                shapeRenderer.setColor(0.6f, 0, 0, 0.4f);
                if (player.getCastingAbilityName() == AbilityName.Player_MeleeSwordAttack)
                    for (Rectangle hitbox : player.getActingHitboxes())
                        if (hitbox != null)
                            shapeRenderer.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);

                shapeRenderer.end();
                Gdx.gl.glDisable(GL30.GL_BLEND);

                //***************//
                batcher.begin();
            }
        }
        //else { draw(mogila);}
    }

    protected void drawSkeletons(float runTime)
    {
        // region Отрисовка скелетов
        for (Skeleton skeleton : skeletons) {
            skeletonWidthMult = skeleton.model.getWidth() / 30f;
            skeletonHeightMult = skeleton.model.getHeight() / 60f;

            if (!skeleton.isDead())
            {
                // здоровье скелета
                skeletonHps.get(skeleton).draw(
                        batcher,
                        Integer.toString(skeleton.getCurrentHP()),
                        // (skeletonWidth - skeletonHpTextWidth) / 2
                        skeleton.model.getX() + (skeleton.model.getWidth() - skeletonHps.get(skeleton).getSpaceWidth() * Integer.toString(skeleton.getCurrentHP()).length()) / 2f,
                        skeleton.model.getY() + skeleton.model.getHeight() + 15 * skeletonHeightMult
                );

                skeleton_melee_sword_attack_animations.get(skeleton).setFrameDuration(
                        skeleton.getAbilityCastTime(AbilityName.Skeleton_MeleeSwordAttack) /
                                skeleton_melee_sword_attack_animations.get(skeleton).getKeyFrames().length
                );

                switch (skeleton.getCastingAbilityName()) {
                    // Атака
                    case Skeleton_MeleeSwordAttack:
                        if (skeleton.model.getTurnedSide() == Directions.RIGHT) {
                            batcher.draw(
                                    (TextureRegion) skeleton_melee_sword_attack_animations.get(skeleton).getKeyFrame(skeleton.getCastingElapsedTime()),
                                    skeleton.model.getX() + skeleton_frame_Width * skeletonWidthMult - (skeleton_frame_Width * skeletonWidthMult - skeleton.model.getWidth()) / 2f,
                                    skeleton.model.getY(),
                                    -skeleton_frame_Width * skeletonWidthMult,
                                    skeleton_frame_Height * skeletonHeightMult
                            );
                        } else {
                            batcher.draw(
                                    (TextureRegion) skeleton_melee_sword_attack_animations.get(skeleton).getKeyFrame(skeleton.getCastingElapsedTime()),
                                    skeleton.model.getX() - (skeleton_frame_Width * skeletonWidthMult - skeleton.model.getWidth()) / 2f,
                                    skeleton.model.getY(),
                                    skeleton_frame_Width * skeletonWidthMult,
                                    skeleton_frame_Height * skeletonHeightMult
                            );
                        }
                        break;

                    // Ходьба
                    case NONE:
                        // если идет вправо
                        if (skeleton.model.getControlsDirection() == Directions.RIGHT) {
                            skeleton.model.elapsedWalkingTime += Gdx.graphics.getDeltaTime();
                            if (!skeleton_start_walking_animation.get(skeleton).isAnimationFinished(skeleton.model.elapsedWalkingTime ))
                            {
                                batcher.draw(
                                        (TextureRegion) skeleton_start_walking_animation.get(skeleton).getKeyFrame(skeleton.model.elapsedWalkingTime),
                                        skeleton.model.getX() + skeleton_frame_Width * skeletonWidthMult - (skeleton_frame_Width * skeletonWidthMult - skeleton.model.getWidth()) / 2f,
                                        skeleton.model.getY(),
                                        -skeleton_frame_Width * skeletonWidthMult,
                                        skeleton_frame_Height * skeletonHeightMult
                                );
                            }
                                else{
                                batcher.draw(
                                        (TextureRegion) skeleton_walking_animations.get(skeleton).getKeyFrame(skeleton.model.elapsedWalkingTime),
                                        skeleton.model.getX() + skeleton_frame_Width * skeletonWidthMult - (skeleton_frame_Width * skeletonWidthMult - skeleton.model.getWidth()) / 2f,
                                        skeleton.model.getY(),
                                        -skeleton_frame_Width * skeletonWidthMult,
                                        skeleton_frame_Height * skeletonHeightMult
                                );
                            }
                        }
                        // если идет влево
                        else if (skeleton.model.getControlsDirection() == Directions.LEFT) {
                            skeleton.model.elapsedWalkingTime += Gdx.graphics.getDeltaTime();
                            if (!skeleton_start_walking_animation.get(skeleton).isAnimationFinished(skeleton.model.elapsedWalkingTime )) {
                                batcher.draw(
                                        (TextureRegion) skeleton_start_walking_animation.get(skeleton).getKeyFrame(skeleton.model.elapsedWalkingTime),
                                        skeleton.model.getX() - (skeleton_frame_Width * skeletonWidthMult - skeleton.model.getWidth()) / 2f,
                                        skeleton.model.getY(),
                                        skeleton_frame_Width * skeletonWidthMult,
                                        skeleton_frame_Height * skeletonHeightMult
                                );
                            }
                            else{
                                batcher.draw(
                                        (TextureRegion) skeleton_walking_animations.get(skeleton).getKeyFrame(skeleton.model.elapsedWalkingTime),
                                        skeleton.model.getX() - (skeleton_frame_Width * skeletonWidthMult - skeleton.model.getWidth()) / 2f,
                                        skeleton.model.getY(),
                                        skeleton_frame_Width * skeletonWidthMult,
                                        skeleton_frame_Height * skeletonHeightMult
                                );
                            }
                        }
                        // если стоит на месте
                        else {
                            skeleton.model.elapsedWalkingTime = 0;

                            if (skeleton.model.getTurnedSide() == Directions.RIGHT)
                                batcher.draw(
                                        skeletons_standing.get(skeleton),
                                        skeleton.model.getX() + skeleton_frame_Width * skeletonWidthMult - (skeleton_frame_Width * skeletonWidthMult - skeleton.model.getWidth()) / 2f,
                                        skeleton.model.getY(),
                                        -skeleton_frame_Width * skeletonWidthMult,
                                        skeleton_frame_Height * skeletonHeightMult
                                );
                            else
                                batcher.draw(
                                        skeletons_standing.get(skeleton),
                                        skeleton.model.getX() - (skeleton_frame_Width * skeletonWidthMult - skeleton.model.getWidth()) / 2f,
                                        skeleton.model.getY(),
                                        skeleton_frame_Width * skeletonWidthMult,
                                        skeleton_frame_Height * skeletonHeightMult
                                );
                        }
                        break;

                }
            }


            //region Хитбоксы атаки
            if (DEBUG)
            {
                batcher.end();
                //***************//

                camera.update();
                shapeRenderer.setProjectionMatrix(camera.combined);
                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

                // Хитбокс игрока
                shapeRenderer.setColor(Color.GREEN);
                shapeRenderer.rect(
                        skeleton.model.getHitbox().x,
                        skeleton.model.getHitbox().y,
                        skeleton.model.getHitbox().width,
                        skeleton.model.getHitbox().height
                );

                // Края хитбоксов атаки
                shapeRenderer.setColor(1, 0, 0, 0);
                if (skeleton.getActingAbilityName() == AbilityName.Skeleton_MeleeSwordAttack)
                    for (Rectangle hitbox : skeleton.getActingHitboxes()) {
                        shapeRenderer.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);

                        shapeRenderer.rect(
                                skeleton.getActingBoundingRec().x,
                                skeleton.getActingBoundingRec().y,
                                skeleton.getActingBoundingRec().width,
                                skeleton.getActingBoundingRec().height
                        );
                    }

                shapeRenderer.end();

                // Хитбокс атаки
                Gdx.gl.glEnable(GL30.GL_BLEND);
                Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

                shapeRenderer.setColor(0.6f, 0, 0, 0.4f);
                if (skeleton.getActingAbilityName() == AbilityName.Skeleton_MeleeSwordAttack)
                    for (Rectangle hitbox : skeleton.getActingHitboxes())
                        if (hitbox != null)
                            shapeRenderer.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);

                shapeRenderer.end();
                Gdx.gl.glDisable(GL30.GL_BLEND);

                //***************//
                batcher.begin();
            }
            //endregion
        }
        //endregion

        // region Отрисовка останков скелетов

        for (SkeletonDeath point : skeletonDeathPoints)
        {
            if (!point.isAnimationRemoved && !point.skeletonDeathAnimation.isAnimationFinished(point.elapsedTime))
            {
                point.elapsedTime += Gdx.graphics.getDeltaTime();
                if (point.turnedSide == Directions.RIGHT)
                    batcher.draw(
                        (TextureRegion) point.skeletonDeathAnimation.getKeyFrame(point.elapsedTime),
                        point.x + skeleton_frame_Width * point.skeletonWidthMult - (skeleton_frame_Width * point.skeletonWidthMult - point.skeletonWidth) / 2f,
                        point.y,
                        -skeleton_frame_Width * point.skeletonWidthMult,
                        skeleton_frame_Height * point.skeletonHeightMult
                    );
                else
                    batcher.draw(
                            (TextureRegion) point.skeletonDeathAnimation.getKeyFrame(point.elapsedTime),
                            point.x - (skeleton_frame_Width * point.skeletonWidthMult - point.skeletonWidth) / 2f,
                            point.y,
                            skeleton_frame_Width * point.skeletonWidthMult,
                            skeleton_frame_Height * point.skeletonHeightMult
                    );
            }
            else {
                if (!point.isAnimationRemoved) {
                    point.skeletonDeathAnimation = null;
                    point.isAnimationRemoved = true;
                }

                if (point.turnedSide == Directions.RIGHT)
                    batcher.draw(
                            point.skeletonDeathRemains,
                            point.x + skeleton_frame_Width * point.skeletonWidthMult - (skeleton_frame_Width * point.skeletonWidthMult - point.skeletonWidth) / 2f,
                            point.y,
                            -skeleton_frame_Width * point.skeletonWidthMult,
                            skeleton_frame_Height * point.skeletonHeightMult
                    );
                else
                    batcher.draw(
                            point.skeletonDeathRemains,
                            point.x - (skeleton_frame_Width * point.skeletonWidthMult - point.skeletonWidth) / 2f,
                            point.y,
                            skeleton_frame_Width * point.skeletonWidthMult,
                            skeleton_frame_Height * point.skeletonHeightMult
                    );

            }
        }

        // endregion
    }

}
