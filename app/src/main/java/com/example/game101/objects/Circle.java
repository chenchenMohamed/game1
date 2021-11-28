package com.example.game101.objects;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.game101.Stage1.GameDisplay;

public abstract class Circle extends GameObject{

    public double radius;
    protected Paint paint;
    public boolean isCollidingVertical = true;

    public Circle(Context context, int color, double positionX, double positionY, double radius) {
        super(positionX, positionY);

        this.radius = radius;
        paint = new Paint();
        paint.setColor(color);
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay){
        canvas.drawCircle(
                (float) gameDisplay.getGameToDisplayCoordinatesX(positionX),
                (float) gameDisplay.getGameToDisplayCoordinatesY(positionY),
                (float) radius,
                paint
        );
    }

    public static boolean isColliding(Circle obj1, Circle obj2) {
        double distance = getDistanceBetweenObjects(obj1,obj2);
        double distanceToCollision = obj1.getRadius() + obj2.getRadius();
        if(distance < distanceToCollision) return true;
        else return false;
    }

    public double getRadius() {
        return radius;
    }
}
