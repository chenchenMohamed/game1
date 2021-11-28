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
import com.example.game101.objects.Ballon;
import com.example.game101.objects.Player;
import com.example.game101.utils.Utils;

import java.util.List;

public class FlechPlayer {

    public Bitmap flech;
    public Bitmap flechFixe;

    private Paint paint;
    private Paint paint2;

    private Player player;
    private List<Ballon> ballonList;

    private Ballon balChoisi;

    private int alpha = 0;
    private boolean isChangeAlpha = false;

    private float x;
    private float y;

    private float xFlech = 0;
    private float yFlech = 0;

    private float rayon = 80;
    private float yDistance = -45;
    private int screenX;
    private int screenY;

    public FlechPlayer(Resources res, Player player , List<Ballon> ballonList, Context context, int screenX, int screenY){
        this.player = player;

        this.screenX = screenX;
        this.screenY = screenY;
        this.ballonList = ballonList;
        this.balChoisi = ballonList.get(0);

        flechFixe = BitmapFactory.decodeResource(res, R.drawable.flechdirection);

        flechFixe = Bitmap.createScaledBitmap(flechFixe, 150, 150, false);

        int color2 = ContextCompat.getColor(context, R.color.player);
        paint = new Paint();
        paint.setColor(color2);

        int color = ContextCompat.getColor(context, R.color.enemy);
        paint2 = new Paint();
        paint2.setColor(color);

    }

    public void recycleImages(){
        flech.recycle();
        flechFixe.recycle();
    }

    public void update(){
        Ballon ballon = ballonList.get(0);
        for(Ballon ball:ballonList){
            double dist1 = Utils.getDistanceBetweenPoints(player.getPositionX(), player.getPositionY(), ball.x, ball.y);
            double dist2 = Utils.getDistanceBetweenPoints(player.getPositionX(), player.getPositionY(), ballon.x, ballon.y);
            if(dist1<dist2){
                ballon = ball;
            }
        }
        this.balChoisi = ballon;
        this.calculeAlfa();
    }

    public void calculeAlfa(){

        double LPP = Utils.getDistanceBetweenPoints(player.getPositionX(), player.getPositionY(), player.getPositionX() + rayon, player.getPositionY());

        double dx = -rayon/LPP;

        double t = dx*(balChoisi.x - player.getPositionX()) ;

        double ex = t * dx + player.getPositionX();
        double ey = player.getPositionY();

        double LPA = Utils.getDistanceBetweenPoints(player.getPositionX(), player.getPositionY(), ex, ey);

        double LPB = Utils.getDistanceBetweenPoints(player.getPositionX(), player.getPositionY(), balChoisi.x, balChoisi.y);

        double unitaireX = (balChoisi.x - player.getPositionX())/LPB;
        double unitaireY = (balChoisi.y - player.getPositionY())/LPB;

        if (LPB != 0) {


         // alpha = (int) (180.0 / Math.PI * Math.atan2( 1 - unitaireX, - unitaireY));
            alpha = calculAngle(1,0,unitaireX,unitaireY);
            if(balChoisi.y < player.getPositionY()){
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


        }

       // this.x = (float) (rayon * unitaireX + player.getPositionX());
       // this.y = (float) (rayon * unitaireY + player.getPositionY());

        this.x = (float) (unitaireX );
        this.y = (float) (unitaireY );

        this.xFlech = this.x - flech.getWidth() / 2;
        this.yFlech = this.y - flech.getHeight() / 2;

    }



/*
    public Bitmap scaleCenterCrop(Bitmap source, int newHeight, int newWidth) {
        int sourceWidth = source.getWidth();
        int sourceHeight = source.getHeight();

        // Compute the scaling factors to fit the new height and width, respectively.
        // To cover the final image, the final scaling will be the bigger
        // of these two.
        float xScale = (float) newWidth / sourceWidth;
        float yScale = (float) newHeight / sourceHeight;
        float scale = Math.max(xScale, yScale);

        // Now get the size of the source bitmap when scaled
        float scaledWidth = scale * sourceWidth;
        float scaledHeight = scale * sourceHeight;

        // Let's find out the upper left coordinates if the scaled bitmap
        // should be centered in the new size give by the parameters
        float left = (newWidth - scaledWidth) / 2;
        float top = (newHeight - scaledHeight) / 2;

        // The target rectangle for the new, scaled version of the source bitmap will now
        // be
        RectF targetRect = new RectF(left, top, left + scaledWidth, top + scaledHeight);

        // Finally, we create a new bitmap of the specified size and draw our new,
        // scaled bitmap onto it.
        Bitmap dest = Bitmap.createBitmap(newWidth, newHeight, source.getConfig());
        Canvas canvas = new Canvas(dest);
        canvas.drawBitmap(source, null, targetRect, null);

        return dest;
    }
*/
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
                   (float)  screenX/2 + xFlech,
                   (float)  screenY - 120 + yFlech,
                   paint
           );
       }catch (RuntimeException e){
           e.printStackTrace();
       }
    }

}
