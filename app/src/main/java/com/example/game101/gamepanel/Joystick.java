package com.example.game101.gamepanel;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.game101.R;
import com.example.game101.utils.Utils;

public class Joystick {
    private Paint innerCirclePaint;
    private Paint outerCirclePaint;
    private int outerCircleRadius;
    private int innerCircleRadius;
    private int outerCircleCenterPositionX;
    private int outerCircleCenterPositionY;
    private int innerCircleCenterPositionX;
    private int innerCircleCenterPositionY;
    private boolean isPressed = false;
    private double actuatorX;
    private double actuatorY;

    private Bitmap background;
    private Bitmap background2;
    private Paint paint;

    private int screenX;
    private int screenY;


    public Joystick(Resources res, int centerPositionX, int centerPositionY, int outerCircleRadius, int innerCircleRadius, int screenX, int screenY){
        //Outer and inner circle make up the joystick

        background = BitmapFactory.decodeResource(res, R.drawable.button1);
        background = Bitmap.createScaledBitmap(background, 100, 100, false);

        background2 = BitmapFactory.decodeResource(res, R.drawable.button2);
        background2 = Bitmap.createScaledBitmap(background2, 150, 150, false);

        this.screenX = screenX;
        this.screenY = screenY;

        outerCircleCenterPositionX = centerPositionX;
       outerCircleCenterPositionY = centerPositionY;
       innerCircleCenterPositionX = centerPositionX;
       innerCircleCenterPositionY = centerPositionY;

       //Radii of circles
       this.innerCircleRadius = innerCircleRadius;
       this.outerCircleRadius = outerCircleRadius;

       //paint of circles
        outerCirclePaint = new Paint();
        outerCirclePaint.setColor(Color.GRAY);
        outerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        innerCirclePaint = new Paint();
        innerCirclePaint.setColor(Color.BLUE);
        innerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        paint = new Paint();
    }

    public void draw(Canvas canvas) {
        //Draw outer circle
        canvas.drawCircle(
                outerCircleCenterPositionX,
                outerCircleCenterPositionY,
                outerCircleRadius,
                outerCirclePaint
        );

        //Draw inner circle
        canvas.drawCircle(
                innerCircleCenterPositionX,
                innerCircleCenterPositionY,
                innerCircleRadius,
                innerCirclePaint
        );

        try{
        canvas.drawBitmap(
                this.background,
                innerCircleCenterPositionX - 50,
                innerCircleCenterPositionY -50,
                paint
        );


        canvas.drawBitmap(
                this.background2,
                screenX - 200,
                screenY/2 + 25,
                paint
        );
        }catch (RuntimeException e){
            e.printStackTrace();
        }

    }

    public void recycleImages(){
        this.background.recycle();
        this.background2.recycle();
    }

    public boolean isPressed(double touchPositionx, double touchPositiony) {
        double joystickCenterToTouchDistance = Utils.getDistanceBetweenPoints(
                outerCircleCenterPositionX ,outerCircleCenterPositionY
                ,touchPositionx, touchPositiony
        );
        return joystickCenterToTouchDistance < 320;
    }

    public void setIsPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }

    public boolean getIsPressed() {
        return this.isPressed;
    }

    public void resetActuator() {
        actuatorX = 0.0;
        actuatorY = 0.0;
    }

    public void update() {
        updateInnerCirclePosition();
    }

    private void updateInnerCirclePosition(){
        innerCircleCenterPositionX = (int) (outerCircleCenterPositionX + actuatorX * outerCircleRadius);
        innerCircleCenterPositionY = (int) (outerCircleCenterPositionY + actuatorY * outerCircleRadius);
    }

    public void setActuator(double touchPositionX, double touchPositionY){
        double deltaX = touchPositionX - outerCircleCenterPositionX;
        double deltaY = touchPositionY - outerCircleCenterPositionY;
        double deltaDistance = Utils.getDistanceBetweenPoints(0,0,deltaX,deltaY);
        if(deltaDistance < outerCircleRadius){
            actuatorX = deltaX / outerCircleRadius;
            actuatorY = deltaY / outerCircleRadius;
        }else{
            actuatorX = deltaX / deltaDistance;
            actuatorY = deltaY / deltaDistance;
        }
    }

    public double getActuatorX() {
        return actuatorX;
    }

    public double getActuatorY() {
        return actuatorY;
    }
}
