package com.example.game101.utils;

import android.util.Log;

import com.example.game101.objects.Circle;

import java.util.List;

public class Utils {
    public static double getDistanceBetweenPoints(double p1x, double p1y, double p2x, double p2y) {
        return Math.sqrt(
                Math.pow(p1x - p2x, 2) +
                Math.pow(p1y - p2y, 2)
        );
    }


    public static Velocity isColliddingGroupe(List<ObjectCollider> rectangleList, Circle obj2, double velocityX1, double velocityY1){

        for (ObjectCollider rectangle : rectangleList) {
            Velocity velocity =  calculVelocity(rectangle, obj2, velocityX1, velocityY1);
            if(velocity.velocityX != 3333){
                velocityX1 = velocity.velocityX;
            }
            if(velocity.velocityY != 3333) {
                velocityY1 = velocity.velocityY;
            }
        }

        return new Velocity(velocityX1, velocityY1);
    }


    public static Velocity calculVelocity(ObjectCollider obj1, Circle obj2, double velocityX1, double velocityY1){
        double velocityX = velocityX1;
        double velocityY = velocityY1;

        double positionXVertial = obj2.getPositionX() + velocityX;
        double positionYVertial = obj2.getPositionY() + velocityY;

        if(positionXVertial < obj1.rectangle.left - obj2.radius || positionXVertial > obj1.rectangle.right + obj2.radius ){
            return new Velocity(3333, 3333);
        }else if(positionYVertial < obj1.rectangle.top - obj2.radius || positionYVertial > obj1.rectangle.bottom + obj2.radius ){
            return new Velocity(3333, 3333);
        }else{
            double distX = Math.min(Math.abs(positionXVertial - (obj1.rectangle.left - obj2.radius)), Math.abs(positionXVertial - (obj1.rectangle.right + obj2.radius)))+1;
            double distY = Math.min(Math.abs(positionYVertial - (obj1.rectangle.top - obj2.radius)), Math.abs(positionYVertial - (obj1.rectangle.bottom + obj2.radius)))+1;

            double newVelocityX = velocityX;
            double newVelocityY = velocityY;

            if(velocityX > 0 ){
                 newVelocityX = velocityX - distX;
            }else if(velocityX < 0 ){
                 newVelocityX = velocityX + distX;
            }

            if(velocityY > 0 ){
                newVelocityY = velocityY - distY;
            }else if(velocityY < 0 ){
                newVelocityY = velocityY + distY;
            }

            double distance1 =  Utils.getDistanceBetweenPoints(obj2.getPositionX(), obj2.getPositionY(),obj2.getPositionX()+newVelocityX,positionYVertial);
            double distance2 =  Utils.getDistanceBetweenPoints(obj2.getPositionX(), obj2.getPositionY(),positionXVertial, obj2.getPositionY()+newVelocityY);

            if(distance1 < distance2){
                double newPosition = obj2.getPositionX()+newVelocityX;
                if(newPosition < obj1.rectangle.left - obj2.radius || newPosition > obj1.rectangle.right + obj2.radius ){
                    return new Velocity(newVelocityX, velocityY);
                }else if(positionYVertial < obj1.rectangle.top - obj2.radius || positionYVertial > obj1.rectangle.bottom + obj2.radius ) {
                    return new Velocity(newVelocityX, velocityY);
                }else{
                    return new Velocity(velocityX, newVelocityY);
                }
            }else if(distance1 > distance2){
                double newPosition = obj2.getPositionY()+newVelocityY;
                if(positionXVertial < obj1.rectangle.left - obj2.radius || positionXVertial > obj1.rectangle.right + obj2.radius ){
                    return new Velocity(velocityX, newVelocityY);
                }else if(newPosition < obj1.rectangle.top - obj2.radius || newPosition > obj1.rectangle.bottom + obj2.radius ) {
                    return new Velocity(velocityX, newVelocityY);
                }else{
                    return new Velocity(newVelocityX, velocityY);
                }
            }else{
                return new Velocity(newVelocityX, newVelocityY);
            }

        }
    }

