package com.example.game101.gamepanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.game101.R;

public class GameOver {
    private Context context;
    private int screenX;
    private int screenY;
    private int x;
    private int y = 300;
    public String text = "Pause";
    Paint paint = new Paint();

    public GameOver(Context context, int screenX, int screenY){

        this.context = context;
        this.screenX = screenX;
        this.screenY = screenY;
        setPause();
    }

    public void setWin(){
        text = "You Win";

        x = screenX/2 - 260;

        int color = ContextCompat.getColor(context, R.color.colorPrimary);
        paint.setColor(color);

        float textSize = 150;
        paint.setTextSize(textSize);

    }

    public void setGameOver(){
        text = "Game Over";
        x = screenX/2 - 350;

        int color = ContextCompat.getColor(context, R.color.gameOver);
        paint.setColor(color);

        float textSize = 150;
        paint.setTextSize(textSize);

    }

    public void setPause(){
        text = "Pause";
        x = screenX/2 - 200;

        int color = ContextCompat.getColor(context, R.color.gameOver);
        paint.setColor(color);

        float textSize = 150;
        paint.setTextSize(textSize);

    }


    public void draw(Canvas canvas) {
        canvas.drawText(text, x, y, paint);
    }
}
