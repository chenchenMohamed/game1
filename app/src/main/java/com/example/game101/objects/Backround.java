package com.example.game101.objects;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.game101.Stage1.GameDisplay;
import com.example.game101.R;

public class Backround {

    private Bitmap[] backgrounds;

    private Paint paint;


    public Backround(Resources res){
        backgrounds = null;
        backgrounds = new Bitmap[2];

        /*backgrounds[0] = BitmapFactory.decodeResource(res, R.drawable.texture);
        backgrounds[0] = Bitmap.createScaledBitmap(backgrounds[0], 600, 600, false);
*/
        backgrounds[0] = BitmapFactory.decodeResource(res, R.drawable.texture2);
        backgrounds[0] = Bitmap.createScaledBitmap(backgrounds[0], 200, 200, false);

        backgrounds[1] = BitmapFactory.decodeResource(res, R.drawable.desert);
        backgrounds[1] = Bitmap.createScaledBitmap(backgrounds[1], 3000, 3000, false);

        paint = new Paint();
    }

    public void recycleImages(){
       backgrounds[0].recycle();
       backgrounds[1].recycle();
    }



    public void draw(Canvas canvas, GameDisplay gameDisplay){
/// fermeture principale
 // center

        try {
            canvas.drawBitmap(
                    backgrounds[1],
                    (float) gameDisplay.getGameToDisplayCoordinatesX(-600),
                    (float) gameDisplay.getGameToDisplayCoordinatesY(-100),
                    paint
            );
//top
            canvas.drawBitmap(
                    backgrounds[1],
                    (float) gameDisplay.getGameToDisplayCoordinatesX(-3600),
                    (float) gameDisplay.getGameToDisplayCoordinatesY(-3100),
                    paint
            );

            canvas.drawBitmap(
                    backgrounds[1],
                    (float) gameDisplay.getGameToDisplayCoordinatesX(-600),
                    (float) gameDisplay.getGameToDisplayCoordinatesY(-3100),
                    paint
            );

            canvas.drawBitmap(
                    backgrounds[1],
                    (float) gameDisplay.getGameToDisplayCoordinatesX(2400),
                    (float) gameDisplay.getGameToDisplayCoordinatesY(-3100),
                    paint
            );
//left
            canvas.drawBitmap(
                    backgrounds[1],
                    (float) gameDisplay.getGameToDisplayCoordinatesX(-3600),
                    (float) gameDisplay.getGameToDisplayCoordinatesY(-100),
                    paint
            );
//right
            canvas.drawBitmap(
                    backgrounds[1],
                    (float) gameDisplay.getGameToDisplayCoordinatesX(2400),
                    (float) gameDisplay.getGameToDisplayCoordinatesY(-100),
                    paint
            );
//bottom
            canvas.drawBitmap(
                    backgrounds[1],
                    (float) gameDisplay.getGameToDisplayCoordinatesX(-3600),
                    (float) gameDisplay.getGameToDisplayCoordinatesY(2900),
                    paint
            );

            canvas.drawBitmap(
                    backgrounds[1],
                    (float) gameDisplay.getGameToDisplayCoordinatesX(-600),
                    (float) gameDisplay.getGameToDisplayCoordinatesY(2900),
                    paint
            );

            canvas.drawBitmap(
                    backgrounds[1],
                    (float) gameDisplay.getGameToDisplayCoordinatesX(2400),
                    (float) gameDisplay.getGameToDisplayCoordinatesY(-2900),
                    paint
            );

//fini
        canvas.drawBitmap(
                    backgrounds[0],
                    (float) gameDisplay.getGameToDisplayCoordinatesX(100),
                    (float) gameDisplay.getGameToDisplayCoordinatesY(100),
                    paint
         );

        // autres


        }catch (RuntimeException e){
            e.printStackTrace();
        }

    }

}
