package com.example.game101.objects;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.game101.Stage1.Game;
import com.example.game101.Stage1.GameDisplay;
import com.example.game101.Stage1.GameLoop;
import com.example.game101.R;
import com.example.game101.utils.ObjectCollider;
import com.example.game101.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Bombe extends Thread{
    public boolean isRun = true;

    public Bitmap[] ballon = new Bitmap[9];

    private int compteur = 0;

    private Game game;

    private AnimationBombe animationBombe;

    private Paint paint;

    private Float x,y;

    public Bombe(Resources res, Context context, Game game, AnimationBombe animationBombe, Float x, Float y){
        this.game = game;
        this.animationBombe = animationBombe;
        this.x = x;
        this.y = y;
        start();
    }

    @Override
    public void run() {

        super.run();

        //while(degreeRotation < 8 ){
        while(true){
            try {
                sleep(100);
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

        if(degreeRotation<15) {
            degreeRotation += 1;
        }else{
            degreeRotation = 0;
        }

    }

    private void remove(){

    }


    public void draw(Canvas canvas, GameDisplay gameDisplay){
        try {

            canvas.drawBitmap(
                    animationBombe.getEnemyProduct(degreeRotation),
                    (float) gameDisplay.getGameToDisplayCoordinatesX(this.x-50),
                    (float) gameDisplay.getGameToDisplayCoordinatesY(this.y-50),
                    paint
            );
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }


}


