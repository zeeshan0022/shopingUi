package com.example.sshopappui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import miaoyongjun.pagetransformer.MagicTransformer;
import miaoyongjun.pagetransformer.TransitionEffect;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Enter_item extends AppCompatActivity {
    ImageView itemImage,close;
    TextView itemPrice,itemTitle;
    ImageButton back,addToCart;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    Button goToCart,buyNow;
    Intent intent;
    Bundle b;
    Bundle bundle;
    ArrayList<cartClass> cartList;
    ViewPager viewPager;
    TextView[] Dots;
    LinearLayout DotsLayout;
    Animation buy,add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_item);

        itemTitle = findViewById(R.id.enterItemTitle);
        itemPrice = findViewById(R.id.enterItemPrice);
        back = findViewById(R.id.back_but2);
        addToCart = findViewById(R.id.addToCart);
        buyNow = findViewById(R.id.buy);
        viewPager = findViewById(R.id.itemImageViewPager);
        DotsLayout = findViewById(R.id.Dots);
        loadItems();

        buy = AnimationUtils.loadAnimation(this,R.anim.offers);
        add = AnimationUtils.loadAnimation(this,R.anim.left_toright);

        addToCart.startAnimation(add);
        buyNow.startAnimation(buy);

        b = getIntent().getExtras();

        int image = b.getInt("ItemImage");

        itemTitle.setText(b.getString("ItemTitle"));
        itemPrice.setText(b.getString("ItemPrice"));

        int[] images = {image,image,image,image,image};

        ItemImages_ViewPager adapter = new ItemImages_ViewPager(this,images);
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, MagicTransformer.getPageTransformer(miaoyongjun.pagetransformer.TransitionEffect.Cube));




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Add to cart button event
        bundle = new Bundle();

        addToCart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                cartClass product = new cartClass(b.getString("ItemTitle"),b.getString("ItemPrice"),b.getInt("ItemImage"));
                cartList.add(product);
                saveItems();



                builder = new AlertDialog.Builder(Enter_item.this);
                View view = getLayoutInflater().inflate(R.layout.add_to_cart_dialog,null,false);

                builder.setView(view);
                dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

                dialog.show();


                goToCart = dialog.findViewById(R.id.goToCart);
                close = dialog.findViewById(R.id.close);

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });
                intent = new Intent(Enter_item.this,Cart.class);
                goToCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(intent);
                    }
                });

            }
        });

        // buy now button click event

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Enter_item.this,orderComfirmation.class));
            }
        });

        addDotsIndicator(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addDotsIndicator(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void addDotsIndicator(int position)
    {
        Dots = new TextView[5];
        DotsLayout.removeAllViews();

        for(int i = 0; i < Dots.length ; i++)
        {
            Dots[i] = new TextView(this);
            Dots[i].setText(Html.fromHtml("&#8226;"));
            Dots[i].setTextSize(28);
            Dots[i].setTextColor(getResources().getColor(R.color.Transparent));

            DotsLayout.addView(Dots[i]);

        }

        if(Dots.length > 0)
        {
            Dots[position].setTextColor(getResources().getColor(R.color.Brown));
            Dots[position].setTextSize(30);

        }
    }
    public void saveItems()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(cartList);
        editor.putString("CartList",json);
        editor.apply();
    }
    public void loadItems()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("CartList",null);
        Type type = new TypeToken<ArrayList<cartClass>>() {}.getType();
        cartList = gson.fromJson(json,type);

        if(cartList == null)
        {
            cartList = new ArrayList<>();
        }
    }
    public enum TransitionEffect
    {
        Default,
        Alpha,
        rotate,
        Cube,
        Flip,
        Accordion,
        ZoomFade,
        Fade,
        ZoomCenter,
        ZoomStack,
        Stack,
        Depth,
        Zoom,
        InRightDown,
        InRightUp,
        SlowBackground
    }

}
