package com.masi4.gameobjects;

// TODO: Сделать общим для всех мобов (после создания общего класса)

import com.masi4.gameobjects.objects.Skeleton;

/**
 * Слушатели события спавна и смерти моба
 */
public interface SkeletonListener
{
    void skeletonSpawned(Skeleton skeleton);
    void skeletonDead(Skeleton skeleton);
}
