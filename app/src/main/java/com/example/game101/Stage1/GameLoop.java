package com.example.game101.Stage1;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import com.example.game101.Stage1.Game;

public class GameLoop extends Thread{

    private Game game;
    private boolean isRunning = false;
    private SurfaceHolder surfaceHolder;

    public static final double MAX_UPS = 30.0;
    private static final double UPS_PERIOD = 1E+3/MAX_UPS;
    private double averageUPS;
    private double averageFPS;


    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
        this.game = game;
        this.surfaceHolder = surfaceHolder;
    }

    public double getAverageUPS() {
        return averageUPS;
    }

    public double getAverageFPS() {
        return averageFPS;
    }

    public void startLoop() {
        Log.d("GameLoop.java","startLoop()");
        isRunning = true;
        start();
    }



    @Override
    public void run(){
        Log.d("GameLoop.java","run()");

        super.run();

        int updateCount = 0;
        int frameCount = 0;

        long startTime = System.currentTimeMillis();
        long elapsedTime = 0;
        long sleepTime;

        Canvas canvas = null;
        while(isRunning){
            try{
                    canvas = surfaceHolder.lockCanvas();
                    if (canvas == null) continue;
                    synchronized (surfaceHolder) {
                        game.update();
                        updateCount++;
                        game.draw(canvas);
                        long diff = System.currentTimeMillis() - startTime;
                        if(diff < 33){
                            sleep(33 - diff);
                        }else{
                            averageFPS++;
                        }
                        startTime = System.currentTimeMillis();
                      }

            }catch (IllegalArgumentException | InterruptedException e){
                e.printStackTrace();
            }finally {

                if(canvas != null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        frameCount++;
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }

            /*
            elapsedTime = System.currentTimeMillis() - startTime;
            sleepTime = (long) (updateCount * UPS_PERIOD - elapsedTime);
                    if(sleepTime > 0){
                        try{
                            sleep(sleepTime);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
            //skip frames to keep up with target UPS
            while(sleepTime < 0 && updateCount < MAX_UPS-1){
                game.update();
                updateCount++;
                elapsedTime = System.currentTimeMillis() - startTime;
                sleepTime = (long) (updateCount*UPS_PERIOD - elapsedTime);
            }


            elapsedTime = System.currentTimeMillis() - startTime;
            if(elapsedTime >= 1000){
                averageUPS = updateCount / (1E-3 * elapsedTime);
                averageFPS = frameCount / (1E-3 * elapsedTime);
                updateCount = 0;
                frameCount = 0;
                startTime = System.currentTimeMillis();
            }
*/
        }

    }

    public void stopLoop() {
        Log.d("GameLoop.java","stopLoop");
        isRunning = false;

        try{
            join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
