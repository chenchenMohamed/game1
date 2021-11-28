package com.example.game101.objects;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.game101.Stage1.GameDisplay;
import com.example.game101.R;

public class Ballon {

    public Float x;
    public Float y;
    private Paint paint;
    private float raduis = 40;
    private AnimationPlayer animationPlayer;

    public Ballon(Context context, AnimationPlayer animationPlayer, Resources res, Float x, Float y){
        this.x = x;
        this.y = y;
        this.animationPlayer = animationPlayer;
        paint = new Paint();
        paint.setColor(ContextCompat.getColor(context, R.color.player));
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay){
        try {

        canvas.drawBitmap(
                animationPlayer.ballon,
                (float) gameDisplay.getGameToDisplayCoordinatesX(this.x-50),
                (float) gameDisplay.getGameToDisplayCoordinatesY(this.y-50),
                paint
        );
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }



}
