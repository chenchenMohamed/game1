package com.example.game101.objects;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.game101.Stage1.Game;
import com.example.game101.Stage1.GameLoop;
import com.example.game101.R;
import com.example.game101.utils.ObjectCollider;
import com.example.game101.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Spell extends Circle  {

    private static final double SPEED_PIXELS_PER_SECOND =  800.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;

    private List<ObjectCollider> rectangleList = new ArrayList<ObjectCollider>();
    private Game game;

    public Spell(Context context, Player spellcaster, List<Rectangle> rectangleList, Game game) {
       super(
               context,
               ContextCompat.getColor(context, R.color.spell),
               spellcaster.getPositionX(),
               spellcaster.getPositionY(),
               25
       );


        for(int i = 0; i< rectangleList.size();i++){
            this.rectangleList.add(new ObjectCollider(rectangleList.get(i)));
        }

        this.game = game;

        velocityX = spellcaster.getDirectionX()*MAX_SPEED;
        velocityY = spellcaster.getDirectionY()*MAX_SPEED;



    }

    @Override
    public void update() {
        Utils.Velocity objetVelocity = Utils.isColliddingGroupe(rectangleList, this, velocityX, velocityY);

        if(objetVelocity.velocityX != velocityX || objetVelocity.velocityY != velocityY){
            game.deleteSpell(this);
        }
        positionX += velocityX;
        positionY += velocityY;
    }
}
