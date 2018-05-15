package com.masi4.gamehelpers;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.masi4.Directions;
import com.masi4.gameobjects.objects.Skeleton;

// TODO: ВЕЛОСИПЕД (?)
public class SkeletonDeath
{
    public Animation skeletonDeathAnimation;
    public TextureRegion skeletonDeathRemains;
    public Directions turnedSide;
    public int skeletonWidth;
    public int skeletonHeight;
    public float x;
    public float y;
    public float elapsedTime;
    public boolean isAnimationRemoved;

    public SkeletonDeath(Skeleton skeleton)
    {
        this.x = skeleton.graphics.getX();
        this.y = skeleton.graphics.getY();
        this.turnedSide = skeleton.graphics.getTurnedSide();
        this.skeletonWidth = skeleton.graphics.getWidth();
        this.skeletonHeight = skeleton.graphics.getHeight();
        isAnimationRemoved = false;
    }
}
