package com.example.game101.objects;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

import com.example.game101.R;
import com.example.game101.utils.Utils;

import java.util.List;


public class AnimationEnemy extends Thread {

    public boolean isRun = true;

    public Bitmap[] ballon = new Bitmap[4];

    private int compteur = 0;

    public AnimationEnemy(Resources res, Context context){

        Bitmap ballon1 = BitmapFactory.decodeResource(res, R.drawable.loading);
        ballon1 = Bitmap.createScaledBitmap(ballon1, 100, 100, false);
        Matrix matrix = new Matrix();
        matrix.setRotate(10); // degrees
        ballon[0] = Bitmap.createBitmap(ballon1, 0, 0, ballon1.getHeight(), ballon1.getWidth(), matrix, true);

        ballon1 = BitmapFactory.decodeResource(res, R.drawable.loading);
        ballon1 = Bitmap.createScaledBitmap(ballon1, 100, 100, false);
        matrix = new Matrix();
        matrix.setRotate(20); // degrees
        ballon[1] = Bitmap.createBitmap(ballon1, 0, 0, ballon1.getHeight(), ballon1.getWidth(), matrix, true);

        ballon1 = BitmapFactory.decodeResource(res, R.drawable.loading);
        ballon1 = Bitmap.createScaledBitmap(ballon1, 100, 100, false);
        matrix = new Matrix();
        matrix.setRotate(30); // degrees
        ballon[2] = Bitmap.createBitmap(ballon1, 0, 0, ballon1.getHeight(), ballon1.getWidth(), matrix, true);

        ballon1 = BitmapFactory.decodeResource(res, R.drawable.loading);
        ballon1 = Bitmap.createScaledBitmap(ballon1, 100, 100, false);
        matrix = new Matrix();
        matrix.setRotate(45); // degrees
        ballon[3] = Bitmap.createBitmap(ballon1, 0, 0, ballon1.getHeight(), ballon1.getWidth(), matrix, true);

        start();
    }

    public void recycleImages(){
        //ballon.recycle();

        for(int i = 0; i < 4; i++){
            ballon[i].recycle();
        }

    }

    public Bitmap getEnemyProduct(){
        return ballon[degreeRotation];
    }

    @Override
    public void run() {

        super.run();

        while (true) {

            try {
                sleep(33);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            update();
        }

    }

    private int degreeRotation = 0;

    private  void update(){
        if(!isRun){
            return;
        }


        degreeRotation += 1;

        if(degreeRotation > 3){
            degreeRotation = 0;
        }

    }

}

