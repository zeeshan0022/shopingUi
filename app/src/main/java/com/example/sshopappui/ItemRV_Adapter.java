package com.example.sshopappui;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ItemRV_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    Context context;
    ArrayList<item_class> items;
    ArrayList<item_class> items2;
    int i = 1;
    Activity activity;
    public ItemRV_Adapter(Context context,ArrayList<item_class> items,Activity activity) {
        this.context = context;
        this.items2 = items;
        this.items = items;
        this.activity = activity;
    }

    @NonNull
    @Override

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        viewHolder holder = new viewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        if(i == 1)
        {
            ((viewHolder)holder).itemCard.setCardBackgroundColor(context.getResources().getColor(R.color.Light_blue));

        }else if(i == 3)
        {
            i=0;
        }
        Glide.with(context)
                .asBitmap()
                .load(items.get(position).getItemImage())
                .into(((viewHolder)holder).itemImage);
        ((viewHolder)holder).itemTitle.setText(items.get(position).getItemTitle());
        ((viewHolder)holder).price.setText(items.get(position).getPrice());
        i++;

        Animation itemAnim = AnimationUtils.loadAnimation(context,R.anim.categories);
        ((viewHolder)holder).itemCard.setAnimation(itemAnim);
        ((viewHolder)holder).itemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Enter_item.class);
                Bundle b = new Bundle();
                b.putString("ItemTitle",items.get(position).getItemTitle());
                b.putString("ItemPrice",items.get(position).getPrice());
                b.putInt("ItemImage",items.get(position).getItemImage());

                intent.putExtras(b);

                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View,String>(((viewHolder)holder).itemTitle,"ItemTitle");
                pairs[1] = new Pair<View,String>(((viewHolder)holder).price,"ItemPrice");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity,pairs);



                context.startActivity(intent,options.toBundle());

            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();

                if (charString.isEmpty()){
                    items = items2;
                }else{

                    ArrayList<item_class> filterList = new ArrayList<>();

                    for (item_class data : items2){

                        if (data.getItemTitle().toLowerCase().contains(charString)){
                            filterList.add(data);
                        }
                    }

                    items = filterList;

                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = items;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                items = (ArrayList<item_class>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class viewHolder  extends RecyclerView.ViewHolder
    {
        ImageView itemImage;
        TextView itemTitle,price;
        CardView itemCard;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            price = itemView.findViewById(R.id.itemPrice);
            itemCard = itemView.findViewById(R.id.itemCard);
        }
    }
}
