package com.example.game101.Stage1;

import com.example.game101.objects.GameObject;

public class GameDisplay {
    private double displayCenterX;
    private double displayCenterY;

    private double gameCenterX;
    private double gameCenterY;

    private GameObject centerObject;

    private double gameToDisplayCoordinateOffsetX;
    private double gameToDisplayCoordinateOffsetY;

    public GameDisplay(int widthPixels, int heightPixels, GameObject centerObject){
        this.centerObject = centerObject;

        displayCenterX = widthPixels/2.0;
        displayCenterY = heightPixels/2.0;
    }

    public void update(){
        gameCenterX = centerObject.getPositionX();
        gameCenterY = centerObject.getPositionY();

        gameToDisplayCoordinateOffsetX = displayCenterX - gameCenterX;
        gameToDisplayCoordinateOffsetY = displayCenterY - gameCenterY;
    }
    public double getGameToDisplayCoordinatesX(double x){
        return x + gameToDisplayCoordinateOffsetX;
    }

    public double getGameToDisplayCoordinatesY(double y){
        return y + gameToDisplayCoordinateOffsetY;
    }
}
