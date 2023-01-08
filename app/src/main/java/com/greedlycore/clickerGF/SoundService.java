package com.greedlycore.clickerGF;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.blm.R;

public class SoundService extends Thread {
    MediaPlayer player;
    Context context;

    public SoundService(Context context){
        player = MediaPlayer.create(context, R.raw.fon); //select music file
        player.setLooping(true);

    }

    @Override
    public void run() {
        super.run();
    }

    void startMusic(){
        player.start();
    }

    void stopMusic(){
        player.pause();
    }




}