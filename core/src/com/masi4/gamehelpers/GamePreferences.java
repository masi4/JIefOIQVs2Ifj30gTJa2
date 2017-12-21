package com.masi4.gamehelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;

/**
 * Created by U1wknUzeU6 on 21.12.2017.
 */

public class GamePreferences
{
    public static Preferences Options = Gdx.app.getPreferences("GameOptions");
    public static I18NBundle loc = I18NBundle.createBundle( Gdx.files.internal("lang/lang"),new Locale("ru"));
    public static void SwitchToEn(){
        loc = I18NBundle.createBundle( Gdx.files.internal("lang/lang"),new Locale("en"));
    }
    public static void SwitchToRu(){
        loc = I18NBundle.createBundle( Gdx.files.internal("lang/lang"),new Locale("ru"));
    }
}
