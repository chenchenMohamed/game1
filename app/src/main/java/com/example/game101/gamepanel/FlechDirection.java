package com.example.game101.gamepanel;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.game101.Stage1.GameDisplay;
import com.example.game101.R;
import com.example.game101.objects.Player;

public class FlechDirection {

    public Bitmap flech;
    public Bitmap flechFixe;

    private Paint paint;

    private Player player;

    private int alpha = 0;

    private float x;
    private float y;

    private float xFlech = 0;
    private float yFlech = 0;

    private float rayon = 80;

    public FlechDirection(Resources res, Player player , Context context){
        this.player = player;


        flechFixe = BitmapFactory.decodeResource(res, R.drawable.flech);

        flechFixe = Bitmap.createScaledBitmap(flechFixe, 100, 100, false);

        int color = ContextCompat.getColor(context, R.color.enemy);
        paint = new Paint();
        paint.setColor(color);

    }

    public void recycleImages(){
       flech.recycle();
       flechFixe.recycle();
    }

    public void update(){
        this.calculeAlfa();
    }

    public void calculeAlfa(){

            //   alpha = (int) (180.0 / Math.PI * Math.atan2( 1 - unitaireX, - unitaireY));
            alpha = calculAngle(1,0, player.getDirectionX(), player.getDirectionY());
            if(0 > player.getDirectionY()){
                alpha = -1 * alpha;
            }

            Matrix matrix = new Matrix();
            // matrix.postRotate(alpha);
            matrix.setRotate(
                    alpha, // degrees
                    flechFixe.getWidth() / 2, // px
                    flechFixe.getHeight() / 2 // py
            );



            flech = Bitmap.createBitmap(flechFixe, 0, 0, flechFixe.getHeight(), flechFixe.getWidth(), matrix, true);
            // flech = scaleCenterCrop(flech,flechFixe.getHeight(),flechFixe.getWidth());




        this.x = (float) (rayon * player.getDirectionX() + player.getPositionX());
        this.y = (float) (rayon * player.getDirectionY() + player.getPositionY());

        this.xFlech = this.x - flech.getWidth() / 2;
        this.yFlech = this.y - flech.getHeight() / 2;

    }


    public int calculAngle(double x1, double y1, double x2, double y2 ){

        double dot = x1 * x2 + y1 * y2;

        double mag_v1 = Math.sqrt( x1 * x1 + y1 * y1);
        double mag_v2 = Math.sqrt( x2 * x2 + y2 * y2);

        double cosa = (double) (dot/(mag_v1 * mag_v2));

        int angle = (int) Math.toDegrees(Math.acos(cosa));

        return angle;
    }



    public void draw(Canvas canvas, GameDisplay gameDisplay){
        try{
           canvas.drawBitmap(
                   this.flech,
                   (float)gameDisplay.getGameToDisplayCoordinatesX(xFlech ),
                   (float)gameDisplay.getGameToDisplayCoordinatesY(yFlech),
                   paint
           );
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

}


