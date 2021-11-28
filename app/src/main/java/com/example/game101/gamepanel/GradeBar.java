package com.example.game101.gamepanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.game101.Stage1.GameDisplay;
import com.example.game101.R;
import com.example.game101.objects.Enemy;

public class GradeBar {

    private Enemy enemy;
    private int width, height, margin;
    private Paint borderPaint, healthPaint;


    public GradeBar(Context context, Enemy enemy){
        this.enemy = enemy;
        this.width = 100;
        this.height = 20;
        this.margin = 2;

        this.borderPaint = new Paint();
        int borderColor = ContextCompat.getColor(context, R.color.healthBarBorder);
        borderPaint.setColor(borderColor);

        this.healthPaint = new Paint();
        int healthColor = ContextCompat.getColor(context, R.color.player);
        healthPaint.setColor(healthColor);

    }

    public void draw(Canvas canvas, GameDisplay gameDisplay){
        float x = (float) enemy.getPositionX();
        float y = (float) enemy.getPositionY();
        float distanceToPlayer = 40;
        float healthPointsPercentage =(float) enemy.grade/10;

        float borderLeft, borderTop, borderRight, borderBottom;
        borderLeft = x - width/2;
        borderRight = x + width/2;
        borderBottom = y - distanceToPlayer;
        borderTop = borderBottom - height;

        canvas.drawRect(
                (float) gameDisplay.getGameToDisplayCoordinatesX(borderLeft),
                (float) gameDisplay.getGameToDisplayCoordinatesY(borderTop),
                (float) gameDisplay.getGameToDisplayCoordinatesX(borderRight),
                (float) gameDisplay.getGameToDisplayCoordinatesY(borderBottom),
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
                (float) gameDisplay.getGameToDisplayCoordinatesX(healthLeft),
                (float) gameDisplay.getGameToDisplayCoordinatesY(healthTop),
                (float) gameDisplay.getGameToDisplayCoordinatesX(healthRight),
                (float) gameDisplay.getGameToDisplayCoordinatesY(healthBottom),
                healthPaint
        );

    }
}

