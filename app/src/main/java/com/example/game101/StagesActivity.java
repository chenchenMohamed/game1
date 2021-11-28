package com.example.game101;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.example.game101.SliderStages.SliderAdapter;
import com.example.game101.SliderStages.SliderItem;

import java.util.ArrayList;
import java.util.List;

public class StagesActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stages);

        viewPager2 = findViewById(R.id.viewPagerImageSlider);

        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.image2));
        sliderItems.add(new SliderItem(R.drawable.image2));
        sliderItems.add(new SliderItem(R.drawable.image2));

        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2, this));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                //page.setScaleY(0.85f + r * 015f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);

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