    public static class Velocity{
        public double velocityX;
        public double velocityY;

        public Velocity(double velocityX, double velocityY){
            this.velocityX = velocityX;
            this.velocityY = velocityY;
        }
    }

}




/*
///version 2 pour la collission

public static Velocity isColliddingRectangleCircle(ObjectCollider obj1, Circle obj2, double velocityX1, double velocityY1){
        double velocityX = velocityX1;
        double velocityY = velocityY1;

        double positionXVertial = obj2.getPositionX() + velocityX;
        double positionYVertial = obj2.getPositionY() + velocityY;

        double distX = isCollidding(obj1.rectangle.left, obj1.rectangle.top, obj1.rectangle.left, obj1.rectangle.bottom, obj2, positionXVertial, positionYVertial);
        double distX2 = isCollidding(obj1.rectangle.right, obj1.rectangle.top, obj1.rectangle.right, obj1.rectangle.bottom, obj2, positionXVertial, positionYVertial);
        if(distX2 < distX ){
            distX = distX2;
        }

        double distY = isCollidding(obj1.rectangle.left, obj1.rectangle.top, obj1.rectangle.right, obj1.rectangle.top, obj2, positionXVertial, positionYVertial);
        double distY2 = isCollidding(obj1.rectangle.left, obj1.rectangle.bottom, obj1.rectangle.right, obj1.rectangle.bottom, obj2, positionXVertial, positionYVertial);
        if(distY2 < distY ){
            distY = distY2;
        }

      // distX = obj2.radius + 5;
      // distY = obj2.radius + 5;

        if(distX == 2222 && distY == 2222) {
            double LAC = Utils.getDistanceBetweenPoints(obj1.rectangle.left, obj1.rectangle.top, positionXVertial, positionYVertial);
            double LBC = Utils.getDistanceBetweenPoints(obj1.rectangle.left, obj1.rectangle.bottom, positionXVertial, positionYVertial);
            double LDC = Utils.getDistanceBetweenPoints(obj1.rectangle.right, obj1.rectangle.top, positionXVertial, positionYVertial);
            double LCC = Utils.getDistanceBetweenPoints(obj1.rectangle.right, obj1.rectangle.bottom, positionXVertial, positionYVertial);
            double minDistanceAuCorne = Math.min(Math.min(LAC, LBC),Math.min(LDC, LCC));



            if(minDistanceAuCorne < obj2.radius){
                double positionXVertial2 = obj2.getPositionX() + velocityX;
                double positionYVertial2 = obj2.getPositionY() + velocityY;
                double distVerticalX;
                double distHorizontalY;

                if(LAC < obj2.radius){
                    distVerticalX = Utils.getDistanceBetweenPoints(positionXVertial2, obj2.getPositionY(), obj1.rectangle.left, obj1.rectangle.top);
                    distHorizontalY = Utils.getDistanceBetweenPoints(obj2.getPositionX(), positionYVertial2, obj1.rectangle.left, obj1.rectangle.top);
                }else if(LBC < obj2.radius){
                    distVerticalX = Utils.getDistanceBetweenPoints(positionXVertial2, obj2.getPositionY(), obj1.rectangle.left, obj1.rectangle.bottom);
                    distHorizontalY = Utils.getDistanceBetweenPoints(obj2.getPositionX(), positionYVertial2, obj1.rectangle.left, obj1.rectangle.bottom);
                }else if(LDC < obj2.radius){
                    distVerticalX = Utils.getDistanceBetweenPoints(positionXVertial2, obj2.getPositionY(), obj1.rectangle.right, obj1.rectangle.top);
                    distHorizontalY = Utils.getDistanceBetweenPoints(obj2.getPositionX(), positionYVertial2, obj1.rectangle.right, obj1.rectangle.top);
                }else{
                    distVerticalX = Utils.getDistanceBetweenPoints(positionXVertial2, obj2.getPositionY(), obj1.rectangle.right, obj1.rectangle.bottom);
                    distHorizontalY = Utils.getDistanceBetweenPoints(obj2.getPositionX(), positionYVertial2, obj1.rectangle.right, obj1.rectangle.bottom);
                }


               Log.w("collission11", "fin");
               if(distVerticalX < distHorizontalY){
                   velocityX = Utils.calculReduction(distVerticalX, velocityX, obj2.radius);
               }else{
                   velocityY = Utils.calculReduction(distHorizontalY, velocityY, obj2.radius);
               }

            }
            return new Velocity(velocityX, velocityY);
        }

        if(distX < obj2.radius){
            velocityX = Utils.calculReduction(distX, velocityX, obj2.radius);
        }

        if(distY < obj2.radius){
           velocityY = Utils.calculReduction(distY, velocityY, obj2.radius);
        }


        return new Velocity(velocityX, velocityY);
    }

    public static double calculReduction(double dist, double velocity, double radius){
        if(velocity > 0){
            //Log.w("collission33", distY+" velocityX= "+ velocityX + ", velocityY= "+velocityY);
            velocity -= radius - dist;
            if(velocity <0){
                velocity = 0;
            }
            return  velocity;
        }else{
            //Log.w("collission44", distY+" velocityX= "+ velocityX + ", velocityY= "+velocityY);
            velocity += radius - dist;
            if(velocity >0){
                velocity = 0;
            }
            return  velocity;
        }

    }

    public static double isCollidding(float ax, float ay, float bx, float by, Circle obj2, double positionXVertial, double positionYVertial){

        double LAB = Utils.getDistanceBetweenPoints(ax, ay, bx, by);

        double dx = (bx - ax)/LAB;
        double dy = (by - ay)/LAB;

        double t = dx*(positionXVertial - ax) + dy*(positionYVertial - ay);


        double ex = t * dx + ax;
        double ey = t * dy + ay;


        double LAE = Utils.getDistanceBetweenPoints(ax, ay, ex, ey);

        double LEC = Utils.getDistanceBetweenPoints(ex, ey, positionXVertial, positionYVertial);

        if( LEC > obj2.radius){
            return 3333;
        }

        if(t < 0){
            return 2222;
        }else if(LAE > LAB){
            return 2222;
        }

        if( LEC < obj2.radius){
            return LEC;
        }

        return 3333;

    }



/// version 1 pour la collission

 public static Velocity isColliddingRectangleCircle(ObjectCollider obj1, Circle obj2, double velocityX1, double velocityY1){
        double velocityX = velocityX1;
        double velocityY = velocityY1;

        double positionXVertial = obj2.getPositionX() + velocityX;
        double positionYVertial = obj2.getPositionY() + velocityY;



        double dist = 0;
        if(obj1.isVertical){
          dist = isColliddingTestVerticale(obj1,obj2,positionXVertial,positionYVertial);
        }else{
          dist = isColliddingTestHorizontal(obj1,obj2,positionXVertial,positionYVertial);
        }

       if(obj1.isVertical){
            if(dist < obj2.radius){
                if(velocityX > 0){
                    Log.w("collission11", dist+" velocityX= "+ velocityX + ", velocityY= "+velocityY);
                    velocityX -= obj2.radius - dist;
                }else{
                    Log.w("collission22", dist+" velocityX= "+ velocityX + ", velocityY= "+velocityY);

                    velocityX += obj2.radius - dist;
                }
            }
       }else{
            if(dist < obj2.radius){
                if(velocityY > 0){
                    Log.w("collission33", dist+" velocityX= "+ velocityX + ", velocityY= "+velocityY);
                    velocityY -= obj2.radius - dist;
                }else{
                    Log.w("collission44", dist+" velocityX= "+ velocityX + ", velocityY= "+velocityY);
                    velocityY += obj2.radius - dist;
                }
            }
       }


        return new Velocity(velocityX, velocityY);
    }

    public static double isColliddingTestVerticale(ObjectCollider obj1, Circle obj2, double positionXVertial, double positionYVertial){

        if(obj2.getPositionX() < obj1.rectangle.left){
            obj1.isVertical = true;
            return isCollidding(obj1.rectangle.left, obj1.rectangle.top, obj1.rectangle.left, obj1.rectangle.bottom, obj2, positionXVertial, positionYVertial);
        }else if(obj2.getPositionX() > obj1.rectangle.right) {
            obj1.isVertical = true;
            return isCollidding(obj1.rectangle.right, obj1.rectangle.top, obj1.rectangle.right, obj1.rectangle.bottom, obj2, positionXVertial, positionYVertial);
        }else if(obj2.getPositionY() < obj1.rectangle.top) {
            obj1.isVertical = false;
            return isCollidding(obj1.rectangle.left, obj1.rectangle.top, obj1.rectangle.right, obj1.rectangle.top, obj2, positionXVertial, positionYVertial);
        }else{
            obj1.isVertical = false;
            return isCollidding(obj1.rectangle.left, obj1.rectangle.bottom, obj1.rectangle.right, obj1.rectangle.bottom, obj2, positionXVertial, positionYVertial);
        }
    }

    public static double isColliddingTestHorizontal(ObjectCollider obj1, Circle obj2, double positionXVertial, double positionYVertial){

        if(obj2.getPositionY() < obj1.rectangle.top) {
            obj1.isVertical = false;
            return isCollidding(obj1.rectangle.left, obj1.rectangle.top, obj1.rectangle.right, obj1.rectangle.top, obj2, positionXVertial, positionYVertial);
        }else if(obj2.getPositionY() > obj1.rectangle.bottom) {
            obj1.isVertical = false;
            return isCollidding(obj1.rectangle.left, obj1.rectangle.bottom, obj1.rectangle.right, obj1.rectangle.bottom, obj2, positionXVertial, positionYVertial);
        }else if(obj2.getPositionX() < obj1.rectangle.left){
            obj1.isVertical = true;
            return isCollidding(obj1.rectangle.left, obj1.rectangle.top, obj1.rectangle.left, obj1.rectangle.bottom, obj2, positionXVertial, positionYVertial);
        }else{
            obj1.isVertical = true;
            return isCollidding(obj1.rectangle.right, obj1.rectangle.top, obj1.rectangle.right, obj1.rectangle.bottom, obj2, positionXVertial, positionYVertial);
        }
    }

    public static double isCollidding(float ax, float ay, float bx, float by, Circle obj2, double positionXVertial, double positionYVertial){

        double LAB = Utils.getDistanceBetweenPoints(ax, ay, bx, by);

        double dx = (bx - ax)/LAB;
        double dy = (by - ay)/LAB;

        double t = dx*(positionXVertial - ax) + dy*(positionYVertial - ay);


        double ex = t * dx + ax;
        double ey = t * dy + ay;


        double LAC = Utils.getDistanceBetweenPoints(ax, ay, positionXVertial, positionYVertial);
        double LBC = Utils.getDistanceBetweenPoints(bx, by, positionXVertial, positionYVertial);
        double LAE = Utils.getDistanceBetweenPoints(ax, ay, ex, ey);


        if(t < 0){
            if(LAC < obj2.radius){
                return LAC;
            }
            return 3333;
        }else if(LAE > LAB){
            if(LBC < obj2.radius){
                return LBC;
            }
            return 3333;
        }



        double LEC = Utils.getDistanceBetweenPoints(ex, ey, positionXVertial, positionYVertial);

        if( LEC < obj2.radius){
            return LEC;
        }

        return 3333;
    }

 */