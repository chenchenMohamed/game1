package com.example.game101.objects;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.game101.Stage1.GameDisplay;

public class Rectangle extends GameObject {

    public float left;
    public float top;
    public float right;
    public float bottom;

    protected Paint paint;

    public Rectangle(Context context, int color, double positionX, double positionY, float left, float top, float right, float bottom ) {
        super(positionX, positionY);

        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;

        paint = new Paint();
        paint.setColor(color);
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay){
        canvas.drawRect(
                (float) gameDisplay.getGameToDisplayCoordinatesX(left),
                (float) gameDisplay.getGameToDisplayCoordinatesY(top),
                (float) gameDisplay.getGameToDisplayCoordinatesX(right),
                (float) gameDisplay.getGameToDisplayCoordinatesY(bottom),
                paint
        );
    }

    @Override
    public void update() {

    }



    /*
    public static boolean isColliding(Circle obj1, Rectangle obj2) {
        double distance = getDistanceBetweenObjects(obj1,obj2);
        double distanceToCollision = obj1.getRadius() + obj2.getRadius();
        if(distance < distanceToCollision) return true;
        else return false;
    }
    */

}
