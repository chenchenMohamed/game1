package com.example.game101.objects;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.game101.R;
import com.example.game101.utils.Utils;

import java.util.List;

public class AnimationPlayer extends Thread {

    public boolean isRun = true;

    private Bitmap[] backgroundPlayer;
    private Bitmap[] backgroundEnemy;
    public Bitmap ballon;

    private int compteur = 0;

    public AnimationPlayer(Resources res, Context context){

        backgroundPlayer = new Bitmap[2];
        backgroundEnemy = new Bitmap[2];

        backgroundPlayer[0] = BitmapFactory.decodeResource(res, R.drawable.avioncoupe1);
        backgroundPlayer[0] = Bitmap.createScaledBitmap(backgroundPlayer[0], 83, 83, false);

        backgroundPlayer[1] = BitmapFactory.decodeResource(res, R.drawable.avioncoupe2);
        backgroundPlayer[1] = Bitmap.createScaledBitmap(backgroundPlayer[1], 83, 83, false);

        backgroundEnemy[0] = BitmapFactory.decodeResource(res, R.drawable.avion11coupe);
        backgroundEnemy[0] = Bitmap.createScaledBitmap(backgroundEnemy[0], 80, 80, false);

        backgroundEnemy[1] = BitmapFactory.decodeResource(res, R.drawable.avion22coupe);
        backgroundEnemy[1] = Bitmap.createScaledBitmap(backgroundEnemy[1], 80, 80, false);

        ballon = BitmapFactory.decodeResource(res, R.drawable.ballon);
        ballon = Bitmap.createScaledBitmap(ballon, 100, 100, false);

        start();
    }

    public void recycleImages(){
        backgroundPlayer[0].recycle();
        backgroundPlayer[1].recycle();
        backgroundEnemy[0].recycle();
        backgroundEnemy[1].recycle();
        ballon.recycle();
    }

    public Bitmap getPlayer(){
        return backgroundPlayer[compteur];
    }

    public Bitmap getEnemy(){
        return backgroundEnemy[compteur];
    }

    @Override
    public void run() {

        super.run();

        while (true) {

            try {
                sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            update();
        }

    }

    private  void update(){
        if(!isRun){
            return;
        }

        if(compteur == 1){
            compteur = 0;
        }else{
            compteur = 1;
        }
    }

}
