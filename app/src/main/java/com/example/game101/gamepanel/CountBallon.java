package com.example.game101.gamepanel;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.game101.R;

public class CountBallon {
    private Context context;
    public Bitmap background;
    private int screenX;
    private int screenY;
    public int somme = 0;

    public CountBallon(Resources res, Context context, int screenX, int screenY){
        this.context = context;
        background = BitmapFactory.decodeResource(res, R.drawable.ballon);
        background = Bitmap.createScaledBitmap(background, 100, 100, false);
        this.screenX = screenX;
        this.screenY = screenY;
    }

    public void recycleImages(){
       background.recycle();
    }

    public void draw(Canvas canvas){
        drawUPS(canvas);
    }

    public void drawUPS(Canvas canvas) {
        String averageUPS = Integer.toString(this.somme)+"/10";
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(70);
        canvas.drawText( averageUPS, screenX-200, 90, paint);

        try{
            canvas.drawBitmap(
                    this.background,
                    screenX-300,
                    20,
                    paint
            );
        }catch (RuntimeException e){
            e.printStackTrace();
        }

    }

}