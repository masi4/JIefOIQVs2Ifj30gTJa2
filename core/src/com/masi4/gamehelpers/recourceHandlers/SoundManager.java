package com.masi4.gamehelpers.recourceHandlers;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;
import com.masi4.gamehelpers.GamePreferences;
import static com.masi4.myGame.GameMain.random;

/**
    Сделан, чтобы не писать проверку, включен ли звук, в каждом методе, где проигрывается звук
 */

public class SoundManager
{
    /**
     * Plays a sound if "Sound" is enabled in options.
     * @param sound sound to be played
     * @return the id of the sound instance if successful, or -1 on failure.
     */
    public static long play(Sound sound)
    {
        // TODO: если включен звук в настройках
        // позже сделать регулировку громкости (master volume) и сделать play(masterVolume);
        if (GamePreferences.Options.getInteger("Sound") == 1)
            return sound.play();
        else return -1;
    }

    /**
     * Plays a sound with specified volume if "Sound" is enabled in options.
     * @param sound sound to be played
     * @param volume volume of sound to be played
     * @return the id of the sound instance if successful, or -1 on failure.
     */
    public static long play(Sound sound, float volume)
    {
        if (GamePreferences.Options.getInteger("Sound") == 1)
            return sound.play(volume);
        else return -1;
    }

    /**
     * Plays a random sound of the array if "Sound" is enabled in options.
     * @param soundArr array of sounds to be played
     * @return the id of the sound instance if successful, or -1 on failure.
     */
    public static long playRandom(Array<Sound> soundArr)
    {
        if (GamePreferences.Options.getInteger("Sound") == 1)
            return soundArr.get(random.nextInt(soundArr.size)).play();
        else return -1;
    }

    /**
     * Plays a random sound of the array with specified volume if "Sound" is enabled in options.
     * @param soundArr array of sounds to be played
     * @param volume volume of sound to be played
     * @return the id of the sound instance if successful, or -1 on failure.
     */
    public static long playRandom(Array<Sound> soundArr, float volume)
    {
        if (GamePreferences.Options.getInteger("Sound") == 1)
            return soundArr.get(random.nextInt(soundArr.size)).play(volume);
        else return -1;
    }

    /**
     * Plays Music file
     * @param music music file to be played
     * @param isLooping if music will be looped
     */
    public static void playMusic(Music music, boolean isLooping)
    {
        if (GamePreferences.Options.getInteger("Sound") == 1) {
            music.setLooping(isLooping);
            music.play();
        }
    }

    /**
     * Plays Music file with specified volume
     * @param music music file to be played
     * @param isLooping if music will be looped
     * @param volume volume of music to be played
     */
    public static void playMusic(Music music, boolean isLooping, float volume)
    {
        if (GamePreferences.Options.getInteger("Sound") == 1) {
            music.setLooping(isLooping);
            music.setVolume(volume);
            music.play();
        }
    }

}
