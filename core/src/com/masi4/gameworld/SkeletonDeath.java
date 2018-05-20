package com.masi4.gameworld;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.masi4.gamehelpers.helpers.Directions;
import com.masi4.gameobjects.objects.Skeleton;

// TODO: ВЕЛОСИПЕД (?)
public class SkeletonDeath
{
    public Animation skeletonDeathAnimation;
    public TextureRegion skeletonDeathRemains;
    public Directions turnedSide;
    public int skeletonWidth;
    public int skeletonHeight;
    public float skeletonWidthMult;
    public float skeletonHeightMult;
    public float x;
    public float y;
    public float elapsedTime;
    public boolean isAnimationRemoved;

    public SkeletonDeath(Skeleton skeleton)
    {
        this.x = skeleton.model.getX();
        this.y = skeleton.model.getY();
        this.turnedSide = skeleton.model.getTurnedSide();
        this.skeletonWidth = skeleton.model.getWidth();
        this.skeletonHeight = skeleton.model.getHeight();
        this.skeletonWidthMult = skeleton.model.getWidth() / 30f;
        this.skeletonHeightMult = skeleton.model.getHeight() / 60f;
        this.isAnimationRemoved = false;
    }
}
