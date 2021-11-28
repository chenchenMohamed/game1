package com.example.game101.gamepanel;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.game101.Stage1.GameDisplay;
import com.example.game101.R;
import com.example.game101.objects.Player;

public class HealthBar {

    private Player player;
    private int width, height, margin;
    private Paint borderPaint, healthPaint;
    private Bitmap coeur;

    public HealthBar(Context context, Player player, Resources res){
       this.player = player;
       this.width = 200;
       this.height = 50;
       this.margin = 5;

       this.borderPaint = new Paint();
       int borderColor = ContextCompat.getColor(context, R.color.healthBarBorder);
       borderPaint.setColor(borderColor);

        this.healthPaint = new Paint();
        int healthColor = ContextCompat.getColor(context, R.color.healthBarHealth);
        healthPaint.setColor(healthColor);

        coeur = BitmapFactory.decodeResource(res, R.drawable.coeur);

        coeur = Bitmap.createScaledBitmap(coeur, 80, 80, false);

    }

    public void recycleImages(){
        this.coeur.recycle();
    }

   public void draw(Canvas canvas, GameDisplay gameDisplay){
       float x = (float) 160;
       float y = (float) 135;
       float distanceToPlayer = 40;
       float healthPointsPercentage =(float) player.getHealthPoints()/player.MAX_HEALTH_POINTS;

       float borderLeft, borderTop, borderRight, borderBottom;
       borderLeft = x - width/2;
       borderRight = x + width/2;
       borderBottom = y - distanceToPlayer;
       borderTop = borderBottom - height;

       canvas.drawRect(
               (float) borderLeft,
               (float) borderTop,
               (float) borderRight,
               (float) borderBottom,
               borderPaint
       );

       float healthLeft, healthTop, healthRight, healthBottom, healthWidth, healthHeight;

       healthWidth = width - 2 * margin;
       healthHeight = height - 2 * margin;
       healthLeft = borderLeft + margin;
       healthRight = healthLeft + healthWidth * healthPointsPercentage;
       healthBottom = borderBottom - margin;
       healthTop = healthBottom - healthHeight;

       canvas.drawRect(
               (float) healthLeft,
               (float) healthTop,
               (float) healthRight,
               (float) healthBottom,
               healthPaint
       );

try{
       canvas.drawBitmap(
               this.coeur,
               (float)  20,
               (float)  25,
               borderPaint
       );
}catch (RuntimeException e){
    e.printStackTrace();
}
   }
}
