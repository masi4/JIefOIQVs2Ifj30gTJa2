package com.masi4.gameworld;

/**
 * Created by OIEFIJM on 23.10.2017.
 *
 * Данный класс отвечает за обновление игровых объектов
 */

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectSet;

import static com.masi4.gamehelpers.GameCharactersDefaults.*;

import com.masi4.Abilities.AbilityName;
import com.masi4.gamehelpers.helpers.Directions;
import com.masi4.gameobjects.SkeletonListener;
import com.masi4.gameobjects.Stats;
import com.masi4.gameobjects.objectSound.SkeletonSound;
import com.masi4.gameobjects.objects.Player;
import com.masi4.gameobjects.Level;
import com.masi4.gameobjects.objects.Skeleton;
import static com.masi4.myGame.GameMain.*;

public class GameWorld
{
    // TODO: сделать event manager
    private Array<SkeletonListener> skeletonListeners;
    private Player player;
    private Level level;

    private ObjectSet<Skeleton> skeletons;
    private float skeletonSpawnTimer;
    private int skeletonsAlive;
    private int skeletonsKilled;

    // Свойства игрока (размеры, шмот etc) возможно вынести в отдельный файл
    public GameWorld(Level.LevelNames levelName)
    {
        skeletonListeners = new Array<SkeletonListener>();

        level = new Level(levelName);
        player = new Player(PLAYER_DEFAULT_WIDTH, PLAYER_DEFAULT_HEIGHT, level.getWorldGravity(), true);
        player.setCoords(0, level.getFloorHeight());
        player.subscribeAbilities(this);

        skeletons = new ObjectSet<Skeleton>();
        skeletonsKilled = 0;

        Array<Boolean> boolArray = new Array<Boolean>();
        boolArray.add(true);
        boolArray.add(false);
    }

    // TODO: подумать о порядке обработки (например, сначала передвижения, потом способности)
    public void update(float delta)
    {
        skeletonSpawnTimer += delta;

        // Если прошла секунда
        if (SPAWN_SKELETONS && skeletonSpawnTimer > 1)
        {
            // Спавним скелетов
            // TODO: нормальный спавн раз в секунду с некоторым шансом
            if (skeletonsAlive < 6 && random.nextInt(10000) < 5000)
                spawnRandomSkeleton();
        }

        //
        // Обновляем игрока
        //
        if (!player.isDead()) {
            // Обновление позиции
            player.model.update_position(delta);

            // Обработка столкновений
            if (player.model.getY() < level.getFloorHeight()) {
                player.model.handleDownCollision(level.getFloorHeight());
            }
            if (player.model.getY() + player.model.getHeight() > level.getHeight()) {
                player.model.handleUpCollision(level.getHeight());
            }
            if (player.model.getX() < 0) {
                player.model.handleLeftCollision(0);
            }
            if (player.model.getX() + player.model.getWidth() > level.getWidth()) {
                player.model.handleRightCollision(level.getWidth());
            }
            player.setCoords(player.model.getX(), player.model.getY());

            // Состояние звука
            player.updateSounds(delta);
            // Состояние способностей
            player.updateAbilities(delta);
            // Состояние баффов/дебаффов
            player.updateEffects(delta);
        }

        //
        // Обновляем скелетов
        //

        for (Skeleton skeleton : skeletons) {
            if (!skeleton.isDead()) {
                skeleton.model.update_position(delta);

                if (random.nextInt(15000) < 100)
                    skeleton.model.jump();

                if (Math.abs(skeleton.model.getX() - player.model.getX()) > skeleton.model.getWidth())
                    if (skeleton.model.getX() < player.model.getX())
                        skeleton.model.moveRight(skeleton);
                    else
                        skeleton.model.moveLeft(skeleton);
                else {
                    skeleton.model.releaseMovementControls();
                    skeleton.executeAbility(AbilityName.Skeleton_MeleeSwordAttack);
                }

                // Обработка столкновений
                if (skeleton.model.getY() < level.getFloorHeight())   // сделать циклом для всех levelobjects
                {
                    skeleton.model.handleDownCollision(level.getFloorHeight());
                }
                if (skeleton.model.getY() + skeleton.model.getHeight() > level.getHeight()) {
                    skeleton.model.handleUpCollision(level.getHeight());
                }
                if (skeleton.model.getX() < 0) {
                    skeleton.model.handleLeftCollision(0);
                }
                if (skeleton.model.getX() + skeleton.model.getWidth() > level.getWidth()) {
                    skeleton.model.handleRightCollision(level.getWidth());
                }
                skeleton.setCoords(skeleton.model.getX(), skeleton.model.getY());

                // Обработка способностей
                skeleton.updateAbilities(delta);

                // Если скелетон дамажит игрока
                if (skeleton.getActingAbilityName() != AbilityName.NONE)
                    if (skeleton.getActingBoundingRec().overlaps(player.model.getHitbox()))
                        for (Rectangle hitbox : skeleton.getActingHitboxes()) {
                            if (hitbox.overlaps(player.model.getHitbox())) {
                                player.takeDamage(skeleton.dealDamage(skeleton.getActingAbilityName(), player));
                                break;
                            }
                        }

                // Если игрок дамажит скелета
                if (player.getCastingAbilityName() != AbilityName.NONE)
                    if (player.getActingBoundingRec().overlaps(skeleton.model.getHitbox()))
                        for (Rectangle hitbox : player.getActingHitboxes()) {
                            if (hitbox.overlaps(skeleton.model.getHitbox()))
                            {
                                skeleton.takeDamage(player.dealDamage(player.getCastingAbilityName(), skeleton)); // заменить getCastingAbility на getActingAbility
                                if (skeleton.isDead()) {
                                    SkeletonSound.dead();
                                    invokeSkeletonDead(skeleton);
                                }
                                break;
                            }
                        }
            }
        }
        if (skeletonSpawnTimer > 1) skeletonSpawnTimer = 0;
    }

