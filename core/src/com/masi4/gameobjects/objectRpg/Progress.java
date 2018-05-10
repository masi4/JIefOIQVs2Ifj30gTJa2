package com.masi4.gameobjects.objectRpg;

/**
 * Created by OIEFIJM on 06.04.2018.
 *
 * Информация о талантах, текущем состоянии степени крутости дефолтной абилки etc.
 *
 */

public class Progress
{
    private String currentDefaultAbility;

    public Progress(String currentDefaultAbility)
    {
        this.currentDefaultAbility = currentDefaultAbility;
    }

    public String getCurrentDefaultAbility()
    {
        return currentDefaultAbility;
    }
}
