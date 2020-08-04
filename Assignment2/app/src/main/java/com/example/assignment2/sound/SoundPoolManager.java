package com.example.assignment2.sound;

import android.content.Context;
import android.media.SoundPool;

import com.example.assignment2.R;

import java.util.HashMap;
import java.util.Map;

public class SoundPoolManager implements SoundPool.OnLoadCompleteListener {
    private Map<Sound, Integer> soundIds;
    private SoundPool pool;
    private int loadId;
    private boolean ready;

    //Handles the music/sound effects for the game.

    public SoundPoolManager(Context context) {
        soundIds = new HashMap<>();
        pool = new SoundPool(10,
                android.media.AudioManager.STREAM_MUSIC,
                0);
        pool.setOnLoadCompleteListener(this);

        // load order matches Sound's declaration order
        pool.load(context, R.raw.right, 0);
        pool.load(context, R.raw.wrong, 0);
    }

    @Override
    public void onLoadComplete(SoundPool soundPool,
                               int sampleId, int status) {
        this.ready = status == 0;

        Sound sound = Sound.values()[loadId++];
        soundIds.put(sound, sampleId);
    }

    public boolean isReady() {
        return ready;
    }

    public void playEffect(Sound sound) {
        Integer id = soundIds.get(sound);
        assert id != null;
        pool.play(id, 1, 1,
                1, 0, 1);
    }
}
