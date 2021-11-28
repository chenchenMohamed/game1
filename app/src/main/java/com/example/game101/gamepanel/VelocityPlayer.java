package com.example.game101.gamepanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.game101.R;
import com.example.game101.objects.Player;

public class VelocityPlayer {

    private Context context;
    private Player player;

    public VelocityPlayer(Context context, Player player){
        this.context = context;
        this.player = player;
    }

    public void draw(Canvas canvas){
        drawVelocityX(canvas);
        drawVelocityY(canvas);
    }


    public void drawVelocityX(Canvas canvas) {
        String averageUPS = Double.toString(player.velocityXFinal);
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(30);
        canvas.drawText("VelocityX: " + averageUPS, 100, 250, paint);
    }

    public void drawVelocityY(Canvas canvas) {
        String averageUPS = Double.toString(player.velocityYFinal);
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(30);
        canvas.drawText("VelocityY: " + averageUPS, 100, 300, paint);
    }

}
