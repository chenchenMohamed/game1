package com.example.game101.gamepanel;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.game101.R;

public class PauseGame {
    private Context context;
    private int screenX;
    private int screenY;
    private Bitmap buttonPause;
    private Bitmap buttonPlay;
    private Bitmap buttonReplay;
    private Bitmap buttonHome;
    private Bitmap desert;
    private int y;
    private int[] tabX = new int[3];
    private  Paint paint;
    public  boolean isRun = true;
    private int rayonButton = 0;


    public PauseGame(Resources res, Context context, int screenX, int screenY){

        rayonButton = (int) screenY/6;
        desert = BitmapFactory.decodeResource(res, R.drawable.desert);
        desert = Bitmap.createScaledBitmap(desert, 3000, 3000, false);

        buttonPause = BitmapFactory.decodeResource(res, R.drawable.pause);
        buttonPause = Bitmap.createScaledBitmap(buttonPause, 100, 100, false);

        buttonPlay = BitmapFactory.decodeResource(res, R.drawable.play);
        buttonPlay = Bitmap.createScaledBitmap(buttonPlay, rayonButton*2, rayonButton*2, false);

        buttonReplay = BitmapFactory.decodeResource(res, R.drawable.replay);
        buttonReplay = Bitmap.createScaledBitmap(buttonReplay, rayonButton*2, rayonButton*2, false);

        buttonHome = BitmapFactory.decodeResource(res, R.drawable.home);
        buttonHome = Bitmap.createScaledBitmap(buttonHome, rayonButton*2, rayonButton*2, false);

        paint = new Paint();
        this.screenX = screenX;
        this.screenY = screenY;

         y = screenY - rayonButton * 2 - 100;

         tabX[0] = screenX/2 - (rayonButton * 3 + 10);
         tabX[1] = screenX/2 - rayonButton;
         tabX[2] = screenX/2 + rayonButton + 10;
    }

    public void draw(Canvas canvas) {

        if(isRun) {
            playDraw(canvas);
        }else{
            pauseDraw(canvas);
        }

    }

    public void recycleImages(){
        this.buttonPause.recycle();
        this.desert.recycle();
        this.buttonPlay.recycle();
        this.buttonReplay.recycle();
        this.buttonHome.recycle();
    }

    private void playDraw(Canvas canvas) {
        canvas.drawBitmap(
                this.buttonPause,
                (float) screenX - 120,
                (float) 120,
                paint
        );
    }

    private void pauseDraw(Canvas canvas){

        try{
            canvas.drawBitmap(
                    this.desert,
                    (float) -10,
                    (float) 0,
                    paint
            );

            canvas.drawBitmap(
                    this.buttonPlay,
                    (float) tabX[0],
                    (float) y,
                    paint
            );

            canvas.drawBitmap(
                    this.buttonReplay,
                    (float) tabX[1],
                    (float) y,
                    paint
            );

            canvas.drawBitmap(
                    this.buttonHome,
                    (float) tabX[2],
                    (float) y,
                    paint
            );
        }catch (RuntimeException e){
            e.printStackTrace();
        }

    }


    public boolean isInputPause(float x , float y){
             if(x > screenX - 120 && y > 120 && y < 220){
                 return true;
             }
             return false;
    }

    public boolean isInputPlay(float x , float y){
        if(x > tabX[0] && x < tabX[0]+rayonButton*2 && y > this.y && y < this.y + rayonButton*2){
            return true;
        }
        return false;
    }

    public boolean isInputReplay(float x , float y){
        if(x > tabX[1] && x < tabX[1]+rayonButton*2 && y > this.y && y < this.y + rayonButton*2){
            return true;
        }
        return false;
    }

    public boolean isInputHome(float x , float y){
        if(x > tabX[2] && x < tabX[2]+rayonButton*2 && y > this.y && y < this.y + rayonButton*2){
            return true;
        }
        return false;
    }

}

