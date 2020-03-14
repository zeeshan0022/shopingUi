package com.example.sshopappui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class orderComfirmation extends AppCompatActivity {

    Button confirm;
    ImageButton back;
    TextView fullname,coutry,address,city,zipcode,state;
    EditText fullnameEdit,coutryEdit,addressEdit,cityEdit,zipcodeEdit,stateEdit;
    Animation textsAnim,EditsAnim,creditCardAnim;
    CardView creditCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_comfirmation);

        confirm = findViewById(R.id.confirmOrder);
        back = findViewById(R.id.back_b);
        fullname = findViewById(R.id.fullName);
        coutry = findViewById(R.id.country);
        address = findViewById(R.id.address);
        city = findViewById(R.id.city);
        zipcode = findViewById(R.id.zipCode);
        state = findViewById(R.id.state);
        creditCard = findViewById(R.id.creditCard);

        fullnameEdit = findViewById(R.id.fullNameEdit);
        coutryEdit = findViewById(R.id.countryEdit);
        addressEdit = findViewById(R.id.addressEdit);
        cityEdit = findViewById(R.id.cityEdit);
        zipcodeEdit = findViewById(R.id.zipCodeEdit);
        stateEdit = findViewById(R.id.stateEdit);

        textsAnim = AnimationUtils.loadAnimation(this,R.anim.orderconfirm_text);
        EditsAnim = AnimationUtils.loadAnimation(this,R.anim.ordercomfirm_edittexts);
        creditCardAnim = AnimationUtils.loadAnimation(this,R.anim.creditcard);

        fullname.startAnimation(textsAnim);
        address.startAnimation(textsAnim);
        coutry.startAnimation(textsAnim);
        city.startAnimation(textsAnim);
        state.startAnimation(textsAnim);
        zipcode.startAnimation(textsAnim);

        fullnameEdit.startAnimation(EditsAnim);
        addressEdit.startAnimation(EditsAnim);
        coutryEdit.startAnimation(EditsAnim);
        cityEdit.startAnimation(EditsAnim);
        stateEdit.startAnimation(EditsAnim);
        zipcodeEdit.startAnimation(EditsAnim);
        creditCard.startAnimation(creditCardAnim);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(orderComfirmation.this,Finish.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });









    }
}
