package com.example.sshopappui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    RecyclerView categoryRV;
    Activity activity;
    CategoryRV_Adapter adapter;
    CardView offer1,offer2,offer3,offer4;
    Animation o1Anim;
    Button offer1But,offer2But,offer3But;

    BottomNavigationView bn;
    Animation bottomViewAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bn = findViewById(R.id.bottomNavigation);
        bottomViewAnim = AnimationUtils.loadAnimation(this,R.anim.splash_info);
        bn.startAnimation(bottomViewAnim);


        categoryRV = (RecyclerView)findViewById(R.id.categoryRV);
        offer1 = findViewById(R.id.offerCard);
        offer2 = findViewById(R.id.offerCard2);
        offer3 = findViewById(R.id.offerCard3);
        offer4 = findViewById(R.id.offerCard4);
        offer1But = findViewById(R.id.offer1But);
        offer2But = findViewById(R.id.offer2But);
        offer3But = findViewById(R.id.offer3But);

        // offers CardView Animations
        setAnim(offer1,R.anim.offers);
        setAnim(offer2,R.anim.offers2);
        setAnim(offer3,R.anim.offers3);
        setAnim(offer4,R.anim.offers4);

        adapter = new CategoryRV_Adapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);

        categoryRV.setAdapter(adapter);
        categoryRV.setLayoutManager(layoutManager);

        offer1But.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,emptyOffers.class));
            }
        });
        offer2But.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,emptyOffers.class));
            }
        });
        offer3But.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,emptyOffers.class));
            }
        });







        bn.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.home :
                        break;

                    case R.id.cart :
                        startActivity(new Intent(Home.this,Cart.class));
                        break;
                    case  R.id.menu :
                        startActivity(new Intent(Home.this,menu.class));



                }
                return true;
            }
        });

    }
    public void setAnim(CardView card, int anim)
    {
        o1Anim = AnimationUtils.loadAnimation(this,anim);
        card.startAnimation(o1Anim);

    }

}
