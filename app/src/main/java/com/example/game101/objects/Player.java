package com.example.game101.objects;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.game101.Stage1.GameDisplay;
import com.example.game101.Stage1.GameLoop;
import com.example.game101.gamepanel.FlechDirection;
import com.example.game101.gamepanel.FlechPlayer;
import com.example.game101.gamepanel.HealthBar;
import com.example.game101.gamepanel.Joystick;
import com.example.game101.R;
import com.example.game101.utils.ObjectCollider;
import com.example.game101.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Player extends Circle {
    public static final int MAX_HEALTH_POINTS = 10;
    private Paint paint;

    private List<ObjectCollider> rectangleList = new ArrayList<ObjectCollider>();
    private double positionXVertial = 0;
    private double positionYVertial = 0;

    public double velocityXFinal = 0;
    public double velocityYFinal = 0;

    protected static final double SPEED_PIXELS_PER_SECOND = 400.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;

    private final Joystick joystick;

    private FlechPlayer flechPlayer;
    private FlechDirection flechDirection;
    private HealthBar healthBar;
    private int healthPoints;

    private List<Ballon> ballonList;
    private AnimationPlayer animationPlayer;



    public Player(AnimationPlayer animationPlayer, Resources res, Context context, Joystick joystick, double positionX, double positionY, double radius, List<Rectangle> rectangleList, List<Ballon> ballonList, int screenX, int screenY){
       super(context, ContextCompat.getColor(context, R.color.player), positionX, positionY, radius);
     /*  background = BitmapFactory.decodeResource(res, R.drawable.ballon);
       background = Bitmap.createScaledBitmap(background, 95, 95, false);
     */

        this.animationPlayer = animationPlayer;
       this.ballonList = ballonList;

       this.flechPlayer = new FlechPlayer(res, this, ballonList, context, screenX, screenY);
       this.flechDirection = new FlechDirection(res,this, context);

       this.joystick = joystick;
       this.healthBar = new HealthBar(context,this, res);

       this.healthPoints = MAX_HEALTH_POINTS;
       for(int i = 0; i< rectangleList.size();i++){
           this.rectangleList.add(new ObjectCollider(rectangleList.get(i)));
       }
    }

    public void recycleImages(){
        this.joystick.recycleImages();
        this.flechDirection.recycleImages();
        this.flechPlayer.recycleImages();
        this.healthBar.recycleImages();
    }

    public void update() {

        velocityX = joystick.getActuatorX() * MAX_SPEED;
        velocityY = joystick.getActuatorY() * MAX_SPEED;

        Utils.Velocity objetVelocity = Utils.isColliddingGroupe(rectangleList, this, velocityX, velocityY);

        velocityX = objetVelocity.velocityX;
        velocityY = objetVelocity.velocityY;

        this.velocityXFinal = velocityX;
        this.velocityYFinal = velocityY;

        positionX += velocityX ;
        positionY += velocityY ;

        if(velocityX != 0 || velocityY != 0){
            double distance = Utils.getDistanceBetweenPoints(0,0,velocityX, velocityY);
            directionX = velocityX/distance;
            directionY = velocityY/distance;
        }

        flechPlayer.update();
        flechDirection.update();


    }

    public void draw(Canvas canvas, GameDisplay gameDisplay){
        //super.draw(canvas, gameDisplay);
        healthBar.draw(canvas, gameDisplay);
        flechPlayer.draw(canvas, gameDisplay);
        flechDirection.draw(canvas, gameDisplay);
try{
          canvas.drawBitmap(
                    this.animationPlayer.getPlayer(),
                    (float) gameDisplay.getGameToDisplayCoordinatesX(this.getPositionX()-42),
                    (float) gameDisplay.getGameToDisplayCoordinatesY(this.getPositionY()-42),
                    paint
          );
}catch (RuntimeException e){
    e.printStackTrace();
}

    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints -= healthPoints;
        if(this.healthPoints < 0){
            this.healthPoints = 0;
        }
        
    }

}


