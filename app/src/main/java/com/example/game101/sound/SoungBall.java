package com.example.game101.sound;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.game101.R;

public class SoungBall{

    private Context context;
    MediaPlayer[] mediaPlayer;
    private int pos= 0;

    public SoungBall(Context context){
      this.context = context;
      mediaPlayer = new MediaPlayer[]{MediaPlayer.create(context, R.raw.sound04), MediaPlayer.create(context, R.raw.sound04), MediaPlayer.create(context, R.raw.sound04), MediaPlayer.create(context, R.raw.sound04)};
    }

    public void playFire(){
        mediaPlayer[pos].start();
        pos++;
        if(pos > 3){
            pos = 0;
        }
    }


}
