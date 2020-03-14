package com.example.sshopappui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class Finish extends AppCompatActivity {
    Button backHome;
    TextView title,desc;
    Animation t,d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        getSupportActionBar().hide();

        backHome = findViewById(R.id.backToHome);
        title = findViewById(R.id.titleFinish);
        desc = findViewById(R.id.descFinish);

        t = AnimationUtils.loadAnimation(this,R.anim.view_pager_title);
        d = AnimationUtils.loadAnimation(this,R.anim.view_pager_desc);

        title.setAnimation(t);
        desc.setAnimation(d);

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Finish.this,Home.class));
            }
        });


    }
}
