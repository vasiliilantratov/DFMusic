package com.wiretech.df.dfmusic.Classes;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.wiretech.df.dfmusic.API.Classes.Song;
import com.wiretech.df.dfmusic.Const;
import com.wiretech.df.dfmusic.DataBase.DBManager;
import com.wiretech.df.dfmusic.Interfaces.OnPlayerListener;
import com.wiretech.df.dfmusic.Receivers.NoisyAudioStreamReceiver;
import com.wiretech.df.dfmusic.Services.MusicNotificationService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Player implements MediaPlayer.OnPreparedListener,
        MediaPlayer.OnCompletionListener, MediaPlayer.OnBufferingUpdateListener {
    public static Player instance = new Player();

    private NoisyAudioStreamReceiver mNoisyAudioStreamReceiver = new NoisyAudioStreamReceiver();
    private IntentFilter intentFilter = new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY);

    private Context mContext = null;

    private MediaPlayer mMediaPlayer;

    private List<OnPlayerListener> mListListeners;

    private Song mOldSong;
    private Song mSong;
    private Song mNewSong;

    private boolean isPlaying = false;
    private boolean isLooping = false;

    private Player() {
        mListListeners = new ArrayList<>();
    }

    public void play(Context context) {
        mContext = context;
        if (mMediaPlayer == null) {
            playNewSong();
        } else {
            if (isPlaying && (mSong.getId() != mNewSong.getId())) {
                playNewSong();
            } else {
                mMediaPlayer.start();
                isPlaying = true;
            }
        }
    }

    public void initNewSong() {
        mNewSong = DBManager.getSongById(MusicState.instance.getSongId());
    }

    public int getPlayingSongId() {
        if (mSong != null) {
            return mSong.getId();
        } else {
            return -1;
        }
    }

    public int getFinishSongId() {
        if (mOldSong != null) {
            return mOldSong.getId();
        } else {
            return -1;
        }
    }

    public String getSongName() {
        return mNewSong.getName();
    }

    public String getSingerName() {
        return mNewSong.getSinger();
    }

    public String getSongsLength() {
        return mNewSong.getLength();
    }

    public String getSongFullAlbumURL() {
        return mNewSong.getFullAlbumURL();
    }

    private void playNewSong() {
        releaseMediaPlayer();
        mMediaPlayer = new MediaPlayer();
        mSong = mNewSong;
        try {
            mMediaPlayer.setDataSource(mSong.getFullSongURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(isLooping);
        //Log.d(LOG_TAG, "prepareAsync");
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setOnBufferingUpdateListener(this);
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.prepareAsync();
    }

    public void pause() {
        if (mMediaPlayer != null) {
            mMediaPlayer.pause();
        }
        isPlaying = false;
    }

    public void stop() {
        releaseMediaPlayer();
    }

    public void seekTo(int to) {
        if (mMediaPlayer != null) {
            mMediaPlayer.seekTo(to);
        }
    }

    public boolean getIsPlaying() {
        return isPlaying;
    }

    public void setLooping(boolean l) {
        isLooping = l;
        if (mMediaPlayer != null) {
            mMediaPlayer.setLooping(isLooping);
        }
    }

    public int getDuration() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getDuration();
        } else {
            return 0;
        }
    }

    public int getCurrentPosition() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getCurrentPosition();
        } else {
            return 0;
        }
    }

    public void setOnPlayerListener(OnPlayerListener listener) {
        mListListeners.add(listener);
    }

    public void unSetOnPlayerListener(OnPlayerListener listener) {
        mListListeners.remove(listener);
    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            try {
                mMediaPlayer.release();
                mMediaPlayer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        for (OnPlayerListener listener : mListListeners) {
            listener.OnBufferingUpdateListener(percent);
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if ((mp.getDuration() - mp.getCurrentPosition()) < 1000) {
            mOldSong = mSong;
            Intent nextIntent = new Intent(mContext, MusicNotificationService.class);
            nextIntent.setAction(Const.ACTION.NEXTFOREGROUND_ACTION);
            PendingIntent pNextIntent = PendingIntent.getService(mContext, 0, nextIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            try {
                pNextIntent.send();
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
            for (OnPlayerListener listener : mListListeners) {
                listener.OnCompletionListener();
            }
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
        isPlaying = true;
    }
}