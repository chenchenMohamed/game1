package com.example.game101;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Stage1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage1);

        final ImageView image1 = findViewById(R.id.mission1);
        final ImageView image2 = findViewById(R.id.mission2);
        final ImageView image3 = findViewById(R.id.mission3);
        final ImageView image4 = findViewById(R.id.mission4);

        image1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                image1.setImageDrawable(null);
                image2.setImageDrawable(null);
                image3.setImageDrawable(null);
                image4.setImageDrawable(null);
                Intent myIntent = new Intent(v.getContext(), Mession1S1Activity.class);
                startActivity(myIntent);
            }
        });
    }


    @Override
    protected  void onResume(){
        final ConstraintLayout constraintLayout = findViewById(R.id.contenaire);
        constraintLayout.setBackgroundResource(R.drawable.image2);
        super.onResume();
    }


    @Override
    protected  void onPause(){
        final ConstraintLayout constraintLayout = findViewById(R.id.contenaire);
        constraintLayout.setBackground(null);
        super.onPause();
    }
}
