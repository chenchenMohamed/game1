package com.example.game101.utils;

import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;

import com.example.game101.Stage1.Game;
import com.example.game101.R;
import com.example.game101.objects.Enemy;
import com.example.game101.objects.EnemyProduction;
import com.example.game101.objects.Player;
import com.example.game101.objects.Rectangle;

import java.util.List;

public class TestCollissionEnemy extends Thread {
    private Game game;
    private Resources res;
    private Context context;
    private Player player;
    private List<Rectangle> rectangleList;
    public boolean isRun = true;
    private MediaPlayer mediaPlayer;
    private MediaPlayer soungIntroduction;


    public TestCollissionEnemy(Game game, Resources res, Context context, Player player, List<Rectangle> rectangleList) {
        this.game = game;
        this.res = res;
        this.context = context;
        this.player = player;
        this.rectangleList = rectangleList;
        mediaPlayer = MediaPlayer.create(context, R.raw.buzzersound);
        mediaPlayer.setVolume(0.5f,0.5f);

        soungIntroduction = MediaPlayer.create(context, R.raw.pacmanntroduction);
        soungIntroduction.start();

        start();
    }

    @Override
    public void run() {

        super.run();

        while (true) {

            try {
                //sleep(5000000);
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            update();

            if(soungIntroduction != null){
                if(!soungIntroduction.isPlaying()){
                    soungIntroduction = null;
                }
            }
        }

    }



    private void update(){
        if(!isRun){
            return;
        }

        mediaPlayer.start();

        List<Enemy> enemyList = game.enemyList;
        List<Enemy> newEnemyList = game.enemyList;

        List<EnemyProduction> enemyProductionList = game.enemyProductionList;
        List<EnemyProduction> newEnemyProductionList = game.enemyProductionList;


        for (int i = 0; i < enemyList.size() - 1; i++) {

            for (int j = i + 1; j < enemyList.size(); j++) {
                if (70 > Utils.getDistanceBetweenPoints(enemyList.get(i).getPositionX(),enemyList.get(i).getPositionY(), enemyList.get(j).getPositionX(), enemyList.get(j).getPositionY())) {
                    newEnemyList.get(i).setGrade(enemyList.get(j).grade);
                    newEnemyList.remove(enemyList.get(j));
                }
            }

        }

        for (int i = 0; i < newEnemyProductionList.size() - 1; i++) {
            newEnemyList.add(new Enemy(game.animationPlayer, res, context, player, rectangleList, newEnemyProductionList.get(i).getPositionX(), newEnemyProductionList.get(i).getPositionY()));
            newEnemyProductionList.remove(newEnemyProductionList.get(i));
        }

        double x = Math.random()*1900;
        double y = 1900;

        newEnemyProductionList.add(new EnemyProduction(game.animationEnemy, res, context, player, rectangleList, x, y));

        this.game.newEnemyList = newEnemyList;
        game.isNewListEnemy = true;

        this.game.newEnemyProductionList = newEnemyProductionList;
        game.isNewListEnemyProduction = true;

    }
    
}