    public void respawnPlayer()
    {
        player = new Player(PLAYER_DEFAULT_WIDTH, PLAYER_DEFAULT_HEIGHT, level.getWorldGravity(), true);
        player.setCoords(0, level.getFloorHeight());
        player.subscribeAbilities(this);
    }

    private void spawnSkeleton(Skeleton skeleton, int positionX)
    {
        skeletons.add(skeleton);
        skeleton.setCoords(positionX, level.getFloorHeight());
        skeletonsAlive++;

        invokeSkeletonSpawned(skeleton);
    }

    private void spawnSkeleton(Skeleton skeleton)
    {
        // Спавним скелета в слепую зону
        int x;
        // Координата Х конца слепой зоны слева
        float leftBlindEnd = player.model.getX() - 0.42f * SCREEN_WIDTH - SKELETON_DEFAULT_WIDTH - 50;
        // Координата Х начала слепой зоны справа
        float rightBlindStart = player.model.getX() + 0.58f * SCREEN_WIDTH + SKELETON_DEFAULT_WIDTH + 50;
        // Если слева и справа от игрока есть место, не видимое камерой
        // TODO: proportion и прочие характеристики привязки камеры вынести куда нибудь в отдельное место
        if (leftBlindEnd > 0 && rightBlindStart < level.getWidth()) {
            // coin flip
            if (random.nextInt(5000) > 2500)
                x = random.nextInt(Math.round(leftBlindEnd));
            else
                x =  Math.round(rightBlindStart + random.nextInt(level.getWidth() - Math.round(rightBlindStart)));
        }
        else if (leftBlindEnd > 0)
            x = random.nextInt(Math.round(leftBlindEnd));
        else
            x =  Math.round(rightBlindStart + random.nextInt(level.getWidth() - Math.round(rightBlindStart)));

        // спавним
        spawnSkeleton(skeleton, x);
    }

    private void spawnSkeleton()
    {
        Skeleton skeleton = new Skeleton(
                SKELETON_DEFAULT_WIDTH,
                SKELETON_DEFAULT_HEIGHT,
                level.getWorldGravity(),
                true,
                random.nextInt(10000) > 5000 ? Directions.RIGHT : Directions.LEFT, // coin flip
                new Stats(
                        SKELETON_DEFAULT_STAMINA,
                        SKELETON_DEFAULT_DAMAGE,
                        SKELETON_DEFAULT_INTELLECT,
                        SKELETON_DEFAULT_HASTE,
                        SKELETON_DEFAULT_DEFENCE,
                        SKELETON_DEFAULT_BLOCKING,
                        SKELETON_DEFAULT_BONUS_SPEED_PROCENT
                )
        );

        spawnSkeleton(skeleton);
    }

    private void spawnRandomSkeleton()
    {
        // от 0.6 до 1.4
        float coef = 0.001f * (600 + random.nextInt(800));

        Skeleton skeleton = new Skeleton(
                Math.round(SKELETON_DEFAULT_WIDTH * coef),
                Math.round(SKELETON_DEFAULT_HEIGHT * coef),
                Math.round(SKELETON_DEFAULT_INITIAL_JUMP_SPEED / coef > 0 ? coef : 1),
                Math.round(SKELETON_DEFAULT_HORIZONTAL_VELOCITY_GAIN / coef),
                Math.round(SKELETON_DEFAULT_MAX_HORIZONTAL_VELOCITY / coef),
                level.getWorldGravity(),
                true,
                random.nextInt(10000) > 5000 ? Directions.RIGHT : Directions.LEFT,
                new Stats(
                        Math.round(SKELETON_DEFAULT_STAMINA * coef),
                        Math.round(SKELETON_DEFAULT_DAMAGE * coef),
                        Math.round(SKELETON_DEFAULT_INTELLECT),
                        Math.round(SKELETON_DEFAULT_HASTE),
                        Math.round(SKELETON_DEFAULT_DEFENCE),
                        Math.round(SKELETON_DEFAULT_BLOCKING),
                        Math.round(SKELETON_DEFAULT_BONUS_SPEED_PROCENT)
                )
        );

        spawnSkeleton(skeleton);
    }

    public Player getPlayer() { return player; }

    public ObjectSet<Skeleton> getSkeletons() { return skeletons; }

    public int getLevelWidth() { return level.getWidth(); }

    public void addSkeletonListener(SkeletonListener listener)
    {
        skeletonListeners.add(listener);
    }

    // Listeners: GameplayScreen, MeleeSwordAbility, Level1_Renderer
    private void invokeSkeletonSpawned(Skeleton skeleton)
    {
        for (SkeletonListener listener : skeletonListeners)
            listener.skeletonSpawned(skeleton);
    }

    private void invokeSkeletonDead(Skeleton skeleton)
    {
        skeletonsKilled++;
        skeletonsAlive--;
        for (SkeletonListener listener : skeletonListeners)
            listener.skeletonDead(skeleton);
    }
}
