package com.example.sshopappui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

class FillCart extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Cart.Update {
    Context c;
    ArrayList<cartClass> cartList;
    Button yes,no;
    ImageView close;
    int Qt,price;
    TextView Total;
    Animation removeAnim;
    public FillCart(Context c, ArrayList<cartClass> cartList, TextView total) {
        this.c = c;
        this.cartList = cartList;
        Total = total;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_added_tocart,parent,false);

        ViewHodler hodler = new ViewHodler(view);

        return hodler;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        /*Intent intent = ((Activity) c).getIntent();

        Bundle bundle = intent.getExtras();*/
        cartClass cart = cartList.get(position);
        removeAnim = AnimationUtils.loadAnimation(c,R.anim.categories);
        ((ViewHodler) holder).layout.startAnimation(removeAnim);

        ((ViewHodler) holder).image.setImageResource(cart.getImage());
        ((ViewHodler) holder).title.setText(cart.getTitle());
        ((ViewHodler) holder).price.setText(cart.getPrice());

        // Remove item button click
        ((ViewHodler)holder).removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateTotal(Total,(ViewHodler)holder);
                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                View view = LayoutInflater.from(c).inflate(R.layout.delete_dialog,null);

                builder.setView(view);
                final AlertDialog dialog;
                dialog = builder.create();
                dialog.show();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;



                yes = dialog.findViewById(R.id.Yes);
                no = dialog.findViewById(R.id.No);
                close = dialog.findViewById(R.id.close);

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        removeAnim = AnimationUtils.loadAnimation(c,R.anim.remove);
                        ((ViewHodler) holder).layout.startAnimation(removeAnim);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                cartList.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, cartList.size());
                                dialog.dismiss();
                                Total.setText("0");
                                saveItems();
                            }

                        },500);


                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });




            }
        });
        price = Integer.parseInt(((ViewHodler)holder).price.getText().toString());
        Qt = Integer.parseInt(((ViewHodler)holder).quantity.getText().toString());
        updateTotal(Total,(ViewHodler)holder);

        ((ViewHodler)holder).dropUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price = Integer.parseInt(((ViewHodler)holder).price.getText().toString());
                Qt = Integer.parseInt(((ViewHodler)holder).quantity.getText().toString());
                Qt++;
                ((ViewHodler)holder).quantity.setText(String.valueOf(Qt));

                updateTotal(Total,(ViewHodler)holder);


            }
        });
        ((ViewHodler)holder).dropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Qt = Integer.parseInt(((ViewHodler)holder).quantity.getText().toString());

                if (Qt == 0)
                {

                }else {
                    price = Integer.parseInt(((ViewHodler)holder).price.getText().toString());
                    Qt--;
                    ((ViewHodler) holder).quantity.setText(String.valueOf(Qt));

                    updateTotal(Total,(ViewHodler)holder);
                }
            }
        });





    }

    public String Total(ViewHodler holder)
    {
        int price;
        int qt,total;
        int T =0;

        for (int i = 0 ; i < cartList.size() ; i++)
        {
            price = Integer.parseInt(holder.price.getText().toString());
            qt = Integer.parseInt(holder.quantity.getText().toString());
            total = price*qt;
            T += total;
        }
        return String.valueOf(T);
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    @Override
    public void updateTotal(TextView text ,ViewHodler hodler) {
        text.setText(Total(hodler));
    }



    public class ViewHodler extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title,price;
        LinearLayout layout;
        ImageButton removeItem,dropDown,dropUp;
        TextView quantity;

        public ViewHodler(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image1);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            layout = itemView.findViewById(R.id.item);
            removeItem = itemView.findViewById(R.id.removeItem);
            dropDown = itemView.findViewById(R.id.dropDrown);
            dropUp = itemView.findViewById(R.id.dropUp);
            quantity = itemView.findViewById(R.id.quantity);

        }
    }
    public void saveItems()
    {
        SharedPreferences sharedPreferences = c.getSharedPreferences("shared preferences",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(cartList);
        editor.putString("CartList",json);
        editor.apply();

    }


}
