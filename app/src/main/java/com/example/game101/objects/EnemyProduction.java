package com.example.game101.objects;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.game101.Stage1.GameDisplay;
import com.example.game101.Stage1.GameLoop;
import com.example.game101.R;
import com.example.game101.gamepanel.GradeBar;
import com.example.game101.utils.ObjectCollider;
import com.example.game101.utils.PointGame;
import com.example.game101.utils.TrajetEnemyUtils;
import com.example.game101.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class EnemyProduction extends Circle{
    private final Player player;
    private static final double SPEED_PIXELS_PER_SECOND = Player.SPEED_PIXELS_PER_SECOND * 0.6;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;

    private  static final double SPAWNS_PER_MINUTE = 20;
    private  static final double SPAWNS_PER_SECOND = SPAWNS_PER_MINUTE/60.0;
    private  static final double UPDATES_PER_SPAWN = GameLoop.MAX_UPS/SPAWNS_PER_SECOND;
    private static double updatesUntilNextSpawn = UPDATES_PER_SPAWN;


    private boolean isBlock = false; // 0 for null 1 ,-1 for vertical else 2 ,-2 for horizontal
    private PointGame pointSiuvant;

    private AnimationEnemy animationEnemy;
    public EnemyProduction(Context context, Player player, double positionX, double positionY, double radius) {
        super(context, ContextCompat.getColor(context, R.color.enemy), positionX, positionY, radius);

        this.player = player;
    }
    private List<ObjectCollider> rectangleList = new ArrayList<ObjectCollider>();

    public EnemyProduction(AnimationEnemy animationEnemy, Resources res, Context context, Player player, List<Rectangle> rectangleList, double posX, double posY) {
        super(context,
                ContextCompat.getColor(context, R.color.enemy),
                posX,
                posY,
                40
        );

        this.animationEnemy = animationEnemy;

     /*   background = BitmapFactory.decodeResource(res, R.drawable.ballon2);
        background = Bitmap.createScaledBitmap(background, 150, 120, false);
*/

        for(int i = 0; i< rectangleList.size();i++){
            this.rectangleList.add(new ObjectCollider(rectangleList.get(i)));
        }
        this.player = player;

    }

    public static boolean readyToSpawn() {
        if(updatesUntilNextSpawn <= 0){
            updatesUntilNextSpawn += UPDATES_PER_SPAWN;
            return true;
        }else{
            updatesUntilNextSpawn--;
            return false;
        }
    }

    @Override
    public void update() {

        if(isBlock){
//            this.traitementIsBlock();
        }

        if(!isBlock){
  //          this.notTraitementIsBlock();
        }

      }


    public void draw(Canvas canvas, GameDisplay gameDisplay){
        //super.draw(canvas, gameDisplay);
        try{
            canvas.drawBitmap(
                    this.animationEnemy.getEnemyProduct(),
                    (float) gameDisplay.getGameToDisplayCoordinatesX(this.getPositionX()-(this.animationEnemy.getEnemyProduct().getWidth() / 2)),
                    (float) gameDisplay.getGameToDisplayCoordinatesY(this.getPositionY()-(this.animationEnemy.getEnemyProduct().getHeight() / 2)),
                    paint
            );

        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

}
