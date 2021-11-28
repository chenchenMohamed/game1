package com.example.game101;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class LogoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        new WaitTimeSecondes(5000,this);
    }


    public void changeActivity(){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final ImageView image = findViewById(R.id.imageView2);
                image.setImageResource(android.R.color.transparent);
                 }
        });
        Intent i = new Intent(this,MenuActivity.class);
        startActivity(i);

    }

}

class WaitTimeSecondes extends Thread {

    private int time = 0;
    private LogoActivity logoActivity;
    public WaitTimeSecondes(int time, LogoActivity logoActivity){
        this.time = time;
        this.logoActivity = logoActivity;
        start();
    }

    @Override
    public void run() {

        super.run();

            try {
                //sleep(5000000);
                sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        logoActivity.changeActivity();
    }


}
