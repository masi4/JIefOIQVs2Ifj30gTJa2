package com.masi4.Abilities.MobsAbilities.SkeletonAbilities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.masi4.Directions;
import com.masi4.gameobjects.Stats;
import com.masi4.gameobjects.objectGraphics.SkeletonGraphics;
import com.masi4.gameobjects.objects.Player;
import com.masi4.gameobjects.objects.Skeleton;
import static com.masi4.gamehelpers.GameCharactersDefaults.*;

import java.util.Random;

/**
 *  Обычная атака мечом. По логике полностью идентична обычной атаке мечом игрока
 *  TODO: возможно вынести в общий класс
 */

public class Skeleton_MeleeSwordAttack implements SkeletonAbility
{
    // TODO: исправить велосипед
    public static final Rectangle EMPTY = new Rectangle(0, 0, 1, 1);
    Random random = new Random();

    // TODO: Если будет зависимость от статов (рейтинг скорости), то добавить сюда зависимость от статов
    private float castTime;
    private float cooldown;
    private float elapsedTime;
    private boolean isCasting;
    /** Появился ли хитбокс **/
    private boolean isActing;
    private float currentCooldown;

    private int minDamage;
    private int maxDamage;
    // Если урон будет наносится не только игроку, то сделать ObjectMap, а также вынести куда-нибудь информацию, кому может наносится урон
    private boolean isDamageDealt;

    private final Vector2 coords = new Vector2(15, 10);

    private Array<Rectangle> hitboxes;
    private Rectangle boundingRec;

    public Skeleton_MeleeSwordAttack(SkeletonGraphics graphics, Stats stats)
    {
        castTime = SKELETON_DEFAULT_MELEE_SWORD_ATTACK_CAST_TIME;
        cooldown = SKELETON_DEFAULT_MELEE_SWORD_ATTACK_COOLDOWN;
        elapsedTime = 0;
        isCasting = false;
        isActing = false;
        currentCooldown = 0;

        minDamage = Math.round(stats.getDamage() * 0.7f);
        maxDamage = Math.round(stats.getDamage() * 1.3f);

        hitboxes = new Array<Rectangle>(1);
        hitboxes.add(new Rectangle(
                coords.x + graphics.getWidth(),
                coords.y,
                graphics.getWidth() / 30f * 36,
                graphics.getHeight() / 60f * 70
        ));
        boundingRec = new Rectangle(hitboxes.get(0));


    }

    @Override
    public void execute(Skeleton skeleton) {
        if (!isCasting && currentCooldown == 0)
            isCasting = true;
    }

    @Override
    public void update(Skeleton skeleton, float delta)
    {
        if (isCasting && currentCooldown == 0) {
            elapsedTime += delta;

            if (isActing = elapsedTime > 0.07f)
                if (skeleton.graphics.getTurnedSide() == Directions.RIGHT) {
                    hitboxes.get(0).setPosition(coords.x + skeleton.graphics.getX(), coords.y + skeleton.graphics.getY());
                    boundingRec.setPosition(hitboxes.get(0).x, hitboxes.get(0).y);
                }
                else {
                    hitboxes.get(0).setPosition(
                            -coords.x - hitboxes.get(0).width + skeleton.graphics.getX() + skeleton.graphics.getWidth(),
                            coords.y + skeleton.graphics.getY()
                    );
                    boundingRec.setPosition(hitboxes.get(0).x, hitboxes.get(0).y);
                }

            if (elapsedTime > castTime) {
                elapsedTime = 0;
                isCasting = false;
                isActing = false;
                currentCooldown = cooldown;

                isDamageDealt = false;
            }
        }
        else {
            currentCooldown -= delta;
            if (currentCooldown < 0) currentCooldown = 0;
        }
    }

    @Override
    public Array<Rectangle> getHitboxes() {
        if (isActing)
            return hitboxes;
        else return null;
    }

    @Override
    public Rectangle getBoundingRec() {
        if (isActing)
            return boundingRec;
        else return EMPTY;
    }

    @Override
    public boolean isCasting() { return isCasting; }

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
    public int dealDamage(Player player) {
        if (!isDamageDealt) {
            isDamageDealt = true;
            return minDamage + random.nextInt(maxDamage - minDamage);
        }
        else return 0;
    }
}
