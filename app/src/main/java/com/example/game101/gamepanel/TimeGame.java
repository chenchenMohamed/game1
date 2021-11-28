package com.example.game101.gamepanel;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.example.game101.R;
import com.example.game101.objects.Enemy;
import com.example.game101.objects.Player;
import com.example.game101.utils.Utils;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class TimeGame extends Thread{
    private Context context;
    private int screenX;
    private int screenY;
    private Player player;
    private int time = 100;
    public boolean isRun = true;


    public TimeGame(Context context, int screenX, int screenY, Player player){
        this.context = context;
        this.screenX = screenX;
        this.screenY = screenY;
        this.player = player;
        start();
    }

    public int getTime(){
        return time;
    }

    @Override
    public void run() {

        super.run();

        while (true) {

            try {
                //sleep(5000000);
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            update();

        }

    }

    private void update(){
          if(!isRun){
              return;
          }

        if(this.time <= 0){
            player.setHealthPoints(20);
        }else{
            this.time--;
        }
    }


    public void draw(Canvas canvas){
        drawUPS(canvas);
    }

    public void drawUPS(Canvas canvas) {
        String averageUPS = Integer.toString(this.time)+"";
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(100);
        paint.setTextScaleX(1.2f);
        canvas.drawText( averageUPS, screenX / 2 -60, 100, paint);
    }



}