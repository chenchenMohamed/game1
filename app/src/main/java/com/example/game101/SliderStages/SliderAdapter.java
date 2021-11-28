package com.example.game101.SliderStages;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.game101.R;
import com.example.game101.Mession1S1Activity;
import com.example.game101.Stage1Activity;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class SliderAdapter extends  RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private List<SliderItem> sliderItems;
    private ViewPager2 viewPager2;
    private Context context;
    public SliderAdapter(List<SliderItem> sliderItems, ViewPager2 viewPager2, Context context) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
        this.context = context;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.slide_item_container,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull final SliderViewHolder holder, int position) {
         holder.setImage(sliderItems.get(position));
         holder.imageView.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 Intent myIntent = new Intent(v.getContext(), Stage1Activity.class);
                 context.startActivity(myIntent);
              }
         });
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    class  SliderViewHolder extends RecyclerView.ViewHolder{

        private RoundedImageView imageView;

        SliderViewHolder(@NonNull View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlide);
        }

        void setImage(SliderItem sliderItem){
            imageView.setImageResource(sliderItem.getImage());
        }

    }
}
