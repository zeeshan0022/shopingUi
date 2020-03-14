package com.example.sshopappui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import androidx.viewpager.widget.PagerAdapter;

import com.airbnb.lottie.LottieAnimationView;

import org.json.JSONArray;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyViewPagerAdapter extends PagerAdapter {
    LottieAnimationView lottie;
    TextView title,message;
    Context context;
    int[] lottieArray = {R.raw.good_quality,R.raw.delivery,R.raw.payment};
    String[] titles = {"Best quality","Fast delivery","Safe payment"};
    String[] descs = {"You will get the best quality of products and services with our store ,if you didn't like the product you will easy get back your money","the most store have the delivery problem which is slow so with us You will receive your order fast with our delivery company","All your payment information are safe so pay your orders Comfortable with your credit card or your verified paypal account"};


    public MyViewPagerAdapter (Context context)
    {
        this.context = context;
    }
    @Override
    public int getCount() {
        return lottieArray.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (Object) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        lottie = view.findViewById(R.id.layout_lottie);
        title = view.findViewById(R.id.title);
        message = view.findViewById(R.id.desc);

        lottie.setAnimation(lottieArray[position]);
        title.setText(titles[position]);
        message.setText(descs[position]);

        Animation titleAnim = AnimationUtils.loadAnimation(context, R.anim.view_pager_title);
        Animation descAnim = AnimationUtils.loadAnimation(context,R.anim.view_pager_desc);

        title.startAnimation(titleAnim);
        message.startAnimation(descAnim);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
