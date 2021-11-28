package com.example.game101.objects;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.game101.R;
import com.example.game101.Stage1.Game;

public class AnimationBombe {
    public Bitmap[] ballon = new Bitmap[9];

    public AnimationBombe(Resources res){
        Bitmap ballon1 = BitmapFactory.decodeResource(res, R.drawable.bombe1);
        ballon1 = Bitmap.createScaledBitmap(ballon1, 100, 100, false);
        ballon[0] = ballon1;

        ballon1 = BitmapFactory.decodeResource(res, R.drawable.bombe2);
        ballon1 = Bitmap.createScaledBitmap(ballon1, 100, 100, false);
        ballon[1] = ballon1;

        ballon1 = BitmapFactory.decodeResource(res, R.drawable.bombe3);
        ballon1 = Bitmap.createScaledBitmap(ballon1, 100, 100, false);
        ballon[2] = ballon1;

        ballon1 = BitmapFactory.decodeResource(res, R.drawable.bombe4);
        ballon1 = Bitmap.createScaledBitmap(ballon1, 100, 100, false);
        ballon[3] = ballon1;

        ballon1 = BitmapFactory.decodeResource(res, R.drawable.bombe5);
        ballon1 = Bitmap.createScaledBitmap(ballon1, 100, 100, false);
        ballon[4] = ballon1;

        ballon1 = BitmapFactory.decodeResource(res, R.drawable.bombe6);
        ballon1 = Bitmap.createScaledBitmap(ballon1, 100, 100, false);
        ballon[5] = ballon1;

        ballon1 = BitmapFactory.decodeResource(res, R.drawable.bombe7);
        ballon1 = Bitmap.createScaledBitmap(ballon1, 100, 100, false);
        ballon[6] = ballon1;

        ballon1 = BitmapFactory.decodeResource(res, R.drawable.bombe8);
        ballon1 = Bitmap.createScaledBitmap(ballon1, 100, 100, false);
        ballon[7] = ballon1;

        ballon1 = BitmapFactory.decodeResource(res, R.drawable.bombe9);
        ballon1 = Bitmap.createScaledBitmap(ballon1, 100, 100, false);
        ballon[8] = ballon1;
    }

    public void recycleImages(){
        //ballon.recycle();

        for(int i = 0; i < 4; i++){
            ballon[i].recycle();
        }

    }

    public Bitmap getEnemyProduct(int pos){
        if(pos > (ballon.length-1)){
            return ballon[ballon.length-1];
        }
        return ballon[pos];
    }


}
