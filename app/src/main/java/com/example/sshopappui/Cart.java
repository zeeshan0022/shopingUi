package com.example.sshopappui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Cart extends AppCompatActivity {

    RecyclerView ItemsAddedToCart;
    ArrayList<cartClass> cartList;
    FillCart fillCart;
    TextView Total;
    Button buy;
    ImageButton back;
    FloatingActionButton home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        ItemsAddedToCart = findViewById(R.id.itemsAddRV);
        Total = findViewById(R.id.TotalPrice);
        buy = findViewById(R.id.buy_);
        back = findViewById(R.id.back_b);
        home = findViewById(R.id.floatingHome);


        loadItems();

        fillCart = new FillCart(this,cartList,Total);
        ItemsAddedToCart.setAdapter(fillCart);
        ItemsAddedToCart.setLayoutManager(new LinearLayoutManager(this));





        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cartList.isEmpty() == false & Integer.parseInt(Total.getText().toString()) != 0)
                {
                    startActivity(new Intent(Cart.this,orderComfirmation.class));
                }else if(cartList.isEmpty() == true)
                    Toast.makeText(Cart.this,"there is no product",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Cart.this,"Quantity = 0 : choose a quantity for you item",Toast.LENGTH_LONG).show();

            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cart.this,Home.class));
            }
        });


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
    public interface Update
    {
        void updateTotal(TextView textView, FillCart.ViewHodler hodler);
    }


}
