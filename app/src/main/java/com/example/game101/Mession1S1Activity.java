package com.example.game101;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.game101.Stage1.Game;

public class Mession1S1Activity extends AppCompatActivity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity.java", "onCreate()");
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        game = new Game(this, point.x, point.y,this);
        setContentView(game);
    }

    @Override
    protected  void onStart(){
        Log.d("MainActivity.java", "onStart()");
         super.onStart();
    }

    @Override
    protected  void onResume(){
        Log.d("MainActivity.java", "onResume()");
        super.onResume();
    }

    public void onRestartActivity(){

        Intent myIntent = new Intent(this, Mession1S1Activity.class);
        startActivity(myIntent);


    }

    @Override
    protected  void onPause(){
        Log.d("MainActivity.java", "onPause()");
        game.pause();
        super.onPause();
    }

    @Override
    protected  void onStop(){
        Log.d("MainActivity.java", "onStop()");
        super.onStop();
    }

    @Override
    protected  void onDestroy(){
//        finish();
        super.onDestroy();
  //      Runtime.getRuntime().gc();
  //      System.gc();

        Log.d("MainActivity.java", "onDestroy()");

        //System.exit(0);
    }

    public static void restartActivity(Activity activity) {
        //System.gc();

        if (Build.VERSION.SDK_INT >= 11) {
            activity.recreate();
        } else {
            activity.finish();
            activity.startActivity(activity.getIntent());
        }
    }
    
}
