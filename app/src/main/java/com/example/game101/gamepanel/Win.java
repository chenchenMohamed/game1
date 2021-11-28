package com.example.game101.gamepanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.game101.R;

public class Win {
    private Context context;
    private int screenX;
    private int screenY;

    public Win(Context context, int screenX, int screenY){

        this.context = context;
        this.screenX = screenX;
        this.screenY = screenY;
    }

    public void draw(Canvas canvas) {
        String text = "You Win";

        float x = screenX/2 - 180;
        float y = screenY/2 - 100;

        Paint paint = new Paint();

        int color = ContextCompat.getColor(context, R.color.colorPrimary);
        paint.setColor(color);
        float textSize = 100;
        paint.setTextSize(textSize);

        canvas.drawText(text, x, y, paint);
    }
}

