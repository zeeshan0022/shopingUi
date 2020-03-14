package com.example.sshopappui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class SplashScreen extends AppCompatActivity {
    Animation infoLayoutAnim;
    LinearLayout infoLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, viewPager.class));
                finish();

            }
        },6000);

        infoLayout = findViewById(R.id.infoLayout);
        infoLayoutAnim = AnimationUtils.loadAnimation(this,R.anim.splash_info);
        infoLayout.startAnimation(infoLayoutAnim);
    }
}
