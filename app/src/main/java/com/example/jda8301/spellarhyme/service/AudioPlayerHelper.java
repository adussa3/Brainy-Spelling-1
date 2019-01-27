package com.example.jda8301.spellarhyme.service;

import com.example.jda8301.spellarhyme.MyApplication;

import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.content.Context;


import android.support.v7.app.AppCompatActivity;


import com.example.jda8301.spellarhyme.utils.Config;

import java.io.IOException;

public class AudioPlayerHelper {
    private static final String EXTENSION_FILE = ".mp3";

    private static volatile AudioPlayerHelper Instance = null;

    MediaPlayer mMediaPlayer;
    Handler mHandlerDelay;
    Runnable mRunnableDelay;
    MediaPlayer mIdiomPlayer;


    private AudioPlayerHelper() {
        mHandlerDelay = new Handler();
        mMediaPlayer = new MediaPlayer();
    }

    public static AudioPlayerHelper getInstance() {
        AudioPlayerHelper localInstance = Instance;
        if (localInstance == null) {
            synchronized (AudioPlayerHelper.class) {
                localInstance = Instance;
                if (localInstance == null) {
                    Instance = localInstance = new AudioPlayerHelper();
                }
            }
        }
        return localInstance;
    }


    private int convertSecondStringToMilliSeconds(String durationString) {
        if (TextUtils.isEmpty(durationString)) return 0;
        return (int) (Float.parseFloat(durationString) * 1000);
    }

    public void stopPlayer() {
        if (null != mHandlerDelay) {
            mHandlerDelay.removeCallbacksAndMessages(null);
        }
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
        }
        mMediaPlayer.reset();
    }

    public void playAudio(String path) {
        playWithOffset(path, 0);
    }

    public void playWithOffset(String path, int offset){
        stopPlayer();
        mMediaPlayer.reset();
        mMediaPlayer = new MediaPlayer();
        try {
            AssetFileDescriptor afd = MyApplication.getAppContext().getAssets().openFd(path + EXTENSION_FILE);
            mMediaPlayer.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mMediaPlayer.prepare();
            mMediaPlayer.seekTo(offset);
        } catch (IOException e) {
            Log.d("error","INVALID FILE PATH");
        }
        mMediaPlayer.start();
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
        });
    }

    public int getCurrentPosition(){
        return mMediaPlayer.getCurrentPosition();
    }


    /**
     * Listener done playing after delay time
     */
    public interface DonePlayingListener {
        void donePlaying();
    }


    /**
     * Listener play to end file audio
     */
    public interface CompletedListener {
        void onCompleted();
    }

}
