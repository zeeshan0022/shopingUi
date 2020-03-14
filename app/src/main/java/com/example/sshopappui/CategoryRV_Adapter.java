package com.example.sshopappui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryRV_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context c;
    int[] images = {R.drawable.fashion,R.drawable.tech,R.drawable.home,R.drawable.sport,R.drawable.automobiles};
    String[] titles = {"Fashion","Electronics","Home","Sport","Automobiles"};
    public CategoryRV_Adapter(Context c) {
        this.c = c;
    }


    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categorie_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        ((ViewHolder)holder).categoryImage.setImageResource(images[position]);
        ((ViewHolder)holder).categoryTitle.setText(titles[position]);

        Animation catAnim = AnimationUtils.loadAnimation(c,R.anim.categories);
        ((ViewHolder)holder).categoryCard.startAnimation(catAnim);

        ((ViewHolder)holder).categoryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();

                b.putString("CategoryTitle",titles[position]);
                b.putInt("CategoryImage",images[position]);
                Intent intent = new Intent(c,items.class);
                intent.putExtras(b);
                c.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView categoryImage;
        TextView categoryTitle;
        CardView categoryCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryCard = itemView.findViewById(R.id.categoryCard);
            categoryImage = itemView.findViewById(R.id.categoryImage);
            categoryTitle = itemView.findViewById(R.id.categoryTitle);
        }
    }
}
