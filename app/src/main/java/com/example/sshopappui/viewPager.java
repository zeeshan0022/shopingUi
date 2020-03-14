package com.example.sshopappui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import miaoyongjun.pagetransformer.MagicTransformer;
import miaoyongjun.pagetransformer.TransitionEffect;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class viewPager extends AppCompatActivity {
    ViewPager viewPager;
    TextView Dots[];
    LinearLayout DotsLayout;
    ImageButton left,right;
    Button finish;
    Animation finishAnim;
    int CurrentPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        DotsLayout = findViewById(R.id.Dots);
        left = findViewById(R.id.back_but);
        right = findViewById(R.id.next_but);
        finish = findViewById(R.id.finish_but);




        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(this);

        viewPager = findViewById(R.id.viewPager);

        viewPager.setAdapter(myViewPagerAdapter);






        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(CurrentPage - 1 );
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(CurrentPage + 1 );
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
                CurrentPage = position;
                if(position == 0)
                {
                    right.setEnabled(true);
                    right.setVisibility(View.VISIBLE);
                    left.setVisibility(View.INVISIBLE);
                    finish.setVisibility(View.INVISIBLE);
                    right.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewPager.setCurrentItem(CurrentPage + 1);
                        }
                    });
                }
                else if(position == Dots.length-1)
                {
                    right.setEnabled(false);
                    right.setVisibility(View.INVISIBLE);
                    left.setVisibility(View.INVISIBLE);
                    finish.setVisibility(View.VISIBLE);

                    finishAnim = AnimationUtils.loadAnimation(viewPager.this,R.anim.splash_info);
                    finish.startAnimation(finishAnim);
                    finish.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(com.example.sshopappui.viewPager.this,Home.class));
                        }
                    });
                }
                else
                {
                    right.setEnabled(true);
                    right.setVisibility(View.VISIBLE);
                    left.setVisibility(View.VISIBLE);
                    finish.setVisibility(View.INVISIBLE);
                    right.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewPager.setCurrentItem(CurrentPage + 1);
                        }
                    });

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }



    public void addDotsIndicator(int position)
    {
        Dots = new TextView[3];
        DotsLayout.removeAllViews();

        for(int i = 0; i < Dots.length ; i++)
        {
            Dots[i] = new TextView(this);
            Dots[i].setText(Html.fromHtml("&#8226;"));
            Dots[i].setTextSize(25);
            Dots[i].setTextColor(getResources().getColor(R.color.Transparent));

            DotsLayout.addView(Dots[i]);

        }

        if(Dots.length > 0)
        {
            Dots[position].setTextColor(getResources().getColor(R.color.Brown));
            Dots[position].setTextSize(30);

        }
    }

}
