package com.example.game101.utils;

import com.example.game101.objects.Rectangle;

import java.util.List;

public class TrajetEnemyUtils {

private static PointGame[] arrayPoints;

public static void setRectangles(List<Rectangle> rectangleList){
        arrayPoints = new PointGame[rectangleList.size() * 2];

        int count = 0;

        for(Rectangle rect:rectangleList){
          arrayPoints[count] = new PointGame(rect.left - 50, rect.top -50);
          count++;
          arrayPoints[count] = new PointGame(rect.right + 50, rect.bottom +50);
          count++;
        }
}


public static PointGame pointPlusProche(double x, double y){
    double dist = 10000;
    PointGame p1 = new PointGame(0,0);
    for(PointGame point:arrayPoints){
        if(Utils.getDistanceBetweenPoints(point.x, point.y, x, y) < dist){
            dist = Utils.getDistanceBetweenPoints(point.x, point.y, x, y);
            p1 = point;
        }
    }
    return p1;
}

}

