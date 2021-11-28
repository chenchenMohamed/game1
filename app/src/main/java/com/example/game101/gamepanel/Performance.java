package com.example.game101.gamepanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.game101.Stage1.GameLoop;
import com.example.game101.R;

public class Performance {
    private Context context;
    private GameLoop gameLoop;
    private int somme = 0;

    public Performance(Context context, GameLoop gameLoop){
       this.context = context;
       this.gameLoop = gameLoop;
    }

    public void draw(Canvas canvas){
        //drawUPS(canvas);
        drawFPS(canvas);
        //drawCountFPS(canvas);
    }

    public void drawUPS(Canvas canvas) {
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(30);
        canvas.drawText("UPS: " + averageUPS, 100, 100, paint);
    }


    public void drawFPS(Canvas canvas) {
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(30);
        canvas.drawText("FPS: " + averageFPS, 100, 150, paint);
    }


    public void drawCountFPS(Canvas canvas) {
        if(gameLoop.getAverageFPS() < 29){
            somme++;
        }
        String averageUPS = Integer.toString(somme);
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(30);
        canvas.drawText("Somme: " + averageUPS, 100, 200, paint);
    }




}
