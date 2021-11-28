package com.example.game101;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final Button button = findViewById(R.id.buttonPlay);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Intent myIntent = new Intent(v.getContext(), Stage1Activity.class);
                Intent myIntent = new Intent(v.getContext(), StagesActivity.class);
                startActivity(myIntent);
            }
        });

        final Button buttonExit = findViewById(R.id.buttonExit);
        buttonExit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.exit(1);
            }
        });
    }


    @Override
    protected  void onResume(){
        final ConstraintLayout constraintLayout = findViewById(R.id.contenaire);
        constraintLayout.setBackgroundResource(R.drawable.imagegame);
        super.onResume();
    }


    @Override
    protected  void onPause(){
        final ConstraintLayout constraintLayout = findViewById(R.id.contenaire);
        constraintLayout.setBackground(null);
        super.onPause();
    }



}
