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
import com.masi4.Directions;
import com.masi4.gameobjects.SkeletonListener;
import com.masi4.gameobjects.Stats;
import com.masi4.gameobjects.objects.Player;
import com.masi4.gameobjects.Level;
import com.masi4.gameobjects.objects.Skeleton;
import static com.masi4.myGame.GameMain.*;

import java.util.Random;

public class GameWorld
{
    private Random random;
    // TODO: сделать event manager
    private Array<SkeletonListener> skeletonListeners;

    private Player player;
    private Level level;
    private ObjectSet<Skeleton> skeletons;

    private int skeletonsAlive;
    private int skeletonsKilled;

    // Свойства игрока (размеры, шмот etc) возможно вынести в отдельный файл
    public GameWorld(Level.LevelNames levelName)
    {
        random = new Random();
        skeletonListeners = new Array<SkeletonListener>();

        level = new Level(levelName);
        player = new Player(PLAYER_DEFAULT_WIDTH, PLAYER_DEFAULT_HEIGHT, level.getWorldGravity(), true);
        player.setCoords(0, level.getFloorHeight()); // TODO: надо спавнить игрока пониже. (edit: для этого нужно понизить уровень пола в классе Level)
        player.subscribeAbilities(this);

        skeletons = new ObjectSet<Skeleton>();
        skeletonsKilled = 0;
    }

    // TODO: подумать о порядке обработки (например, сначала передвижения, потом способности)
    public void update(float delta)
    {
        // Спавним скелетов
        // TODO: спавнить разного размера
        if (SPAWN_SKELETONS)
        {
            if (skeletonsAlive < 10)
                if (skeletonsAlive < 3) {
                    spawnSkeletonRandomPos();
                    spawnSkeletonRandomPos();
                } else if (random.nextInt(30000) < 50)
                    spawnSkeletonRandomPos();
        }

        //
        // Обновляем игрока
        //
        if (!player.isDead()) {
            // Обновление позиции
            player.graphics.update_position(delta);

            // Обработка столкновений
            if (player.graphics.getY() < level.getFloorHeight()) {
                player.graphics.handleDownCollision(level.getFloorHeight());
            }
            if (player.graphics.getY() + player.graphics.getHeight() > level.getHeight()) {
                player.graphics.handleUpCollision(level.getHeight());
            }
            if (player.graphics.getX() < 0) {
                player.graphics.handleLeftCollision(0);
            }
            if (player.graphics.getX() + player.graphics.getWidth() > level.getWidth()) {
                player.graphics.handleRightCollision(level.getWidth());
            }
            player.setCoords(player.graphics.getX(), player.graphics.getY());

            // Обработка способностей
            player.updateAbilities(delta);
        }

        //
        // Обновляем скелетов
        //

        for (Skeleton skeleton : skeletons) {
            if (!skeleton.isDead()) {
                skeleton.graphics.update_position(delta);

                // TODO: controlSkeletons
                // вероятность 9% что прыгнет
                if (random.nextInt(15000) < 100)
                    skeleton.graphics.jump();

                if (Math.abs(skeleton.graphics.getX() - player.graphics.getX()) > skeleton.graphics.getWidth())
                    if (skeleton.graphics.getX() < player.graphics.getX())
                        skeleton.graphics.moveRight(skeleton);
                    else
                        skeleton.graphics.moveLeft(skeleton);
                else {
                    skeleton.graphics.releaseMovementControls();
                    skeleton.executeAbility(AbilityName.Skeleton_MeleeSwordAttack);
                }

                // Обработка столкновений
                if (skeleton.graphics.getY() < level.getFloorHeight())   // сделать циклом для всех levelobjects
                {
                    skeleton.graphics.handleDownCollision(level.getFloorHeight());
                }
                if (skeleton.graphics.getY() + skeleton.graphics.getHeight() > level.getHeight()) {
                    skeleton.graphics.handleUpCollision(level.getHeight());
                }
                if (skeleton.graphics.getX() < 0) {
                    skeleton.graphics.handleLeftCollision(0);
                }
                if (skeleton.graphics.getX() + skeleton.graphics.getWidth() > level.getWidth()) {
                    skeleton.graphics.handleRightCollision(level.getWidth());
                }
                skeleton.setCoords(skeleton.graphics.getX(), skeleton.graphics.getY());

                // Обработка способностей
                skeleton.updateAbilities(delta);

                // Если скелетон дамажит игрока
                if (skeleton.getActingAbilityName() != AbilityName.NONE)
                    if (skeleton.getActingBoundingRec().overlaps(player.graphics.getHitbox()))
                        for (Rectangle hitbox : skeleton.getActingHitboxes()) {
                            if (hitbox.overlaps(player.graphics.getHitbox())) {
                                player.takeDamage(skeleton.dealDamage(skeleton.getActingAbilityName(), player));
                                break;
                            }
                        }

                // Если игрок дамажит скелета
                if (player.getCastingAbilityName() != AbilityName.NONE)
                    if (player.getActingBoundingRec().overlaps(skeleton.graphics.getHitbox()))
                        for (Rectangle hitbox : player.getActingHitboxes()) {
                            if (hitbox.overlaps(skeleton.graphics.getHitbox())) {
                                skeleton.takeDamage(player.dealDamage(player.getCastingAbilityName(), skeleton)); // заменить getCastingAbility на getActingAbility
                                if (skeleton.isDead())
                                    invokeSkeletonDead(skeleton);
                                break;
                            }
                        }
            }
        }
    }

    private void spawnSkeleton(int positionX)
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

        skeletons.add(skeleton);
        skeleton.setCoords(positionX, level.getFloorHeight());
        skeletonsAlive++;

        invokeSkeletonSpawned(skeleton);
    }

    private void spawnSkeletonRandomPos()
    {
        // Спавним скелета в слепую зону
        int x;
        // Координата Х конца слепой зоны слева
        float leftBlindEnd = player.graphics.getX() - 0.42f * SCREEN_WIDTH - SKELETON_DEFAULT_WIDTH - 50;
        // Координата Х начала слепой зоны справа
        float rightBlindStart = player.graphics.getX() + 0.58f * SCREEN_WIDTH + SKELETON_DEFAULT_WIDTH + 50;
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

        spawnSkeleton(x);
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
        for (SkeletonListener listener : skeletonListeners)
            listener.skeletonDead(skeleton);
    }
}
