package com.example.sshopappui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class items extends AppCompatActivity {
    TextView title;
    ImageButton back;
    ImageView backImage;
    RecyclerView items;
    ArrayList<item_class> ListItems;
    EditText search_filter;
    Animation seachAnim,itemsTopLayoutAnim;
    RelativeLayout itemsTopLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);


        // controls initialization
        title = findViewById(R.id.categoryTitle2);
        back = findViewById(R.id.back_b);
        backImage = findViewById(R.id.back_image);
        search_filter = findViewById(R.id.search_filter);
        itemsTopLayout = findViewById(R.id.itemsTopLayout);

        // Load Animations
        itemsTopLayoutAnim = AnimationUtils.loadAnimation(this,R.anim.top_views);

        // Set animations
        itemsTopLayout.startAnimation(itemsTopLayoutAnim);

        Bundle b = getIntent().getExtras();

        getSupportActionBar().hide();

        title.setText(b.getString("CategoryTitle"));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        backImage.setImageResource(b.getInt("CategoryImage"));


        fill();

        items = findViewById(R.id.itemsRV);
        final ItemRV_Adapter adapter = new ItemRV_Adapter(this,ListItems,this);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL, false);

        items.setAdapter(adapter);
        items.setLayoutManager(layoutManager);

        search_filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(adapter != null)
                {
                    adapter.getFilter().filter(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });





    }

    public void fill()
    {
        ListItems = new ArrayList<>();
        ListItems.add(new item_class(R.drawable.shoes_nike,"Nike Air Max 270 React ENG","196"));
        ListItems.add(new item_class(R.drawable.item2,"adidas Trefoil Hoodie","22"));
        ListItems.add(new item_class(R.drawable.item3,"Women's Textured Gray Suit Pant","65"));
        ListItems.add(new item_class(R.drawable.item4,"leather jacket for men - brown","88"));
        ListItems.add(new item_class(R.drawable.item5,"Women's Hoodies - Printed Sweatshirts ","19"));
        ListItems.add(new item_class(R.drawable.item6,"Men's casual jacket blue&gray","30"));
        ListItems.add(new item_class(R.drawable.item7,"Solid hoodie female sweatshirt","21"));
        ListItems.add(new item_class(R.drawable.item8,"Wilfred Button-Front Dress","45"));
        ListItems.add(new item_class(R.drawable.item9,"adidas Superstar Slip","25"));
        ListItems.add(new item_class(R.drawable.item10,"Blacksmith Smith Unisex Slip Resistant Dress Shoes - Black","109"));
        ListItems.add(new item_class(R.drawable.item11,"Rnu 100 men's running shoes","12"));
        ListItems.add(new item_class(R.drawable.item12,"Men's Fashion Jeans With Rips","14"));
        ListItems.add(new item_class(R.drawable.item13,"Vans Men's Old Skool Black Sneaker","79"));
        ListItems.add(new item_class(R.drawable.item14,"PINSEN Women Sandals new Female Shoes","18"));
    }


}
