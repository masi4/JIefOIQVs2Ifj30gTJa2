package com.masi4.Abilities.PlayerAbilities;

/**
 * Created by OIEFIJM on 25.02.2018.
 */

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.masi4.gamehelpers.helpers.Directions;
import com.masi4.gameobjects.SkeletonListener;
import com.masi4.gameobjects.objects.Player;
import com.masi4.gameobjects.Stats;
import com.masi4.gameobjects.objects.Skeleton;
import com.masi4.gameworld.GameWorld;
import static com.masi4.gamehelpers.GameCharactersDefaults.*;

import static com.masi4.myGame.GameMain.random;


/**
 *  Default sword attack for player
 */
public class Player_MeleeSwordAttack implements PlayerAbility, SkeletonListener
{
    // TODO: Если будет зависимость от статов (рейтинг скорости), то добавить сюда зависимость от статов
    private float castTime;
    private float cooldown;
    private float elapsedTime;
    private boolean isCasting;
    // TODO: реализовать
    private boolean isActing;
    private float currentCooldown;

    private int minDamage;
    private int maxDamage;
    private ObjectMap<Skeleton, Boolean> isDamageDealt;

    //
    // хитбоксы способности
    //

    // сдвиг относительно хитбокса игрока
    private final Vector2 shift = new Vector2(7, 5);
    // координаты хитбоксов атаки относительно хитбокса игрока
    private final Vector2 coords0 = new Vector2(shift.x + 0, shift.y + 0);
    private final Vector2 coords1 = new Vector2(shift.x + 11, shift.y + 7);
    private final Vector2 coords2 = new Vector2(shift.x + 26, shift.y + 22);
    private final Vector2 coords3 = new Vector2(shift.x + 65, shift.y + 32);
    private final Vector2 coords4 = new Vector2(shift.x + 39, shift.y + 48);

    private Array<Rectangle> hitboxes;
    private Rectangle boundingRec;

    public Player_MeleeSwordAttack(Stats stats)
    {
        castTime = PLAYER_DEFAULT_MELEE_SWORD_ATTACK_CAST_TIME;
        cooldown = PLAYER_DEFAULT_MELEE_SWORD_ATTACK_COOLDOWN;
        elapsedTime = 0;
        isCasting = false;
        currentCooldown = 0;

        minDamage = Math.round(stats.getDamage() * 0.7f);
        maxDamage = Math.round(stats.getDamage() * 1.3f);
        isDamageDealt = new ObjectMap<Skeleton, Boolean>();

        // hitboxes
        hitboxes = new Array<Rectangle>(5);
        hitboxes.add(new Rectangle(coords0.x, coords0.y, 27, 25));
        hitboxes.add(new Rectangle(coords1.x, coords1.y, 45, 27));
        hitboxes.add(new Rectangle(coords2.x, coords2.y, 40, 27));
        hitboxes.add(new Rectangle(coords3.x, coords3.y, 21, 17));
        hitboxes.add(new Rectangle(coords4.x, coords4.y, 53, 60));

        boundingRec = new Rectangle(
                coords0.x,
                coords0.y,
                hitboxes.get(4).x + hitboxes.get(4).width - coords0.x,
                hitboxes.get(4).y + hitboxes.get(4).height- coords0.y);
    }

    @Override
    public void execute(Player player)
    {
        if (!isCasting && currentCooldown == 0) {
            isCasting = true;
        }
    }

    // Только для атак вроде ближнего боя сработает приём "обновлять, только если кастуется"
    // Для какого-нибудь фаербола будет спавнится самостоятельный объект, который будет
    // иметь свою отрисовку в рендерере.
    @Override
    public void update(Player player, float delta)
    {
        if (isCasting && currentCooldown == 0) {
            elapsedTime += delta;

            // Если игрок смотрит вправо
            if (player.model.getTurnedSide() == Directions.RIGHT) {
                hitboxes.get(0).setPosition(coords0.x + player.model.getX(), coords0.y + player.model.getY());
                hitboxes.get(1).setPosition(coords1.x + player.model.getX(), coords1.y + player.model.getY());
                hitboxes.get(2).setPosition(coords2.x + player.model.getX(), coords2.y + player.model.getY());
                hitboxes.get(3).setPosition(coords3.x + player.model.getX(), coords3.y + player.model.getY());
                hitboxes.get(4).setPosition(coords4.x + player.model.getX(), coords4.y + player.model.getY());
                boundingRec.setPosition(coords0.x + player.model.getX(), coords0.y + player.model.getY());
            }
            // Если смотрит влево, то зеркально отражаем по горизонтали
            else {
                hitboxes.get(0).setPosition(
                        -coords0.x - hitboxes.get(0).width + player.model.getX() + player.model.getWidth(),
                         coords0.y + player.model.getY()
                );

                hitboxes.get(1).setPosition(
                        -coords1.x - hitboxes.get(1).width + player.model.getX() + player.model.getWidth(),
                        coords1.y + player.model.getY()
                );

                hitboxes.get(2).setPosition(
                        -coords2.x - hitboxes.get(2).width + player.model.getX() + player.model.getWidth(),
                        coords2.y + player.model.getY()
                );

                hitboxes.get(3).setPosition(
                        -coords3.x - hitboxes.get(3).width + player.model.getX() + player.model.getWidth(),
                        coords3.y + player.model.getY()
                );

                hitboxes.get(4).setPosition(
                        -coords4.x - hitboxes.get(4).width + player.model.getX() + player.model.getWidth(),
                        coords4.y + player.model.getY()
                );

                boundingRec.setPosition(
                        -coords0.x - boundingRec.width + player.model.getX() + player.model.getWidth(),
                        coords0.y + player.model.getY()
                );
            }

            if (elapsedTime > castTime) {
                elapsedTime = 0;
                isCasting = false;
                currentCooldown = cooldown;

                for (ObjectMap.Entry<Skeleton, Boolean> entry : isDamageDealt)
                {
                    isDamageDealt.put(entry.key, false);
                }
            }
        }
        else {
            currentCooldown -= delta;
            if (currentCooldown < 0) currentCooldown = 0;
        }
    }

    @Override
    public Array<Rectangle> getHitboxes() {
        if (isCasting)
            return hitboxes;
        else return null;
    }

    @Override
    public Rectangle getBoundingRec() {
        if (isCasting)
            return boundingRec;
        else return null;
    }

    @Override
    public boolean isCasting() {
        return isCasting;
    }

    public boolean isActing() { return isActing; }

    @Override
    public float getCastTime() { return castTime; }

    @Override
    public float getElapsedTime() {
        return elapsedTime;
    }

    @Override
    public float getCooldown() { return cooldown; }

    @Override
    public float getCurrentCooldown() {
        return currentCooldown;
    }

    @Override
    public int dealDamage(Skeleton skeleton) {
        if (!isDamageDealt.get(skeleton)) {
            isDamageDealt.put(skeleton, true);
            return minDamage + random.nextInt(maxDamage - minDamage);
        }
        else return 0;
    }


    @Override
    public void skeletonSpawned(Skeleton skeleton)
    {
        isDamageDealt.put(skeleton, false);
    }

    @Override
    public void skeletonDead(Skeleton skeleton)
    {
        isDamageDealt.remove(skeleton);
    }

    @Override
    public void subscribeAbility(GameWorld gameWorld) {
        gameWorld.addSkeletonListener(this);
    }


}
